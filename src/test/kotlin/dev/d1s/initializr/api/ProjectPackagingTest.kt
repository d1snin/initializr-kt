package dev.d1s.initializr.api

import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

internal class ProjectPackagingTest {

    @Test
    fun `should return valid raw values`() {
        expectThat(ProjectPackaging.JAR.raw) isEqualTo
                "jar"

        expectThat(ProjectPackaging.WAR.raw) isEqualTo
                "war"
    }
}