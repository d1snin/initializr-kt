package dev.d1s.initializr.service

import com.fasterxml.jackson.databind.ObjectMapper
import dev.d1s.initializr.constant.*
import dev.d1s.initializr.dto.ErrorDto
import dev.d1s.initializr.exception.ProjectCreationFailedException
import dev.d1s.initializr.factory.httpClient
import dev.d1s.initializr.factory.objectMapper
import dev.d1s.initializr.service.impl.InitializrServiceImpl
import dev.d1s.initializr.testUtil.validConfiguration
import dev.d1s.initializr.testUtil.validProject
import dev.d1s.teabag.testing.assertDoesNotThrowBlocking
import dev.d1s.teabag.testing.assertThrowsBlocking
import dev.d1s.teabag.testing.constant.VALID_STUB
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.time.Instant
import kotlin.test.BeforeTest
import kotlin.test.Test

internal class InitializrServiceImplTest {

    private val objectMapper = mockk<ObjectMapper>()

    private val validConfiguration = validConfiguration()

    private val validProject = validProject()

    private var httpEngine = MockEngine {
        respond(byteArrayOf())
    }

    private val httpClient = HttpClient(
        httpEngine
    )

    private val badHttpClient = HttpClient(
        MockEngine {
            respond(VALID_STUB, HttpStatusCode.BadRequest)
        }
    ) {
        expectSuccess = false
    }

    @BeforeTest
    fun setup() {
        every {
            objectMapper.readValue(
                VALID_STUB,
                ErrorDto::class.java
            )
        } returns ErrorDto(
            Instant.EPOCH,
            0,
            VALID_STUB,
            VALID_STUB,
            VALID_STUB
        )
    }

    @Test
    fun `should create project`() {
        this.newInstanceWithMockedDeps {
            val project = assertDoesNotThrowBlocking {
                it.createProject(validConfiguration)
            }

            val lastRequest = httpEngine.requestHistory.last()

            validConfiguration.run {
                expectThat(lastRequest.url.parameters) isEqualTo Parameters.build {
                    append(PARAMETER_PROJECT_TYPE, type!!.raw)
                    append(PARAMETER_LANGUAGE, language!!.raw)
                    append(PARAMETER_SPRING_BOOT_VERSION, springBootVersion!!)
                    append(PARAMETER_GROUP, group!!)
                    append(PARAMETER_ARTIFACT, artifact!!)
                    append(PARAMETER_PACKAGE_NAME, packageName!!)
                    append(PARAMETER_NAME, name!!)
                    append(PARAMETER_DESCRIPTION, description!!)
                    append(PARAMETER_PACKAGING, packaging!!.raw)
                    append(PARAMETER_JAVA_VERSION, javaVersion!!.raw)
                    append(PARAMETER_DEPENDENCIES, dependencies.joinToString(",") {
                        it.raw
                    })
                }
            }

            expectThat(project) isEqualTo
                    validProject
        }
    }

    @Test
    fun `should throw ProjectCreationFailedException if the response code is non 200`() {
        this.newInstanceWithMockedDeps(true) {
            val exception = assertThrowsBlocking<ProjectCreationFailedException> {
                it.createProject(validConfiguration)
            }

            expectThat(exception.message) isEqualTo
                    "Project creation failed: $VALID_STUB"
        }
    }

    private fun newInstanceWithMockedDeps(useBadClient: Boolean = false, block: (InitializrServiceImpl) -> Unit) {
        mockkStatic("dev.d1s.initializr.factory.InternalComponentsKt") {
            every {
                httpClient()
            } returns if (useBadClient) {
                badHttpClient
            } else {
                httpClient
            }

            every {
                objectMapper()
            } returns objectMapper

            block(InitializrServiceImpl())
        }
    }
}