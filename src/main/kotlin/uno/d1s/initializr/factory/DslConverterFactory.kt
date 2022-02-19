package uno.d1s.initializr.factory

import uno.d1s.initializr.domain.api.ProjectConfiguration
import uno.d1s.initializr.dsl.ProjectConfigurationDsl
import uno.d1s.initializr.dsl.converter.DslConverter
import uno.d1s.initializr.dsl.converter.impl.ProjectConfigurationDslConverter

internal val projectConfigurationDslConverter: DslConverter<ProjectConfigurationDsl, ProjectConfiguration> =
    ProjectConfigurationDslConverter