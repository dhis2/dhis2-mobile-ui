package org.hisp.dhis.mobile.ui.designsystem.component.table.actions

import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableCell
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableDialogModel
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableSelection

/**
 * Interface for handling interactions with table components.
 * Implementations of this interface should provide specific logic for each interaction.
 */
interface TableInteractions {
    /**
     * Called when the table selection changes.
     * @param newTableSelection The new table selection.
     */
    fun onSelectionChange(newTableSelection: TableSelection) = run { }

    /**
     * Called when a decoration is clicked.
     * @param dialogModel The model representing the dialog to be shown.
     */
    fun onDecorationClick(dialogModel: TableDialogModel) = run { }

    /**
     * Called when a table cell is clicked.
     * @param tableCell The cell that was clicked.
     */
    fun onClick(tableCell: TableCell) = run { }

    /**
     * Called when an option is selected in a table cell.
     * @param cell The cell where the option was selected.
     * @param code The code of the selected option.
     * @param label The label of the selected option.
     */
    fun onOptionSelected(
        cell: TableCell,
        code: String,
        label: String,
    ) = run { }
}
