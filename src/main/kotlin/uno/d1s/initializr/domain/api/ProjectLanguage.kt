package uno.d1s.initializr.domain.api

import uno.d1s.initializr.util.fromRawString

public enum class ProjectLanguage(public override val raw: String) : RawView {
    JAVA("java"), KOTLIN("kotlin"), GROOVY("groovy");

    public companion object {
        public fun fromRaw(raw: String): ProjectLanguage = fromRawString(raw)
    }
}