package dev.d1s.initializr.api

import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

internal class ProjectLanguageTest {

    @Test
    fun `should return valid raw values`() {
        expectThat(ProjectLanguage.JAVA.raw) isEqualTo
                "java"

        expectThat(ProjectLanguage.KOTLIN.raw) isEqualTo
                "kotlin"

        expectThat(ProjectLanguage.GROOVY.raw) isEqualTo
                "groovy"
    }
}