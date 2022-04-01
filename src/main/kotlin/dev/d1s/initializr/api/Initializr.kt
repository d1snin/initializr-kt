package dev.d1s.initializr.api

import dev.d1s.initializr.dsl.ProjectConfigurationDsl
import dev.d1s.initializr.dsl.marker.InitializrDslMarker

public interface Initializr {

    @InitializrDslMarker
    public suspend fun createProject(
        configuration: ProjectConfigurationDsl.() -> Unit = {}
    ): Project

    @InitializrDslMarker
    public fun setDefaultProjectConfiguration(
        configuration: ProjectConfigurationDsl.() -> Unit
    )
}