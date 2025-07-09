package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.RowHeader
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableCell
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableCellContent
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableHeader
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableHeaderCell
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableHeaderRow
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableModel
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableRowModel
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.DataTable
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.junit.Rule
import org.junit.Test

class TableCellSnapshotTest {
    @get:Rule
    val paparazzi = paparazzi()

    @Test
    fun launchTableCell() {
        paparazzi.snapshot {
            DataTable(
                tableList = listOf(tableModel()),
                topContent = {
                    Spacer(modifier = Modifier.size(Spacing.Spacing16))
                },
                contentPadding =
                    PaddingValues(
                        top = Spacing.Spacing16,
                        bottom = Spacing.Spacing200,
                        start = Spacing.Spacing16,
                        end = Spacing.Spacing16,
                    ),
            )
        }
    }

    private fun tableModel() = TableModel(
        id = "tableId",
        title = "Table",
        tableHeaderModel = TableHeader(
            rows = listOf(
                TableHeaderRow(
                    cells = listOf(
                        TableHeaderCell("Opt 1"),
                        TableHeaderCell("Opt 2"),
                        TableHeaderCell("Opt 3"),
                    ),
                ),
            ),
            extraColumns = emptyList(),
        ),
        tableRows = listOf(
            TableRowModel(
                rowHeaders = listOf(
                    RowHeader(
                        id = "0",
                        title = "Label 1",
                        row = 0,
                        column = 0,
                    ),
                ),
                values = mapOf(
                    0 to TableCell(
                        id = "cell",
                        row = 0,
                        column = 0,
                        content = TableCellContent.Text("1"),
                        editable = true,
                        mandatory = false,
                        error = null,
                        warning = null,
                        legendColor = null,
                    ),
                    1 to TableCell(
                        id = "cell",
                        row = 0,
                        column = 1,
                        content = TableCellContent.Text("2"),
                        editable = false,
                        mandatory = false,
                        error = null,
                        warning = null,
                        legendColor = null,
                    ),
                    2 to TableCell(
                        id = "cell",
                        row = 0,
                        column = 2,
                        content = TableCellContent.Text("3"),
                        editable = false,
                        mandatory = false,
                        error = null,
                        warning = null,
                        legendColor = -1937,
                    ),
                ),
                maxLines = 3,
            ),
            TableRowModel(
                rowHeaders = listOf(
                    RowHeader(
                        id = "1",
                        title = "Label 2",
                        row = 1,
                        column = 0,
                    ),
                ),
                values = mapOf(
                    0 to TableCell(
                        id = "cell",
                        row = 1,
                        column = 0,
                        content = TableCellContent.Text("3"),
                        editable = true,
                        mandatory = true,
                        error = null,
                        warning = null,
                        legendColor = null,
                    ),
                    1 to TableCell(
                        id = "cell",
                        row = 1,
                        column = 1,
                        content = TableCellContent.Text("4"),
                        editable = false,
                        mandatory = false,
                        error = "Error",
                        warning = null,
                        legendColor = null,
                    ),
                    2 to TableCell(
                        id = "cell",
                        row = 1,
                        column = 2,
                        content = TableCellContent.Text(""),
                        editable = true,
                        mandatory = true,
                        error = null,
                        warning = null,
                        legendColor = null,
                    ),
                ),
                maxLines = 3,
            ),
            TableRowModel(
                rowHeaders = listOf(
                    RowHeader(
                        id = "2",
                        title = "Label 2",
                        row = 2,
                        column = 0,
                    ),
                ),
                values = mapOf(
                    0 to TableCell(
                        id = "cell",
                        row = 2,
                        column = 0,
                        content = TableCellContent.Text("5"),
                        editable = true,
                        mandatory = false,
                        error = null,
                        warning = "Warning",
                        legendColor = null,
                    ),
                    1 to TableCell(
                        id = "cell",
                        row = 2,
                        column = 1,
                        content = TableCellContent.Text("6"),
                        editable = true,
                        mandatory = false,
                        error = null,
                        warning = null,
                        legendColor = -112,
                    ),
                    2 to TableCell(
                        id = "cell",
                        row = 2,
                        column = 2,
                        content = TableCellContent.Text("11"),
                        editable = false,
                        mandatory = false,
                        error = null,
                        warning = null,
                        legendColor = null,
                    ),
                ),
                maxLines = 3,
            ),
        ),
    )
}
