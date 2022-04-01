package dev.d1s.initializr.api

import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

internal class ProjectTypeTest {

    @Test
    fun `should return valid raw values`() {
        expectThat(ProjectType.GRADLE.raw) isEqualTo
                "gradle-project"

        expectThat(ProjectType.MAVEN.raw) isEqualTo
                "maven-project"
    }
}