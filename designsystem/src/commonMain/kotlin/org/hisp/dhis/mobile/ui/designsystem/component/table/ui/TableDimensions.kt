package org.hisp.dhis.mobile.ui.designsystem.component.table.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import kotlin.math.roundToInt

const val GROUPED_ID = "GROUPED"

/**
 * Data class representing the dimensions of the table component.
 *
 * @property tableHorizontalPadding The horizontal padding of the table.
 * @property tableVerticalPadding The vertical padding of the table.
 * @property defaultCellWidth The default width of the table cells.
 * @property defaultCellHeight The default height of the table cells.
 * @property defaultRowHeaderWidth The default width of the row header.
 * @property defaultHeaderHeight The default height of the header.
 * @property defaultHeaderTextSize The default size of the header text.
 * @property defaultRowHeaderTextSize The default size of the row header text.
 * @property defaultCellTextSize The default size of the cell text.
 * @property totalWidth The total width of the table.
 * @property cellPaddingValues The padding of the table cells.
 * @property headerCellPaddingValues The padding values of the header cells.
 * @property tableBottomPadding The bottom padding of the table.
 * @property extraWidths The extra widths of the table.
 * @property rowHeaderWidths The widths of the row headers.
 * @property columnWidth The widths of the columns.
 * @property minRowHeaderWidth The minimum width of the row header.
 * @property minColumnWidth The minimum width of the column.
 * @property maxRowHeaderWidth The maximum width of the row header.
 * @property maxColumnWidth The maximum width of the column.
 * @property tableEndExtraScroll The extra scroll of the table end.
 */
