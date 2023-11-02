package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.junit.Rule
import org.junit.Test

class InputOrgUnitTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldDisplayInputOrgUnitCorrectly() {
        rule.setContent {
            InputOrgUnit(
                title = "Label",
                onOrgUnitActionCLicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_ORG_UNIT").assertExists()
        rule.onNodeWithTag("INPUT_ORG_UNIT_LEGEND").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_ORG_UNIT_SUPPORTING_TEXT").assertDoesNotExist()
    }

    @Test
    fun shouldDisplayDropdownButtonWhenEmpty() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("") }
            InputOrgUnit(
                title = "Label",
                inputText = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                onOrgUnitActionCLicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_ORG_UNIT").assertExists()
        rule.onNodeWithTag("ORG_UNIT_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_ORG_UNIT_DROPDOWN_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_ORG_UNIT_DROPDOWN_BUTTON").assertIsEnabled()
    }

    @Test
    fun shouldDisplayResetButtonWhenNotEmpty() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("Sample data") }
            InputOrgUnit(
                title = "Label",
                inputText = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                onOrgUnitActionCLicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_ORG_UNIT").assertExists()
        rule.onNodeWithTag("ORG_UNIT_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_ORG_UNIT_RESET_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_ORG_UNIT_RESET_BUTTON").assertIsEnabled()
    }

    @Test
    fun shouldBeClickableIfDisabled() {
        rule.setContent {
            InputOrgUnit(
                title = "Label",
                state = InputShellState.DISABLED,
                onOrgUnitActionCLicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_ORG_UNIT").assertExists()
        rule.onNodeWithTag("ORG_UNIT_BUTTON").assertExists()
        rule.onNodeWithTag("ORG_UNIT_BUTTON").assertIsNotEnabled()
        rule.onNodeWithTag("INPUT_ORG_UNIT_DROPDOWN_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_ORG_UNIT_DROPDOWN_BUTTON").assertIsNotEnabled()
    }

    @Test
    fun shouldDeleteContentWhenResetButtonIsClickedAndHideResetButton() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("PHC fake") }

            InputOrgUnit(
                title = "Label",
                inputText = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                onOrgUnitActionCLicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_ORG_UNIT").assertExists()
        rule.onNodeWithTag("INPUT_ORG_UNIT_RESET_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_ORG_UNIT_RESET_BUTTON").performClick()
        rule.onNodeWithTag("INPUT_ORG_UNIT_TEXT").assertTextEquals("")
        rule.onNodeWithTag("INPUT_ORG_UNIT_RESET_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldHideResetButtonWhenDisabled() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("PHC fake") }
            InputOrgUnit(
                title = "Label",
                state = InputShellState.DISABLED,
                inputText = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                onOrgUnitActionCLicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_ORG_UNIT").assertExists()
        rule.onNodeWithTag("INPUT_ORG_UNIT_RESET_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldShowLegendCorrectly() {
        rule.setContent {
            InputOrgUnit(
                title = "Label",
                inputText = "Input",
                legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
                onOrgUnitActionCLicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_ORG_UNIT").assertExists()
        rule.onNodeWithTag("INPUT_ORG_UNIT_LEGEND").assertExists()
        rule.onNodeWithTag("INPUT_ORG_UNIT_LEGEND").assertHasNoClickAction()
    }

    @Test
    fun shouldShowSupportingTextCorrectly() {
        rule.setContent {
            InputOrgUnit(
                title = "Label",
                inputText = "Input",
                supportingText = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
                onOrgUnitActionCLicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_ORG_UNIT").assertExists()
        rule.onNodeWithTag("INPUT_ORG_UNIT_SUPPORTING_TEXT").assertExists()
    }

    @Test
    fun shouldEnableOrgUnitActionButtonWhenEnabled() {
        rule.setContent {
            var inputValue by remember { mutableStateOf("") }

            InputOrgUnit(
                title = "Label",
                inputText = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                onOrgUnitActionCLicked = {},
            )
        }
        rule.onNodeWithTag("ORG_UNIT_BUTTON").assertIsEnabled()
    }

    @Test
    fun shouldDisableOrgUnitActionButtonOnWhenDisabled() {
        rule.setContent {
            var inputValue by remember { mutableStateOf("PHC fake") }

            InputOrgUnit(
                title = "Label",
                state = InputShellState.DISABLED,
                inputText = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                onOrgUnitActionCLicked = {},
            )
        }
        rule.onNodeWithTag("ORG_UNIT_BUTTON").assertIsNotEnabled()
    }
}
