package uno.d1s.initializr.dsl.converter.impl

import uno.d1s.initializr.domain.api.ProjectConfiguration
import uno.d1s.initializr.dsl.ProjectConfigurationDsl
import uno.d1s.initializr.dsl.converter.DslConverter
import java.util.concurrent.atomic.AtomicReference

internal object ProjectConfigurationDslConverter : DslConverter<ProjectConfigurationDsl, ProjectConfiguration> {

    override var defaultProjectConfiguration: AtomicReference<ProjectConfigurationDsl?> = AtomicReference()

    override fun toApi(dsl: ProjectConfigurationDsl): ProjectConfiguration = dsl.run {
        val defaults = defaultProjectConfiguration.get()

        ProjectConfiguration(
            type ?: defaults?.type ?: throw exception("type"),
            language ?: defaults?.language ?: throw exception("language"),
            springBootVersion ?: defaults?.springBootVersion ?: throw exception("springBootVersion"),
            group ?: defaults?.group ?: throw exception("group"),
            artifact ?: defaults?.artifact ?: throw exception("artifact"),
            name ?: defaults?.name ?: throw exception("name"),
            packageName ?: defaults?.packageName ?: throw exception("packageName"),
            description ?: defaults?.description ?: throw exception("description"),
            packaging ?: defaults?.packaging ?: throw exception("packaging"),
            javaVersion ?: defaults?.javaVersion ?: throw exception("javaVersion"),
            dependencies
        )
    }

    private fun exception(property: String) = IllegalStateException("$property must be specified in the dsl.")
}