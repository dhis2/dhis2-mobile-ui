package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ProgressIndicator
import org.hisp.dhis.mobile.ui.designsystem.component.ProgressIndicatorType
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
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.compositions.LocalTableResizeActions
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.extensions.fixedStickyHeader
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

const val SELECTED_CELL_INDEX_SCROLL_OFFSET = -2
const val SCROLL_OFFSET_STICKY_HEADER = -250f

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
@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun Table(
    tableList: List<TableModel>,
    tableHeaderRow: @Composable (
        (
            index: Int,
            tableModel: TableModel,
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
    loading: Boolean = false,
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
                        ?.invoke(tableIndex, tableModel)
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
            LaunchedEffect(tableSelection.getSelectedCellRowIndex(tableSelection.tableId), contentPadding) {
                val selectedIndex = tableSelection.getSelectedCellRowIndex(tableSelection.tableId)
                val isCellSelection = tableSelection is TableSelection.CellSelection
                val isKeyboardOpen = keyboardState == Keyboard.Opened
                val isItemVisible = verticalScrollState.layoutInfo.visibleItemsInfo.any { itemInfo ->
                    itemInfo.index == selectedIndex && itemInfo.offset >= 0
                }
                val shouldScroll = isItemVisible && (isCellSelection || isKeyboardOpen)
                if (shouldScroll) {
                    verticalScrollState.animateScrollToItem(
                        selectedIndex,
                        SELECTED_CELL_INDEX_SCROLL_OFFSET,
                    )
                }
            }

            LaunchedEffect(keyboardState) {
                if (tableSelection is TableSelection.CellSelection && keyboardState == Keyboard.Closed) {
                    verticalScrollState.animateScrollBy(SCROLL_OFFSET_STICKY_HEADER)
                }
            }
            val isFirstItemHidden by remember {
                derivedStateOf {
                    verticalScrollState.firstVisibleItemIndex > 0
                }
            }

            val offset by animateIntOffsetAsState(
                targetValue = if (isFirstItemHidden) {
                    with(LocalDensity.current) {
                        IntOffset(0, Spacing.Spacing16.roundToPx())
                    }
                } else {
                    IntOffset.Zero
                },
                label = "offset",
            )

            LazyColumn(
                modifier = Modifier
                    .testTag("TABLE_SCROLLABLE_COLUMN")
                    .background(Color.Transparent)
                    .fillMaxWidth()
                    .onSizeChanged {
                        resizeActions.onTableWidthChanged(it.width)
                    }.draggableList(
                        scrollState = verticalScrollState,
                        draggableType = DraggableType.Vertical,
                    ).offset { offset },
                verticalArrangement = spacedBy(
                    if (TableTheme.configuration.groupTables) {
                        0.dp
                    } else {
                        TableTheme.dimensions.tableVerticalPadding
                    },
                ),
                contentPadding = PaddingValues(bottom = contentPadding.calculateBottomPadding()),
                state = verticalScrollState,
            ) {
                topContent?.let { item { it.invoke() } }
                if (loading) {
                    item {
                        Box(
                            modifier = Modifier.fillMaxSize().background(SurfaceColor.SurfaceBright),
                            contentAlignment = Alignment.Center,
                        ) {
                            ProgressIndicator(type = ProgressIndicatorType.CIRCULAR)
                        }
                    }
                } else {
                    tableList.forEachIndexed { tableIndex, tableModel ->
                        val isLastTable = tableList.lastIndex == tableIndex
                        fixedStickyHeader(
                            key = tableModel.id,
                        ) {
                            tableHeaderRow?.takeIf { tableModel.hasHeaders() }?.invoke(
                                tableIndex,
                                tableModel,
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
                                showFooter = true,
                            )
                        }
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
            visible = TableTheme.dimensions.hasOverriddenWidths(GROUPED_ID) && TableTheme.tableSelection.canDisplayReset(),
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
