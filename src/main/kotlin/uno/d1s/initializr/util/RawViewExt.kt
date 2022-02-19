package uno.d1s.initializr.util

import uno.d1s.initializr.domain.internal.RawView

internal inline fun <reified T> fromRawString(raw: String): T where T : Enum<T>, T : RawView<T> =
    enumValues<T>().first {
        it.raw == raw
    }