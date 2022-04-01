package dev.d1s.initializr.testUtil

import dev.d1s.initializr.api.*
import dev.d1s.initializr.dsl.ProjectConfigurationDsl
import dev.d1s.teabag.testing.constant.VALID_STUB
import java.util.*

internal fun emptyDsl() = ProjectConfigurationDsl()

internal fun validDsl() = ProjectConfigurationDsl().apply {
    type = ProjectType.GRADLE
    language = ProjectLanguage.KOTLIN
    springBootVersion = VALID_STUB
    group = VALID_STUB
    artifact = VALID_STUB
    name = VALID_STUB
    packageName = VALID_STUB
    description = VALID_STUB
    packaging = ProjectPackaging.JAR
    javaVersion = JavaVersion.VERSION_11
    dependencies.add(ProjectDependency.APACHE_CAMEL)
}

internal fun validConfiguration() = ProjectConfiguration(
    ProjectType.GRADLE,
    ProjectLanguage.KOTLIN,
    VALID_STUB,
    VALID_STUB,
    VALID_STUB,
    VALID_STUB,
    VALID_STUB,
    VALID_STUB,
    ProjectPackaging.JAR,
    JavaVersion.VERSION_11,
    EnumSet.of(ProjectDependency.APACHE_CAMEL)
)

internal fun validProject() = Project(
    validConfiguration(),
    byteArrayOf()
)

