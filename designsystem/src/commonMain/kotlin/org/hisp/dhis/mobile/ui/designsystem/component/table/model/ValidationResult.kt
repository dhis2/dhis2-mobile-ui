package org.hisp.dhis.mobile.ui.designsystem.component.table.model

sealed class ValidationResult {
    data class Success(val value: String?) : ValidationResult()
    data class Error(val message: String) : ValidationResult()
}
