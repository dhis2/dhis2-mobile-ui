package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.internal.TableCornerUiState
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
    modifier: Modifier = Modifier,
    tableCornerUiState: TableCornerUiState,
    tableId: String,
    label: String?,
    rowColumnHeaders: Int,
    maxRowColumnHeaders: Int,
    onClick: () -> Unit,
) {
    val isSelected = TableTheme.tableSelection.isCornerSelected(tableId)
    val config = TableTheme.configuration
    val colorPrimary = MaterialTheme.colorScheme.primary

    Box(
        modifier = modifier
            .cornerBackground(
                hasLabel = !label.isNullOrEmpty(),
                isSelected = isSelected,
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
            .drawBehind {
                repeat(rowColumnHeaders) { index ->
                    val xOffset = (size.width / rowColumnHeaders) * (index + 1)
                    drawRect(
                        color = colorPrimary,
                        topLeft = Offset((xOffset - 1.dp.toPx()), 0f),
                        size = Size(1.dp.toPx(), size.height),
                    )
                }
            }
            .clickable { onClick() },
        contentAlignment = Alignment.CenterEnd,
    ) {
        label?.takeIf { it.isNotEmpty() }?.let { label ->
            Text(
                modifier = Modifier
                    .padding(
                        horizontal = Spacing.Spacing8,
                        vertical = Spacing.Spacing12,
                    )
                    .fillMaxWidth()
                    .align(Alignment.Center),
                color = if (isSelected) {
                    MaterialTheme.colorScheme.onPrimary
                } else {
                    TableTheme.colors.headerText
                },
                text = label,
                textAlign = TextAlign.Start,
                fontSize = TableTheme.dimensions.defaultHeaderTextSize,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 3,
                softWrap = true,
            )
        }

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
