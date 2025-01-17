package org.hisp.dhis.mobile.ui.designsystem.component.table.model

import kotlinx.serialization.Serializable

/**
 * Data class representing the header of a table.
 *
 * @property rows The list of header rows in the table.
 * @property hasTotals Indicates if the table has a totals column.
 */
@Serializable
data class TableHeader(val rows: List<TableHeaderRow>, val hasTotals: Boolean = false) {

    /**
     * Calculates the number of columns for a given row index.
     *
     * @param rowIndex The index of the row.
     * @return The total number of columns.
     */
    fun numberOfColumns(rowIndex: Int): Int {
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
    fun tableMaxColumns() = numberOfColumns(rows.size - 1)
}
