package uno.d1s.initializr.domain.api

import uno.d1s.initializr.util.fromRawString

public enum class ProjectType(public override val raw: String) : RawView {
    GRADLE("gradle-project"), MAVEN("maven-project");

    public companion object {
        public fun fromRaw(raw: String): ProjectType = fromRawString(raw)
    }
}