package uno.d1s.initializr.domain.api

import uno.d1s.initializr.util.fromRawString

public enum class ProjectPackaging(public override val raw: String) : RawView {
    JAR("jar"), WAR("war");

    public companion object {
        public fun fromRaw(raw: String): ProjectPackaging = fromRawString(raw)
    }
}