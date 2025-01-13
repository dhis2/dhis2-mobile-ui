package org.hisp.dhis.mobile.ui.designsystem.component.table.actions

import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableCell
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.ValidationResult

/**
 * Interface for validating table cells.
 * Implementations of this interface should provide specific validation logic.
 */
interface Validator {

    /**
     * Validates the given [tableCell].
     * @param tableCell The cell to be validated.
     * @return A [ValidationResult] indicating the outcome of the validation.
     */
    fun validate(tableCell: TableCell): ValidationResult {
        return ValidationResult.Success(tableCell.value)
    }
}
