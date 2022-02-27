package uno.d1s.initializr.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uno.d1s.initializr.constant.*
import uno.d1s.initializr.domain.api.Project
import uno.d1s.initializr.domain.api.ProjectConfiguration
import uno.d1s.initializr.dto.ErrorDto
import uno.d1s.initializr.exception.ProjectCreationFailedException
import uno.d1s.initializr.service.InitializrService

internal object InitializrServiceImpl : InitializrService {

    private val httpClient: HttpClient = HttpClient(CIO) {
        expectSuccess = false
    }

    private val objectMapper = ObjectMapper().apply {
        registerKotlinModule()
    }

    override suspend fun newProject(configuration: ProjectConfiguration): Project = withContext(Dispatchers.IO) {
        Project(configuration,
            configuration.run {
                val response = httpClient.get(BASE_URL) {
                    parameter(PARAMETER_PROJECT_TYPE, type!!.raw)
                    parameter(PARAMETER_LANGUAGE, language!!.raw)
                    parameter(PARAMETER_SPRING_BOOT_VERSION, springBootVersion!!)
                    parameter(PARAMETER_GROUP, group!!)
                    parameter(PARAMETER_ARTIFACT, artifact!!)
                    parameter(PARAMETER_PACKAGE_NAME, packageName!!)
                    parameter(PARAMETER_NAME, name!!)
                    parameter(PARAMETER_DESCRIPTION, description!!)
                    parameter(PARAMETER_PACKAGING, packaging!!.raw)
                    parameter(PARAMETER_JAVA_VERSION, javaVersion!!.raw)

                    if (dependencies.isNotEmpty()) {
                        parameter(PARAMETER_DEPENDENCIES, dependencies.joinToString(",") {
                            it.raw
                        })
                    }
                }

                if (response.status != HttpStatusCode.OK) {
                    throw ProjectCreationFailedException(
                        objectMapper.readValue(
                            response.bodyAsText(),
                            ErrorDto::class.java
                        ).message
                    )
                } else {
                    response.readBytes()
                }
            }
        )
    }
}