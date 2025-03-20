package org.hisp.dhis.mobile.ui.designsystem.component.table.model

import kotlinx.serialization.Serializable
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableSelection

/**
 * Data class representing a table model.
 *
 * @property id The unique identifier of the table.
 * @property title The title of the table.
 * @property tableHeaderModel The header of the table.
 * @property tableRows The rows of the table.
 */
@Serializable
data class TableModel(
    val id: String,
    val title: String = "",
    val tableHeaderModel: TableHeader,
    val tableRows: List<TableRowModel>,
) {
    fun countChildrenOfSelectedHeader(
        headerRowIndex: Int,
        headerColumnIndex: Int,
    ): Map<Int, TableSelection.HeaderCellRange> {
        return tableHeaderModel.rows
            .filterIndexed { index, _ -> index > headerRowIndex }
            .mapIndexed { index, _ ->
                val rowIndex = headerRowIndex + 1 + index
                val rowSize =
                    tableHeaderModel.numberOfColumns(rowIndex) / tableHeaderModel.numberOfColumns(
                        headerRowIndex,
                    )
                val init = headerColumnIndex * rowSize
                val end = (headerColumnIndex + 1) * rowSize - 1
                rowIndex to TableSelection.HeaderCellRange(rowSize, init, end)
            }.toMap()
    }

    /**
     * Get the next cell to be selected.
     *
     * @param cellSelection The current cell selection.
     * @param successValidation The validation result.
     * @return The next cell to be selected.
     */
    fun getNextCell(
        cellSelection: TableSelection.CellSelection,
        successValidation: Boolean,
    ): Pair<TableCell, TableSelection.CellSelection>? = when {
        !successValidation ->
            cellSelection

        cellSelection.columnIndex < tableHeaderModel.tableMaxColumns() - 1 ->
            cellSelection.copy(columnIndex = cellSelection.columnIndex + 1)

        cellSelection.rowIndex < tableRows.size - 1 ->
            cellSelection.copy(
                columnIndex = 0,
                rowIndex = cellSelection.rowIndex + 1,
                globalIndex = cellSelection.globalIndex + 1,
            )

        else -> null
    }?.let { nextCell ->
        val tableCell = tableRows[nextCell.rowIndex].values[nextCell.columnIndex]
        when (tableCell?.editable) {
            true -> Pair(tableCell, nextCell)
            else -> getNextCell(nextCell, successValidation)
        }
    }

    /**
     * Check if a cell has an error.
     *
     * @param cell The cell to check.
     * @return The cell with an error.
     */
    fun cellHasError(cell: TableSelection.CellSelection): TableCell? {
        return when {
            tableRows.size == 1 && tableRows.size == cell.rowIndex -> {
                tableRows[0].values[cell.columnIndex]?.takeIf { it.error != null }
            }

            tableRows.size == cell.rowIndex -> {
                tableRows[cell.rowIndex - 1].values[cell.columnIndex]?.takeIf { it.error != null }
            }

            else -> tableRows[cell.rowIndex].values[cell.columnIndex]?.takeIf { it.error != null }
        }
    }

    /**
     * Check if the table has a cell with a specific ID.
     *
     * @param cellId The ID to check.
     * @return True if the table has a cell with the ID, false otherwise.
     */
    fun hasCellWithId(cellId: String?): Boolean {
        return tableRows.any { row ->
            row.rowHeaders.any {
                it.id.isNullOrEmpty().not() && cellId?.contains(it.id!!) == true
            }
        }
    }

    fun hasHeaders() = tableHeaderModel.tableMaxColumns() > 0
}
