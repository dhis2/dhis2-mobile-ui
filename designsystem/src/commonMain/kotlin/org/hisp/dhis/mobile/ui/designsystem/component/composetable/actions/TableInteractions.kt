package org.hisp.dhis.mobile.ui.designsystem.component.composetable.actions

import org.hisp.dhis.mobile.ui.designsystem.component.composetable.model.TableCell
import org.hisp.dhis.mobile.ui.designsystem.component.composetable.model.TableDialogModel
import org.hisp.dhis.mobile.ui.designsystem.component.composetable.ui.TableSelection

interface TableInteractions {
    fun onSelectionChange(newTableSelection: TableSelection) = run { }
    fun onDecorationClick(dialogModel: TableDialogModel) = run { }
    fun onClick(tableCell: TableCell) = run { }
    fun onOptionSelected(cell: TableCell, code: String, label: String) = run { }
}
