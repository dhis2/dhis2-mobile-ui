package org.hisp.dhis.showcaseapp.screens.basicTextInputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputUnitInterval

@Composable
fun InputUnitIntervalScreen() {
    ColumnScreenContainer(title = BasicTextInputs.INPUT_UNIT_INTERVAL.label) {
        ColumnComponentContainer("Basic unit interval - Keyboard: Numbers. Range between 0 and 1 ") {
            var inputValue1 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("0.25")) }
            InputUnitInterval(
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

        ColumnComponentContainer("Basic unit interval required field") {
            var inputValueRequired by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                mutableStateOf(
                    TextFieldValue(),
                )
            }
            InputUnitInterval(
                title = "Label",
                inputTextFieldValue = inputValueRequired,
                onValueChanged = {
                    if (it != null) {
                        inputValueRequired = it
                    }
                },
                state = InputShellState.ERROR,
                isRequiredField = true,
            )
        }

        ColumnComponentContainer("Disabled unit interval  ") {
            var inputValue6 by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                mutableStateOf(
                    TextFieldValue(),
                )
            }
            InputUnitInterval(
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

        ColumnComponentContainer("Disabled unit interval with content ") {
            var inputValue7 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("0.19")) }
            InputUnitInterval(
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
