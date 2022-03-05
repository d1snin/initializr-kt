package dev.d1s.initializr.factory

import dev.d1s.initializr.domain.api.ProjectConfiguration
import dev.d1s.initializr.dsl.ProjectConfigurationDsl
import dev.d1s.initializr.dsl.converter.DslConverter
import dev.d1s.initializr.dsl.converter.impl.ProjectConfigurationDslConverter

internal val projectConfigurationDslConverter: DslConverter<ProjectConfigurationDsl, ProjectConfiguration> =
    ProjectConfigurationDslConverter