package org.hisp.dhis.mobile.ui.designsystem.component.composetable.model

sealed class ValidationResult {
    data class Success(val value: String?) : ValidationResult()
    data class Error(val message: String) : ValidationResult()
}
