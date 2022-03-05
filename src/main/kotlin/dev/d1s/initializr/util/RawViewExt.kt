package dev.d1s.initializr.util

import dev.d1s.initializr.domain.api.RawView

public class RawEnumValueNotFoundException(public val raw: String) : RuntimeException()

internal inline fun <reified T> fromRawString(raw: String): T where T : Enum<T>, T : RawView =
    enumValues<T>().firstOrNull {
        it.raw == raw
    } ?: throw RawEnumValueNotFoundException(raw)