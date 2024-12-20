package org.hisp.dhis.mobile.ui.designsystem.component.table.actions

import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableCell
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.ValidationResult

interface Validator {
    fun validate(tableCell: TableCell): ValidationResult {
        return ValidationResult.Success(tableCell.value)
    }
}
