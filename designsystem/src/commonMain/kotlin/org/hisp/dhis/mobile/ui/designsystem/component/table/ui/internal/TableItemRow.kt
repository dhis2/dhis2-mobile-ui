package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.zIndex
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableDialogModel
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableModel
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableRowModel
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.internal.ItemHeaderUiState
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.internal.ResizingCell
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.LocalTableSelection
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableTheme
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.rowIndexSemantic
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.rowTestTag
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.rowValuesTestTag
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.tableIdSemantic

/**
 * Composable function to display a table item row.
 *
 * @param tableModel The model containing the table data.
 * @param horizontalScrollState The scroll state for horizontal scrolling.
 * @param rowModel The model containing the row data.
 * @param rowHeaderCellStyle A composable function to provide the style for the row header cell.
 * @param onRowHeaderClick Callback function invoked when the row header is clicked.
 * @param onDecorationClick Callback function invoked when a decoration is clicked.
 * @param onHeaderResize Callback function invoked when the header is resized.
 * @param onResizing Callback function invoked during the resizing of the header.
 * @param columnCount number of columns
 */
@Composable
internal fun TableItemRow(
    tableModel: TableModel,
    horizontalScrollState: ScrollState,
    rowModel: TableRowModel,
    rowHeaderCellStyle: @Composable
    (
        rowHeaderIndex: Int?,
        rowHeaderColumnIndex: Int?,
    ) -> CellStyle,
    onRowHeaderClick: (rowHeaderIndex: Int?, rowHeaderColumnIndex: Int?) -> Unit,
    onDecorationClick: (dialogModel: TableDialogModel) -> Unit,
    onHeaderResize: (Float) -> Unit,
    onResizing: (ResizingCell?) -> Unit,
    columnCount: Int,
    maxRowColumnHeaders: Int,
) {
    val tableSelection = LocalTableSelection.current
    val isRowSelected = LocalTableSelection.current.isRowSelected(
        selectedTableId = tableModel.id,
        rowHeaderIndex = rowModel.rowHeader.row,
    )
    val isCellSelectedOnRow = rowModel.values.any {
        tableSelection.isCellSelected(tableModel.id, it.value.column, rowModel.rowHeader.row)
    }
    val config = TableTheme.configuration

    Row(
        Modifier
            .semantics {
                testTag = rowTestTag(tableModel.id, rowModel.rowHeader.id)
                tableIdSemantic = tableModel.id
                rowIndexSemantic = rowModel.rowHeader.row
            }
            .width(IntrinsicSize.Min)
            .zIndex(if (isCellSelectedOnRow) 1f else 0f),
    ) {
        Row(Modifier.height(IntrinsicSize.Min)) {
            rowModel.rowHeaders.forEachIndexed { rowHeaderColumnIndex, rowHeader ->
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .zIndex((rowModel.rowHeaders.size - rowModel.row() - rowHeaderColumnIndex).toFloat()),
                ) {
                    val hideLabel by remember(tableModel) {
                        derivedStateOf {
                            val prevRow = tableModel.tableRows.getOrNull(rowModel.row() - 1)
                            prevRow?.rowHeaders?.get(rowHeaderColumnIndex)?.id == rowModel.rowHeaders.getOrNull(
                                rowHeaderColumnIndex,
                            )?.id
                        }
                    }

                    ItemHeader(
                        uiState = ItemHeaderUiState(
                            tableId = tableModel.id,
                            hideLabel = hideLabel,
                            totalColumns = rowModel.rowHeaders.size,
                            rowHeader = rowHeader,
                            cellStyle = rowHeaderCellStyle(rowHeader.row, rowHeaderColumnIndex),
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
                                            tableId = tableModel.id ?: "",
                                        ).times(maxRowColumnHeaders).toDp()
                                    }
                            },
                            maxLines = rowModel.maxLines,
                        ),
                        onCellSelected = {
                            onRowHeaderClick(
                                it,
                                rowHeaderColumnIndex,
                            )
                        },
                        onDecorationClick = onDecorationClick,
                        onHeaderResize = onHeaderResize,
                        onResizing = onResizing,
                    )
                }
            }
            ItemValues(
                modifier = Modifier
                    .semantics {
                        testTag = rowValuesTestTag(tableModel.id, rowModel.rowHeader.id)
                    },
                tableId = tableModel.id,
                horizontalScrollState = horizontalScrollState,
                cellValues = rowModel.values,
                maxLines = rowModel.maxLines,
                tableHeaderModel = tableModel.tableHeaderModel,
                rowIndex = rowModel.rowHeader.row,
                columnCount = columnCount,
            )
        }
    }
}
