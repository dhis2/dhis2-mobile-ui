package org.hisp.dhis.mobile.ui.designsystem.component.table.model

import kotlinx.serialization.Serializable

/**
 * Data class representing the header of a table.
 *
 * @property rows The list of header rows in the table.
 * @property extraColumns Extra columns to add at the end of the table.
 */
@Serializable
data class TableHeader(val rows: List<TableHeaderRow>, val extraColumns: List<TableHeaderCell>) {

    /**
     * Calculates the number of columns for a given row index.
     *
     * @param rowIndex The index of the row.
     * @return The total number of columns.
     */
    fun numberOfColumns(rowIndex: Int): Int {
        if (rowIndex < 0) return 0
        var totalCells = 1
        for (index in 0 until rowIndex + 1) {
            totalCells *= rows[index].cells.size
        }
        return totalCells
    }

    /**
     * Calculates the maximum number of columns in the table.
     *
     * @return The maximum number of columns.
     */
    fun tableMaxColumns() = numberOfColumns(rows.size - 1) + extraColumns.size
}
