package org.hisp.dhis.common.screens.actionInputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputLink
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState

@Composable
fun InputLinkScreen() {
    ColumnScreenContainer(title = ActionInputs.INPUT_LINK.label) {
        ColumnComponentContainer("Basic Link ") {
            var inputText1 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }

            InputLink(
                title = "Label",
                supportingText = listOf(SupportingTextData("Example: example.com")),
                inputTextFieldValue = inputText1,
                onValueChanged = {
                    if (it != null) {
                        inputText1 = it
                    }
                },
                onLinkActionCLicked = {},
                state = InputShellState.UNFOCUSED,
            )
        }

        ColumnComponentContainer("Basic Link with invalid link ") {
            var inputText2 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("example.")) }

            InputLink(
                title = "Label",
                supportingText = listOf(SupportingTextData("Example: example.com")),
                inputTextFieldValue = inputText2,
                onValueChanged = {
                    if (it != null) {
                        inputText2 = it
                    }
                },
                onLinkActionCLicked = {},
                state = InputShellState.UNFOCUSED,
            )
        }

        ColumnComponentContainer("Basic Link with valid link ") {
            var inputText3 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue(("example.com"))) }

            InputLink(
                title = "Label",
                supportingText = listOf(SupportingTextData("Example: example.com")),
                inputTextFieldValue = inputText3,
                onValueChanged = {
                    if (it != null) {
                        inputText3 = it
                    }
                },
                onLinkActionCLicked = {},
                state = InputShellState.UNFOCUSED,
            )
        }

        ColumnComponentContainer("Error Link required field ") {
            var inputText4 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
            InputLink(
                title = "Label",
                state = InputShellState.ERROR,
                supportingText = listOf(SupportingTextData("Required", SupportingTextState.ERROR)),
                inputTextFieldValue = inputText4,
                isRequiredField = true,
                onValueChanged = {
                    if (it != null) {
                        inputText4 = it
                    }
                },
                onLinkActionCLicked = {},
            )
        }

        ColumnComponentContainer("Disabled Link with content ") {
            var inputText5 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("example.com")) }
            InputLink(
                title = "Label",
                state = InputShellState.DISABLED,
                inputTextFieldValue = inputText5,
                onValueChanged = {
                    if (it != null) {
                        inputText5 = it
                    }
                },
                onLinkActionCLicked = {},
            )
        }
    }
}
