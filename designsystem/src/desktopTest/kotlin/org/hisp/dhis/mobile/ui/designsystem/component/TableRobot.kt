package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableModel
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.DataTable
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.CellSelected
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.ColumnBackground
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.ColumnIndexHeader
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.HasError
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.IsBlocked
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.MANDATORY_ICON_TEST_TAG
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.RowBackground
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.RowIndex
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.RowIndexHeader
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.TableId
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.TableIdColumnHeader
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.cellTestTag
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.headerTestTag
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.rowHeaderTestTag
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
    fun initTable(table: List<TableModel>) {
        composeTestRule.setContent {
            DataTable(tableList = table)
        }
    }

    fun assertRowHeaderBackgroundChangeToPrimary(
        tableId: String,
        rowIndex: Int,
    ) {
        composeTestRule
            .onNode(
                SemanticsMatcher
                    .expectValue(TableId, tableId)
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
        composeTestRule
            .onNode(
                SemanticsMatcher
                    .expectValue(TableIdColumnHeader, tableId)
                    .and(SemanticsMatcher.expectValue(RowIndexHeader, rowIndex))
                    .and(SemanticsMatcher.expectValue(ColumnIndexHeader, columnIndex))
                    .and(SemanticsMatcher.expectValue(ColumnBackground, color)),
            ).assertExists()
    }

    fun clickOnCell(
        tableId: String,
        cellId: String,
    ) {
        composeTestRule
            .onNodeWithTag(cellTestTag(tableId, cellId), true)
            .performScrollTo()
            .performClick()
    }

    fun clickOnHeaderElement(
        tableId: String,
        rowIndex: Int,
        columnIndex: Int,
    ) {
        composeTestRule
            .onNodeWithTag(
                headerTestTag(tableId, rowIndex, columnIndex),
                true,
            ).performClick()
        composeTestRule.waitForIdle()
    }

    fun clickOnRowHeader(
        tableId: String,
        rowHeaderId: String,
    ) {
        composeTestRule.onNodeWithTag(rowHeaderTestTag(tableId, rowHeaderId)).performClick()
        composeTestRule.waitForIdle()
    }

    fun assertRowHeaderText(
        tableId: String,
        text: String,
        rowHeaderId: String,
    ) {
        composeTestRule.onNodeWithTag(rowHeaderTestTag(tableId, rowHeaderId)).assertTextEquals(text)
    }

    fun assertRowHeaderIsClickable(
        tableId: String,
        rowHeaderId: String,
    ) {
        composeTestRule.onNodeWithTag(rowHeaderTestTag(tableId, rowHeaderId)).assertIsEnabled()
    }

    fun assertCellHasMandatoryIcon(
        tableId: String,
        cellId: String,
    ) {
        composeTestRule
            .onNode(
                hasParent(hasTestTag(cellTestTag(tableId, cellId)))
                    and
                    hasTestTag(MANDATORY_ICON_TEST_TAG),
                true,
            ).assertIsDisplayed()
    }

    fun assertCellBlockedCell(
        tableId: String,
        cellId: String,
    ) {
        composeTestRule
            .onNode(
                hasTestTag(cellTestTag(tableId, cellId))
                    and
                    SemanticsMatcher.expectValue(IsBlocked, true),
                true,
            ).assertIsDisplayed()
    }

    fun assertCellIsSelected(
        tableId: String,
        cellId: String,
    ) {
        composeTestRule
            .onNode(
                hasTestTag(cellTestTag(tableId, cellId))
                    and
                    SemanticsMatcher.expectValue(CellSelected, true)
                    and
                    SemanticsMatcher.expectValue(HasError, false)
                    and
                    SemanticsMatcher.expectValue(RowBackground, SurfaceColor.SurfaceBright),
                true,
            ).assertIsDisplayed()
    }

    fun assertCellErrorStyle(
        tableId: String,
        cellId: String,
    ) {
        composeTestRule
            .onNode(
                hasTestTag(cellTestTag(tableId, cellId))
                    and
                    SemanticsMatcher.expectValue(HasError, true)
                    and
                    SemanticsMatcher.expectValue(RowBackground, SurfaceColor.ErrorContainer),
                true,
            ).assertIsDisplayed()
    }

    fun assertCellWarningStyle(
        tableId: String,
        cellId: String,
    ) {
        composeTestRule
            .onNode(
                hasTestTag(cellTestTag(tableId, cellId))
                    and
                    SemanticsMatcher.expectValue(HasError, true)
                    and
                    SemanticsMatcher.expectValue(RowBackground, SurfaceColor.WarningContainer),
                true,
            ).assertIsDisplayed()
    }

    fun assertCellDisabledStyle(
        tableId: String,
        cellId: String,
    ) {
        composeTestRule
            .onNode(
                hasTestTag(cellTestTag(tableId, cellId))
                    and
                    SemanticsMatcher.expectValue(IsBlocked, true)
                    and
                    SemanticsMatcher.expectValue(RowBackground, SurfaceColor.DisabledSurfaceBright),
                true,
            ).assertIsDisplayed()
    }
}
