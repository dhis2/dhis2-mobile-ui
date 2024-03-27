package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.model.InputUserModel
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.junit.Rule
import org.junit.Test

class InputUserTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldDisplayInputUserCorrectly() {
        rule.setContent {
            InputUser(
                InputUserModel(
                    title = "Label",
                    state = InputShellState.UNFOCUSED,

                ),

            )
        }
        rule.onNodeWithTag(InputUserModel.MAIN).assertExists()
        rule.onNodeWithTag(InputUserModel.LEGEND).assertDoesNotExist()
        rule.onNodeWithTag(InputUserModel.SUPPORTING_TEXT).assertDoesNotExist()
    }

    @Test
    fun shouldAllowUserInputWhenEnabled() {
        rule.setContent {
            var inputValue by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
            InputUser(
                InputUserModel(
                    title = "Label",
                    inputTextFieldValue = inputValue,
                    onValueChanged = {
                        inputValue = it ?: TextFieldValue()
                    },
                    state = InputShellState.UNFOCUSED,
                ),

            )
        }
        rule.onNodeWithTag(InputUserModel.MAIN).assertExists()
        rule.onNodeWithTag(InputUserModel.TEXT_FIELD).performTextInput("User")
        rule.onNodeWithTag(InputUserModel.TEXT_FIELD).assert(hasText("User"))
    }

    @Test
    fun shouldNotAllowUserInputWhenDisabled() {
        rule.setContent {
            InputUser(
                InputUserModel(
                    title = "Label",
                    state = InputShellState.DISABLED,
                ),

            )
        }
        rule.onNodeWithTag(InputUserModel.MAIN).assertExists()
        rule.onNodeWithTag(InputUserModel.TEXT_FIELD).assertIsNotEnabled()
    }

    @Test
    fun shouldShowResetButtonWhenTextFieldHasContent() {
        rule.setContent {
            var inputValue by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
            InputUser(
                InputUserModel(
                    title = "Label",
                    inputTextFieldValue = inputValue,
                    onValueChanged = {
                        inputValue = it ?: TextFieldValue()
                    },
                    state = InputShellState.UNFOCUSED,
                ),

            )
        }
        rule.onNodeWithTag(InputUserModel.MAIN).assertExists()
        rule.onNodeWithTag(InputUserModel.TEXT_FIELD).assertExists()
        rule.onNodeWithTag(InputUserModel.TEXT_FIELD).performTextInput("User")
        rule.onNodeWithTag(InputUserModel.RESET_BUTTON).assertExists()
    }

    @Test
    fun shouldDeleteContentWhenResetButtonIsClickedAndHideResetButton() {
        rule.setContent {
            var inputValue by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("Android")) }

            InputUser(
                InputUserModel(
                    title = "Label",
                    inputTextFieldValue = inputValue,
                    onValueChanged = {
                        inputValue = it ?: TextFieldValue()
                    },
                    state = InputShellState.UNFOCUSED,
                ),

            )
        }
        rule.onNodeWithTag(InputUserModel.MAIN).assertExists()
        rule.onNodeWithTag(InputUserModel.RESET_BUTTON).assertExists()
        rule.onNodeWithTag(InputUserModel.RESET_BUTTON).performClick()
        rule.onNodeWithTag(InputUserModel.TEXT_FIELD).assertTextEquals("")
        rule.onNodeWithTag(InputUserModel.RESET_BUTTON).assertDoesNotExist()
    }

    @Test
    fun shouldHideResetButtonWhenDisabled() {
        rule.setContent {
            var inputValue by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("Android")) }
            InputUser(
                InputUserModel(
                    title = "Label",
                    state = InputShellState.DISABLED,
                    inputTextFieldValue = inputValue,
                    onValueChanged = {
                        inputValue = it ?: TextFieldValue()
                    },
                ),

            )
        }
        rule.onNodeWithTag(InputUserModel.MAIN).assertExists()
        rule.onNodeWithTag(InputUserModel.RESET_BUTTON).assertDoesNotExist()
    }

    @Test
    fun shouldShowLegendCorrectly() {
        rule.setContent {
            InputUser(
                InputUserModel(
                    title = "Label",
                    inputTextFieldValue = TextFieldValue("Input"),
                    legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
                    state = InputShellState.UNFOCUSED,
                ),
            )
        }
        rule.onNodeWithTag(InputUserModel.MAIN).assertExists()
        rule.onNodeWithTag(InputUserModel.LEGEND).assertExists()
        rule.onNodeWithTag(InputUserModel.LEGEND).assertHasNoClickAction()
    }

    @Test
    fun shouldShowSupportingTextCorrectly() {
        rule.setContent {
            InputUser(
                InputUserModel(
                    title = "Label",
                    inputTextFieldValue = TextFieldValue("Input"),
                    supportingText = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
                    state = InputShellState.UNFOCUSED,
                ),
            )
        }
        rule.onNodeWithTag(InputUserModel.MAIN).assertExists()
        rule.onNodeWithTag(InputUserModel.SUPPORTING_TEXT).assertExists()
    }
}
