package dev.d1s.initializr.factory

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import dev.d1s.initializr.domain.api.ProjectConfiguration
import dev.d1s.initializr.dsl.ProjectConfigurationDsl
import dev.d1s.initializr.dsl.converter.DslConverter
import dev.d1s.initializr.dsl.converter.impl.ProjectConfigurationDslConverter
import dev.d1s.initializr.service.InitializrService
import dev.d1s.initializr.service.impl.InitializrServiceImpl
import io.ktor.client.*
import io.ktor.client.engine.cio.*

internal fun projectConfigurationDslConverter(): DslConverter<ProjectConfigurationDsl, ProjectConfiguration> =
    ProjectConfigurationDslConverter()

internal fun initializrService(): InitializrService = InitializrServiceImpl()

internal fun httpClient() = HttpClient(CIO) {
    expectSuccess = false
}

internal fun objectMapper() = ObjectMapper().apply {
    registerKotlinModule()
}