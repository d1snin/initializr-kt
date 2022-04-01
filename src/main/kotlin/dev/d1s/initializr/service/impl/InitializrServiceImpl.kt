package dev.d1s.initializr.service.impl

import dev.d1s.initializr.constant.*
import dev.d1s.initializr.api.Project
import dev.d1s.initializr.api.ProjectConfiguration
import dev.d1s.initializr.dto.ErrorDto
import dev.d1s.initializr.exception.ProjectCreationFailedException
import dev.d1s.initializr.factory.httpClient
import dev.d1s.initializr.factory.objectMapper
import dev.d1s.initializr.service.InitializrService
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class InitializrServiceImpl : InitializrService {

    private val httpClient = httpClient()

    private val objectMapper = objectMapper()

    override suspend fun createProject(configuration: ProjectConfiguration): Project = withContext(Dispatchers.IO) {
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