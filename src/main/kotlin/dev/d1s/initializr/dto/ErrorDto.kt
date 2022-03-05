package dev.d1s.initializr.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.Instant

internal data class ErrorDto(
    @JsonIgnore val timestamp: Instant?,
    @JsonIgnore val status: Int?,
    @JsonIgnore val error: String?,
    val message: String,
    @JsonIgnore val path: String?
)
