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
import org.hisp.dhis.mobile.ui.designsystem.component.InputLetter
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState
import org.hisp.dhis.mobile.ui.designsystem.component.Title
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun InputLetterScreen() {
    ColumnComponentContainer {
        Title("Input Letter component", textColor = TextColor.OnSurfaceVariant)
        SubTitle(" Basic Input Letter", textColor = TextColor.OnSurfaceVariant)
        var inputValue1 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }

        InputLetter(
            title = "Label",
            inputTextFieldValue = inputValue1,
            onValueChanged = {
                inputValue1 = it
            },
            state = InputShellState.UNFOCUSED,
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle(" Basic Input Letter with error", textColor = TextColor.OnSurfaceVariant)
        var inputValueError by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }

        InputLetter(
            title = "Label",
            inputTextFieldValue = inputValueError,
            onValueChanged = {
                inputValueError = it
            },
            supportingText = listOf(SupportingTextData("Letters only. eg. A, B, C", SupportingTextState.ERROR)),
            state = InputShellState.ERROR,
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        var inputValue6 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }

        SubTitle("Disabled Input Letter ", textColor = TextColor.OnSurfaceVariant)
        InputLetter(
            title = "Label",
            inputTextFieldValue = inputValue6,
            state = InputShellState.DISABLED,
            onValueChanged = {
                inputValue6 = it
            },
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        var inputValue7 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("A")) }

        SubTitle("Disabled Input Letter with content ", textColor = TextColor.OnSurfaceVariant)
        InputLetter(
            title = "Label",
            inputTextFieldValue = inputValue7,
            state = InputShellState.DISABLED,
            onValueChanged = {
                inputValue7 = it
            },
        )
    }
}
