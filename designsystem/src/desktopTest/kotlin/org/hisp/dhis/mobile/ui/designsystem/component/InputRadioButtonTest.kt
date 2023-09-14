package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.junit.Rule
import org.junit.Test

class InputRadioButtonTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldDisplayRadioInputCorrectly() {
        rule.setContent {
            val radioButtonData = listOf(
                RadioButtonData("0", selected = false, enabled = true, textInput = "Option 1"),
                RadioButtonData("1", selected = false, enabled = true, textInput = "Option 2"),
                RadioButtonData("2", selected = false, enabled = true, textInput = "Option 3"),
            )
            var selectedItem by remember {
                mutableStateOf<RadioButtonData?>(radioButtonData[0])
            }
            InputRadioButton(
                title = "Label",
                radioButtonData = radioButtonData,
                itemSelected = selectedItem,
                modifier = Modifier.testTag("RADIO_BUTTON_INPUT"),
                onItemChange = {
                    selectedItem = it
                },
            )
        }
        rule.onNodeWithTag("RADIO_BUTTON_INPUT").assertExists()
        rule.onNodeWithTag("RADIO_BUTTON_INPUT_LEGEND").assertDoesNotExist()
        rule.onNodeWithTag("RADIO_BUTTON_INPUT_SUPPORTING_TEXT").assertDoesNotExist()
    }

    @Test
    fun shouldAllowUserSelectionWhenEnabled() {
        rule.setContent {
            val radioButtonData = listOf(
                RadioButtonData("0", selected = false, enabled = true, textInput = "Option 1"),
                RadioButtonData("1", selected = false, enabled = true, textInput = "Option 2"),
                RadioButtonData("2", selected = false, enabled = true, textInput = "Option 3"),
            )
            var selectedItem by remember {
                mutableStateOf<RadioButtonData?>(radioButtonData[0])
            }
            InputRadioButton(
                title = "Label",
                radioButtonData = radioButtonData,
                modifier = Modifier.testTag("RADIO_BUTTON_INPUT"),
                itemSelected = selectedItem,
                onItemChange = {
                    selectedItem = it
                },
            )
        }
        rule.onNodeWithTag("RADIO_BUTTON_INPUT").assertExists()
        rule.onNodeWithTag("RADIO_BUTTON_1").performClick()
        rule.onNodeWithTag("RADIO_BUTTON_1").assertIsSelected()
    }

    @Test
    fun shouldNotAllowUserSelectionWhenDisabled() {
        rule.setContent {
            val radioButtonData = listOf(
                RadioButtonData("0", selected = false, enabled = true, textInput = "Option 1"),
                RadioButtonData("1", selected = false, enabled = true, textInput = "Option 2"),
                RadioButtonData("2", selected = false, enabled = true, textInput = "Option 3"),
            )
            var selectedItem by remember {
                mutableStateOf<RadioButtonData?>(radioButtonData[0])
            }
            InputRadioButton(
                title = "Label",
                radioButtonData = radioButtonData,
                modifier = Modifier.testTag("RADIO_BUTTON_INPUT"),
                state = InputShellState.DISABLED,
                itemSelected = selectedItem,
                onItemChange = {
                    selectedItem = it
                },
            )
        }
        rule.onNodeWithTag("RADIO_BUTTON_INPUT").assertExists()
        rule.onNodeWithTag("RADIO_BUTTON_1").performClick()
        rule.onNodeWithTag("RADIO_BUTTON_1").assertIsNotSelected()
    }

    @Test
    fun shouldShowClearButtonWhenItemSelected() {
        rule.setContent {
            val radioButtonData = listOf(
                RadioButtonData("0", selected = false, enabled = true, textInput = "Option 1"),
                RadioButtonData("1", selected = false, enabled = true, textInput = "Option 2"),
                RadioButtonData("2", selected = false, enabled = true, textInput = "Option 3"),
            )
            var selectedItem by remember {
                mutableStateOf<RadioButtonData?>(radioButtonData[0])
            }
            InputRadioButton(
                title = "Label",
                radioButtonData = radioButtonData,
                modifier = Modifier.testTag("RADIO_BUTTON_INPUT"),
                itemSelected = selectedItem,
                onItemChange = {
                    selectedItem = it
                },
            )
        }
        rule.onNodeWithTag("RADIO_BUTTON_INPUT").assertExists()
        rule.onNodeWithTag("RADIO_BUTTON_INPUT_CLEAR_BUTTON").assertExists()
    }

    @Test
    fun shouldHideClearButtonWhenNoItemIsSelected() {
        rule.setContent {
            val radioButtonData = listOf(
                RadioButtonData("0", selected = false, enabled = true, textInput = "Option 1"),
                RadioButtonData("1", selected = false, enabled = true, textInput = "Option 2"),
                RadioButtonData("2", selected = false, enabled = true, textInput = "Option 3"),
            )
            var selectedItem by remember {
                mutableStateOf<RadioButtonData?>(null)
            }
            InputRadioButton(
                title = "Label",
                radioButtonData = radioButtonData,
                modifier = Modifier.testTag("RADIO_BUTTON_INPUT"),
                onItemChange = {
                    selectedItem = it
                },
            )
        }
        rule.onNodeWithTag("RADIO_BUTTON_INPUT").assertExists()
        rule.onNodeWithTag("RADIO_BUTTON_INPUT_CLEAR_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldHideClearButtonWhenDisabled() {
        rule.setContent {
            val radioButtonData = listOf(
                RadioButtonData("0", selected = false, enabled = true, textInput = "Option 1"),
                RadioButtonData("1", selected = false, enabled = true, textInput = "Option 2"),
                RadioButtonData("2", selected = false, enabled = true, textInput = "Option 3"),
            )
            var selectedItem by remember {
                mutableStateOf<RadioButtonData?>(radioButtonData[0])
            }
            InputRadioButton(
                title = "Label",
                radioButtonData = radioButtonData,
                state = InputShellState.DISABLED,
                itemSelected = selectedItem,
                modifier = Modifier.testTag("RADIO_BUTTON_INPUT"),
                onItemChange = {
                    selectedItem = it
                },
            )
        }
        rule.onNodeWithTag("RADIO_BUTTON_INPUT").assertExists()
        rule.onNodeWithTag("RADIO_BUTTON_INPUT_CLEAR_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldClearSelectionWhenClearButtonIsClickedAndHideClearButton() {
        rule.setContent {
            val radioButtonData = listOf(
                RadioButtonData("0", selected = false, enabled = true, textInput = "Option 1"),
                RadioButtonData("1", selected = false, enabled = true, textInput = "Option 2"),
                RadioButtonData("2", selected = false, enabled = true, textInput = "Option 3"),
            )
            var selectedItem by remember {
                mutableStateOf<RadioButtonData?>(radioButtonData[0])
            }
            InputRadioButton(
                title = "Label",
                radioButtonData = radioButtonData,
                modifier = Modifier.testTag("RADIO_BUTTON_INPUT"),
                itemSelected = selectedItem,
                onItemChange = {
                    selectedItem = it
                },
            )
        }
        rule.onNodeWithTag("RADIO_BUTTON_INPUT").assertExists()
        rule.onNodeWithTag("RADIO_BUTTON_INPUT_CLEAR_BUTTON").assertExists()
        rule.onNodeWithTag("RADIO_BUTTON_INPUT_CLEAR_BUTTON").performClick()
        rule.onNodeWithTag("RADIO_BUTTON_0").assertIsNotSelected()
        rule.onNodeWithTag("RADIO_BUTTON_INPUT_CLEAR_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldShowLegendCorrectly() {
        rule.setContent {
            val radioButtonData = listOf(
                RadioButtonData("0", selected = false, enabled = true, textInput = "Option 1"),
                RadioButtonData("1", selected = false, enabled = true, textInput = "Option 2"),
                RadioButtonData("2", selected = false, enabled = true, textInput = "Option 3"),
            )
            InputRadioButton(
                title = "Label",
                radioButtonData = radioButtonData,
                legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
                onItemChange = {},
            )
        }

        rule.onNodeWithTag("RADIO_BUTTON_INPUT").assertExists()
        rule.onNodeWithTag("RADIO_BUTTON_INPUT_LEGEND").assertExists()
        rule.onNodeWithTag("RADIO_BUTTON_INPUT_LEGEND").assertHasClickAction()
    }

    @Test
    fun shouldShowSupportingTextCorrectly() {
        rule.setContent {
            val radioButtonData = listOf(
                RadioButtonData("0", selected = false, enabled = true, textInput = "Option 1"),
                RadioButtonData("1", selected = false, enabled = true, textInput = "Option 2"),
                RadioButtonData("2", selected = false, enabled = true, textInput = "Option 3"),
            )
            InputRadioButton(
                title = "Label",
                radioButtonData = radioButtonData,
                supportingText = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
                onItemChange = {},
            )
        }
        rule.onNodeWithTag("RADIO_BUTTON_INPUT").assertExists()
        rule.onNodeWithTag("RADIO_BUTTON_INPUT_SUPPORTING_TEXT").assertExists()
    }
}
