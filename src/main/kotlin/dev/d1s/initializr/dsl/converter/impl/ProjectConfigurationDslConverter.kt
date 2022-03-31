package dev.d1s.initializr.dsl.converter.impl

import dev.d1s.initializr.domain.api.ProjectConfiguration
import dev.d1s.initializr.dsl.ProjectConfigurationDsl
import dev.d1s.initializr.dsl.converter.DslConverter
import java.util.concurrent.atomic.AtomicReference

internal class ProjectConfigurationDslConverter : DslConverter<ProjectConfigurationDsl, ProjectConfiguration> {

    override var defaults: AtomicReference<ProjectConfigurationDsl> = AtomicReference()

    override fun toApi(dsl: ProjectConfigurationDsl): ProjectConfiguration = dsl.run {
        val def = defaults.get()

        ProjectConfiguration(
            (type ?: def?.type) ?: throw exception("type"),
            (language ?: def?.language) ?: throw exception("language"),
            (springBootVersion) ?: def?.springBootVersion ?: throw exception("springBootVersion"),
            (group ?: def?.group) ?: throw exception("group"),
            (artifact ?: def?.artifact) ?: throw exception("artifact"),
            (name ?: def?.name) ?: throw exception("name"),
            (packageName ?: def?.packageName) ?: throw exception("packageName"),
            (description ?: def?.description) ?: throw exception("description"),
            (packaging ?: def?.packaging) ?: throw exception("packaging"),
            (javaVersion ?: def?.javaVersion) ?: throw exception("javaVersion"),
            dependencies
        )
    }

    private fun exception(property: String) = IllegalStateException("$property must be specified in the dsl.")
}