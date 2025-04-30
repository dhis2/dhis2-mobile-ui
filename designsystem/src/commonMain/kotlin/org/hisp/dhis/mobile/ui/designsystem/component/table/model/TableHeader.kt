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

    /**
     * Calculates the column indexes for a given header row and column index.
     * @param headerRowIndex The index of the header row.
     * @param headerRowColumnIndex The index of the header row column.
     * @param subColumnCount The number of sub columns for a given header row.
     * @return start and end indexes of the columns
     * */
    fun columnIndexes(
        headerRowIndex: Int,
        headerRowColumnIndex: Int,
        subColumnCount: Int = numberOfSubColumns(headerRowIndex),
    ): Pair<Int, Int> {
        val startColumnIndex = headerRowColumnIndex * subColumnCount
        val endColumnIndex = startColumnIndex + subColumnCount
        return Pair(startColumnIndex, endColumnIndex)
    }

    /**
     * Calculates the number of sub columns for a given header row index.
     * @param headerRowIndex The index of the header row.
     * @return The number of sub columns for a given header row.
     * */
    fun numberOfSubColumns(headerRowIndex: Int): Int {
        return tableMaxColumns()
            .div(numberOfColumns(headerRowIndex) + extraColumns.size)
    }
}
