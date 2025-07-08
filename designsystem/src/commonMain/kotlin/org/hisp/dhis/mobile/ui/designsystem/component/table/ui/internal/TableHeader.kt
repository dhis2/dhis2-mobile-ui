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
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
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
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableTheme.tableSelection
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.columnHeaderColumn
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.columnHeaderRow
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.headerRowTestTag
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.headerTestTag
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.headersTestTag
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.tableIdSemantic
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline

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
 * @param totalTableColumns max number of columns in the table, including extra columns and empty non-selectable ones.
 */
@Composable
internal fun TableHeader(
    tableId: String,
    modifier: Modifier = Modifier,
    tableHeaderModel: TableHeader,
    horizontalScrollState: ScrollState,
    totalTableColumns: Int,
    cellStyle: @Composable
    (columnIndex: Int, rowIndex: Int, disabled: Boolean) -> CellStyle.HeaderStyle,
    onHeaderCellSelected: (columnIndex: Int, headerRowIndex: Int) -> Unit,
    onHeaderResize: (Int, Float) -> Unit,
    onResizing: (ResizingCell?) -> Unit,
) {
    val configuration = TableTheme.configuration
    val extraEmptyColumns = totalTableColumns - tableHeaderModel.tableMaxColumns()
    val extraNonEmptyColumns = tableHeaderModel.extraColumns.size

    Row(
        modifier = modifier
            .semantics {
                testTag = headersTestTag(tableId)
                this.tableIdSemantic = tableId
            }
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
                // For each row in the header, create a row with cells
                Row(
                    modifier = Modifier
                        .semantics {
                            testTag = headerRowTestTag(tableId, rowIndex)
                            this.tableIdSemantic = tableId
                        }
                        .height(IntrinsicSize.Min)
                        .zIndex(1f),
                ) {
                    val totalRowHeaderColumns = tableHeaderModel.numberOfColumns(rowIndex)
                    val rowOptions = tableHeaderRow.cells.size

                    var headerRowColumns = tableHeaderModel.numberOfColumns(
                        rowIndex,
                    )

                    if (headerRowColumns == 1) headerRowColumns += extraNonEmptyColumns
                    // Repeat for each column in the row header
                    repeat(
                        times = totalRowHeaderColumns,
                        action = { columnIndex ->
                            val isSelected =
                                tableSelection.isHeaderSelected(tableId, columnIndex, rowIndex)
                            val cellIndex = columnIndex % rowOptions
                            HeaderCell(
                                modifier = Modifier
                                    .semantics {
                                        testTag = headerTestTag(tableId, rowIndex, columnIndex)
                                        this.tableIdSemantic = tableId
                                        columnHeaderRow = rowIndex
                                        columnHeaderColumn = columnIndex
                                    }
                                    .zIndex(if (isSelected) 1f else 0f),
                                itemHeaderUiState = ItemColumnHeaderUiState(
                                    tableId = tableId,
                                    rowIndex = rowIndex,
                                    columnIndex = columnIndex,
                                    headerCell = tableHeaderRow.cells[cellIndex],
                                    headerMeasures = HeaderMeasures(
                                        width = dimensions.headerCellWidth(
                                            tableId = tableId,
                                            column = columnIndex,
                                            headerRowColumns = headerRowColumns,
                                            totalColumns = totalTableColumns,
                                            extraColumns = extraEmptyColumns,
                                            groupedTables = configuration.groupTables,
                                        ),
                                        height = dimensions.defaultHeaderHeight,
                                    ),
                                    paddingValues = dimensions.headerCellPaddingValues,
                                    cellStyle = cellStyle(columnIndex, rowIndex, tableHeaderRow.cells[cellIndex].disabled),
                                    onCellSelected = { onHeaderCellSelected(it, rowIndex) },
                                    onHeaderResize = { headerRow, column, newValue ->
                                        val numberOfSubColumns =
                                            tableHeaderModel.numberOfSubColumns(rowIndex)
                                        val (columnStartIndex, columnEndIndex) = tableHeaderModel.columnIndexes(
                                            headerRow,
                                            column,
                                            numberOfSubColumns,
                                        )
                                        for (i in columnStartIndex until columnEndIndex) {
                                            onHeaderResize(i, newValue.div(numberOfSubColumns))
                                        }
                                    },
                                    onResizing = onResizing,
                                    isLastColumn = totalTableColumns == columnIndex + 1 && columnIndex > 0,
                                ) { dimensions, currentOffsetX ->
                                    dimensions.canUpdateColumnHeaderWidth(
                                        tableId = tableId,
                                        currentOffsetX = currentOffsetX,
                                        columnIndex = columnIndex,
                                        totalColumns = tableHeaderModel.tableMaxColumns(),
                                        groupedTables = configuration.groupTables,
                                    )
                                },
                            )
                        },
                    )
                    // Add extra columns in the header row
                    tableHeaderModel.extraColumns.forEachIndexed { extraColumnIndex, extraColumnHeader ->
                        val columnIndex = totalRowHeaderColumns + extraColumnIndex
                        HeaderCell(
                            itemHeaderUiState = ItemColumnHeaderUiState(
                                tableId = tableId,
                                rowIndex = rowIndex,
                                columnIndex = columnIndex,
                                headerCell = extraColumnHeader
                                    .takeIf { rowIndex == tableHeaderModel.rows.lastIndex }
                                    ?: TableHeaderCell(""),
                                headerMeasures = HeaderMeasures(
                                    dimensions.headerCellWidth(
                                        tableId = tableId,
                                        column = columnIndex,
                                        headerRowColumns = tableHeaderModel.numberOfColumns(
                                            tableHeaderModel.rows.size - 1,
                                        ) + tableHeaderModel.extraColumns.size,
                                        totalColumns = totalTableColumns,
                                        extraColumns = extraEmptyColumns,
                                        groupedTables = configuration.groupTables,
                                    ),
                                    dimensions.defaultHeaderHeight * tableHeaderModel.rows.size,
                                ),
                                paddingValues = dimensions.headerCellPaddingValues,
                                cellStyle = cellStyle(
                                    tableHeaderModel.numberOfColumns(tableHeaderModel.rows.size - 1),
                                    tableHeaderModel.rows.size - 1,
                                    extraColumnHeader.disabled,
                                ),
                                onCellSelected = {},
                                onHeaderResize = { _, _, _ -> },
                                onResizing = {},
                                isLastColumn = false,
                                checkMaxCondition = { _, _ -> false },
                            ),
                        )
                    }
                    // Add extra empty columns in the header row to fill the remaining space
                    repeat(extraEmptyColumns) { extraColumnIndex ->
                        val columnIndex =
                            totalRowHeaderColumns + tableHeaderModel.extraColumns.size + extraColumnIndex
                        HeaderCell(
                            ItemColumnHeaderUiState(
                                tableId = tableId,
                                rowIndex = rowIndex,
                                columnIndex = columnIndex,
                                headerCell = TableHeaderCell(""),
                                headerMeasures = HeaderMeasures(
                                    width = dimensions.headerCellWidth(
                                        tableId = tableId,
                                        column = columnIndex,
                                        headerRowColumns = tableHeaderModel.numberOfColumns(
                                            tableHeaderModel.rows.size - 1,
                                        ) + extraEmptyColumns,
                                        totalColumns = totalTableColumns,
                                        extraColumns = extraEmptyColumns,
                                        groupedTables = configuration.groupTables,
                                    ),
                                    height = dimensions.defaultHeaderHeight * tableHeaderModel.rows.size,
                                ),
                                paddingValues = dimensions.headerCellPaddingValues,
                                cellStyle = CellStyle.HeaderStyle(
                                    backgroundColor = TableTheme.colors.disabledCellBackground,
                                    textColor = Color.Transparent,
                                    dividerColor = Outline.Light,
                                ),
                                onCellSelected = {},
                                onHeaderResize = { _, _, _ -> },
                                onResizing = {},
                                isLastColumn = false,
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
