package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.junit.Rule
import org.junit.Test

class InputYesNoFieldTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldDisplayInputYesNoFieldCorrectly() {
        rule.setContent {
            var selectedItem by remember {
                mutableStateOf<InputYesNoFieldValues?>(null)
            }
            InputYesNoField(
                title = "Label",
                modifier = Modifier.testTag("INPUT_YES_NO_FIELD"),
                itemSelected = selectedItem,
                onItemChange = {
                    selectedItem = it
                },
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_YES_NO_FIELD").assertExists()
        rule.onNodeWithTag("INPUT_YES_NO_FIELD_LEGEND").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_YES_NO_FIELD_SUPPORTING_TEXT").assertDoesNotExist()
    }

    @Test
    fun shouldAllowUserSelectionWhenEnabled() {
        rule.setContent {
            var selectedItem by remember {
                mutableStateOf<InputYesNoFieldValues?>(null)
            }
            InputYesNoField(
                title = "Label",
                modifier = Modifier.testTag("INPUT_YES_NO_FIELD"),
                itemSelected = selectedItem,
                onItemChange = {
                    selectedItem = it
                },
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_YES_NO_FIELD").assertExists()
        rule.onNodeWithTag("RADIO_BUTTON_Yes").performClick()
        rule.onNodeWithTag("RADIO_BUTTON_Yes").assertIsSelected()
    }

    @Test
    fun shouldNotAllowUserSelectionWhenDisabled() {
        rule.setContent {
            var selectedItem by remember {
                mutableStateOf<InputYesNoFieldValues?>(null)
            }
            InputYesNoField(
                title = "Label",
                modifier = Modifier.testTag("INPUT_YES_NO_FIELD"),
                state = InputShellState.DISABLED,
                itemSelected = selectedItem,
                onItemChange = {
                    selectedItem = it
                },
            )
        }
        rule.onNodeWithTag("INPUT_YES_NO_FIELD").assertExists()
        rule.onNodeWithTag("RADIO_BUTTON_Yes").performClick()
        rule.onNodeWithTag("RADIO_BUTTON_Yes").assertIsNotSelected()
    }

    @Test
    fun shouldShowClearButtonWhenItemSelected() {
        rule.setContent {
            var selectedItem by remember {
                mutableStateOf<InputYesNoFieldValues?>(InputYesNoFieldValues.YES)
            }
            InputYesNoField(
                title = "Label",
                modifier = Modifier.testTag("INPUT_YES_NO_FIELD"),
                itemSelected = selectedItem,
                onItemChange = {
                    selectedItem = it
                },
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_YES_NO_FIELD").assertExists()
        rule.onNodeWithTag("INPUT_YES_NO_FIELD_CLEAR_BUTTON").assertExists()
    }

    @Test
    fun shouldHideClearButtonWhenNoItemIsSelected() {
        rule.setContent {
            InputYesNoField(
                title = "Label",
                modifier = Modifier.testTag("INPUT_YES_NO_FIELD"),
                onItemChange = {
                },
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_YES_NO_FIELD").assertExists()
        rule.onNodeWithTag("INPUT_YES_NO_FIELD_CLEAR_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldHideClearButtonWhenDisabled() {
        rule.setContent {
            var selectedItem by remember {
                mutableStateOf<InputYesNoFieldValues?>(InputYesNoFieldValues.YES)
            }
            InputYesNoField(
                title = "Label",
                state = InputShellState.DISABLED,
                itemSelected = selectedItem,
                modifier = Modifier.testTag("INPUT_YES_NO_FIELD"),
                onItemChange = {
                    selectedItem = it
                },
            )
        }
        rule.onNodeWithTag("INPUT_YES_NO_FIELD").assertExists()
        rule.onNodeWithTag("INPUT_YES_NO_FIELD_CLEAR_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldClearSelectionWhenClearButtonIsClickedAndHideClearButton() {
        rule.setContent {
            var selectedItem by remember {
                mutableStateOf<InputYesNoFieldValues?>(InputYesNoFieldValues.YES)
            }
            InputYesNoField(
                title = "Label",
                modifier = Modifier.testTag("INPUT_YES_NO_FIELD"),
                itemSelected = selectedItem,
                onItemChange = {
                    selectedItem = it
                },
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_YES_NO_FIELD").assertExists()
        rule.onNodeWithTag("INPUT_YES_NO_FIELD_CLEAR_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_YES_NO_FIELD_CLEAR_BUTTON").performClick()
        rule.onNodeWithTag("RADIO_BUTTON_Yes").assertIsNotSelected()
        rule.onNodeWithTag("INPUT_YES_NO_FIELD_CLEAR_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldShowLegendCorrectly() {
        rule.setContent {
            InputYesNoField(
                title = "Label",
                legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
                onItemChange = {},
                state = InputShellState.UNFOCUSED,
            )
        }

        rule.onNodeWithTag("INPUT_YES_NO_FIELD").assertExists()
        rule.onNodeWithTag("INPUT_YES_NO_FIELD_LEGEND").assertExists()
        rule.onNodeWithTag("INPUT_YES_NO_FIELD_LEGEND").assertHasNoClickAction()
    }

    @Test
    fun shouldShowSupportingTextCorrectly() {
        rule.setContent {
            InputYesNoField(
                title = "Label",
                supportingText = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
                onItemChange = {},
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_YES_NO_FIELD").assertExists()
        rule.onNodeWithTag("INPUT_YES_NO_FIELD_SUPPORTING_TEXT").assertExists()
    }
}
