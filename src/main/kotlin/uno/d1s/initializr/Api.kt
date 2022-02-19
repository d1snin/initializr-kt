package uno.d1s.initializr

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uno.d1s.initializr.dsl.ProjectConfigurationDsl
import uno.d1s.initializr.factory.initializrService
import uno.d1s.initializr.factory.projectConfigurationDslConverter

private val dslConverter = projectConfigurationDslConverter
private val initializr = initializrService

public fun defaultProjectConfiguration(configuration: ProjectConfigurationDsl.() -> Unit) {
    CoroutineScope(Dispatchers.Default).launch {
        dslConverter.defaultProjectConfiguration.set(ProjectConfigurationDsl().apply(configuration))
    }
}

public suspend fun newProject(configuration: ProjectConfigurationDsl.() -> Unit): ByteArray =
    initializr.newProject(
        dslConverter.toApi(ProjectConfigurationDsl().apply(configuration))
    )