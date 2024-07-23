package org.hisp.dhis.common.screens.basicTextInputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.common.screens.previews.lorem_medium
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputLongText
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState

@Composable
fun InputLongTextScreen() {
    ColumnScreenContainer(title = BasicTextInputs.INPUT_LONG_TEXT.label) {
        ColumnComponentContainer(" Basic Input Long Text") {
            var inputValue1 by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                mutableStateOf(
                    TextFieldValue(lorem_medium),
                )
            }
            InputLongText(
                title = "Label",
                inputTextFieldValue = inputValue1,
                onValueChanged = {
                    if (it != null) {
                        inputValue1 = it
                    }
                },
                state = InputShellState.UNFOCUSED,
            )
        }

        ColumnComponentContainer(" Basic Input Long Text with error message") {
            var inputValueError by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                mutableStateOf(
                    TextFieldValue(lorem_medium),
                )
            }
            InputLongText(
                title = "Label",
                inputTextFieldValue = inputValueError,
                onValueChanged = {
                    if (it != null) {
                        inputValueError = it
                    }
                },
                supportingText = listOf(
                    SupportingTextData(
                        "100000/1000000 characters used",
                        state = SupportingTextState.ERROR,
                    ),
                ),
                state = InputShellState.ERROR,
            )
        }

        ColumnComponentContainer("Disabled Input Long Text ") {
            var inputValue6 by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                mutableStateOf(
                    TextFieldValue(""),
                )
            }
            InputLongText(
                title = "Label",
                inputTextFieldValue = inputValue6,
                state = InputShellState.DISABLED,
                onValueChanged = {
                    if (it != null) {
                        inputValue6 = it
                    }
                },
            )
        }

        ColumnComponentContainer("Disabled Input text with content ") {
            var inputValue7 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("Content")) }
            InputLongText(
                title = "Label",
                inputTextFieldValue = inputValue7,
                state = InputShellState.DISABLED,
                onValueChanged = {
                    if (it != null) {
                        inputValue7 = it
                    }
                },
            )
        }
    }
}
