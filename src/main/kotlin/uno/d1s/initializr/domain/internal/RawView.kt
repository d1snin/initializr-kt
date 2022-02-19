package uno.d1s.initializr.domain.internal

internal interface RawView<T> {

    val raw: String

    fun fromRaw(raw: String): T
}