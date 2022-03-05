package dev.d1s.initializr.domain.api

import dev.d1s.initializr.util.fromRawString

public enum class JavaVersion(public override val raw: String) : RawView {
    VERSION_17("17"), VERSION_11("11"), VERSION_8("8");

    public companion object {
        public fun fromRaw(raw: String): JavaVersion = fromRawString(raw)
    }
}