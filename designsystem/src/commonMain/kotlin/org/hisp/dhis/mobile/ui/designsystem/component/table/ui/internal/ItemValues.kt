package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.model.DraggableType
import org.hisp.dhis.mobile.ui.designsystem.component.modifier.draggableList
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableCell
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableHeader
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableTheme
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableTheme.tableSelection
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.modifiers.rowSupportForCellBorder
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.CELL_TEST_TAG

/**
 * Composable function to display item values in a table row.
 *
 * @param tableId The ID of the table.
 * @param horizontalScrollState The state of the horizontal scroll.
 * @param maxLines The maximum number of lines to display in each cell.
 * @param cellValues A map of column indices to table cells representing the cell values.
 * @param tableHeaderModel The model representing the table header.
 * @param rowIndex The row header item.
 * @param columnCount number of columns
 */
@Composable
internal fun ItemValues(
    modifier: Modifier = Modifier,
    tableId: String,
    horizontalScrollState: ScrollState,
    maxLines: Int,
    cellValues: Map<Int, TableCell>,
    tableHeaderModel: TableHeader,
    rowIndex: Int,
    columnCount: Int,
    columnCount: Int,
) {
    val firstCellSelected = tableSelection.isCellSelected(tableId, 0, rowIndex)
    Row(
        modifier = modifier
            .rowSupportForCellBorder(firstCellSelected && horizontalScrollState.value == 0, TableTheme.colors.primary)
            .horizontalScroll(state = horizontalScrollState)
            .draggableList(
                scrollState = horizontalScrollState,
                draggableType = DraggableType.Horizontal,
            ),
    ) {
        repeat(
            times = columnCount,
            action = { columnIndex ->
                val cellValue = cellValues[columnIndex] ?: TableCell(
                    id = "",
                    editable = false,
                    value = "",
                    column = columnIndex,
                )

                key("$tableId$CELL_TEST_TAG${cellValue.row}${cellValue.column}") {
                    TableCell(
                        tableId = tableId,
                        cell = cellValue,
                        maxLines = maxLines,
                        headerExtraSize = TableTheme.dimensions.extraSize(
                            groupedTables = TableTheme.configuration.groupTables,
                            tableId = tableId,
                            totalColumns = tableHeaderModel.tableMaxColumns(),
                            extraColumns = tableHeaderModel.extraColumns.size,
                            column = columnIndex,
                        ),
                    )
                }
            },
        )
        Spacer(Modifier.size(TableTheme.dimensions.tableEndExtraScroll))
    }
}
