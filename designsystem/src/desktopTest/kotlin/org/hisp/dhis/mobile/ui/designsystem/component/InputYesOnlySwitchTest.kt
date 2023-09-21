package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.junit.Rule
import org.junit.Test

class InputYesOnlySwitchTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldDisplayInputYesOnlySwitchCorrectly() {
        rule.setContent {
            var selectedItem by remember {
                mutableStateOf(false)
            }
            InputYesOnlySwitch(
                title = "Label",
                isChecked = selectedItem,
                modifier = Modifier.testTag("INPUT_YES_ONLY_SWITCH"),
                onClick = {
                    selectedItem = it
                },
            )
        }
        rule.onNodeWithTag("INPUT_YES_ONLY_SWITCH").assertExists()
        rule.onNodeWithTag("INPUT_YES_ONLY_SWITCH_LEGEND").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_YES_ONLY_SWITCH_SUPPORTING_TEXT").assertDoesNotExist()
    }

    @Test
    fun shouldAllowUserSelectionWhenEnabled() {
        rule.setContent {
            var selectedItem by remember {
                mutableStateOf(false)
            }
            InputYesOnlySwitch(
                title = "Label",
                isChecked = selectedItem,
                modifier = Modifier.testTag("INPUT_YES_ONLY_SWITCH"),
                onClick = {
                    selectedItem = it
                },
            )
        }
        rule.onNodeWithTag("INPUT_YES_ONLY_SWITCH").assertExists()
        rule.onNodeWithTag("SWITCH").performClick()
        rule.onNodeWithTag("SWITCH").assertIsFocused()
    }

    @Test
    fun shouldNotAllowUserSelectionWhenDisabled() {
        rule.setContent {
            var selectedItem by remember {
                mutableStateOf(false)
            }
            InputYesOnlySwitch(
                title = "Label",
                isChecked = selectedItem,
                state = InputShellState.DISABLED,
                onClick = {
                    selectedItem = it
                },
            )
        }
        rule.onNodeWithTag("INPUT_YES_ONLY_SWITCH").assertExists()
        rule.onNodeWithTag("SWITCH").performClick()
        rule.onNodeWithTag("SWITCH").assertIsNotEnabled()
    }

    @Test
    fun shouldShowLegendCorrectly() {
        rule.setContent {
            var selectedItem by remember {
                mutableStateOf(false)
            }
            InputYesOnlySwitch(
                title = "Label",
                isChecked = selectedItem,
                legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
                onClick = {
                    selectedItem = it
                },
            )
        }

        rule.onNodeWithTag("INPUT_YES_ONLY_SWITCH").assertExists()
        rule.onNodeWithTag("INPUT_YES_ONLY_SWITCH_LEGEND").assertExists()
        rule.onNodeWithTag("INPUT_YES_ONLY_SWITCH_LEGEND").assertHasClickAction()
    }

    @Test
    fun shouldShowSupportingTextCorrectly() {
        rule.setContent {
            var selectedItem by remember {
                mutableStateOf(false)
            }
            InputYesOnlySwitch(
                title = "Label",
                isChecked = selectedItem,
                supportingText = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
                onClick = {
                    selectedItem = it
                },
            )
        }
        rule.onNodeWithTag("INPUT_YES_ONLY_SWITCH").assertExists()
        rule.onNodeWithTag("INPUT_YES_ONLY_SWITCH_SUPPORTING_TEXT").assertExists()
    }
}
