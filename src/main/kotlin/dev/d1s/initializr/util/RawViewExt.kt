package dev.d1s.initializr.util

import dev.d1s.initializr.api.RawView
import dev.d1s.initializr.exception.ProjectCreationFailedException

internal inline fun <reified T> fromRawString(raw: String): T where T : Enum<T>, T : RawView =
    enumValues<T>().firstOrNull {
        it.raw == raw
    } ?: throw ProjectCreationFailedException("Failed to resolve raw value: $raw")