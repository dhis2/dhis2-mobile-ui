package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
        var inputValue1 by rememberSaveable { mutableStateOf("") }

        InputLetter(
            title = "Label",
            inputText = inputValue1,
            onValueChanged = {
                if (it != null) {
                    inputValue1 = it
                }
            },
        )
        Spacer(Modifier.size(Spacing.Spacing8))

        SubTitle(" Basic Input Letter with error", textColor = TextColor.OnSurfaceVariant)
        var inputValueError by rememberSaveable { mutableStateOf("") }

        InputLetter(
            title = "Label",
            inputText = inputValueError,
            onValueChanged = {
                if (it != null) {
                    inputValueError = it
                }
            },
            supportingText = listOf(SupportingTextData("Letters only. eg. A, B, C", SupportingTextState.ERROR)),
            state = InputShellState.ERROR,
        )
        Spacer(Modifier.size(Spacing.Spacing8))

        var inputValue6 by rememberSaveable { mutableStateOf("") }

        SubTitle("Disabled Input Letter ", textColor = TextColor.OnSurfaceVariant)
        InputLetter(
            title = "Label",
            inputText = inputValue6,
            state = InputShellState.DISABLED,
            onValueChanged = {
                if (it != null) {
                    inputValue6 = it
                }
            },
        )
        Spacer(Modifier.size(Spacing.Spacing8))

        var inputValue7 by rememberSaveable { mutableStateOf("A") }

        SubTitle("Disabled Input Letter with content ", textColor = TextColor.OnSurfaceVariant)
        InputLetter(
            title = "Label",
            inputText = inputValue7,
            state = InputShellState.DISABLED,
            onValueChanged = {
                if (it != null) {
                    inputValue7 = it
                }
            },
        )
    }
}
