package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.isFocused
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.junit.Rule
import org.junit.Test

class InputCheckBoxTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldDisplayInputCheckBoxCorrectly() {
        rule.setContent {
            val checkBoxDataList = remember {
                mutableStateListOf(
                    CheckBoxData("0", checked = true, enabled = true, textInput = "Option 1"),
                    CheckBoxData("1", checked = false, enabled = false, textInput = "Option 2"),
                    CheckBoxData("2", checked = false, enabled = true, textInput = "Option 3"),
                )
            }

            InputCheckBox(
                title = "Label",
                checkBoxData = checkBoxDataList,
                modifier = Modifier.testTag("INPUT_CHECK_BOX"),
                onItemChange = { checkBoxData ->
                    val index = checkBoxDataList.withIndex().first { it.value.uid == checkBoxData.uid }.index
                    checkBoxDataList[index] = checkBoxData.copy(checked = !checkBoxData.checked)
                },
                onClearSelection = { checkBoxDataList.replaceAll { it.copy(checked = false) } },
            )
        }
        rule.onNodeWithTag("INPUT_CHECK_BOX").assertExists()
        rule.onNodeWithTag("INPUT_CHECK_BOX_LEGEND").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_CHECK_BOX_SUPPORTING_TEXT").assertDoesNotExist()
    }

    @Test
    fun shouldAllowUserSelectionWhenEnabled() {
        rule.setContent {
            val checkBoxDataList = remember {
                mutableStateListOf(
                    CheckBoxData("0", checked = true, enabled = true, textInput = "Option 1"),
                    CheckBoxData("1", checked = false, enabled = true, textInput = "Option 2"),
                    CheckBoxData("2", checked = false, enabled = true, textInput = "Option 3"),
                )
            }

            InputCheckBox(
                title = "Label",
                checkBoxData = checkBoxDataList,
                modifier = Modifier.testTag("INPUT_CHECK_BOX"),
                onItemChange = { checkBoxData ->
                    val index = checkBoxDataList.withIndex().first { it.value.uid == checkBoxData.uid }.index
                    checkBoxDataList[index] = checkBoxData.copy(checked = !checkBoxData.checked)
                },
                onClearSelection = { checkBoxDataList.replaceAll { it.copy(checked = false) } },
            )
        }
        rule.onNodeWithTag("INPUT_CHECK_BOX").assertExists()
        rule.onNodeWithTag("CHECK_BOX_1").performClick()
        rule.onNodeWithTag("CHECK_BOX_1").assertIsFocused()
    }

    @Test
    fun shouldNotAllowUserSelectionWhenDisabled() {
        rule.setContent {
            val checkBoxDataList = remember {
                mutableStateListOf(
                    CheckBoxData("0", checked = true, enabled = true, textInput = "Option 1"),
                    CheckBoxData("1", checked = false, enabled = false, textInput = "Option 2"),
                    CheckBoxData("2", checked = false, enabled = true, textInput = "Option 3"),
                )
            }

            InputCheckBox(
                title = "Label",
                checkBoxData = checkBoxDataList,
                modifier = Modifier.testTag("INPUT_CHECK_BOX"),
                state = InputShellState.DISABLED,
                onItemChange = { },
                onClearSelection = { },
            )
        }
        rule.onNodeWithTag("INPUT_CHECK_BOX").assertExists()
        rule.onNodeWithTag("CHECK_BOX_1").performClick()
        rule.onNodeWithTag("CHECK_BOX_1").assertIsNotEnabled()
        rule.onNodeWithTag("CHECK_BOX_1").assert(!isFocused())
    }

    @Test
    fun shouldShowClearButtonWhenItemChecked() {
        rule.setContent {
            val checkBoxDataList = remember {
                mutableStateListOf(
                    CheckBoxData("0", checked = true, enabled = true, textInput = "Option 1"),
                    CheckBoxData("1", checked = false, enabled = false, textInput = "Option 2"),
                    CheckBoxData("2", checked = false, enabled = true, textInput = "Option 3"),
                )
            }

            InputCheckBox(
                title = "Label",
                checkBoxData = checkBoxDataList,
                modifier = Modifier.testTag("INPUT_CHECK_BOX"),
                onItemChange = { checkBoxData ->
                    val index = checkBoxDataList.withIndex().first { it.value.uid == checkBoxData.uid }.index
                    checkBoxDataList[index] = checkBoxData.copy(checked = !checkBoxData.checked)
                },
                onClearSelection = { checkBoxDataList.replaceAll { it.copy(checked = false) } },
            )
        }
        rule.onNodeWithTag("INPUT_CHECK_BOX").assertExists()
        rule.onNodeWithTag("INPUT_CHECK_BOX_CLEAR_BUTTON").assertExists()
    }

    @Test
    fun shouldHideClearButtonWhenNoItemChecked() {
        rule.setContent {
            val checkBoxDataList = remember {
                mutableStateListOf(
                    CheckBoxData("0", checked = false, enabled = true, textInput = "Option 1"),
                    CheckBoxData("1", checked = false, enabled = false, textInput = "Option 2"),
                    CheckBoxData("2", checked = false, enabled = true, textInput = "Option 3"),
                )
            }

            InputCheckBox(
                title = "Label",
                checkBoxData = checkBoxDataList,
                modifier = Modifier.testTag("INPUT_CHECK_BOX"),
                onItemChange = { checkBoxData ->
                    val index = checkBoxDataList.withIndex().first { it.value.uid == checkBoxData.uid }.index
                    checkBoxDataList[index] = checkBoxData.copy(checked = !checkBoxData.checked)
                },
                onClearSelection = { checkBoxDataList.replaceAll { it.copy(checked = false) } },
            )
        }
        rule.onNodeWithTag("INPUT_CHECK_BOX").assertExists()
        rule.onNodeWithTag("INPUT_CHECK_BOX_CLEAR_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldHideClearButtonWhenDisabled() {
        rule.setContent {
            val checkBoxDataList = remember {
                mutableStateListOf(
                    CheckBoxData("0", checked = true, enabled = true, textInput = "Option 1"),
                    CheckBoxData("1", checked = false, enabled = false, textInput = "Option 2"),
                    CheckBoxData("2", checked = false, enabled = true, textInput = "Option 3"),
                )
            }

            InputCheckBox(
                title = "Label",
                checkBoxData = checkBoxDataList,
                modifier = Modifier.testTag("INPUT_CHECK_BOX"),
                state = InputShellState.DISABLED,
                onItemChange = { },
                onClearSelection = { },
            )
        }
        rule.onNodeWithTag("INPUT_CHECK_BOX").assertExists()
        rule.onNodeWithTag("INPUT_CHECK_BOX_CLEAR_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldClearSelectionWhenClearButtonIsClickedAndHideClearButton() {
        rule.setContent {
            val checkBoxDataList = remember {
                mutableStateListOf(
                    CheckBoxData("0", checked = true, enabled = true, textInput = "Option 1"),
                    CheckBoxData("1", checked = true, enabled = true, textInput = "Option 2"),
                    CheckBoxData("2", checked = false, enabled = true, textInput = "Option 3"),
                )
            }

            InputCheckBox(
                title = "Label",
                checkBoxData = checkBoxDataList,
                modifier = Modifier.testTag("INPUT_CHECK_BOX"),
                onItemChange = { checkBoxData ->
                    val index = checkBoxDataList.withIndex().first { it.value.uid == checkBoxData.uid }.index
                    checkBoxDataList[index] = checkBoxData.copy(checked = !checkBoxData.checked)
                },
                onClearSelection = { checkBoxDataList.replaceAll { it.copy(checked = false) } },
            )
        }
        rule.onNodeWithTag("INPUT_CHECK_BOX").assertExists()
        rule.onNodeWithTag("INPUT_CHECK_BOX_CLEAR_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_CHECK_BOX_CLEAR_BUTTON").performClick()
        rule.onNodeWithTag("CHECK_BOX_0").assertIsNotFocused()
        rule.onNodeWithTag("CHECK_BOX_1").assertIsNotFocused()
        rule.onNodeWithTag("INPUT_CHECK_BOX_CLEAR_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldShowLegendCorrectly() {
        rule.setContent {
            val checkBoxDataList = remember {
                mutableStateListOf(
                    CheckBoxData("0", checked = true, enabled = true, textInput = "Option 1"),
                    CheckBoxData("1", checked = true, enabled = false, textInput = "Option 2"),
                    CheckBoxData("2", checked = false, enabled = true, textInput = "Option 3"),
                )
            }

            InputCheckBox(
                title = "Label",
                checkBoxData = checkBoxDataList,
                legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
                onItemChange = { },
                onClearSelection = { },
            )
        }

        rule.onNodeWithTag("INPUT_CHECK_BOX").assertExists()
        rule.onNodeWithTag("INPUT_CHECK_BOX_LEGEND").assertExists()
        rule.onNodeWithTag("INPUT_CHECK_BOX_LEGEND").assertHasClickAction()
    }

    @Test
    fun shouldShowSupportingTextCorrectly() {
        rule.setContent {
            val checkBoxDataList = remember {
                mutableStateListOf(
                    CheckBoxData("0", checked = true, enabled = true, textInput = "Option 1"),
                    CheckBoxData("1", checked = true, enabled = false, textInput = "Option 2"),
                    CheckBoxData("2", checked = false, enabled = true, textInput = "Option 3"),
                )
            }

            InputCheckBox(
                title = "Label",
                checkBoxData = checkBoxDataList,
                supportingText = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
                onItemChange = { },
                onClearSelection = { },
            )
        }
        rule.onNodeWithTag("INPUT_CHECK_BOX").assertExists()
        rule.onNodeWithTag("INPUT_CHECK_BOX_SUPPORTING_TEXT").assertExists()
    }
}
