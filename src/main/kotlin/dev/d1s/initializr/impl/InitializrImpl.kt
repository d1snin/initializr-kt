package dev.d1s.initializr.impl

import dev.d1s.initializr.api.Initializr
import dev.d1s.initializr.api.Project
import dev.d1s.initializr.dsl.ProjectConfigurationDsl
import dev.d1s.initializr.factory.initializrService
import dev.d1s.initializr.factory.projectConfigurationDslConverter

internal class InitializrImpl : Initializr {

    private val dslConverter = projectConfigurationDslConverter()

    private val initializrService = initializrService()

    override suspend fun createProject(configuration: ProjectConfigurationDsl.() -> Unit): Project =
        initializrService.createProject(
            dslConverter.toApi(ProjectConfigurationDsl().apply(configuration))
        )

    override fun setDefaultProjectConfiguration(configuration: ProjectConfigurationDsl.() -> Unit) {
        dslConverter.defaults.set(ProjectConfigurationDsl().apply(configuration))
    }
}