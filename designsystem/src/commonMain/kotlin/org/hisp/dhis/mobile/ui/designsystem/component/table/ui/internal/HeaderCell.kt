package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.zIndex
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.internal.ItemColumnHeaderUiState
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.LocalTableSelection
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableSelection
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableTheme
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.columnBackground
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.columnIndexHeader
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.rowIndexHeader
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.tableIdColumnHeader

/**
 * Composable function to display a header cell.
 *
 * @param itemHeaderUiState The state of the header cell.
 * @param modifier The modifier to be applied to the cell.
 */
@Composable
internal fun HeaderCell(
    itemHeaderUiState: ItemColumnHeaderUiState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .width(with(LocalDensity.current) { itemHeaderUiState.headerMeasures.width.toDp() })
            .fillMaxHeight()
            .background(itemHeaderUiState.cellStyle.backgroundColor())
            .testTag(itemHeaderUiState.testTag)
            .semantics {
                itemHeaderUiState.tableId?.let { tableIdColumnHeader = it }
                columnIndexHeader = itemHeaderUiState.columnIndex
                rowIndexHeader = itemHeaderUiState.rowIndex
                columnBackground = itemHeaderUiState.cellStyle.backgroundColor()
            }
            .clickable {
                itemHeaderUiState.onCellSelected(itemHeaderUiState.columnIndex)
            },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = Modifier
                .padding(itemHeaderUiState.paddingValues)
                .align(Alignment.Center)
                .fillMaxWidth()
                .align(Alignment.Center),
            color = itemHeaderUiState.cellStyle.mainColor(),
            text = itemHeaderUiState.headerCell.value,
            textAlign = TextAlign.Center,
            fontSize = TableTheme.dimensions.defaultHeaderTextSize,
            overflow = TextOverflow.Ellipsis,
            maxLines = 3,
            softWrap = true,
        )
        HorizontalDivider(
            color = TableTheme.colors.primary,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
        )
        val isSelected = when (LocalTableSelection.current) {
            is TableSelection.AllCellSelection -> false
            else -> LocalTableSelection.current.isHeaderSelected(
                selectedTableId = itemHeaderUiState.tableId ?: "",
                columnIndex = itemHeaderUiState.columnIndex,
                columnHeaderRowIndex = itemHeaderUiState.rowIndex,
            )
        }
        if (isSelected) {
            VerticalResizingRule(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .zIndex(2f),
                checkMaxMinCondition = itemHeaderUiState.checkMaxCondition,
                onHeaderResize = { newValue ->
                    itemHeaderUiState.onHeaderResize(
                        itemHeaderUiState.rowIndex,
                        itemHeaderUiState.columnIndex,
                        newValue,
                    )
                },
                onResizing = itemHeaderUiState.onResizing,
            )
        }
    }
}
