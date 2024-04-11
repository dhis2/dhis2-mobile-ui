package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.isFocused
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.junit.Rule
import org.junit.Test

class InputYesOnlyCheckBoxTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldDisplayInputYesOnlyCheckBoxCorrectly() {
        rule.setContent {
            val checkboxData by rememberSaveable {
                mutableStateOf(CheckBoxData(uid = "0", checked = false, enabled = true, textInput = "Label"))
            }
            InputYesOnlyCheckBox(
                checkBoxData = checkboxData,
                modifier = Modifier.testTag("INPUT_YES_ONLY_CHECKBOX"),
                onClick = {
                },
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_YES_ONLY_CHECKBOX").assertExists()
        rule.onNodeWithTag("INPUT_YES_ONLY_CHECKBOX_LEGEND").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_YES_ONLY_CHECKBOX_SUPPORTING_TEXT").assertDoesNotExist()
    }

    @Test
    fun shouldAllowUserSelectionWhenEnabled() {
        rule.setContent {
            var checkboxData by rememberSaveable {
                mutableStateOf(CheckBoxData(uid = "0", checked = false, enabled = true, textInput = "Label"))
            }
            InputYesOnlyCheckBox(
                checkBoxData = checkboxData,
                modifier = Modifier.testTag("INPUT_YES_ONLY_CHECKBOX"),
                onClick = {
                    checkboxData = checkboxData.copy(checked = !checkboxData.checked)
                },
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_YES_ONLY_CHECKBOX").assertExists()
        rule.onNodeWithTag("CHECK_BOX_0").performClick()
        rule.onNodeWithTag("CHECK_BOX_0").assertIsEnabled()
    }

    @Test
    fun shouldNotAllowUserSelectionWhenDisabled() {
        rule.setContent {
            var checkboxData by rememberSaveable {
                mutableStateOf(CheckBoxData(uid = "0", checked = false, enabled = false, textInput = "Label"))
            }
            InputYesOnlyCheckBox(
                checkBoxData = checkboxData,
                modifier = Modifier.testTag("INPUT_YES_ONLY_CHECKBOX"),
                state = InputShellState.DISABLED,
                onClick = {
                    checkboxData = checkboxData.copy(checked = !checkboxData.checked)
                },
            )
        }
        rule.onNodeWithTag("INPUT_YES_ONLY_CHECKBOX").assertExists()
        rule.onNodeWithTag("CHECK_BOX_0").performClick()
        rule.onNodeWithTag("CHECK_BOX_0").assertIsNotEnabled()
        rule.onNodeWithTag("CHECK_BOX_0").assert(!isFocused())
    }

    @Test
    fun shouldShowLegendCorrectly() {
        rule.setContent {
            var checkboxData by rememberSaveable {
                mutableStateOf(CheckBoxData(uid = "0", checked = false, enabled = true, textInput = "Label"))
            }
            InputYesOnlyCheckBox(
                checkBoxData = checkboxData,
                legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
                onClick = {
                    checkboxData = checkboxData.copy(enabled = !checkboxData.enabled)
                },
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_YES_ONLY_CHECKBOX").assertExists()
        rule.onNodeWithTag("INPUT_YES_ONLY_CHECKBOX_LEGEND").assertExists()
        rule.onNodeWithTag("INPUT_YES_ONLY_CHECKBOX_LEGEND").assertHasNoClickAction()
    }

    @Test
    fun shouldShowSupportingTextCorrectly() {
        rule.setContent {
            var checkboxData by rememberSaveable {
                mutableStateOf(CheckBoxData(uid = "0", checked = false, enabled = true, textInput = "Label"))
            }
            InputYesOnlyCheckBox(
                checkBoxData = checkboxData,
                supportingText = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
                onClick = {
                    checkboxData = checkboxData.copy(enabled = !checkboxData.enabled)
                },
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_YES_ONLY_CHECKBOX").assertExists()
        rule.onNodeWithTag("INPUT_YES_ONLY_CHECKBOX_SUPPORTING_TEXT").assertExists()
    }
}
