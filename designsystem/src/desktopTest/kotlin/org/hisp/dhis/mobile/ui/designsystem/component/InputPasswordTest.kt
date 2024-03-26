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
import org.hisp.dhis.mobile.ui.designsystem.component.model.InputPasswordModel
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.junit.Rule
import org.junit.Test

class InputPasswordTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldDisplayInputUserCorrectly() {
        rule.setContent {
            InputPassword(
                InputPasswordModel(
                    title = "Label",
                    state = InputShellState.UNFOCUSED,

                ),

            )
        }
        rule.onNodeWithTag(InputPasswordModel.MAIN).assertExists()
        rule.onNodeWithTag(InputPasswordModel.LEGEND).assertDoesNotExist()
        rule.onNodeWithTag(InputPasswordModel.SUPPORTING_TEXT).assertDoesNotExist()
    }

    @Test
    fun shouldAllowUserInputWhenEnabled() {
        rule.setContent {
            var inputValue by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
            InputPassword(
                InputPasswordModel(
                    title = "Label",
                    inputTextFieldValue = inputValue,
                    onValueChanged = {
                        inputValue = it ?: TextFieldValue()
                    },
                    state = InputShellState.UNFOCUSED,
                ),

            )
        }
        rule.onNodeWithTag(InputPasswordModel.MAIN).assertExists()
        rule.onNodeWithTag(InputPasswordModel.TEXT_FIELD).performTextInput("User")
        rule.onNodeWithTag(InputPasswordModel.ACTION_BUTTON).performClick()
        rule.onNodeWithTag(InputPasswordModel.TEXT_FIELD).assert(hasText("User"))
    }

    @Test
    fun shouldNotAllowUserInputWhenDisabled() {
        rule.setContent {
            InputPassword(
                InputPasswordModel(
                    title = "Label",
                    state = InputShellState.DISABLED,
                ),

            )
        }
        rule.onNodeWithTag(InputPasswordModel.MAIN).assertExists()
        rule.onNodeWithTag(InputPasswordModel.TEXT_FIELD).assertIsNotEnabled()
    }

    @Test
    fun shouldShowResetButtonWhenTextFieldHasContent() {
        rule.setContent {
            var inputValue by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
            InputPassword(
                InputPasswordModel(
                    title = "Label",
                    inputTextFieldValue = inputValue,
                    onValueChanged = {
                        inputValue = it ?: TextFieldValue()
                    },
                    state = InputShellState.UNFOCUSED,
                ),

            )
        }
        rule.onNodeWithTag(InputPasswordModel.MAIN).assertExists()
        rule.onNodeWithTag(InputPasswordModel.TEXT_FIELD).assertExists()
        rule.onNodeWithTag(InputPasswordModel.TEXT_FIELD).performTextInput("User")
        rule.onNodeWithTag(InputPasswordModel.RESET_BUTTON).assertExists()
    }

    @Test
    fun shouldDeleteContentWhenResetButtonIsClickedAndHideResetButton() {
        rule.setContent {
            var inputValue by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("Android")) }

            InputPassword(
                InputPasswordModel(
                    title = "Label",
                    inputTextFieldValue = inputValue,
                    onValueChanged = {
                        inputValue = it ?: TextFieldValue()
                    },
                    state = InputShellState.UNFOCUSED,
                ),

            )
        }
        rule.onNodeWithTag(InputPasswordModel.MAIN).assertExists()
        rule.onNodeWithTag(InputPasswordModel.RESET_BUTTON).assertExists()
        rule.onNodeWithTag(InputPasswordModel.RESET_BUTTON).performClick()
        rule.onNodeWithTag(InputPasswordModel.TEXT_FIELD).assertTextEquals("")
        rule.onNodeWithTag(InputPasswordModel.RESET_BUTTON).assertDoesNotExist()
    }

    @Test
    fun shouldHideResetButtonWhenDisabled() {
        rule.setContent {
            var inputValue by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("Android")) }
            InputPassword(
                InputPasswordModel(
                    title = "Label",
                    state = InputShellState.DISABLED,
                    inputTextFieldValue = inputValue,
                    onValueChanged = {
                        inputValue = it ?: TextFieldValue()
                    },
                ),

            )
        }
        rule.onNodeWithTag(InputPasswordModel.MAIN).assertExists()
        rule.onNodeWithTag(InputPasswordModel.RESET_BUTTON).assertDoesNotExist()
    }

    @Test
    fun shouldShowLegendCorrectly() {
        rule.setContent {
            InputPassword(
                InputPasswordModel(
                    title = "Label",
                    inputTextFieldValue = TextFieldValue("Input"),
                    legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
                    state = InputShellState.UNFOCUSED,
                ),
            )
        }
        rule.onNodeWithTag(InputPasswordModel.MAIN).assertExists()
        rule.onNodeWithTag(InputPasswordModel.LEGEND).assertExists()
        rule.onNodeWithTag(InputPasswordModel.LEGEND).assertHasNoClickAction()
    }

    @Test
    fun shouldShowSupportingTextCorrectly() {
        rule.setContent {
            InputPassword(
                InputPasswordModel(
                    title = "Label",
                    inputTextFieldValue = TextFieldValue("Input"),
                    supportingText = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
                    state = InputShellState.UNFOCUSED,
                ),
            )
        }
        rule.onNodeWithTag(InputPasswordModel.MAIN).assertExists()
        rule.onNodeWithTag(InputPasswordModel.SUPPORTING_TEXT).assertExists()
    }
}
