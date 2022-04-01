package dev.d1s.initializr.api

import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

internal class JavaVersionTest {

    @Test
    fun `should return value raw values`() {
        expectThat(JavaVersion.VERSION_17.raw) isEqualTo
                "17"

        expectThat(JavaVersion.VERSION_11.raw) isEqualTo
                "11"

        expectThat(JavaVersion.VERSION_8.raw) isEqualTo
                "8"
    }
}