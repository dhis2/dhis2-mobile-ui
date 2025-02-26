package org.hisp.dhis.mobile.ui.designsystem.component.table.ui

import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Sealed class representing different types of table selections.
 *
 * @property tableId The ID of the table.
 */
sealed class TableSelection(open val tableId: String) {

    /**
     * Represents an unselected state with an optional previous selected table ID.
     *
     * @property previousSelectedTableId The ID of the previously selected table.
     */
    data class Unselected(
        val previousSelectedTableId: String? = null,
    ) : TableSelection(previousSelectedTableId ?: "")

    /**
     * Represents a selection of all cells in the table.
     *
     * @property tableId The ID of the table.
     */
    data class AllCellSelection(
        override val tableId: String,
    ) : TableSelection(tableId)

    /**
     * Represents a selection of a row in the table.
     *
     * @property tableId The ID of the table.
     * @property rowIndex The index of the selected row.
     */
    data class RowSelection(
        override val tableId: String,
        val rowIndex: List<Int>,
        val rowColumnIndex: Int,
    ) : TableSelection(tableId)

    /**
     * Represents a selection of a column in the table.
     *
     * @property tableId The ID of the table.
     * @property columnIndex The index of the selected column.
     * @property columnHeaderRow The index of the header row of the selected column.
     * @property childrenOfSelectedHeader The children of the selected header.
     */
    data class ColumnSelection(
        override val tableId: String,
        val columnIndex: Int,
        val columnHeaderRow: Int,
        val childrenOfSelectedHeader: Map<Int, HeaderCellRange>,
    ) : TableSelection(tableId)

    /**
     * Represents a range of header cells.
     *
     * @property size The size of the range.
     * @property firstIndex The first index of the range.
     * @property lastIndex The last index of the range.
     */
    data class HeaderCellRange(val size: Int, val firstIndex: Int, val lastIndex: Int) {

        /**
         * Checks if the given column index is within the range.
         *
         * @param columnIndex The index of the column to check.
         * @return `true` if the column index is within the range, `false` otherwise.
         */
        fun isInRange(columnIndex: Int): Boolean {
            return columnIndex in firstIndex..lastIndex
        }
    }

    /**
     * Represents a selection of a cell in the table.
     *
     * @property tableId The ID of the table.
     * @property columnIndex The index of the selected column.
     * @property rowIndex The index of the selected row.
     * @property globalIndex The global index of the selected cell.
     */
    data class CellSelection(
        override val tableId: String,
        val columnIndex: Int,
        val rowIndex: Int,
        val globalIndex: Int,
    ) : TableSelection(tableId)

    /**
     * Gets the row index of the selected cell if the table ID matches.
     *
     * @param selectedTableId The ID of the selected table.
     * @return The row index of the selected cell, or -1 if not selected.
     */
    fun getSelectedCellRowIndex(selectedTableId: String): Int =
        if (selectedTableId == tableId && this is CellSelection) {
            this.rowIndex
        } else {
            -1
        }

    /**
     * Checks if the corner of the table is selected.
     *
     * @param selectedTableId The ID of the selected table.
     * @return True if the corner is selected, false otherwise.
     */
    fun isCornerSelected(selectedTableId: String) =
        selectedTableId == tableId && (this is AllCellSelection)

    /**
     * Checks if a header cell is selected.
     *
     * @param selectedTableId The ID of the selected table.
     * @param columnIndex The index of the column.
     * @param columnHeaderRowIndex The row index of the column header.
     * @return True if the header cell is selected, false otherwise.
     */
    fun isHeaderSelected(selectedTableId: String, columnIndex: Int, columnHeaderRowIndex: Int) =
        selectedTableId == tableId && (this is ColumnSelection) &&
            this.columnIndex == columnIndex &&
            this.columnHeaderRow == columnHeaderRowIndex

