package org.hisp.dhis.mobile.ui.designsystem.component.composetable.actions

import org.hisp.dhis.mobile.ui.designsystem.component.composetable.model.TableCell
import org.hisp.dhis.mobile.ui.designsystem.component.composetable.model.ValidationResult

interface Validator {
    fun validate(tableCell: TableCell): ValidationResult {
        return ValidationResult.Success(tableCell.value)
    }
}
