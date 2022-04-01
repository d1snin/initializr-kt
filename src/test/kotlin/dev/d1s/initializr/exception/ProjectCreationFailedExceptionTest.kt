package dev.d1s.initializr.exception

import dev.d1s.teabag.testing.constant.VALID_STUB
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

internal class ProjectCreationFailedExceptionTest {

    @Test
    fun `should return valid exception message`() {
        expectThat(ProjectCreationFailedException(VALID_STUB).message) isEqualTo
                "Project creation failed: $VALID_STUB"
    }
}