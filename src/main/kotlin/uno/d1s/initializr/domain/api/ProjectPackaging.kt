package uno.d1s.initializr.domain.api

import uno.d1s.initializr.domain.internal.RawView
import uno.d1s.initializr.util.fromRawString

public enum class ProjectPackaging(public override val raw: String) : RawView<ProjectPackaging> {
    JAR("jar"), WAR("war");

    override fun fromRaw(raw: String): ProjectPackaging = fromRawString(raw)
}