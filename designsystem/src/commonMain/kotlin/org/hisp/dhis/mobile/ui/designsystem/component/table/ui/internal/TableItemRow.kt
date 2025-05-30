package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.zIndex
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableModel
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableRowModel
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.internal.ItemHeaderUiState
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.internal.ResizingCell
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.LocalTableSelection
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableTheme
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.modifiers.rowSupportForCellBorder
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.rowIndexSemantic
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.rowTestTag
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.rowValuesTestTag
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.tableIdSemantic
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

/**
 * Composable function to display a table item row.
 *
 * @param tableModel The model containing the table data.
 * @param horizontalScrollState The scroll state for horizontal scrolling.
 * @param rowModels A list containing the row data.
 * @param rowHeaderCellStyle A composable function to provide the style for the row header cell.
 * @param onRowHeaderClick Callback function invoked when the row header is clicked.
 * @param onHeaderResize Callback function invoked when the header is resized.
 * @param onResizing Callback function invoked during the resizing of the header.
 * @param totalTableColumns max number of columns in the table, including extra columns and empty non-selectable ones.
 * @param maxRowColumnHeaders number of columns in the row that have a column header.
 */
@Composable
internal fun TableItemRow(
    tableModel: TableModel,
    horizontalScrollState: ScrollState,
    rowModels: List<TableRowModel>,
    rowHeaderCellStyle: @Composable
    (
        rowHeaderIndex: List<Int>,
        rowHeaderColumnIndex: Int?,
        disabled: Boolean,
    ) -> CellStyle,
    onRowHeaderClick: (rowHeaderIndex: List<Int>, rowHeaderColumnIndex: Int?) -> Unit,
    onHeaderResize: (Float) -> Unit,
    onResizing: (ResizingCell?) -> Unit,
    totalTableColumns: Int,
    maxRowColumnHeaders: Int,
) {
    val tableSelection = LocalTableSelection.current

    val isCellSelectedOnRow = rowModels.any { rowModel ->
        rowModel.values.any {
            tableSelection.isCellSelected(tableModel.id, it.value.column, rowModel.row())
        }
    }
    val config = TableTheme.configuration
    val rowModel = rowModels.first()

    Row(
        Modifier
            .semantics {
                testTag = rowTestTag(tableModel.id, rowModel.id())
                tableIdSemantic = tableModel.id
                rowIndexSemantic = rowModel.row()
            }
            .width(IntrinsicSize.Min)
            .height(IntrinsicSize.Min)
            .padding(start = Spacing.Spacing16, end = Spacing.Spacing16)
            .zIndex(if (isCellSelectedOnRow) 1f else 0f),
    ) {
        repeat(rowModel.rowHeaders.size) { rowHeaderColumnIndex ->

            val rowHeaders = getRowHeaders(rowModels, rowHeaderColumnIndex)

            val isAnyHeaderSelectedInColumn = rowHeaders.any { rowHeader ->
                TableTheme.tableSelection.isRowSelected(
                    selectedTableId = tableModel.id,
                    rowHeaderIndexes = getSelectedIndexes(
                        rowHeader.row,
                        rowModels,
                        rowHeaderColumnIndex,
                    ),
                )
            }
            Column(
                modifier = Modifier.zIndex(
                    if (isAnyHeaderSelectedInColumn) {
                        rowModel.rowHeaders.size + 2f
                    } else {
                        (rowModel.rowHeaders.size - rowHeaderColumnIndex).toFloat()
                    },
                ),
            ) {
                rowHeaders.forEach { rowHeader ->
                    Box(
                        modifier = Modifier.weight(1f),
                    ) {
                        ItemHeader(
                            uiState = ItemHeaderUiState(
                                tableId = tableModel.id,
                                totalColumns = rowModel.rowHeaders.size,
                                rowHeader = rowHeader,
                                cellStyle = rowHeaderCellStyle(
                                    getSelectedIndexes(
                                        rowHeader.row,
                                        rowModels,
                                        rowHeaderColumnIndex,
                                    ),
                                    rowHeaderColumnIndex,
                                    rowHeader.disabled,
                                ),
                                width = when {
                                    maxRowColumnHeaders == rowModel.rowHeaders.size ->
                                        with(LocalDensity.current) {
                                            TableTheme.dimensions.rowHeaderWidth(
                                                groupedTables = config.groupTables,
                                                tableId = tableModel.id,
                                            ).toDp()
                                        }

                                    else ->
                                        with(LocalDensity.current) {
                                            TableTheme.dimensions.rowHeaderWidth(
                                                groupedTables = config.groupTables,
                                                tableId = tableModel.id,
                                            ).times(maxRowColumnHeaders).toDp()
                                        }
                                },
                                maxLines = rowModel.maxLines,
                                headerIndexes = getSelectedIndexes(
                                    rowHeader.row,
                                    rowModels,
                                    rowHeaderColumnIndex,
                                ),
                            ),
                            onCellSelected = { rowIndex ->
                                val indexes = getSelectedIndexes(
                                    rowIndex = rowIndex,
                                    rowModels = rowModels,
                                    rowHeaderColumnIndex = rowHeaderColumnIndex,
                                )
                                if (!rowHeader.disabled) {
                                    onRowHeaderClick(
                                        indexes,
                                        rowHeaderColumnIndex,
                                    )
                                }
                            },
                            onHeaderResize = onHeaderResize,
                            onResizing = onResizing,
                        )
                    }
                }
            }
        }

        Column(
            Modifier.zIndex(rowModel.rowHeaders.size + 1f),
        ) {
            rowModels.forEachIndexed { subRowIndex, tableRowModel ->
                val firstCellSelected =
                    TableTheme.tableSelection.isCellSelected(
                        tableModel.id,
                        0,
                        rowModels[subRowIndex].values[0]?.row ?: -1,
                    )

                val cellSelectedOnRow = tableRowModel.values.any {
                    tableSelection.isCellSelected(tableModel.id, it.value.column, rowModel.row())
                }

                ItemValues(
                    modifier = Modifier
                        .semantics {
                            testTag = rowValuesTestTag(tableModel.id, rowModel.id())
                        }
                        .weight(1f)
                        .rowSupportForCellBorder(
                            isCellSelectedOnRow = cellSelectedOnRow,
                            isFirstCellOnRowSelected = firstCellSelected && horizontalScrollState.value == 0,
                            borderColor = TableTheme.colors.primary,
                            subRowCount = tableModel.tableRows.size,
                            subRowIndex = subRowIndex,
                        ),
                    tableId = tableModel.id,
                    horizontalScrollState = horizontalScrollState,
                    cellValues = tableRowModel.values,
                    maxLines = tableRowModel.maxLines,
                    tableHeaderModel = tableModel.tableHeaderModel,
                    totalTableColumns = totalTableColumns,
                )
            }
        }
    }
}

private fun getRowHeaders(rowModels: List<TableRowModel>, rowHeaderColumnIndex: Int) =
    rowModels.mapNotNull {
        it.rowHeaders.getOrNull(rowHeaderColumnIndex)
    }.distinctBy { it.id }

private fun getSelectedIndexes(
    rowIndex: Int?,
    rowModels: List<TableRowModel>,
    rowHeaderColumnIndex: Int,
): List<Int> {
    val nextSize =
        getRowHeaders(rowModels, rowHeaderColumnIndex + 1).size

    return rowIndex?.let {
        if (nextSize > 0) {
            (rowIndex until rowIndex + nextSize).toList()
        } else {
            listOf(rowIndex)
        }
    } ?: emptyList()
}
