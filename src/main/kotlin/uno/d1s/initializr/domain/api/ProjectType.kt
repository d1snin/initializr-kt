package uno.d1s.initializr.domain.api

import uno.d1s.initializr.domain.internal.RawView

public enum class ProjectType(public override val raw: String) : RawView<ProjectType> {
    GRADLE("gradle-project"), MAVEN("maven-project");

    public override fun fromRaw(raw: String): ProjectType = values().first {
        it.raw == raw
    }
}