package uno.d1s.initializr.dsl.converter

import uno.d1s.initializr.dsl.ProjectConfigurationDsl
import java.util.concurrent.atomic.AtomicReference

internal interface DslConverter<D, A> {

    var defaultProjectConfiguration: AtomicReference<ProjectConfigurationDsl?>

    fun toApi(dsl: D): A
}