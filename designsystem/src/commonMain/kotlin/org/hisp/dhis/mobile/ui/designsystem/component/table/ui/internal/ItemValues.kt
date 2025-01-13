package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.DropdownOption
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableCell
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableHeader
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableTheme
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.CELL_TEST_TAG

/**
 * Composable function to display item values in a table row.
 *
 * @param tableId The ID of the table.
 * @param horizontalScrollState The state of the horizontal scroll.
 * @param maxLines The maximum number of lines to display in each cell.
 * @param cellValues A map of column indices to table cells representing the cell values.
 * @param overridenValues A map of column indices to table cells representing the overridden cell values.
 * @param tableHeaderModel The model representing the table header.
 * @param options The list of dropdown options available for the cells.
 * @param headerLabel The label for the header.
 */
@Composable
internal fun ItemValues(
    tableId: String,
    horizontalScrollState: ScrollState,
    maxLines: Int,
    cellValues: Map<Int, TableCell>,
    overridenValues: Map<Int, TableCell>,
    tableHeaderModel: TableHeader,
    options: List<DropdownOption>,
    headerLabel: String,
) {
    Row(
        modifier = Modifier
            .horizontalScroll(state = horizontalScrollState),
    ) {
        repeat(
            times = cellValues.size,
            action = { columnIndex ->
                val cellValue =
                    if (overridenValues[columnIndex]?.id == cellValues[columnIndex]?.id) {
                        overridenValues[columnIndex]
                    } else {
                        cellValues[columnIndex]
                    } ?: TableCell(value = "", column = columnIndex)

                key("$tableId$CELL_TEST_TAG${cellValue.row}${cellValue.column}") {
                    TableCell(
                        tableId = tableId,
                        cell = cellValue,
                        maxLines = maxLines,
                        headerExtraSize = TableTheme.dimensions.extraSize(
                            tableId,
                            tableHeaderModel.tableMaxColumns(),
                            tableHeaderModel.hasTotals,
                            columnIndex,
                        ),
                        options = options,
                        headerLabel = headerLabel,
                    )
                }
            },
        )
        Spacer(Modifier.size(TableTheme.dimensions.tableEndExtraScroll))
    }
}
