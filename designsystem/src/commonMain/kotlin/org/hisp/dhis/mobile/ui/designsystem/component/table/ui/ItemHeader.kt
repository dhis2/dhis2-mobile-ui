package org.hisp.dhis.mobile.ui.designsystem.component.table.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.ItemHeaderUiState
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableDialogModel
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.semantics.INFO_ICON
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.semantics.infoIconId
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.semantics.rowBackground
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.semantics.rowIndexSemantic
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.semantics.tableIdSemantic
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

/**
 * Composable function to display a table item header.
 *
 * @param uiState The state of the item header, containing information such as table ID, row header, cell style, etc.
 */
@Composable
internal fun ItemHeader(uiState: ItemHeaderUiState) {
    Box {
        Row(
            modifier = Modifier
                .defaultMinSize(
                    minHeight = TableTheme.dimensions.defaultCellHeight,
                )
                .width(uiState.width)
                .fillMaxHeight()
                .background(uiState.cellStyle.backgroundColor())
                .semantics {
                    tableIdSemantic = uiState.tableId
                    rowIndexSemantic = uiState.rowHeader.row
                    infoIconId = if (uiState.rowHeader.showDecoration) INFO_ICON else ""
                    rowBackground = uiState.cellStyle.backgroundColor()
                }
                .testTag("${uiState.tableId}${uiState.rowHeader.row}")
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
                    .padding(Spacing.Spacing4),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f),
                    text = uiState.rowHeader.title,
                    color = uiState.cellStyle.mainColor(),
                    fontSize = TableTheme.dimensions.defaultRowHeaderTextSize,
                    maxLines = uiState.maxLines,
                    overflow = TextOverflow.Ellipsis,
                )
                if (uiState.rowHeader.showDecoration) {
                    Spacer(modifier = Modifier.size(Spacing.Spacing4))
                    // TODO ensure icon is displayed correctly
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
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(Spacing.Spacing1),
                color = TableTheme.colors.primary,
            )
        }

        val isSelected = LocalTableSelection.current !is TableSelection.AllCellSelection &&
            LocalTableSelection.current.isRowSelected(
                selectedTableId = uiState.tableId,
                rowHeaderIndex = uiState.rowHeader.row,
            )
        if (isSelected) {
            VerticalResizingRule(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                checkMaxMinCondition = { dimensions, currentOffsetX ->
                    dimensions.canUpdateRowHeaderWidth(
                        tableId = uiState.tableId,
                        widthOffset = currentOffsetX,
                    )
                },
                onHeaderResize = uiState.onHeaderResize,
                onResizing = uiState.onResizing,
            )
        }
    }
}
