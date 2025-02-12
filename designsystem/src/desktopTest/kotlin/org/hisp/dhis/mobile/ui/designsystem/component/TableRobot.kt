package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import org.hisp.dhis.mobile.ui.designsystem.component.menu.MenuItemTestTags.MENU_ITEM_TEXT
import org.hisp.dhis.mobile.ui.designsystem.component.table.actions.TableInteractions
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableModel
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.DataTable
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.LocalTableSelection
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableColors
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableConfiguration
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableSelection
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableTheme
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.compositions.LocalInteraction
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.CELL_ERROR_UNDERLINE_TEST_TAG
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.CELL_TEST_TAG
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.CELL_VALUE_TEST_TAG
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.ColumnBackground
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.ColumnIndexHeader
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.HEADER_CELL
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.HasError
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.INFO_ICON
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.InfoIconId
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.IsBlocked
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.MANDATORY_ICON_TEST_TAG
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.RowBackground
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.RowIndex
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.RowIndexHeader
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.TableId
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.TableIdColumnHeader
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

fun tableRobot(
    composeTestRule: ComposeContentTestRule,
    tableRobot: TableRobot.() -> Unit,
) {
    TableRobot(composeTestRule).apply {
        tableRobot()
    }
}

class TableRobot(
    private val composeTestRule: ComposeContentTestRule,
) {

    fun initTable(
        table: List<TableModel>,
        tableColors: TableColors = TableColors(),
    ) {
        composeTestRule.setContent {
            var tableSelection by remember {
                mutableStateOf<TableSelection>(TableSelection.Unselected())
            }
            TableTheme(
                tableColors = tableColors.copy(primary = SurfaceColor.Primary),
                tableConfiguration = TableConfiguration(headerActionsEnabled = false),
            ) {
                val iteractions = object : TableInteractions {
                    override fun onSelectionChange(newTableSelection: TableSelection) {
                        tableSelection = newTableSelection
                    }
                }
                CompositionLocalProvider(
                    LocalTableSelection provides tableSelection,
                    LocalInteraction provides iteractions,
                ) {
                    DataTable(
                        tableList = table,
                    )
                }
            }
        }
    }

    fun assertInfoIcon(tableId: String, rowIndex: Int) {
        composeTestRule.onNode(
            SemanticsMatcher.expectValue(TableId, tableId)
                .and(SemanticsMatcher.expectValue(RowIndex, rowIndex))
                .and(SemanticsMatcher.expectValue(InfoIconId, INFO_ICON)),
        ).assertExists()
    }

    fun assertRowHeaderBackgroundChangeToPrimary(
        tableId: String,
        rowIndex: Int,
    ) {
        composeTestRule.onNode(
            SemanticsMatcher.expectValue(TableId, tableId)
                .and(SemanticsMatcher.expectValue(RowIndex, rowIndex))
                .and(SemanticsMatcher.expectValue(RowBackground, SurfaceColor.Primary)),
        ).assertExists()
    }

    fun assertColumnHeaderBackgroundColor(
        tableId: String,
        rowIndex: Int,
        columnIndex: Int,
        color: Color,
    ) {
        composeTestRule.onNode(
            SemanticsMatcher.expectValue(TableIdColumnHeader, tableId)
                .and(SemanticsMatcher.expectValue(RowIndexHeader, rowIndex))
                .and(SemanticsMatcher.expectValue(ColumnIndexHeader, columnIndex))
                .and(SemanticsMatcher.expectValue(ColumnBackground, color)),
        ).assertExists()
    }

    fun clickOnCell(tableId: String, rowIndex: Int, columnIndex: Int) {
        composeTestRule.onNodeWithTag("$tableId${CELL_TEST_TAG}$rowIndex$columnIndex", true)
            .performScrollTo()
            .performClick()
    }

    fun clickOnHeaderElement(tableId: String, rowIndex: Int, columnIndex: Int) {
        composeTestRule.onNodeWithTag("$HEADER_CELL$tableId$rowIndex$columnIndex", true)
            .performClick()
        composeTestRule.waitForIdle()
    }

    fun clickOnRowHeader(tableId: String, rowIndex: Int) {
        composeTestRule.onNodeWithTag("$tableId$rowIndex").performClick()
        composeTestRule.waitForIdle()
    }

    fun assertRowHeaderText(tableId: String, text: String, rowIndex: Int) {
        composeTestRule.onNodeWithTag("${tableId}$rowIndex").assertTextEquals(text)
    }

    fun assertRowHeaderIsClickable(tableId: String, rowIndex: Int) {
        composeTestRule.onNodeWithTag("$tableId$rowIndex").assertIsEnabled()
    }

    fun assertCellHasMandatoryIcon(tableId: String, rowIndex: Int, columnIndex: Int) {
        composeTestRule.onNode(
            hasParent(hasTestTag("$tableId${CELL_TEST_TAG}$rowIndex$columnIndex"))
                and
                hasTestTag(MANDATORY_ICON_TEST_TAG),
            true,
        )
            .assertIsDisplayed()
    }

    fun assertCellBlockedCell(tableId: String, rowIndex: Int, columnIndex: Int) {
        composeTestRule
            .onNode(
                hasTestTag("$tableId${CELL_TEST_TAG}$rowIndex$columnIndex")
                    and
                    SemanticsMatcher.expectValue(IsBlocked, true),
                true,
            )
            .assertIsDisplayed()
    }

    fun assertUnselectedCellErrorStyle(tableId: String, rowIndex: Int, columnIndex: Int) {
        composeTestRule.onNode(
            hasTestTag("$tableId${CELL_TEST_TAG}$rowIndex$columnIndex")
                and
                SemanticsMatcher.expectValue(HasError, true),
            true,
        ).assertIsDisplayed()
        composeTestRule.onNodeWithTag(CELL_ERROR_UNDERLINE_TEST_TAG, true).assertIsDisplayed()
    }

    fun assertSelectedCellErrorStyle(tableId: String, rowIndex: Int, columnIndex: Int) {
        composeTestRule.onNode(
            hasTestTag("$tableId${CELL_TEST_TAG}$rowIndex$columnIndex")
                and
                SemanticsMatcher.expectValue(HasError, true),
            true,
        ).assertIsDisplayed()
    }

    fun selectDropdownItem(text: String) {
        composeTestRule.onNode(
            hasTestTag(MENU_ITEM_TEXT)
                and
                hasText(text),
            true,
        ).performClick()
    }

    fun assertCellHasValue(tableId: String, rowIndex: Int, columnIndex: Int, value: String) {
        composeTestRule.onNode(
            hasParent(hasTestTag("$tableId${CELL_TEST_TAG}$rowIndex$columnIndex"))
                and
                hasTestTag(CELL_VALUE_TEST_TAG),
            true,
        ).assertTextEquals(value)
    }
}
