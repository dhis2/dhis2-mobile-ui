package org.hisp.dhis.mobile.ui.designsystem.component.table.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

/**
 * Data class representing the dimensions of the table component.
 *
 * @property tableHorizontalPadding The horizontal padding of the table.
 * @property tableVerticalPadding The vertical padding of the table.
 * @property defaultCellWidth The default width of the table cells.
 * @property defaultCellHeight The default height of the table cells.
 * @property defaultRowHeaderWidth The default width of the row header.
 * @property defaultHeaderHeight The default height of the header.
 * @property defaultLegendCornerSize The default size of the legend corner.
 * @property defaultLegendBorderWidth The default width of the legend border.
 * @property defaultHeaderTextSize The default size of the header text.
 * @property defaultRowHeaderTextSize The default size of the row header text.
 * @property defaultCellTextSize The default size of the cell text.
 * @property totalWidth The total width of the table.
 * @property cellVerticalPadding The vertical padding of the table cells.
 * @property cellHorizontalPadding The horizontal padding of the table cells.
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
    val defaultCellWidth: Int = 160,
    val defaultCellHeight: Dp = Spacing.Spacing36,
    val defaultRowHeaderWidth: Int = 275,
    val defaultHeaderHeight: Int = 36,
    val defaultLegendCornerSize: Dp = Spacing.Spacing2,
    val defaultLegendBorderWidth: Dp = Spacing.Spacing8,
    val defaultHeaderTextSize: TextUnit = 12.sp,
    val defaultRowHeaderTextSize: TextUnit = 12.sp,
    val defaultCellTextSize: TextUnit = 12.sp,
    val totalWidth: Int = 0,
    val cellVerticalPadding: Dp = Spacing.Spacing4,
    val cellHorizontalPadding: Dp = Spacing.Spacing4,
    val headerCellPaddingValues: PaddingValues = PaddingValues(
        horizontal = Spacing.Spacing4,
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
    fun rowHeaderWidth(tableId: String): Int {
        return (rowHeaderWidths[tableId] ?: defaultRowHeaderWidth) + extraWidthInTable(tableId)
    }

    internal fun defaultCellWidthWithExtraSize(
        tableId: String,
        totalColumns: Int,
        hasExtra: Boolean = false,
    ): Int = defaultCellWidth +
        extraSize(tableId, totalColumns, hasExtra) +
        extraWidthInTable(tableId)

    internal fun columnWidthWithTableExtra(tableId: String, column: Int? = null): Int =
        (columnWidth[tableId]?.get(column) ?: defaultCellWidth) + extraWidthInTable(tableId)

    internal fun headerCellWidth(
        tableId: String,
        column: Int,
        headerRowColumns: Int,
        totalColumns: Int,
        hasTotal: Boolean = false,
    ): Int {
        val rowHeaderRatio = totalColumns / headerRowColumns

        val result = when {
            rowHeaderRatio != 1 -> {
                val maxColumn = rowHeaderRatio * (1 + column) - 1
                val minColumn = rowHeaderRatio * column
                (minColumn..maxColumn).sumOf {
                    columnWidthWithTableExtra(tableId, it) +
                        extraSize(tableId, totalColumns, hasTotal, column)
                }
            }
            else -> columnWidthWithTableExtra(tableId, column) +
                extraSize(tableId, totalColumns, hasTotal, column)
        }
        return result
    }

    internal fun extraSize(tableId: String, totalColumns: Int, hasTotal: Boolean, column: Int? = null): Int {
        val screenWidth = totalWidth
        val tableWidth = tableWidth(tableId, totalColumns, hasTotal)
        val columnHasResizedValue = column?.let {
            columnWidth[tableId]?.containsKey(it)
        }

        return if (tableWidth < screenWidth && columnHasResizedValue != true) {
            val totalColumnCount = 1.takeIf { hasTotal } ?: 0
            val columnsCount = totalColumns + totalColumnCount
            ((screenWidth - tableWidth) / columnsCount).also {
                currentExtraSize[tableId] = it
            }
        } else {
            0
        }
    }

    private fun tableWidth(tableId: String, totalColumns: Int, hasTotal: Boolean): Int {
        val totalCellWidth = defaultCellWidth.takeIf { hasTotal } ?: 0
        return rowHeaderWidth(tableId) + defaultCellWidth * totalColumns + totalCellWidth
    }

    private fun updateAllWidthBy(tableId: String, widthOffset: Float): TableDimensions {
        val newWidth = (extraWidths[tableId] ?: 0) + widthOffset - 11
        val newMap = extraWidths.toMutableMap()
        newMap[tableId] = newWidth.toInt()
        return copy(extraWidths = newMap)
    }

    private fun updateHeaderWidth(tableId: String, widthOffset: Float): TableDimensions {
        val newWidth = (rowHeaderWidths[tableId] ?: defaultRowHeaderWidth) + widthOffset - 11
        val newMap = rowHeaderWidths.toMutableMap()
        newMap[tableId] = newWidth.toInt()
        return copy(rowHeaderWidths = newMap)
    }

    private fun updateColumnWidth(tableId: String, column: Int, widthOffset: Float): TableDimensions {
        val newWidth = (
            columnWidth[tableId]?.get(column)
                ?: (defaultCellWidth + (currentExtraSize[tableId] ?: 0))
            ) + widthOffset - 11

        val newMap = columnWidth.toMutableMap()
        val tableColumnMap = columnWidth[tableId]?.toMutableMap() ?: mutableMapOf()
        tableColumnMap[column] = newWidth.toInt()
        newMap[tableId] = tableColumnMap
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
    fun resetWidth(tableId: String): TableDimensions {
        val newExtraWidths = extraWidths.toMutableMap()
        val newColumnMap = columnWidth.toMutableMap()
        val newRowHeaderMap = rowHeaderWidths.toMutableMap()
        newExtraWidths.remove(tableId)
        newColumnMap.remove(tableId)
        newRowHeaderMap.remove(tableId)
        return this.copy(
            extraWidths = newExtraWidths,
            rowHeaderWidths = newRowHeaderMap,
            columnWidth = newColumnMap,
        )
    }

    internal fun canUpdateRowHeaderWidth(tableId: String, widthOffset: Float): Boolean {
        val desiredDimension = updateHeaderWidth(tableId = tableId, widthOffset = widthOffset)
        return desiredDimension.rowHeaderWidth(tableId) in minRowHeaderWidth..maxRowHeaderWidth
    }

    internal fun canUpdateColumnHeaderWidth(
        tableId: String,
        currentOffsetX: Float,
        columnIndex: Int,
        totalColumns: Int,
        hasTotal: Boolean,
    ): Boolean {
        val desiredDimension = updateColumnWidth(
            tableId = tableId,
            widthOffset = currentOffsetX,
            column = columnIndex,
        )
        return desiredDimension.columnWidthWithTableExtra(
            tableId,
            columnIndex,
        ) + extraSize(
            tableId = tableId,
            totalColumns = totalColumns,
            hasTotal = hasTotal,
            column = columnIndex,
        ) in minColumnWidth..maxColumnWidth
    }

    internal fun canUpdateAllWidths(tableId: String, widthOffset: Float): Boolean {
        val desiredDimension = updateAllWidthBy(tableId = tableId, widthOffset = widthOffset)
        return desiredDimension.rowHeaderWidth(tableId) in minRowHeaderWidth..maxRowHeaderWidth &&
            desiredDimension.columnWidthWithTableExtra(tableId) in minColumnWidth..maxColumnWidth &&
            desiredDimension.columnWidth[tableId]?.all { (column, _) ->
                desiredDimension.columnWidthWithTableExtra(
                    tableId,
                    column,
                ) in minColumnWidth..maxColumnWidth
            } ?: true
    }

    /**
     * Get the width of the row header.
     *
     * @param tableId The ID of the table.
     * @return The width of the row header.
     */
    fun getRowHeaderWidth(tableId: String): Int {
        return rowHeaderWidths[tableId] ?: defaultRowHeaderWidth
    }

    /**
     * Get the width of the column.
     *
     * @param tableId The ID of the table.
     * @param column The index of the column.
     * @return The width of the column.
     */
    fun getColumnWidth(tableId: String, column: Int): Int {
        return columnWidth[tableId]?.get(column) ?: defaultCellWidth
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
