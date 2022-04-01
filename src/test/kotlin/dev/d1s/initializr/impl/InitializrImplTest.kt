package dev.d1s.initializr.impl

import dev.d1s.initializr.dsl.ProjectConfigurationDsl
import dev.d1s.initializr.dsl.converter.impl.ProjectConfigurationDslConverter
import dev.d1s.initializr.factory.initializrService
import dev.d1s.initializr.factory.projectConfigurationDslConverter
import dev.d1s.initializr.service.InitializrService
import dev.d1s.initializr.testUtil.emptyDsl
import dev.d1s.initializr.testUtil.validConfiguration
import dev.d1s.initializr.testUtil.validProject
import dev.d1s.teabag.testing.assertDoesNotThrowBlocking
import io.mockk.*
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.BeforeTest
import kotlin.test.Test

internal class InitializrImplTest {

    private val dslConverter = mockk<ProjectConfigurationDslConverter>()

    private val initializrService = mockk<InitializrService>()

    private val emptyDsl = emptyDsl()

    private val validConfiguration = validConfiguration()

    private val validProject = validProject()

    private val emptyConfigurationFun: ProjectConfigurationDsl.() -> Unit = {}

    @BeforeTest
    fun setup() {
        every {
            dslConverter.toApi(emptyDsl)
        } returns validConfiguration

        justRun {
            dslConverter.defaults.set(emptyDsl)
        }

        coEvery {
            initializrService.createProject(
                validConfiguration
            )
        } returns validProject
    }

    @Test
    fun `should create project`() {
        this.newInstanceWithMockedDeps {
            val project = assertDoesNotThrowBlocking {
                it.createProject(emptyConfigurationFun)
            }

            verify {
                dslConverter.toApi(emptyDsl)
            }

            coVerify {
                initializrService.createProject(validConfiguration)
            }

            expectThat(project) isEqualTo validProject
        }
    }

    @Test
    fun `should set default project configuration`() {
        this.newInstanceWithMockedDeps {
            it.setDefaultProjectConfiguration(emptyConfigurationFun)

            verify {
                dslConverter.defaults.set(emptyDsl)
            }
        }
    }

    private fun newInstanceWithMockedDeps(block: (InitializrImpl) -> Unit) {
        mockkStatic("dev.d1s.initializr.factory.InternalComponentsKt") {
            every {
                projectConfigurationDslConverter()
            } returns dslConverter

            every {
                initializrService()
            } returns initializrService

            block(InitializrImpl())
        }
    }
}