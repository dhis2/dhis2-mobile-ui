package org.hisp.dhis.mobile.ui.designsystem.component.table.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.internal.Keyboard
import org.hisp.dhis.mobile.ui.designsystem.component.internal.keyboardAsState
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableModel
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableRowModel
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.extensions.areAllValuesEmpty
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableTheme.tableSelection
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.compositions.LocalTableResizeActions
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.extensions.fixedStickyHeader

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Table(
    tableList: List<TableModel>,
    tableHeaderRow: @Composable ((index: Int, tableModel: TableModel) -> Unit)? = null,
    tableItemRow: @Composable (
        (
            index: Int,
            tableModel: TableModel,
            tableRowModel: TableRowModel,
        ) -> Unit
    )? = null,
    verticalResizingView: @Composable ((tableHeight: Int?) -> Unit)? = null,
    bottomContent: @Composable (() -> Unit)? = null,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .clip(RoundedCornerShape(8.dp)),
    ) {
        val resizeActions = LocalTableResizeActions.current
        var tableHeight: Int? by remember { mutableStateOf(null) }

        if (!TableTheme.configuration.editable && !tableList.all { it.areAllValuesEmpty() }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = LocalTableDimensions.current.tableVerticalPadding,
                        horizontal = LocalTableDimensions.current.tableHorizontalPadding,
                    )
                    .onSizeChanged {
                        resizeActions.onTableWidthChanged(it.width)
                        tableHeight = it.height
                    },
            ) {
                tableList.forEachIndexed { index, tableModel ->
                    tableHeaderRow?.invoke(index, tableModel)
                    tableModel.tableRows.forEach { tableRowModel ->
                        tableItemRow?.invoke(index, tableModel, tableRowModel)
                        LastRowDivider(
                            tableId = tableModel.id ?: "",
                            isLastRow = tableRowModel.isLastRow,
                        )
                    }
                }
            }
        } else {
            val verticalScrollState = rememberLazyListState()
            val keyboardState by keyboardAsState()
            val tableSelection = LocalTableSelection.current

            LaunchedEffect(keyboardState) {
                val isCellSelection = tableSelection is TableSelection.CellSelection
                val isKeyboardOpen = keyboardState == Keyboard.Opened
                verticalScrollState.animateToIf(
                    tableSelection.getSelectedCellRowIndex(tableSelection.tableId),
                    isCellSelection && isKeyboardOpen,
                )
            }

            LazyColumn(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(
                        horizontal = TableTheme.dimensions.tableHorizontalPadding,
                        vertical = TableTheme.dimensions.tableVerticalPadding,
                    )
                    .onSizeChanged {
                        resizeActions.onTableWidthChanged(it.width)
                    },
                contentPadding = PaddingValues(bottom = TableTheme.dimensions.tableBottomPadding),
                state = verticalScrollState,
            ) {
                tableList.forEachIndexed { index, tableModel ->
                    fixedStickyHeader(
                        fixHeader = keyboardState == Keyboard.Closed,
                        key = tableModel.id,
                    ) {
                        tableHeaderRow?.invoke(index, tableModel)
                    }
                    itemsIndexed(
                        items = tableModel.tableRows,
                        key = { _, item -> item.rowHeader.id!! },
                    ) { _, tableRowModel ->
                        tableItemRow?.invoke(index, tableModel, tableRowModel)
                        LastRowDivider(tableModel.id ?: "", tableRowModel.isLastRow)
                    }
                    stickyFooter(keyboardState == Keyboard.Closed)
                }
                bottomContent?.let { item { it.invoke() } }
            }
        }
        verticalResizingView?.invoke(tableHeight)
    }
}

@Composable
private fun LastRowDivider(tableId: String, isLastRow: Boolean) {
    if (isLastRow) {
        ExtendDivider(
            tableId = tableId,
            selected = tableSelection.isCornerSelected(tableId),
        )
    }
}

private suspend fun LazyListState.animateToIf(index: Int, condition: Boolean) {
    if (condition) {
        apply {
            if (index >= 0) {
                animateScrollToItem(index)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun LazyListScope.stickyFooter(showFooter: Boolean = true) {
    if (showFooter) {
        stickyHeader {
            Spacer(
                modifier = Modifier
                    .height(16.dp)
                    .background(color = Color.White),
            )
        }
    }
}
