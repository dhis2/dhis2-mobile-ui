package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.style.TextOverflow
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.internal.ItemHeaderUiState
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.internal.ResizingCell
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.LocalTableSelection
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableSelection
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableTheme
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.rowBackground
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.rowHeaderTestTag
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.rowIndexSemantic
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.tableIdSemantic
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

/**
 * Composable function to display a table item header.
 *
 * @param uiState The state of the item header, containing information such as table ID, row header, cell style, etc.
 * @param onCellSelected Callback function invoked when a cell is selected.
 * @param onDecorationClick Callback function invoked when the decoration icon is clicked.
 * @param onHeaderResize Callback function invoked when the header is resized.
 * @param onResizing Callback function invoked during the resizing of the header.
 */
@Composable
internal fun ItemHeader(
    uiState: ItemHeaderUiState,
    clickableInteraction: MutableInteractionSource = remember { MutableInteractionSource() },
    onCellSelected: (Int?) -> Unit,
    onHeaderResize: (Float) -> Unit,
    onResizing: (ResizingCell?) -> Unit,
) {
    val tableSelection = LocalTableSelection.current

    Box(
        Modifier
            .defaultMinSize(
                minHeight = TableTheme.dimensions.defaultCellHeight,
            )
            .width(uiState.width)
            .fillMaxHeight()
            .background(uiState.cellStyle.backgroundColor())
            .semantics {
                testTag = rowHeaderTestTag(uiState.tableId, uiState.rowHeader.id)
                tableIdSemantic = uiState.tableId
                rowIndexSemantic = uiState.rowHeader.row
                rowBackground = uiState.cellStyle.backgroundColor()
            }
            .clickable(
                role = Role.Button,
                indication = ripple(),
                interactionSource = clickableInteraction,
                onClick = {
                    onCellSelected(uiState.rowHeader.row)
                },
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(TableTheme.dimensions.headerCellPaddingValues),
            text = uiState.rowHeader.title,
            color = uiState.cellStyle.mainColor(),
            fontSize = TableTheme.dimensions.defaultRowHeaderTextSize,
            maxLines = uiState.maxLines,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodySmall,
        )

        VerticalDivider(
            modifier = Modifier.align(Alignment.TopEnd),
            thickness = Spacing.Spacing1,
            color = TableTheme.colors.primary,
        )

        val isSelected = tableSelection !is TableSelection.AllCellSelection &&
            tableSelection.isRowSelected(
                selectedTableId = uiState.tableId,
                rowHeaderIndexes = uiState.headerIndexes,
            )

        HorizontalDivider(
            modifier = Modifier
                .padding(end = Spacing.Spacing1)
                .align(Alignment.BottomCenter),
            thickness = Spacing.Spacing1,
            color = if (isSelected) {
                MaterialTheme.colorScheme.primary
            } else {
                DividerDefaults.color
            },
        )

        if (isSelected) {
            val config = TableTheme.configuration
            VerticalResizingRule(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                checkMaxMinCondition = { dimensions, currentOffsetX ->
                    dimensions.canUpdateRowHeaderWidth(
                        groupedTables = config.groupTables,
                        tableId = uiState.tableId,
                        widthOffset = currentOffsetX,
                    )
                },
                onHeaderResize = onHeaderResize,
                onResizing = onResizing,
            )
        }
    }
}
