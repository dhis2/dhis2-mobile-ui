package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.internal.TableCornerUiState
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.LocalTableColors
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.LocalTableSelection
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableSelection
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableTheme
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.modifiers.cornerBackground

/**
 * Composable function to display the table corner.
 *
 * @param modifier The modifier to be applied to the layout.
 * @param tableCornerUiState The state of the table corner.
 * @param tableId The ID of the table.
 * @param onClick The action to be executed
 */
@Composable
internal fun TableCorner(
    modifier: Modifier = Modifier,
    tableCornerUiState: TableCornerUiState,
    tableId: String,
    onClick: () -> Unit,
) {
    val isSelected = LocalTableSelection.current is TableSelection.AllCellSelection
    Box(
        modifier = modifier
            .cornerBackground(
                isSelected = isSelected,
                selectedColor = LocalTableColors.current.primaryLight,
                defaultColor = LocalTableColors.current.tableBackground,
            )
            .width(
                with(LocalDensity.current) {
                    TableTheme.dimensions
                        .rowHeaderWidth(tableId)
                        .toDp()
                },
            )
            .clickable { onClick() },
        contentAlignment = Alignment.CenterEnd,
    ) {
        VerticalDivider(
            modifier
                .fillMaxHeight()
                .width(1.dp),
            color = TableTheme.colors.primary,
        )
        if (isSelected) {
            VerticalResizingRule(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .zIndex(1f),
                checkMaxMinCondition = { dimensions, currentOffsetX ->
                    if (tableCornerUiState.singleValueTable) {
                        dimensions.canUpdateRowHeaderWidth(
                            tableId = tableId,
                            widthOffset = currentOffsetX,
                        )
                    } else {
                        dimensions.canUpdateAllWidths(
                            tableId = tableId,
                            widthOffset = currentOffsetX,
                        )
                    }
                },
                onHeaderResize = { newValue ->
                    tableCornerUiState.onTableResize(newValue)
                },
                onResizing = tableCornerUiState.onResizing,
            )
        }
    }
}
