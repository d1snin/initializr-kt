package uno.d1s.initializr.domain.api

import uno.d1s.initializr.domain.internal.RawView
import uno.d1s.initializr.util.fromRawString

public enum class JavaVersion(public override val raw: String) : RawView<JavaVersion> {
    VERSION_17("17"), VERSION_11("11"), VERSION_8("8");

    override fun fromRaw(raw: String): JavaVersion = fromRawString(raw)
}