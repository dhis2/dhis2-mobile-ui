package org.hisp.dhis.mobile.ui.designsystem.component.table.model

import kotlinx.serialization.Serializable

/**
 * Data class representing a cell in a table.
 *
 * @property id The unique identifier of the cell.
 * @property row The row index of the cell.
 * @property column The column index of the cell.
 * @property content The content of the cell.
 * @property editable Indicates if the cell is editable.
 * @property mandatory Indicates if the cell is mandatory.
 * @property error The error message associated with the cell, if any.
 * @property warning The warning message associated with the cell, if any.
 * @property legendColor The color associated with the cell's legend.
 * @property isMultiText Indicates if the cell supports multiple lines of text.
 */
@Serializable
data class TableCell(
    val id: String,
    val row: Int? = null,
    val column: Int,
    val content: TableCellContent,
    val editable: Boolean = true,
    val mandatory: Boolean? = false,
    val error: String? = null,
    val warning: String? = null,
    val legendColor: Int? = null,
    val isMultiText: Boolean = false,
) {
    val value: String?
        get() =
            when (content) {
                is TableCellContent.Text -> content.value
                is TableCellContent.Checkbox -> content.isChecked.toString()
            }

    /**
     * Checks if the cell has either an error or a warning.
     * @return True if the cell has an error or a warning, false otherwise.
     */
    fun hasErrorOrWarning() = errorOrWarningMessage() != null

    /**
     * Retrieves the error or warning message of the cell.
     * @return The error message if present, otherwise the warning message.
     */
    fun errorOrWarningMessage() = error ?: warning
}
