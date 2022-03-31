package dev.d1s.initializr.api

import java.util.*

public data class ProjectConfiguration(
    val type: ProjectType?,
    val language: ProjectLanguage?,
    val springBootVersion: String?,
    val group: String?,
    val artifact: String?,
    val name: String?,
    val packageName: String?,
    val description: String?,
    val packaging: ProjectPackaging?,
    val javaVersion: JavaVersion?,
    val dependencies: EnumSet<ProjectDependency>
)