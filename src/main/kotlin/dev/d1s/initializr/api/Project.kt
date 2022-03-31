package dev.d1s.initializr.api

public data class Project(
    val configuration: ProjectConfiguration,
    val data: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Project

        if (configuration != other.configuration) return false
        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = configuration.hashCode()
        result = 31 * result + data.contentHashCode()
        return result
    }
}