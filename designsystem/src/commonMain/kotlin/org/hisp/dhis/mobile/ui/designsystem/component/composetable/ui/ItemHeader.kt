package org.hisp.dhis.mobile.ui.designsystem.component.composetable.ui

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
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.composetable.model.ItemHeaderUiState
import org.hisp.dhis.mobile.ui.designsystem.component.composetable.model.TableDialogModel
import org.hisp.dhis.mobile.ui.designsystem.component.composetable.ui.semantics.INFO_ICON
import org.hisp.dhis.mobile.ui.designsystem.component.composetable.ui.semantics.infoIconId
import org.hisp.dhis.mobile.ui.designsystem.component.composetable.ui.semantics.rowBackground
import org.hisp.dhis.mobile.ui.designsystem.component.composetable.ui.semantics.rowIndexSemantic
import org.hisp.dhis.mobile.ui.designsystem.component.composetable.ui.semantics.tableIdSemantic

//todo ensure icons are correct and replace hardcoded dp values with spacing class
@Composable
fun ItemHeader(uiState: ItemHeaderUiState) {
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
                    uiState.rowHeader.row?.let { rowIndexSemantic = uiState.rowHeader.row }
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
                    .padding(4.dp),
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
                    Spacer(modifier = Modifier.size(4.dp))
                    //todo ensure icon is displayed correctly
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = "info",
                        modifier = Modifier
                            .height(10.dp)
                            .width(10.dp),
                        tint = uiState.cellStyle.mainColor(),
                    )
                }
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp),
                color = TableTheme.colors.primary,
            )
        }

        val isSelected = LocalTableSelection.current !is TableSelection.AllCellSelection &&
            LocalTableSelection.current.isRowSelected(
                selectedTableId = uiState.tableId,
                rowHeaderIndex = uiState.rowHeader.row ?: -1,
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
