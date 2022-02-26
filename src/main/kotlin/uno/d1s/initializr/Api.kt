package uno.d1s.initializr

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uno.d1s.initializr.domain.api.JavaVersion
import uno.d1s.initializr.domain.api.ProjectLanguage
import uno.d1s.initializr.domain.api.ProjectPackaging
import uno.d1s.initializr.domain.api.ProjectType
import uno.d1s.initializr.dsl.ProjectConfigurationDsl
import uno.d1s.initializr.factory.initializrService
import uno.d1s.initializr.factory.projectConfigurationDslConverter

private val dslConverter = projectConfigurationDslConverter
private val initializr = initializrService

public val ProjectConfigurationDsl.standard: () -> Unit
    get() = {
        type = ProjectType.MAVEN
        language = ProjectLanguage.JAVA
        springBootVersion = "2.6.4"
        group = "com.example"
        artifact = "demo"
        name = "demo"
        description = "Demo project for Spring Boot"
        packageName = "com.example.demo"
        packaging = ProjectPackaging.JAR
        javaVersion = JavaVersion.VERSION_11
    }

public fun defaultProjectConfiguration(configuration: ProjectConfigurationDsl.() -> Unit) {
    CoroutineScope(Dispatchers.Default).launch {
        dslConverter.defaultProjectConfiguration.set(ProjectConfigurationDsl().apply(configuration))
    }
}

public suspend fun newProject(configuration: ProjectConfigurationDsl.() -> Unit): ByteArray =
    initializr.newProject(
        dslConverter.toApi(ProjectConfigurationDsl().apply(configuration))
    )