@Immutable
data class TableDimensions(
    val tableHorizontalPadding: Dp = Spacing.Spacing16,
    val tableVerticalPadding: Dp = Spacing.Spacing16,
    val defaultCellWidth: Int = 260,
    val defaultCellHeight: Dp = Spacing.Spacing40,
    val defaultRowHeaderWidth: Int = 275,
    val defaultHeaderHeight: Int = 36,
    val defaultHeaderTextSize: TextUnit = 12.sp,
    val defaultRowHeaderTextSize: TextUnit = 12.sp,
    val defaultCellTextSize: TextUnit = 12.sp,
    val totalWidth: Int = 0,
    val cellPaddingValues: PaddingValues = PaddingValues(
        horizontal = Spacing.Spacing8,
        vertical = Spacing.Spacing11,
    ),
    val headerCellPaddingValues: PaddingValues = PaddingValues(
        horizontal = Spacing.Spacing8,
        vertical = Spacing.Spacing11,
    ),
    val tableBottomPadding: Dp = Spacing.Spacing200,
    val extraWidths: Map<String, Int> = emptyMap(),
    val rowHeaderWidths: Map<String, Int> = emptyMap(),
    val columnWidth: Map<String, Map<Int, Int>> = emptyMap(),
    val minRowHeaderWidth: Int = 130,
    val minColumnWidth: Int = 130,
    val maxRowHeaderWidth: Int = Int.MAX_VALUE,
    val maxColumnWidth: Int = Int.MAX_VALUE,
    val tableEndExtraScroll: Dp = Spacing.Spacing6,
) {

    private var currentExtraSize: MutableMap<String, Int> = mutableMapOf()
    private fun extraWidthInTable(tableId: String): Int = extraWidths[tableId] ?: 0

    var textInputHeight = 0

    /**
     * Update the width of the row header.
     * @param tableId The ID of the table.
     * @return The updated dimensions.
     */
    fun rowHeaderWidth(
        groupedTables: Boolean,
        tableId: String,
    ): Int {
        val tableIdToUse = if (groupedTables) GROUPED_ID else tableId
        return (rowHeaderWidths[tableIdToUse] ?: defaultRowHeaderWidth) + extraWidthInTable(
            tableIdToUse,
        )
    }

    internal fun columnWidthWithTableExtra(
        groupedTables: Boolean,
        tableId: String,
        column: Int? = null,
    ): Int {
        val tableIdToUse = if (groupedTables) GROUPED_ID else tableId
        return (columnWidth[tableIdToUse]?.get(column) ?: defaultCellWidth) + extraWidthInTable(
            tableIdToUse,
        )
    }

    /**
     * Method to get the required width of the header.
     * @param groupedTables whether the tables are grouped together or not.
     * @param tableId the table id.
     * @param column the index of the column where the header cell is rendered.
     * @param headerRowColumns the number of columns with content.
     * @param totalColumns the total number of columns in the table.
     * @param extraColumns the number of extra empty columns at the end of the table.
     */
    internal fun headerCellWidth(
        groupedTables: Boolean,
        tableId: String,
        column: Int,
        headerRowColumns: Int,
        totalColumns: Int,
        extraColumns: Int = 0,
    ): Int {
        val rowHeaderRatio = if (headerRowColumns != 0) {
            (totalColumns - extraColumns) / headerRowColumns
        } else {
            null
        }

        val result = when {
            rowHeaderRatio != null && rowHeaderRatio > 1 -> {
                val maxColumn = rowHeaderRatio * (1 + column) - 1
                val minColumn = rowHeaderRatio * column
                (minColumn..maxColumn).sumOf {
                    columnWidthWithTableExtra(groupedTables, tableId, it) +
                        extraSize(
                            groupedTables = groupedTables,
                            tableId = tableId,
                            totalColumns = totalColumns,
                            column = it,
                        )
                }
            }

            else -> columnWidthWithTableExtra(groupedTables, tableId, column) +
                extraSize(
                    groupedTables = groupedTables,
                    tableId = tableId,
                    totalColumns = totalColumns,
                    column = column,
                )
        }
        return result
    }

    internal fun extraSize(
        groupedTables: Boolean,
        tableId: String,
        totalColumns: Int,
        totalHeaderRows: Int = 1,
        column: Int? = null,
    ): Int {
        val screenWidth = totalWidth
        val tableIdToUse = if (groupedTables) GROUPED_ID else tableId
        val columnHasResizedValue = column?.let {
            columnWidth[tableIdToUse]?.containsKey(it)
        } ?: false
        val tableWidth = tableWidth(groupedTables, tableIdToUse, totalColumns, totalHeaderRows)

        return if (tableWidth < screenWidth && !columnHasResizedValue) {
            ((screenWidth - tableWidth) / totalColumns).also {
                currentExtraSize[tableIdToUse] = it
            }
        } else {
            0
        }
    }

    private fun tableWidth(
        groupedTables: Boolean,
        tableId: String,
        totalColumns: Int,
        totalRowHeaders: Int,
    ): Int {
        return rowHeaderWidth(
            groupedTables,
            tableId,
        ) * totalRowHeaders + getSumOfColumnWidths(groupedTables, tableId, totalColumns) +
            tableEndExtraScroll.value.roundToInt()
    }

    private fun getSumOfColumnWidths(
        groupedTables: Boolean,
        tableId: String,
        totalColumns: Int,
    ): Int {
        return (0 until totalColumns).sumOf { columnIndex ->
            getColumnWidth(groupedTables, tableId, columnIndex)
        }
    }

    fun updateAllWidthBy(
        groupedTables: Boolean,
        tableId: String,
        widthOffset: Float,
    ): TableDimensions {
        val tableIdToUse = if (groupedTables) GROUPED_ID else tableId

        val newWidth = (extraWidths[tableIdToUse] ?: 0) + widthOffset
        val newMap = extraWidths.toMutableMap()
        newMap[tableIdToUse] = newWidth.toInt()
        return copy(extraWidths = newMap)
    }

    fun updateHeaderWidth(
        groupedTables: Boolean,
        tableId: String,
        widthOffset: Float,
    ): TableDimensions {
        val tableIdToUse = if (groupedTables) GROUPED_ID else tableId
        val newWidth = (rowHeaderWidths[tableIdToUse] ?: defaultRowHeaderWidth) + widthOffset
        val newMap = rowHeaderWidths.toMutableMap()
        newMap[tableIdToUse] = newWidth.toInt()
        return copy(rowHeaderWidths = newMap)
    }

    fun updateColumnWidth(
        groupedTables: Boolean,
        tableId: String,
        column: Int,
        widthOffset: Float,
    ): TableDimensions {
        val tableIdToUse = if (groupedTables) GROUPED_ID else tableId
        val newWidth = (
            columnWidth[tableIdToUse]?.get(column)
                ?: (defaultCellWidth + (currentExtraSize[tableIdToUse] ?: 0))
            ) + widthOffset

        val newMap = columnWidth.toMutableMap()
        val tableColumnMap = columnWidth[tableIdToUse]?.toMutableMap() ?: mutableMapOf()
        tableColumnMap[column] = newWidth.toInt()
        newMap[tableIdToUse] = tableColumnMap
        return this.copy(columnWidth = newMap)
    }

    /**
     * Check if the table has overridden widths.
     * @param tableId The ID of the table.
     * @return `true` if the table has overridden widths, `false` otherwise.
     */
    fun hasOverriddenWidths(tableId: String): Boolean {
        return rowHeaderWidths.containsKey(tableId) ||
            columnWidth.containsKey(tableId) ||
            extraWidths.containsKey(tableId)
    }

    /**
     * Reset the widths of the table.
     * @param tableId The ID of the table.
     * @return The updated dimensions.
     */
    fun resetWidth(
        groupedTables: Boolean,
        tableId: String,
    ): TableDimensions {
        val tableIdToUse = if (groupedTables) GROUPED_ID else tableId
        val newExtraWidths = extraWidths.toMutableMap()
        val newColumnMap = columnWidth.toMutableMap()
        val newRowHeaderMap = rowHeaderWidths.toMutableMap()
        newExtraWidths.remove(tableIdToUse)
        newColumnMap.remove(tableIdToUse)
        newRowHeaderMap.remove(tableIdToUse)
        return this.copy(
            extraWidths = newExtraWidths,
            rowHeaderWidths = newRowHeaderMap,
            columnWidth = newColumnMap,
        )
    }

    internal fun canUpdateRowHeaderWidth(
        groupedTables: Boolean,
        tableId: String,
        widthOffset: Float,
    ): Boolean {
        val desiredDimension = updateHeaderWidth(
            groupedTables = groupedTables,
            tableId = tableId,
            widthOffset = widthOffset,
        )
        return desiredDimension.rowHeaderWidth(
            groupedTables = groupedTables,
            tableId = tableId,
        ) in minRowHeaderWidth..maxRowHeaderWidth
    }

    internal fun canUpdateColumnHeaderWidth(
        tableId: String,
        currentOffsetX: Float,
        columnIndex: Int,
        totalColumns: Int,
        groupedTables: Boolean,
    ): Boolean {
        val desiredDimension = updateColumnWidth(
            groupedTables = groupedTables,
            tableId = tableId,
            widthOffset = currentOffsetX,
            column = columnIndex,
        )
        return desiredDimension.columnWidthWithTableExtra(
            groupedTables = groupedTables,
            tableId = tableId,
            column = columnIndex,
        ) + extraSize(
            groupedTables = groupedTables,
            tableId = tableId,
            totalColumns = totalColumns,
            column = columnIndex,
        ) in minColumnWidth..maxColumnWidth
    }

    internal fun canUpdateAllWidths(
        groupedTables: Boolean,
        tableId: String,
        widthOffset: Float,
    ): Boolean {
        val tableIdToUse = if (groupedTables) GROUPED_ID else tableId
        val desiredDimension = updateAllWidthBy(
            groupedTables = groupedTables,
            tableId = tableIdToUse,
            widthOffset = widthOffset,
        )
        return desiredDimension.rowHeaderWidth(
            groupedTables = groupedTables,
            tableId = tableIdToUse,
        ) in minRowHeaderWidth..maxRowHeaderWidth &&
            desiredDimension.columnWidthWithTableExtra(
                groupedTables = groupedTables,
                tableId = tableIdToUse,
            ) in minColumnWidth..maxColumnWidth &&
            desiredDimension.columnWidth[tableIdToUse]?.all { (column, _) ->
                desiredDimension.columnWidthWithTableExtra(
                    groupedTables = groupedTables,
                    tableId = tableIdToUse,
                    column = column,
                ) in minColumnWidth..maxColumnWidth
            } ?: true
    }

    /**
     * Get the width of the row header.
     *
     * @param tableId The ID of the table.
     * @return The width of the row header.
     */
    fun getRowHeaderWidth(
        groupedTables: Boolean,
        tableId: String,
    ): Int {
        val tableIdToUse = if (groupedTables) GROUPED_ID else tableId
        return rowHeaderWidths[tableIdToUse] ?: defaultRowHeaderWidth
    }

    /**
     * Get the width of the column.
     *
     * @param tableId The ID of the table.
     * @param column The index of the column.
     * @return The width of the column.
     */
    fun getColumnWidth(
        groupedTables: Boolean,
        tableId: String,
        column: Int,
    ): Int {
        val tableIdToUse = if (groupedTables) GROUPED_ID else tableId
        return columnWidth[tableIdToUse]?.get(column) ?: defaultCellWidth
    }

    /**
     * Get the extra widths of the table.
     *
     * @param tableId The ID of the table.
     * @return The extra widths of the table.
     */
    fun getExtraWidths(tableId: String): Int {
        return extraWidths[tableId] ?: 0
    }
}

/**
 * Composition local for the table dimensions.
 */
val LocalTableDimensions = compositionLocalOf { TableDimensions() }
