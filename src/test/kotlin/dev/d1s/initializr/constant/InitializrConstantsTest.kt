package dev.d1s.initializr.constant

import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

internal class InitializrConstantsTest {

    @Test
    fun `should return valid base url`() {
        expectThat(BASE_URL) isEqualTo
                "https://start.spring.io/starter.zip/"
    }

    @Test
    fun `should return valid parameter names`() {
        expectThat(PARAMETER_PROJECT_TYPE) isEqualTo
                "type"

        expectThat(PARAMETER_LANGUAGE) isEqualTo
                "language"

        expectThat(PARAMETER_SPRING_BOOT_VERSION) isEqualTo
                "bootVersion"

        expectThat(PARAMETER_GROUP) isEqualTo
                "groupId"

        expectThat(PARAMETER_ARTIFACT) isEqualTo
                "artifactId"

        expectThat(PARAMETER_PACKAGE_NAME) isEqualTo
                "packageName"

        expectThat(PARAMETER_NAME) isEqualTo
                "name"

        expectThat(PARAMETER_DESCRIPTION) isEqualTo
                "description"

        expectThat(PARAMETER_PACKAGING) isEqualTo
                "packaging"

        expectThat(PARAMETER_JAVA_VERSION) isEqualTo
                "javaVersion"

        expectThat(PARAMETER_DEPENDENCIES) isEqualTo
                "dependencies"
    }
}