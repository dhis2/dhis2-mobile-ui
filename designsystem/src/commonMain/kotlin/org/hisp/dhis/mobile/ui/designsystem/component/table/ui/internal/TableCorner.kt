package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.zIndex
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.internal.TableCornerUiState
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.LocalTableColors
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableTheme
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.modifiers.cornerBackground
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

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
    tableCornerUiState: TableCornerUiState,
    tableId: String,
    maxRowColumnHeaders: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,

) {
    val isSelected = TableTheme.tableSelection.isCornerSelected(tableId)
    val config = TableTheme.configuration

    Box(
        modifier = modifier
            .cornerBackground(
                isSelected = isSelected,
                selectedColor = MaterialTheme.colorScheme.primary,
                defaultColor = LocalTableColors.current.tableBackground,
            )
            .width(
                with(LocalDensity.current) {
                    (
                        maxRowColumnHeaders * TableTheme.dimensions
                            .rowHeaderWidth(
                                groupedTables = config.groupTables,
                                tableId = tableId,
                            )
                        ).toDp()
                },
            )
            .clickable { onClick() },
        contentAlignment = Alignment.CenterEnd,
    ) {
        VerticalDivider(
            thickness = Spacing.Spacing1,
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
                            groupedTables = config.groupTables,
                            tableId = tableId,
                            widthOffset = currentOffsetX,
                        )
                    } else {
                        dimensions.canUpdateAllWidths(
                            groupedTables = config.groupTables,
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
