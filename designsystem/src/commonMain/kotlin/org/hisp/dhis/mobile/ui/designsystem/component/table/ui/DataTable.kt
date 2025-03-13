package org.hisp.dhis.mobile.ui.designsystem.component.table.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.zIndex
import org.hisp.dhis.mobile.ui.designsystem.component.table.actions.TableInteractions
import org.hisp.dhis.mobile.ui.designsystem.component.table.actions.TableResizeActions
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableCell
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableDialogModel
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableModel
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.internal.ResizingCell
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.internal.TableCornerUiState
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.compositions.LocalInteraction
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.compositions.LocalTableResizeActions
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.HorizontalScrollConfig
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.Table
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.TableHeaderRow
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.TableItemRow
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.VerticalResizingView
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.styleForColumnHeader
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.styleForRowHeader

/**
 * Composable function to display a data table.
 *
 * @param tableList The list of table models to be displayed.
 * @param currentSelection The current table selection.
 * @param tableInteractions Optional table interactions callback.
 * @param onResizedActions Optional table resize actions callback.
 * @param topContent Optional composable content to be displayed at the top of the table.
 * @param bottomContent Optional composable content to be displayed at the bottom of the table.
 */
@Composable
fun DataTable(
    tableList: List<TableModel>,
    currentSelection: TableSelection = TableSelection.Unselected(),
    tableInteractions: TableInteractions? = null,
    onResizedActions: TableResizeActions? = null,
    topContent: @Composable (() -> Unit)? = null,
    bottomContent: @Composable (() -> Unit)? = null,
) {
    val maxColumns by remember(tableList.size) {
        derivedStateOf {
            tableList.maxOfOrNull {
                it.tableHeaderModel.tableMaxColumns()
            }
        }
    }
    val maxRowColumnHeaders by remember(tableList.size) {
        derivedStateOf {
            tableList.maxOfOrNull { tableModel ->
                tableModel.tableRows.maxOf { tableRowModel ->
                    tableRowModel.rowHeaders.size
                }
            }
        }
    }

    val themeDimensions = TableTheme.dimensions
    val config = TableTheme.configuration
    var dimensions by remember { mutableStateOf(themeDimensions) }
    var tableSelection by remember(currentSelection) { mutableStateOf(currentSelection) }
    var updatingCell by remember { mutableStateOf<TableCell?>(null) }
    val defaultsTableInteractions by remember {
        mutableStateOf(
            object : TableInteractions {
                override fun onSelectionChange(newTableSelection: TableSelection) {
                    tableSelection = when {
                        tableSelection != newTableSelection -> newTableSelection
                        else -> TableSelection.Unselected()
                    }
                    tableInteractions?.onSelectionChange(newTableSelection)
                }

                override fun onDecorationClick(dialogModel: TableDialogModel) {
                    tableInteractions?.onDecorationClick(dialogModel)
                }

                override fun onClick(tableCell: TableCell) {
                    updatingCell = tableCell
                    tableInteractions?.onClick(tableCell)
                }

                override fun onOptionSelected(cell: TableCell, code: String, label: String) {
                    updatingCell = cell
                    tableInteractions?.onOptionSelected(cell, code, label)
                }
            },
        )
    }
    val tableResizeActions by remember {
        mutableStateOf(
            object : TableResizeActions {
                override fun onTableWidthChanged(width: Int) {
                    dimensions = dimensions.copy(totalWidth = width)
                    onResizedActions?.onTableWidthChanged(width)
                }

                override fun onRowHeaderResize(tableId: String, newValue: Float) {
                    dimensions = dimensions.updateHeaderWidth(
                        config.groupTables,
                        tableId,
                        newValue,
                    )
                    onResizedActions?.onRowHeaderResize(tableId, newValue)
                }

                override fun onColumnHeaderResize(tableId: String, column: Int, newValue: Float) {
                    dimensions = dimensions.updateColumnWidth(
                        config.groupTables,
                        tableId,
                        column,
                        newValue,
                    )
                    onResizedActions?.onColumnHeaderResize(tableId, column, newValue)
                }

                override fun onTableDimensionResize(tableId: String, newValue: Float) {
                    dimensions = dimensions.updateAllWidthBy(
                        config.groupTables,
                        tableId,
                        newValue,
                    )
                    onResizedActions?.onTableDimensionResize(tableId, newValue)
                }

                override fun onTableDimensionReset(tableId: String) {
                    dimensions = dimensions.resetWidth(
                        config.groupTables,
                        tableId,
                    )
                    onResizedActions?.onTableDimensionReset(tableId)
                }
            },
        )
    }
    var resizingCell: ResizingCell? by remember { mutableStateOf(null) }
    val horizontalScrollConfig = if (TableTheme.configuration.groupTables) {
        HorizontalScrollConfig.Grouped(rememberScrollState())
    } else {
        HorizontalScrollConfig.Individual(tableList.map { rememberScrollState() })
    }

    CompositionLocalProvider(
        LocalTableDimensions provides dimensions,
        LocalTableResizeActions provides tableResizeActions,
        LocalTableSelection provides tableSelection,
        LocalInteraction provides defaultsTableInteractions,
    ) {
        Table(
            tableList = tableList,
            tableHeaderRow = { index, tableModel, isTableScrolled ->
                val isSingleValue = tableModel.tableRows.firstOrNull()?.values?.size == 1
                TableHeaderRow(
                    modifier = Modifier
                        .zIndex(if (isTableScrolled) 2f else 0f)
                        .background(Color.White),
                    cornerUiState = TableCornerUiState(
                        isSelected = tableSelection.isCornerSelected(tableModel.id ?: ""),
                        onTableResize = {
                            if (isSingleValue) {
                                tableResizeActions.onRowHeaderResize(
                                    tableModel.id ?: "",
                                    it,
                                )
                            } else {
                                tableResizeActions.onTableDimensionResize(
                                    tableModel.id ?: "",
                                    it,
                                )
                            }
                        },
                        onResizing = { resizingCell = it },
                        singleValueTable = isSingleValue,
                    ),
                    tableModel = tableModel,
                    horizontalScrollState = horizontalScrollConfig.getScrollState(index),
                    columnCount = maxColumns ?: 0,
                    maxRowColumnHeaders = maxRowColumnHeaders ?: 0,
                    cellStyle = { columnIndex, rowIndex ->
                        styleForColumnHeader(
                            isCornerSelected = tableSelection.isCornerSelected(tableModel.id ?: ""),
                            isSelected = tableSelection.isHeaderSelected(
                                selectedTableId = tableModel.id ?: "",
                                columnIndex = columnIndex,
                                columnHeaderRowIndex = rowIndex,
                            ),
                            isParentSelected = tableSelection.isParentHeaderSelected(
                                selectedTableId = tableModel.id ?: "",
                                columnIndex = columnIndex,
                                columnHeaderRowIndex = rowIndex,
                            ),
                            columnIndex = columnIndex,
                        )
                    },
                    onTableCornerClick = {
                        defaultsTableInteractions.onSelectionChange(
                            TableSelection.AllCellSelection(tableModel.id ?: ""),
                        )
                    },
                    onHeaderCellClick = { headerColumnIndex, headerRowIndex ->
                        defaultsTableInteractions.onSelectionChange(
                            TableSelection.ColumnSelection(
                                tableId = tableModel.id ?: "",
                                columnIndex = headerColumnIndex,
                                columnHeaderRow = headerRowIndex,
                                childrenOfSelectedHeader =
                                tableModel.countChildrenOfSelectedHeader(
                                    headerRowIndex,
                                    headerColumnIndex,
                                ),
                            ),
                        )
                    },
                    onHeaderResize = { column, width ->
                        tableResizeActions.onColumnHeaderResize(
                            tableModel.id ?: "",
                            column,
                            width,
                        )
                    },
                    onResizing = { resizingCell = it },
                    onResetResize = {
                        tableResizeActions.onTableDimensionReset(tableModel.id ?: "")
                    },
                )
            },
            tableItemRow = { index, tableModel, tableRowModel ->
                TableItemRow(
                    tableModel = tableModel,
                    horizontalScrollState = horizontalScrollConfig.getScrollState(index),
                    rowModels = tableRowModel,
                    rowHeaderCellStyle = { rowHeaderIndexes, rowColumnIndex ->
                        styleForRowHeader(
                            isCornerSelected = tableSelection.isCornerSelected(tableModel.id ?: ""),
                            isSelected = tableSelection.isRowSelected(
                                selectedTableId = tableModel.id ?: "",
                                rowHeaderIndexes = rowHeaderIndexes,
                            ),
                            isOtherRowSelected = tableSelection.isOtherRowSelected(
                                selectedTableId = tableModel.id ?: "",
                                rowHeaderIndexes = rowHeaderIndexes,
                                rowHeaderColumnIndex = rowColumnIndex ?: -1,
                            ),
                        )
                    },
                    onRowHeaderClick = { rowHeaderIndexes, rowHeaderColumnIndex ->
                        defaultsTableInteractions.onSelectionChange(
                            TableSelection.RowSelection(
                                tableId = tableModel.id ?: "",
                                rowIndex = rowHeaderIndexes,
                                rowColumnIndex = rowHeaderColumnIndex ?: -1,
                            ),
                        )
                    },
                    onHeaderResize = { width ->
                        tableResizeActions.onRowHeaderResize(
                            tableModel.id ?: "",
                            width,
                        )
                    },
                    onResizing = { resizingCell = it },
                    columnCount = maxColumns ?: 0,
                    maxRowColumnHeaders = maxRowColumnHeaders ?: 0,
                )
            },
            verticalResizingView = { tableHeight ->
                VerticalResizingView(
                    modifier = tableHeight?.let {
                        Modifier
                            .height(
                                with(LocalDensity.current) {
                                    it.toDp()
                                },
                            )
                    } ?: Modifier,
                    provideResizingCell = { resizingCell },
                )
            },
            topContent = topContent,
            bottomContent = bottomContent,
            maxRowColumnHeaders = maxRowColumnHeaders ?: 0,
        )
    }
}
