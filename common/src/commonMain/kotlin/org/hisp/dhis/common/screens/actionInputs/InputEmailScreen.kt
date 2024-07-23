package org.hisp.dhis.common.screens.actionInputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputEmail
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState

@Composable
fun InputEmailScreen() {
    ColumnScreenContainer(ActionInputs.INPUT_EMAIL.label) {
        ColumnComponentContainer("Basic Email ") {
            var inputText1 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }

            InputEmail(
                title = "Label",
                supportingText = listOf(SupportingTextData("Example: name@example.com")),
                inputTextFieldValue = inputText1,
                onValueChanged = {
                    inputText1 = it ?: TextFieldValue()
                },
                onEmailActionCLicked = {},
                state = InputShellState.UNFOCUSED,
            )
        }

        ColumnComponentContainer("Basic Email with content ") {
            var inputText2 by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                mutableStateOf(
                    TextFieldValue("fatiman92@gmail.com"),
                )
            }

            InputEmail(
                title = "Label",
                supportingText = listOf(SupportingTextData("Example: name@example.com")),
                inputTextFieldValue = inputText2,
                onValueChanged = {
                    inputText2 = it ?: TextFieldValue()
                },
                onEmailActionCLicked = {},
                state = InputShellState.UNFOCUSED,
            )
        }

        ColumnComponentContainer("Error Email with content ") {
            var inputText3 by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                mutableStateOf(
                    TextFieldValue("fatiman92@gmail.com"),
                )
            }

            InputEmail(
                title = "Label",
                state = InputShellState.ERROR,
                supportingText = listOf(SupportingTextData("Enter a valid email address", SupportingTextState.ERROR)),
                inputTextFieldValue = inputText3,
                onValueChanged = {
                    inputText3 = it ?: TextFieldValue()
                },
                onEmailActionCLicked = {},
            )
        }

        ColumnComponentContainer("Error Email required field ") {
            var inputText4 by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                mutableStateOf(
                    TextFieldValue(),
                )
            }
            InputEmail(
                title = "Label",
                state = InputShellState.ERROR,
                supportingText = listOf(SupportingTextData("Enter email address", SupportingTextState.ERROR)),
                inputTextFieldValue = inputText4,
                isRequiredField = true,
                onValueChanged = {
                    inputText4 = it ?: TextFieldValue()
                },
                onEmailActionCLicked = {},
            )
        }
    }

    ColumnComponentContainer("Disabled Email with content ") {
        var inputText5 by rememberSaveable(stateSaver = TextFieldValue.Saver) {
            mutableStateOf(
                TextFieldValue("fatiman92@gmail.com"),
            )
        }
        InputEmail(
            title = "Label",
            state = InputShellState.DISABLED,
            inputTextFieldValue = inputText5,
            onValueChanged = {
                inputText5 = it ?: TextFieldValue()
            },
            onEmailActionCLicked = {},
        )
    }
}
