package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputText
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState
import org.hisp.dhis.mobile.ui.designsystem.component.Title
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun InputTextScreen() {
    ColumnComponentContainer {
        Title("Input text component", textColor = TextColor.OnSurfaceVariant)
        SubTitle(" Basic Input text", textColor = TextColor.OnSurfaceVariant)
        InputText(title = "Label", inputText = "")

        SubTitle("Input text with legend", textColor = TextColor.OnSurfaceVariant)
        InputText(title = "Label", inputText = "", legendText = "Legend")

        SubTitle("Input text with Supporting text", textColor = TextColor.OnSurfaceVariant)
        InputText(title = "Label", inputText = "", supportingText = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)))

        SubTitle("Input text with Supporting text and legend", textColor = TextColor.OnSurfaceVariant)
        InputText(title = "Label", inputText = "", supportingText = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)), legendText = "Legend")

        SubTitle("Input text with error and warning text and legend", textColor = TextColor.OnSurfaceVariant)
        InputText(
            title = "Label",
            inputText = "",
            supportingText = listOf(
                SupportingTextData("Supporting text", SupportingTextState.DEFAULT),
                SupportingTextData("Supporting text", SupportingTextState.WARNING),
                SupportingTextData("Supporting text", SupportingTextState.ERROR)

            ),
            legendText = "Legend",
            state = mutableStateOf(InputShellState.ERROR)
        )
    }
}
