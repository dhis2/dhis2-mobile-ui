package org.hisp.dhis.mobile.ui.designsystem.component.table.model

/**
 * Sealed class representing the result of a validation operation.
 */
sealed class ValidationResult {
    /**
     * Represents a successful validation result.
     * @property value The value that was successfully validated.
     */
    data class Success(
        val value: String?,
    ) : ValidationResult()

    /**
     * Represents a failed validation result.
     * @property error The error message describing why the validation failed.
     */
    data class Failure(
        val error: String,
    ) : ValidationResult()
}
