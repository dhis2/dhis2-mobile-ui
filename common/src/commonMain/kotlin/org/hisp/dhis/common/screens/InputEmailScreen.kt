package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputEmail
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState
import org.hisp.dhis.mobile.ui.designsystem.component.Title
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun InputEmailScreen() {
    ColumnComponentContainer {
        Title("Input Email component", textColor = TextColor.OnSurfaceVariant)
        SubTitle("Basic Email ", textColor = TextColor.OnSurfaceVariant)
        var inputText1 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }

        InputEmail(
            title = "Label",
            supportingText = listOf(SupportingTextData("Example: name@example.com")),
            inputTextFieldValue = inputText1,
            onValueChanged = {
                inputText1 = it
            },
            onEmailActionCLicked = {},
            state = InputShellState.UNFOCUSED,
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Basic Email with content ", textColor = TextColor.OnSurfaceVariant)
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
                inputText2 = it
            },
            onEmailActionCLicked = {},
            state = InputShellState.UNFOCUSED,
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Error Email with content ", textColor = TextColor.OnSurfaceVariant)
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
                inputText3 = it
            },
            onEmailActionCLicked = {},
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Error Email required field ", textColor = TextColor.OnSurfaceVariant)
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
                inputText4 = it
            },
            onEmailActionCLicked = {},
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Disabled Email with content ", textColor = TextColor.OnSurfaceVariant)
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
                inputText5 = it
            },
            onEmailActionCLicked = {},
        )
    }
}
