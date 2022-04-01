package dev.d1s.initializr.dsl

import strikt.api.expectThat
import strikt.assertions.isEmpty
import strikt.assertions.isNull
import kotlin.test.Test

internal class ProjectConfigurationDslTest {

    @Test
    fun `should return valid default values`() {
        val dsl = ProjectConfigurationDsl()

        dsl.run {
            expectThat(type).isNull()
            expectThat(language).isNull()
            expectThat(springBootVersion).isNull()
            expectThat(group).isNull()
            expectThat(artifact).isNull()
            expectThat(name).isNull()
            expectThat(packageName).isNull()
            expectThat(description).isNull()
            expectThat(packaging).isNull()
            expectThat(javaVersion).isNull()
            expectThat(dependencies).isEmpty()
        }
    }
}