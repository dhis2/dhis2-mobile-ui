package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.zIndex
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableDialogModel
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableModel
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableRowModel
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.internal.ItemHeaderUiState
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.internal.ResizingCell
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableTheme
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.ROW_TEST_TAG

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
 */
@Composable
internal fun TableItemRow(
    tableModel: TableModel,
    horizontalScrollState: ScrollState,
    rowModel: TableRowModel,
    rowHeaderCellStyle: @Composable
    (rowHeaderIndex: Int?) -> CellStyle,
    onRowHeaderClick: (rowHeaderIndex: Int?) -> Unit,
    onDecorationClick: (dialogModel: TableDialogModel) -> Unit,
    onHeaderResize: (Float) -> Unit,
    onResizing: (ResizingCell?) -> Unit,
    columnCount: Int,
) {
    val config = TableTheme.configuration
    Column(
        Modifier
            .testTag("$ROW_TEST_TAG${rowModel.rowHeader.row}")
            .width(IntrinsicSize.Min),
    ) {
        Row(Modifier.height(IntrinsicSize.Min)) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .zIndex(1f),
            ) {
                ItemHeader(
                    ItemHeaderUiState(
                        tableId = tableModel.id ?: "",
                        rowHeader = rowModel.rowHeader,
                        cellStyle = rowHeaderCellStyle(rowModel.rowHeader.row),
                        width = with(LocalDensity.current) {
                            TableTheme.dimensions.rowHeaderWidth(
                                groupedTables = config.groupTables,
                                tableId = tableModel.id ?: "",
                            ).toDp()
                        },
                        maxLines = rowModel.maxLines,
                        onCellSelected = onRowHeaderClick,
                        onDecorationClick = onDecorationClick,
                        onHeaderResize = onHeaderResize,
                        onResizing = onResizing,
                    ),
                )
            }
            ItemValues(
                tableId = tableModel.id ?: "",
                horizontalScrollState = horizontalScrollState,
                cellValues = rowModel.values,
                maxLines = rowModel.maxLines,
                tableHeaderModel = tableModel.tableHeaderModel,
                columnCount = columnCount,
            )
        }
    }
}
