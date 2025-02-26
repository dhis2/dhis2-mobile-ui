package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.RestartAlt
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.zIndex
import org.hisp.dhis.mobile.ui.designsystem.component.IconButton
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableModel
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.internal.ResizingCell
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.internal.TableCornerUiState
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableTheme
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.cornerTestTag
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.tableIdSemantic
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

/**
 * Composable function to display the table header row.
 *
 * @param modifier The modifier to be applied to the layout.
 * @param cornerUiState The UI state of the table corner.
 * @param tableModel The model containing the table data.
 * @param horizontalScrollState The scroll state for horizontal scrolling.
 * @param cellStyle A composable function to provide the style for each cell.
 * @param onTableCornerClick Callback function invoked when the table corner is clicked.
 * @param onHeaderCellClick Callback function invoked when a header cell is clicked.
 * @param onHeaderResize Callback function invoked when the header is resized.
 * @param onResizing Callback function invoked during the resizing of the header.
 * @param onResetResize Callback function invoked to reset the resize state.
 */
@Composable
internal fun TableHeaderRow(
    modifier: Modifier = Modifier,
    cornerUiState: TableCornerUiState,
    tableModel: TableModel,
    horizontalScrollState: ScrollState,
    columnCount: Int,
    maxRowColumnHeaders: Int,
    cellStyle: @Composable
    (headerColumnIndex: Int, headerRowIndex: Int) -> CellStyle.HeaderStyle,
    onTableCornerClick: () -> Unit = {},
    onHeaderCellClick: (headerColumnIndex: Int, headerRowIndex: Int) -> Unit = { _, _ -> },
    onHeaderResize: (Int, Float) -> Unit,
    onResizing: (ResizingCell?) -> Unit,
    onResetResize: () -> Unit = {},
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        val isHeaderActionEnabled = TableTheme.configuration.headerActionsEnabled

        if (isHeaderActionEnabled) {
            TableActions(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = Spacing.Spacing24),
                title = tableModel.title,
                actionIcons = {
                    if (TableTheme.dimensions.hasOverriddenWidths(tableModel.id ?: "")) {
                        IconButton(
                            onClick = onResetResize,
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.RestartAlt,
                                    contentDescription = "",
                                    tint = Color.Black.copy(alpha = 0.87f),
                                )
                            },
                        )
                    }
                },
            )
        }
        Row(Modifier.height(IntrinsicSize.Min)) {
            TableCorner(
                modifier = Modifier
                    .semantics {
                        testTag = cornerTestTag(tableModel.id)
                        tableIdSemantic = tableModel.id
                    }
                    .zIndex(1f),
                tableCornerUiState = cornerUiState,
                maxRowColumnHeaders = maxRowColumnHeaders,
                rowColumnHeaders = tableModel.tableRows.first().rowHeaders.size,
                tableId = tableModel.id,
                onClick = onTableCornerClick,
            )

            TableHeader(
                tableId = tableModel.id,
                modifier = Modifier,
                tableHeaderModel = tableModel.tableHeaderModel,
                horizontalScrollState = horizontalScrollState,
                cellStyle = cellStyle,
                onHeaderCellSelected = onHeaderCellClick,
                onHeaderResize = onHeaderResize,
                onResizing = onResizing,
                columnCount = columnCount,
            )
        }
    }
}
