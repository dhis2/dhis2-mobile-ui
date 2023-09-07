package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.ImeAction
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputInteger
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.LegendData
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState
import org.hisp.dhis.mobile.ui.designsystem.component.Title
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun InputIntegerScreen() {
    ColumnComponentContainer {
        Title("Input Integer component", textColor = TextColor.OnSurfaceVariant)
        SubTitle("Basic Input Integer", textColor = TextColor.OnSurfaceVariant)
        var inputValue1 by rememberSaveable { mutableStateOf("12") }

        InputInteger(
            title = "Label",
            inputText = inputValue1,
            onValueChanged = {
                if (it != null) {
                    inputValue1 = it
                }
            },
        )
        SubTitle("Basic Input Integer with error", textColor = TextColor.OnSurfaceVariant)
        var inputValueError by rememberSaveable { mutableStateOf("") }

        InputInteger(
            title = "Label",
            inputText = inputValueError,
            onValueChanged = {
                if (it != null) {
                    inputValueError = it
                }
            },
            state = InputShellState.ERROR,
            supportingText = listOf(SupportingTextData("Numbers only", SupportingTextState.ERROR)),
        )
        var inputValue2 by rememberSaveable { mutableStateOf("") }
        SubTitle("Input Integer with legend", textColor = TextColor.OnSurfaceVariant)
        InputInteger(
            title = "Label",
            inputText = inputValue2,
            legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
            onValueChanged = {
                if (it != null) {
                    inputValue2 = it
                }
            },
        )

        var inputValue3 by rememberSaveable { mutableStateOf("") }

        SubTitle("Input Integer with Supporting text", textColor = TextColor.OnSurfaceVariant)
        InputInteger(
            title = "Label",
            inputText = inputValue3,
            supportingText = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
            onValueChanged = {
                if (it != null) {
                    inputValue3 = it
                }
            },
        )

        var inputValue4 by rememberSaveable { mutableStateOf("") }

        SubTitle("Input Integer with Supporting text and legend", textColor = TextColor.OnSurfaceVariant)

        InputInteger(
            title = "Label",
            inputText = inputValue4,
            supportingText = listOf(
                SupportingTextData(
                    "Supporting text",
                    SupportingTextState.DEFAULT,
                ),
            ),
            legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
            onValueChanged = {
                if (it != null) {
                    inputValue4 = it
                }
            },
        )
        SubTitle("Input Integer with error and warning text and legend", textColor = TextColor.OnSurfaceVariant)
        var inputValue5 by rememberSaveable { mutableStateOf("") }

        InputInteger(
            title = "Label",
            inputText = inputValue5,
            supportingText = listOf(
                SupportingTextData("Supporting text", SupportingTextState.DEFAULT),
                SupportingTextData("Supporting text", SupportingTextState.WARNING),
                SupportingTextData("Supporting text", SupportingTextState.ERROR),

            ),
            legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
            state = InputShellState.ERROR,
            imeAction = ImeAction.Done,
            onValueChanged = {
                if (it != null) {
                    inputValue5 = it
                }
            },
        )
        var inputValue6 by rememberSaveable { mutableStateOf("") }

        SubTitle("Disabled Input Integer ", textColor = TextColor.OnSurfaceVariant)
        InputInteger(
            title = "Label",
            inputText = inputValue6,
            state = InputShellState.DISABLED,
            onValueChanged = {
                if (it != null) {
                    inputValue6 = it
                }
            },
        )

        var inputValue7 by rememberSaveable { mutableStateOf("1234") }

        SubTitle("Disabled Input Integer with content ", textColor = TextColor.OnSurfaceVariant)
        InputInteger(
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
