package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.zIndex
import org.hisp.dhis.mobile.ui.designsystem.component.model.DraggableType
import org.hisp.dhis.mobile.ui.designsystem.component.modifier.draggableList
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableHeader
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableHeaderCell
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.internal.HeaderMeasures
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.internal.ItemColumnHeaderUiState
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.internal.ResizingCell
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableTheme
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableTheme.dimensions

/**
 * Composable function to display the table header.
 *
 * @param tableId The unique identifier of the table.
 * @param modifier The modifier to be applied to the layout.
 * @param tableHeaderModel The model containing the table header data.
 * @param horizontalScrollState The scroll state for horizontal scrolling.
 * @param cellStyle A composable function to provide the style for each cell.
 * @param onHeaderCellSelected Callback function invoked when a header cell is selected.
 * @param onHeaderResize Callback function invoked when the header is resized.
 * @param onResizing Callback function invoked during the resizing of the header.
 */
@Composable
internal fun TableHeader(
    tableId: String?,
    modifier: Modifier,
    tableHeaderModel: TableHeader,
    horizontalScrollState: ScrollState,
    columnCount: Int,
    cellStyle: @Composable
    (columnIndex: Int, rowIndex: Int) -> CellStyle,
    onHeaderCellSelected: (columnIndex: Int, headerRowIndex: Int) -> Unit,
    onHeaderResize: (Int, Float) -> Unit,
    onResizing: (ResizingCell?) -> Unit,
) {
    val configuration = TableTheme.configuration
    val extraColumns = columnCount - tableHeaderModel.tableMaxColumns()

    Row(
        modifier = modifier
            .horizontalScroll(state = horizontalScrollState)
            .height(IntrinsicSize.Min)
            .draggableList(
                scrollState = horizontalScrollState,
                draggableType = DraggableType.Horizontal,
            ),
    ) {
        Column(
            modifier = Modifier
                .height(IntrinsicSize.Min),
        ) {
            tableHeaderModel.rows.forEachIndexed { rowIndex, tableHeaderRow ->
                Row(
                    modifier = Modifier
                        .height(IntrinsicSize.Min)
                        .zIndex(1f),
                ) {
                    val totalColumns = tableHeaderModel.numberOfColumns(rowIndex)
                    val rowOptions = tableHeaderRow.cells.size
                    repeat(
                        times = totalColumns,
                        action = { columnIndex ->
                            val cellIndex = columnIndex % rowOptions
                            HeaderCell(
                                modifier = Modifier.zIndex((totalColumns - columnIndex) * 1f),
                                itemHeaderUiState = ItemColumnHeaderUiState(
                                    tableId = tableId,
                                    rowIndex = rowIndex,
                                    columnIndex = columnIndex,
                                    headerCell = tableHeaderRow.cells[cellIndex],
                                    headerMeasures = HeaderMeasures(
                                        width = dimensions.headerCellWidth(
                                            tableId = tableId ?: "",
                                            column = columnIndex,
                                            headerRowColumns = tableHeaderModel.numberOfColumns(
                                                rowIndex,
                                            ),
                                            totalColumns = tableHeaderModel.tableMaxColumns(),
                                            extraColumns = tableHeaderModel.extraColumns.size,
                                            groupedTables = configuration.groupTables,
                                        ),
                                        height = dimensions.defaultHeaderHeight,
                                    ),
                                    paddingValues = dimensions.headerCellPaddingValues,
                                    cellStyle = cellStyle(columnIndex, rowIndex),
                                    onCellSelected = { onHeaderCellSelected(it, rowIndex) },
                                    onHeaderResize = { headerRow, column, newValue ->
                                        val numberOfSubColumns =
                                            tableHeaderModel.tableMaxColumns() / tableHeaderModel.numberOfColumns(
                                                headerRow,
                                            )
                                        val startColumnIndex = column * numberOfSubColumns
                                        for (i in startColumnIndex until startColumnIndex + numberOfSubColumns) {
                                            onHeaderResize(i, newValue.div(numberOfSubColumns))
                                        }
                                    },
                                    onResizing = onResizing,
                                    isLastRow = tableHeaderModel.rows.lastIndex == rowIndex,
                                ) { dimensions, currentOffsetX ->
                                    dimensions.canUpdateColumnHeaderWidth(
                                        tableId = tableId ?: "",
                                        currentOffsetX = currentOffsetX,
                                        columnIndex = columnIndex,
                                        totalColumns = tableHeaderModel.tableMaxColumns(),
                                        extraColumns = tableHeaderModel.extraColumns.size,
                                        groupedTables = configuration.groupTables,
                                    )
                                },
                            )
                        },
                    )
                    tableHeaderModel.extraColumns.forEachIndexed { extraColumnIndex, extraColumnHeader ->
                        HeaderCell(
                            itemHeaderUiState = ItemColumnHeaderUiState(
                                tableId = tableId,
                                rowIndex = rowIndex,
                                columnIndex = tableHeaderModel.rows.size + extraColumnIndex,
                                headerCell = extraColumnHeader
                                    .takeIf { rowIndex == tableHeaderModel.rows.lastIndex }
                                    ?: TableHeaderCell(""),
                                headerMeasures = HeaderMeasures(
                                    dimensions.defaultCellWidthWithExtraSize(
                                        tableId = tableId ?: "",
                                        totalColumns = tableHeaderModel.tableMaxColumns(),
                                        groupedTables = configuration.groupTables,
                                    ),
                                    dimensions.defaultHeaderHeight * tableHeaderModel.rows.size,
                                ),
                                paddingValues = dimensions.headerCellPaddingValues,
                                cellStyle = cellStyle(
                                    tableHeaderModel.numberOfColumns(tableHeaderModel.rows.size - 1),
                                    tableHeaderModel.rows.size - 1,
                                ),
                                onCellSelected = {},
                                onHeaderResize = { _, _, _ -> },
                                onResizing = {},
                                isLastRow = false,
                                checkMaxCondition = { _, _ -> false },
                            ),
                        )
                    }
                    repeat(extraColumns) { extraColumnIndex ->
                        val columnIndex =
                            tableHeaderModel.rows.size + tableHeaderModel.extraColumns.size + extraColumnIndex
                        HeaderCell(
                            ItemColumnHeaderUiState(
                                tableId = tableId,
                                rowIndex = rowIndex,
                                columnIndex = columnIndex,
                                headerCell = TableHeaderCell(""),
                                headerMeasures = HeaderMeasures(
                                    width = dimensions.defaultCellWidthWithExtraSize(
                                        tableId = tableId ?: "",
                                        totalColumns = tableHeaderModel.tableMaxColumns(),
                                        groupedTables = configuration.groupTables,
                                    ),
                                    height = dimensions.defaultHeaderHeight * tableHeaderModel.rows.size,
                                ),
                                paddingValues = dimensions.headerCellPaddingValues,
                                cellStyle = CellStyle.HeaderStyle(
                                    backgroundColor = Color.Transparent,
                                    textColor = Color.Transparent,
                                ),
                                onCellSelected = {},
                                onHeaderResize = { _, _, _ -> },
                                onResizing = {},
                                isLastRow = false,
                                checkMaxCondition = { _, _ -> false },
                            ),
                        )
                    }
                }
            }
        }
        Spacer(Modifier.size(dimensions.tableEndExtraScroll))
    }
}
