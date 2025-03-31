package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.RestartAlt
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.internal.Keyboard
import org.hisp.dhis.mobile.ui.designsystem.component.internal.keyboardAsState
import org.hisp.dhis.mobile.ui.designsystem.component.model.DraggableType
import org.hisp.dhis.mobile.ui.designsystem.component.modifier.draggableList
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableModel
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableRowModel
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.internal.extensions.areAllValuesEmpty
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.GROUPED_ID
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.LocalTableSelection
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableSelection
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableTheme
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableTheme.tableSelection
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.compositions.LocalTableResizeActions
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.extensions.fixedStickyHeader
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.jetbrains.compose.resources.ExperimentalResourceApi

/**
 * Composable function to display a table.
 *
 * @param tableList The list of table models to be displayed.
 * @param tableHeaderRow Optional composable function to display the header row of the table.
 * @param tableItemRow Optional composable function to display the item row of the table.
 * @param verticalResizingView Optional composable function to display the vertical resizing view.
 * @param topContent Optional composable content to be displayed at the top of the table.
 * @param bottomContent Optional composable content to be displayed at the bottom of the table.
 * @param maxRowColumnHeaders The maximum number of row column headers.
 * @param contentPadding The padding values for the content of the table.
 */
@OptIn(ExperimentalFoundationApi::class, ExperimentalResourceApi::class)
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
            tableRowModel: List<TableRowModel>,
        ) -> Unit
    )? = null,
    verticalResizingView: @Composable ((tableHeight: Int?) -> Unit)? = null,
    topContent: @Composable (() -> Unit)? = null,
    bottomContent: @Composable (() -> Unit)? = null,
    maxRowColumnHeaders: Int,
    contentPadding: PaddingValues,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = TableTheme.colors.tableBackground,
                shape = MaterialTheme.shapes.small,
            )
            .clip(MaterialTheme.shapes.small),
    ) {
        val resizeActions = LocalTableResizeActions.current
        var tableHeight: Int? by remember { mutableStateOf(null) }

        if (!TableTheme.configuration.editable && !tableList.all { it.areAllValuesEmpty() }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(contentPadding)
                    .onSizeChanged {
                        resizeActions.onTableWidthChanged(it.width)
                        tableHeight = it.height
                    },
            ) {
                tableList.forEachIndexed { tableIndex, tableModel ->
                    val isLastTable = tableList.lastIndex == tableIndex
                    tableHeaderRow?.takeIf { tableModel.hasHeaders() }
                        ?.invoke(tableIndex, tableModel, false)
                    tableModel.tableRows.forEachIndexed { rowIndex, tableRowModel ->
                        val isLastRow = tableModel.tableRows.lastIndex == rowIndex
                        tableItemRow?.invoke(tableIndex, tableModel, listOf(tableRowModel))
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
                                tableId = tableModel.id,
                                rowHeaderCount = maxRowColumnHeaders,
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
            LaunchedEffect(tableSelection.getSelectedCellRowIndex(tableSelection.tableId)) {
                val selectedIndex = tableSelection.getSelectedCellRowIndex(tableSelection.tableId)
                val isCellSelection = tableSelection is TableSelection.CellSelection
                val isKeyboardOpen = keyboardState == Keyboard.Opened
                val isItemVisible = verticalScrollState.layoutInfo.visibleItemsInfo.any { itemInfo ->
                    itemInfo.index == selectedIndex && itemInfo.offset >= 0
                }
                val shouldScroll = isItemVisible && (isCellSelection || isKeyboardOpen)
                verticalScrollState.animateToIf(
                    selectedIndex,
                    shouldScroll,
                )
            }

            val isScrolled by remember {
                derivedStateOf {
                    verticalScrollState.firstVisibleItemScrollOffset != 0
                }
            }
            LazyColumn(
                modifier = Modifier
                    .testTag("TABLE_SCROLLABLE_COLUMN")
                    .background(Color.White)
                    .fillMaxWidth()
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
                contentPadding = contentPadding,
                state = verticalScrollState,
            ) {
                topContent?.let { item { it.invoke() } }
                tableList.forEachIndexed { tableIndex, tableModel ->
                    val isLastTable = tableList.lastIndex == tableIndex
                    fixedStickyHeader(
                        fixHeader = keyboardState == Keyboard.Closed,
                        key = tableModel.id,
                    ) {
                        val isFirstVisibleStickyHeader by remember {
                            derivedStateOf {
                                verticalScrollState
                                    .layoutInfo.visibleItemsInfo
                                    .firstOrNull()?.key == "${tableModel.id}_sticky"
                            }
                        }
                        tableHeaderRow?.takeIf { tableModel.hasHeaders() }?.invoke(
                            tableIndex,
                            tableModel,
                            isFirstVisibleStickyHeader && isScrolled,
                        )
                    }
                    val rowItems =
                        tableModel.tableRows.groupBy { it.rowHeaders.first().id }.values.toList()
                            .dropLast(1)
                    itemsIndexed(
                        items = rowItems,
                        key = { _, item -> "${tableModel.id}_${item.first().id()}" },
                    ) { _, tableRowModel ->
                        tableItemRow?.invoke(tableIndex, tableModel, tableRowModel)
                    }
                    val lastItem =
                        tableModel.tableRows.groupBy { it.rowHeaders.first().id }.values.toList()
                            .last()

                    fixedStickyHeader(
                        fixHeader = keyboardState == Keyboard.Closed,
                        key = "${tableModel.id}_sticky_last_row",
                    ) {
                        tableItemRow?.invoke(
                            tableIndex,
                            tableModel,
                            lastItem,
                        )
                        val showExtendedDivider = if (TableTheme.configuration.groupTables) {
                            isLastTable
                        } else {
                            true
                        }
                        if (showExtendedDivider) {
                            ExtendDivider(
                                tableId = tableModel.id,
                                rowHeaderCount = maxRowColumnHeaders,
                            )
                        }
                    }

                    if (!tableConfiguration.groupTables) {
                        stickyFooter(
                            key = "${tableModel.id}_footer",
                            showFooter = keyboardState == Keyboard.Closed,
                        )
                    }
                }
                bottomContent?.let { item { it.invoke() } }
            }
        }
        verticalResizingView?.invoke(tableHeight)

        AnimatedVisibility(
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .fillMaxWidth(),
            visible = TableTheme.dimensions.hasOverriddenWidths(GROUPED_ID),
        ) {
            Box(
                Modifier
                    .background(Color.White.copy(alpha = 0.8f))
                    .padding(Spacing.Spacing16),
                contentAlignment = Alignment.Center,
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    style = ButtonStyle.TONAL,
                    text = provideStringResource("reset_table_layout"),
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.RestartAlt,
                            contentDescription = "Reset table layout",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                    },
                    onClick = {
                        resizeActions.onTableDimensionReset(GROUPED_ID)
                    },
                )
            }
        }
    }
}

private suspend fun LazyListState.animateToIf(index: Int, condition: Boolean) {
    if (condition) {
        apply {
            if (index >= 0) {
                animateScrollToItem(index, 250)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun LazyListScope.stickyFooter(
    key: String?,
    showFooter: Boolean = true,
) {
    if (showFooter) {
        stickyHeader(key = key) {
            Spacer(
                modifier = Modifier
                    .height(16.dp)
                    .background(color = Color.White),
            )
        }
    }
}
