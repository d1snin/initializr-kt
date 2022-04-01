package dev.d1s.initializr.dsl.converter

import dev.d1s.initializr.api.*
import dev.d1s.initializr.dsl.converter.impl.ProjectConfigurationDslConverter
import dev.d1s.initializr.testUtil.emptyDsl
import dev.d1s.initializr.testUtil.validDsl
import dev.d1s.teabag.testing.constant.VALID_STUB
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

internal class ProjectConfigurationDslConverterTest {

    private val converter = ProjectConfigurationDslConverter()

    private val emptyDsl = emptyDsl()

    private val validDsl = validDsl()

    @Test
    fun `should convert project dsl to project configuration with valid provided dsl`() {
        this.expectValidProperties(
            assertDoesNotThrow {
                converter.toApi(validDsl)
            }
        )
    }

    @Test
    fun `should convert project dsl to project configuration with valid default dsl`() {
        this.configureDefaults()

        this.expectValidProperties(
            assertDoesNotThrow {
                converter.toApi(emptyDsl)
            }
        )
    }

    @Test
    fun `should throw IllegalStateException if the property is null in the provided dsl`() {
        assertThrows<IllegalStateException> {
            converter.toApi(emptyDsl)
        }
    }

    private fun expectValidProperties(config: ProjectConfiguration) {
        config.run {
            expectThat(type) isEqualTo
                    ProjectType.GRADLE

            expectThat(language) isEqualTo
                    ProjectLanguage.KOTLIN

            expectThat(springBootVersion) isEqualTo
                    VALID_STUB

            expectThat(group) isEqualTo
                    VALID_STUB

            expectThat(artifact) isEqualTo
                    VALID_STUB

            expectThat(name) isEqualTo
                    VALID_STUB

            expectThat(packageName) isEqualTo
                    VALID_STUB

            expectThat(description) isEqualTo
                    VALID_STUB

            expectThat(packaging) isEqualTo
                    ProjectPackaging.JAR

            expectThat(javaVersion) isEqualTo
                    JavaVersion.VERSION_11
        }
    }

    private fun configureDefaults() {
        converter.defaults.set(validDsl)
    }
}