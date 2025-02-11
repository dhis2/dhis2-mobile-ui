package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.spacedBy
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
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.internal.Keyboard
import org.hisp.dhis.mobile.ui.designsystem.component.internal.keyboardAsState
import org.hisp.dhis.mobile.ui.designsystem.component.model.DraggableType
import org.hisp.dhis.mobile.ui.designsystem.component.modifier.draggableList
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableModel
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableRowModel
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.internal.extensions.areAllValuesEmpty
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.LocalTableDimensions
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.LocalTableSelection
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableSelection
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableTheme
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableTheme.tableSelection
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.compositions.LocalTableResizeActions
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.extensions.fixedStickyHeader
import org.hisp.dhis.mobile.ui.designsystem.theme.Shape

/**
 * Composable function to display a table.
 *
 * @param tableList The list of table models to be displayed.
 * @param tableHeaderRow Optional composable function to display the header row of the table.
 * @param tableItemRow Optional composable function to display the item row of the table.
 * @param verticalResizingView Optional composable function to display the vertical resizing view.
 * @param bottomContent Optional composable content to be displayed at the bottom of the table.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun Table(
    tableList: List<TableModel>,
    tableHeaderRow: @Composable (
        (
            index: Int,
            tableModel: TableModel,
            isTableScrolled: Boolean,
        ) -> Unit
    )? = null,
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
            .clip(Shape.Small),
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
                tableList.forEachIndexed { tableIndex, tableModel ->
                    val isLastTable = tableList.lastIndex == tableIndex
                    tableHeaderRow?.invoke(tableIndex, tableModel, false)
                    tableModel.tableRows.forEachIndexed { rowIndex, tableRowModel ->
                        val isLastRow = tableModel.tableRows.lastIndex == rowIndex
                        tableItemRow?.invoke(tableIndex, tableModel, tableRowModel)
                        if (!isLastRow or TableTheme.configuration.groupTables) {
                            HorizontalDivider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = TableTheme.dimensions.tableEndExtraScroll),
                            )
                        }
                        val showExtendedDivider = if (TableTheme.configuration.groupTables) {
                            isLastTable && isLastRow
                        } else {
                            isLastRow
                        }
                        if (showExtendedDivider) {
                            ExtendDivider(
                                tableId = tableModel.id ?: "",
                                selected = tableSelection.isCornerSelected(tableModel.id ?: ""),
                            )
                        }
                    }
                }
            }
        } else {
            val verticalScrollState = rememberLazyListState()
            val keyboardState by keyboardAsState()
            val tableSelection = LocalTableSelection.current
            val tableConfiguration = TableTheme.configuration

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
                    .testTag("TABLE_SCROLLABLE_COLUMN")
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(
                        horizontal = TableTheme.dimensions.tableHorizontalPadding,
                        vertical = TableTheme.dimensions.tableVerticalPadding,
                    )
                    .onSizeChanged {
                        resizeActions.onTableWidthChanged(it.width)
                    }.draggableList(
                        scrollState = verticalScrollState,
                        draggableType = DraggableType.Vertical,
                    ),
                verticalArrangement = spacedBy(
                    if (TableTheme.configuration.groupTables) {
                        0.dp
                    } else {
                        TableTheme.dimensions.tableVerticalPadding
                    },
                ),
                contentPadding = PaddingValues(bottom = TableTheme.dimensions.tableBottomPadding),
                state = verticalScrollState,
            ) {
                tableList.forEachIndexed { tableIndex, tableModel ->
                    val isLastTable = tableList.lastIndex == tableIndex
                    fixedStickyHeader(
                        fixHeader = keyboardState == Keyboard.Closed,
                        key = tableModel.id,
                    ) {
                        tableHeaderRow?.invoke(
                            tableIndex,
                            tableModel,
                            verticalScrollState.firstVisibleItemScrollOffset != 0,
                        )
                    }
                    itemsIndexed(
                        items = tableModel.tableRows,
                        key = { _, item -> item.rowHeader.id!! },
                    ) { rowIndex, tableRowModel ->
                        val isLastRow = tableModel.tableRows.lastIndex == rowIndex
                        tableItemRow?.invoke(tableIndex, tableModel, tableRowModel)
                        if (!isLastRow or TableTheme.configuration.groupTables) {
                            HorizontalDivider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = TableTheme.dimensions.tableEndExtraScroll),
                            )
                        }
                        val showExtendedDivider = if (TableTheme.configuration.groupTables) {
                            isLastTable && isLastRow
                        } else {
                            isLastRow
                        }
                        if (showExtendedDivider) {
                            ExtendDivider(
                                tableId = tableModel.id ?: "",
                                selected = TableTheme.tableSelection.isCornerSelected(
                                    tableModel.id ?: "",
                                ),
                            )
                        }
                    }
                    if (!tableConfiguration.groupTables) {
                        stickyFooter(keyboardState == Keyboard.Closed)
                    }
                }
                bottomContent?.let { item { it.invoke() } }
            }
        }
        verticalResizingView?.invoke(tableHeight)
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
