package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.junit.Rule
import org.junit.Test

class InputDropDownTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldDisplayInputDropDownCorrectly() {
        rule.setContent {
            InputDropDown(
                title = "Label",
                state = InputShellState.UNFOCUSED,
                onArrowDropDownButtonClicked = {},
                onResetButtonClicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_LEGEND").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_DROPDOWN_SUPPORTING_TEXT").assertDoesNotExist()
    }

    @Test
    fun shouldAllowDropDownSelectionWhenEnabled() {
        rule.setContent {
            InputDropDown(
                title = "Label",
                state = InputShellState.UNFOCUSED,
                onArrowDropDownButtonClicked = {},
                onResetButtonClicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_ARROW_BUTTON").assertIsEnabled()
        rule.onNodeWithTag("INPUT_DROPDOWN_ARROW_BUTTON")
    }

    @Test
    fun shouldNotAllowDropDownSelectionWhenDisabled() {
        rule.setContent {
            InputDropDown(
                title = "Label",
                state = InputShellState.DISABLED,
                onArrowDropDownButtonClicked = {},
                onResetButtonClicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_ARROW_BUTTON").assertIsNotEnabled()
    }

    @Test
    fun shouldShowResetButtonWhenItemIsSelected() {
        rule.setContent {
            InputDropDown(
                title = "Label",
                selectedItem = "Input",
                state = InputShellState.UNFOCUSED,
                onArrowDropDownButtonClicked = {},
                onResetButtonClicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_RESET_BUTTON").assertExists()
    }

    @Test
    fun shouldHideResetButtonWhenNoItemIsSelected() {
        rule.setContent {
            InputDropDown(
                title = "Label",
                state = InputShellState.UNFOCUSED,
                onArrowDropDownButtonClicked = {},
                onResetButtonClicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_RESET_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldHideResetButtonWhenDisabled() {
        rule.setContent {
            InputDropDown(
                title = "Label",
                selectedItem = "Option 1",
                state = InputShellState.DISABLED,
                onArrowDropDownButtonClicked = {},
                onResetButtonClicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_RESET_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldRemoveSelectedItemWhenResetButtonIsClickedAndHideResetButton() {
        rule.setContent {
            var itemSelected by rememberSaveable { mutableStateOf<String?>("Option 1") }

            InputDropDown(
                title = "Label",
                selectedItem = itemSelected,
                state = InputShellState.UNFOCUSED,
                onArrowDropDownButtonClicked = {},
                onResetButtonClicked = {
                    itemSelected = null
                },
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_RESET_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_RESET_BUTTON").performClick()
        rule.onNodeWithTag("INPUT_DROPDOWN_TEXT").assertTextEquals("")
        rule.onNodeWithTag("INPUT_DROPDOWN_RESET_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldShowLegendCorrectly() {
        rule.setContent {
            InputDropDown(
                title = "Label",
                legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
                state = InputShellState.UNFOCUSED,
                onArrowDropDownButtonClicked = {},
                onResetButtonClicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_LEGEND").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_LEGEND").assertHasClickAction()
    }

    @Test
    fun shouldShowSupportingTextCorrectly() {
        rule.setContent {
            InputDropDown(
                title = "Label",
                supportingTextData = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
                state = InputShellState.UNFOCUSED,
                onArrowDropDownButtonClicked = {},
                onResetButtonClicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_SUPPORTING_TEXT").assertExists()
    }
}
