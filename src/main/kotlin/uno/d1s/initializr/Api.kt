package uno.d1s.initializr

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uno.d1s.initializr.domain.api.*
import uno.d1s.initializr.dsl.ProjectConfigurationDsl
import uno.d1s.initializr.dsl.marker.InitializrDslMarker
import uno.d1s.initializr.factory.initializrService
import uno.d1s.initializr.factory.projectConfigurationDslConverter

private val dslConverter = projectConfigurationDslConverter
private val initializr = initializrService

public fun ProjectConfigurationDsl.demo() {
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

@InitializrDslMarker
public suspend fun defaultProjectConfiguration(configuration: ProjectConfigurationDsl.() -> Unit) {
    withContext(Dispatchers.Default) {
        dslConverter.defaults.set(ProjectConfigurationDsl().apply(configuration))
    }
}

@InitializrDslMarker
public suspend fun newProject(configuration: ProjectConfigurationDsl.() -> Unit = {}): Project =
    initializr.newProject(
        dslConverter.toApi(ProjectConfigurationDsl().apply(configuration))
    )