package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.ui.test.junit4.createComposeRule
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.hisp.dhis.mobile.designsystem.generated.resources.Res
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableModel
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableColors
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.junit.Rule
import org.junit.Test

/**
 * Test class for verifying the behavior of a table component in a Compose UI.
 */
@OptIn(ExperimentalResourceApi::class)
class TableTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val tableColors = TableColors()

    /**
     * Loads a table from a JSON file.
     * Initializes the table in the Compose UI.
     * Asserts that the number of columns in the first and second tables matches the expected
     * maximum number of columns (`MAX_COLUMNS`).
     */
    @Test
    fun shouldAllColumnsBuildProperly() = runBlocking {
        val table = loadTableFromJson("multi_header_table_list.json")
        tableRobot(composeTestRule) {
            initTable(table)
            val columnsFirstTable = table[0].tableHeaderModel.tableMaxColumns()
            val columnsSecondTable = table[1].tableHeaderModel.tableMaxColumns()

            assert(columnsFirstTable == MAX_COLUMNS)
            assert(columnsSecondTable == MAX_COLUMNS)
        }
    }

    /**
     * Loads a table from a JSON file.
     * Initializes the table in the Compose UI.
     * Simulates a click on a specific header element.
     * Asserts that the background color of the clicked header element changes to the expected
     * highlight color (`SurfaceColor.Primary`).
     */
    @Test
    fun shouldHighlightColumnHeaderWhenClickingOnHeader() = runBlocking {
        val table = loadTableFromJson("multi_header_table_list.json")
        tableRobot(composeTestRule) {
            initTable(table)
            val firstTableId = table[0].id

            clickOnHeaderElement(firstTableId, 2, 3)
            assertColumnHeaderBackgroundColor(firstTableId, 2, 3, SurfaceColor.Primary)
        }
    }

    /**
     * Loads a table from a JSON file.
     * Initializes the table in the Compose UI.
     * Simulates a click on a parent header element.
     * Asserts that the background color of the child and grandchild columns changes to the expected
     * highlight color (`tableColors.primaryLight`).
     * Asserts that the background color of non-highlighted columns alternates between two colors
     * (`tableColors.headerBackground1` and `tableColors.headerBackground2`).
     */
    @Test
    fun shouldHighlightChildrenColumnWhenSelectingParent() = runBlocking {
        val table = loadTableFromJson("multi_header_table_list.json")

        tableRobot(composeTestRule) {
            initTable(table)
            val firstTableId = table[0].id!!
            val sonColumnsHighlight = 3
            val grandsonColumnsHighlight = 12
            val maxColumnGrandSon = MAX_COLUMNS

            clickOnHeaderElement(firstTableId, 0, 0)
            for (i in 0 until sonColumnsHighlight) {
                assertColumnHeaderBackgroundColor(firstTableId, 1, i, SurfaceColor.ContainerHighest)
            }
            for (i in 0 until grandsonColumnsHighlight) {
                assertColumnHeaderBackgroundColor(firstTableId, 2, i, SurfaceColor.ContainerHighest)
            }
            val firstNonHighlightColumn = grandsonColumnsHighlight + 1
            for (i in firstNonHighlightColumn until maxColumnGrandSon) {
                if (i % 2 == 0) {
                    assertColumnHeaderBackgroundColor(
                        firstTableId,
                        2,
                        i,
                        tableColors.headerBackground1,
                    )
                } else {
                    assertColumnHeaderBackgroundColor(
                        firstTableId,
                        2,
                        i,
                        tableColors.headerBackground2,
                    )
                }
            }
        }
    }

    /**
     * Loads a table from a JSON file.
     * Initializes the table in the Compose UI.
     * Asserts that the background colors of the header columns alternate between two colors
     * (`tableColors.headerBackground1` and `tableColors.headerBackground2`) for the first, second, and third rows.
     */
    @Test
    fun shouldAssertHeaderColumnColorsEvenOdd() = runBlocking {
        val table = loadTableFromJson("multi_header_table_list.json")

        tableRobot(composeTestRule) {
            initTable(table)
            val firstTableId = table[0].id!!
            val secondRowHeaderChildren = 12
            val thirdRowHeaderChildren = 48

            // Assert first column rows
            assertColumnHeaderBackgroundColor(firstTableId, 0, 0, tableColors.headerBackground1)
            assertColumnHeaderBackgroundColor(firstTableId, 0, 1, tableColors.headerBackground2)
            assertColumnHeaderBackgroundColor(firstTableId, 0, 2, tableColors.headerBackground1)

            // Assert second column rows
            for (i in 0 until secondRowHeaderChildren) {
                if (i % 2 == 0) {
                    assertColumnHeaderBackgroundColor(
                        firstTableId,
                        1,
                        i,
                        tableColors.headerBackground1,
                    )
                } else {
                    assertColumnHeaderBackgroundColor(
                        firstTableId,
                        1,
                        i,
                        tableColors.headerBackground2,
                    )
                }
            }

            // Assert third column rows
            for (i in 0 until thirdRowHeaderChildren) {
                if (i % 2 == 0) {
                    assertColumnHeaderBackgroundColor(
                        firstTableId,
                        2,
                        i,
                        tableColors.headerBackground1,
                    )
                } else {
                    assertColumnHeaderBackgroundColor(
                        firstTableId,
                        2,
                        i,
                        tableColors.headerBackground2,
                    )
                }
            }
        }
    }

    /**
     * Loads a table from a JSON file.
     * Initializes the table in the Compose UI.
     * Simulates a click on a specific row element.
     * Asserts that the clicked row element changes is mandatory.
     */
    @Test
    fun shouldDisplayMandatoryIcon() = runBlocking {
        val table = loadTableFromJson("mandatory_cell_table_list.json")

        tableRobot(composeTestRule) {
            initTable(table)
            with(table.first()) {
                val cellId = tableRows.first().values[0]?.id!!
                clickOnCell(id, cellId)
                assertCellHasMandatoryIcon(id, cellId)
            }
        }
    }

    /**
     * Loads a table from a JSON file.
     * Initializes the table in the Compose UI.
     * Simulates a click on a specific row element.
     * Asserts that the clicked row element has an information icon.
     */
    @Test
    fun shouldShowInformationIcon() = runBlocking {
        val table = loadTableFromJson("multi_header_table_list.json")

        tableRobot(composeTestRule) {
            initTable(table)
            val firstTableId = table[0].id!!
            assertInfoIcon(firstTableId, 0)
        }
    }

    /**
     * Loads a table from a JSON file.
     * Initializes the table in the Compose UI.
     * Asserts that the number of rows in the first and second tables matches the expected number of rows.
     * Asserts that the header text of the first and second tables matches the expected header text.
     * Asserts that the header elements of the first and second tables are clickable.
     */
    @Test
    fun shouldAllRowsBuildProperly() = runBlocking {
        val tables = loadTableFromJson("multi_header_table_list.json")

        tableRobot(composeTestRule) {
            initTable(tables)

            assert(tables[0].tableRows.size == 3)
            assert(tables[1].tableRows.size == 5)

            tables.forEach { table ->
                table.tableRows.forEach { row ->
                    assertRowHeaderText(table.id, row.rowHeader.title, row.rowHeader.id)
                    assertRowHeaderIsClickable(table.id, row.rowHeader.id)
                }
            }
        }
    }

    @Test
    fun shouldClickOnFirstRowElementAndHighlightAllElements() = runBlocking {
        val table = loadTableFromJson("multi_header_table_list.json")

        tableRobot(composeTestRule) {
            initTable(table)

            with(table.first()) {
                composeTestRule.waitForIdle()
                clickOnRowHeader(id, tableRows.first().rowHeader.id)
                assertRowHeaderBackgroundChangeToPrimary(id, 0)
            }
        }
    }

    @Test
    fun shouldSelectCell() = runBlocking {
        val table = loadTableFromJson("mandatory_cell_table_list.json")

        tableRobot(composeTestRule) {
            initTable(table)
            val firstId = table.first().id!!
            clickOnCell(firstId, 0, 0)
            assertCellIsSelected(firstId, 0, 0)
        }
    }

    @Test
    fun shouldBlockClickAndSetCorrectColorIfNonEditable() = runBlocking {
        val tables = loadTableFromJson("multi_header_table_list.json")

        tableRobot(composeTestRule) {
            initTable(tables)

            with(tables.first()) {
                val cellId = tableRows.first().values[1]?.id!!
                clickOnCell(id, cellId)
                assertCellBlockedCell(id, cellId)
            }
        }
    }

    @Test
    fun shouldSetCorrectCellColors() = runBlocking {
        val table = loadTableFromJson("mandatory_cell_table_list.json")

        tableRobot(composeTestRule) {
            initTable(table)
            val firstId = table.first().id!!
            assertCellErrorStyle(firstId, 2, 0)
            assertCellDisabledStyle(firstId, 2, 1)
            assertCellWarningStyle(firstId, 2, 2)
        }
    }

    private suspend fun loadTableFromJson(fileName: String): List<TableModel> {
        val bytes = Res.readBytes("files/json/$fileName")
        val jsonString = bytes.decodeToString()
        return Json.decodeFromString(jsonString)
    }

    companion object {
        const val MAX_COLUMNS = 48
    }
}
