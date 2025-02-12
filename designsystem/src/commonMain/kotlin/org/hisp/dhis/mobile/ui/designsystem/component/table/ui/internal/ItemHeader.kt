package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.style.TextOverflow
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.internal.ItemHeaderUiState
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
 */
@Composable
internal fun ItemHeader(uiState: ItemHeaderUiState) {
    Box(Modifier.width(IntrinsicSize.Min)) {
        Row(
            modifier = Modifier
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
                    infoIconId = if (uiState.rowHeader.showDecoration) INFO_ICON else ""
                    rowBackground = uiState.cellStyle.backgroundColor()
                }
                .clickable {
                    uiState.onCellSelected(uiState.rowHeader.row)
                    if (uiState.rowHeader.showDecoration) {
                        uiState.onDecorationClick(
                            TableDialogModel(
                                uiState.rowHeader.title,
                                uiState.rowHeader.description ?: "",
                            ),
                        )
                    }
                },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = Spacing.Spacing4),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(TableTheme.dimensions.headerCellPaddingValues),
                    text = uiState.rowHeader.title,
                    color = uiState.cellStyle.mainColor(),
                    fontSize = TableTheme.dimensions.defaultRowHeaderTextSize,
                    maxLines = uiState.maxLines,
                    overflow = TextOverflow.Ellipsis,
                )
                if (uiState.rowHeader.showDecoration) {
                    Spacer(modifier = Modifier.size(Spacing.Spacing4))
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = "info",
                        modifier = Modifier
                            .height(Spacing.Spacing10)
                            .width(Spacing.Spacing10),
                        tint = uiState.cellStyle.mainColor(),
                    )
                }
            }
            VerticalDivider(
                thickness = Spacing.Spacing1,
                color = TableTheme.colors.primary,
            )
        }

        val isSelected = LocalTableSelection.current !is TableSelection.AllCellSelection &&
            LocalTableSelection.current.isRowSelected(
                selectedTableId = uiState.tableId,
                rowHeaderIndex = uiState.rowHeader.row,
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
                onHeaderResize = uiState.onHeaderResize,
                onResizing = uiState.onResizing,
            )
        }
        HorizontalDivider(
            modifier = Modifier
                .padding(end = Spacing.Spacing1)
                .align(Alignment.BottomCenter),
            thickness = Spacing.Spacing1,
        )
    }
}
