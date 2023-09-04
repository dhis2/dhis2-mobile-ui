package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputText
import org.hisp.dhis.mobile.ui.designsystem.component.LegendData
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState
import org.hisp.dhis.mobile.ui.designsystem.component.Title
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun InputTextScreen() {
    ColumnComponentContainer {
        Title("Input text component", textColor = TextColor.OnSurfaceVariant)
        SubTitle(" Basic Input text", textColor = TextColor.OnSurfaceVariant)
        val inputValue1 by rememberSaveable { mutableStateOf("Input") }

        InputText(
            title = "Label",
            inputText = inputValue1,
        )
        val inputValue2 by rememberSaveable { mutableStateOf("") }
        SubTitle("Input text with legend", textColor = TextColor.OnSurfaceVariant)
        InputText(title = "Label", inputText = inputValue2, legendData = LegendData(SurfaceColor.CustomGreen, "Legend"))

        val inputValue3 by rememberSaveable { mutableStateOf("") }

        SubTitle("Input text with Supporting text", textColor = TextColor.OnSurfaceVariant)
        InputText(title = "Label", inputText = inputValue3, supportingText = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)))

        val inputValue4 by rememberSaveable { mutableStateOf("") }

        SubTitle("Input text with Supporting text and legend", textColor = TextColor.OnSurfaceVariant)

        InputText(
            title = "Label",
            inputText = inputValue4,
            supportingText = listOf(
                SupportingTextData(
                    "Supporting text",
                    SupportingTextState.DEFAULT,
                ),
            ),
            legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
        )
        SubTitle("Input text with error and warning text and legend", textColor = TextColor.OnSurfaceVariant)
        val inputValue5 by rememberSaveable { mutableStateOf("") }

        InputText(
            title = "Label",
            inputText = inputValue5,
            supportingText = listOf(
                SupportingTextData("Supporting text", SupportingTextState.DEFAULT),
                SupportingTextData("Supporting text", SupportingTextState.WARNING),
                SupportingTextData("Supporting text", SupportingTextState.ERROR),

            ),
            legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
            state = InputShellState.ERROR,
            isLastField = true,
        )
        val inputValue6 by rememberSaveable { mutableStateOf("") }

        SubTitle("Disabled Input text ", textColor = TextColor.OnSurfaceVariant)
        InputText(title = "Label", inputText = inputValue6, state = InputShellState.DISABLED)

        val inputValue7 by rememberSaveable { mutableStateOf("Content") }

        SubTitle("Disabled Input text with content ", textColor = TextColor.OnSurfaceVariant)
        InputText(title = "Label", inputText = inputValue7, state = InputShellState.DISABLED)
    }
}
