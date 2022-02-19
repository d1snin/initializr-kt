package uno.d1s.initializr.domain.api

import uno.d1s.initializr.domain.internal.RawView
import uno.d1s.initializr.util.fromRawString

public enum class ProjectLanguage(public override val raw: String) : RawView<ProjectLanguage> {
    JAVA("java"), KOTLIN("kotlin"), GROOVY("groovy");

    override fun fromRaw(raw: String): ProjectLanguage = fromRawString(raw)
}