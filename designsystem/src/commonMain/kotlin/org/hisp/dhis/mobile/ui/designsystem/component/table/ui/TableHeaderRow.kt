package org.hisp.dhis.mobile.ui.designsystem.component.table.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.RestartAlt
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import org.hisp.dhis.mobile.ui.designsystem.component.IconButton
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.ResizingCell
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableCornerUiState
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableModel

// todo this composable was surrounded with a Constraint layout, review whether it still can be used with
// a column or whether a custom component is needed to maintain any old functionalities
@Composable
fun TableHeaderRow(
    modifier: Modifier = Modifier,
    cornerUiState: TableCornerUiState,
    tableModel: TableModel,
    horizontalScrollState: ScrollState,
    cellStyle: @Composable
    (headerColumnIndex: Int, headerRowIndex: Int) -> CellStyle,
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
                    .padding(bottom = 24.dp),
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

        TableCorner(
            modifier = Modifier
                .zIndex(1f),
            tableCornerUiState = cornerUiState,
            tableId = tableModel.id ?: "",
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
        )
    }
}