    /**
     * Checks if a parent header is selected.
     *
     * @param selectedTableId The ID of the selected table.
     * @param columnIndex The index of the column.
     * @param columnHeaderRowIndex The row index of the column header.
     * @return True if the parent header is selected, false otherwise.
     */
    fun isParentHeaderSelected(
        selectedTableId: String,
        columnIndex: Int,
        columnHeaderRowIndex: Int,
    ) = selectedTableId == tableId && (this is ColumnSelection) &&
        (
            when {
                columnHeaderRowIndex < this.columnHeaderRow -> false
                else -> this.childrenOfSelectedHeader[columnHeaderRowIndex]?.isInRange(
                    columnIndex,
                ) ?: false
            }
            )

    /**
     * Checks if a row is selected.
     *
     * @param selectedTableId The ID of the selected table.
     * @param rowHeaderIndex The index of the row header.
     * @return True if the row is selected, false otherwise.
     */
    fun isRowSelected(
        selectedTableId: String,
        rowHeaderIndex: Int,
    ) = this.isCornerSelected(selectedTableId) ||
        selectedTableId == tableId &&
        (this is RowSelection) &&
        this.rowIndex.contains(rowHeaderIndex)

    fun isRowSelected(
        selectedTableId: String,
        rowHeaderIndexes: List<Int>,
    ) = this.isCornerSelected(selectedTableId) ||
        selectedTableId == tableId &&
        (this is RowSelection) &&
        rowHeaderIndexes == this.rowIndex

    /**
     * Checks if another row is selected.
     *
     * @param selectedTableId The ID of the selected table.
     * @param rowHeaderIndex The index of the row header.
     * @return True if another row is selected, false otherwise.
     */
    fun isOtherRowSelected(
        selectedTableId: String,
        rowHeaderIndexes: List<Int>,
        rowHeaderColumnIndex: Int,
    ) = selectedTableId == tableId &&
        (this is RowSelection) &&
        this.rowColumnIndex < rowHeaderColumnIndex &&
        this.rowIndex.containsAll(rowHeaderIndexes)

    /**
     * Checks if a cell is selected.
     *
     * @param selectedTableId The ID of the selected table.
     * @param columnIndex The index of the column.
     * @param rowIndex The index of the row.
     * @return True if the cell is selected, false otherwise.
     */
    fun isCellSelected(selectedTableId: String, columnIndex: Int, rowIndex: Int) =
        this.tableId == selectedTableId && (this is CellSelection) &&
            this.columnIndex == columnIndex &&
            this.rowIndex == rowIndex

    /**
     * Checks if a cell's parent is selected.
     *
     * @param selectedTableId The ID of the selected table.
     * @param columnIndex The index of the column.
     * @param rowIndex The index of the row.
     * @return True if the cell's parent is selected, false otherwise.
     */
    fun isCellParentSelected(
        selectedTableId: String,
        columnIndex: Int,
        rowIndex: Int,
    ) =
        when (this) {
            is ColumnSelection ->
                if (childrenOfSelectedHeader.isEmpty()) {
                    isCellValid(columnIndex, rowIndex) &&
                        this.columnIndex == columnIndex &&
                        this.tableId == selectedTableId
                } else {
                    isCellValid(columnIndex, rowIndex) &&
                        this.tableId == selectedTableId &&
                        this.childrenOfSelectedHeader.values.last().isInRange(columnIndex)
                }
            is RowSelection -> {
                isRowSelected(selectedTableId, rowIndex)
            }
            else -> false
        }

    /**
     * Checks if a cell is valid.
     *
     * @param columnIndex The index of the column.
     * @param rowIndex The index of the row.
     * @return True if the cell is valid, false otherwise.
     */
    private fun isCellValid(columnIndex: Int, rowIndex: Int): Boolean {
        return columnIndex != -1 && rowIndex != -1
    }
}

/**
 * CompositionLocal to provide [TableSelection] throughout the Compose hierarchy.
 */
val LocalTableSelection = staticCompositionLocalOf<TableSelection> { TableSelection.Unselected() }
