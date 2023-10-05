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
import org.hisp.dhis.mobile.ui.designsystem.component.InputLink
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState
import org.hisp.dhis.mobile.ui.designsystem.component.Title
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun InputLinkScreen() {
    ColumnComponentContainer {
        Title("Input Link component", textColor = TextColor.OnSurfaceVariant)
        SubTitle("Basic Link ", textColor = TextColor.OnSurfaceVariant)
        var inputText1 by rememberSaveable { mutableStateOf("") }

        InputLink(
            title = "Label",
            supportingText = listOf(SupportingTextData("Example: example.com")),
            inputText = inputText1,
            onValueChanged = {
                if (it != null) {
                    inputText1 = it
                }
            },
            onLinkActionCLicked = {},
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Basic Link with invalid link ", textColor = TextColor.OnSurfaceVariant)
        var inputText2 by rememberSaveable { mutableStateOf("example.") }

        InputLink(
            title = "Label",
            supportingText = listOf(SupportingTextData("Example: example.com")),
            inputText = inputText2,
            onValueChanged = {
                if (it != null) {
                    inputText2 = it
                }
            },
            onLinkActionCLicked = {},
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Basic Link with valid link ", textColor = TextColor.OnSurfaceVariant)
        var inputText3 by rememberSaveable { mutableStateOf("example.com") }

        InputLink(
            title = "Label",
            supportingText = listOf(SupportingTextData("Example: example.com")),
            inputText = inputText3,
            onValueChanged = {
                if (it != null) {
                    inputText3 = it
                }
            },
            onLinkActionCLicked = {},
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Error Link required field ", textColor = TextColor.OnSurfaceVariant)
        var inputText4 by rememberSaveable { mutableStateOf("") }
        InputLink(
            title = "Label",
            state = InputShellState.ERROR,
            supportingText = listOf(SupportingTextData("Required", SupportingTextState.ERROR)),
            inputText = inputText4,
            isRequiredField = true,
            onValueChanged = {
                if (it != null) {
                    inputText4 = it
                }
            },
            onLinkActionCLicked = {},
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Disabled Link with content ", textColor = TextColor.OnSurfaceVariant)
        var inputText5 by rememberSaveable { mutableStateOf("example.com") }
        InputLink(
            title = "Label",
            state = InputShellState.DISABLED,
            inputText = inputText5,
            onValueChanged = {
                if (it != null) {
                    inputText5 = it
                }
            },
            onLinkActionCLicked = {},
        )
    }
}
