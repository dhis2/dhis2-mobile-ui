package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.ImeAction
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.Description
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputText
import org.hisp.dhis.mobile.ui.designsystem.component.LegendData
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun FormShellsScreen() {
    ColumnComponentContainer(title = "Form Shells") {
        SubTitle("Outer frames for form elements", TextColor.OnSurface)
        Description("Focused/Unfocused", TextColor.OnSurface)
        var inputValue1 by rememberSaveable { mutableStateOf("Input") }

        InputText(
            "Label",
            inputText = inputValue1,
            onValueChanged = {
                if (it != null) {
                    inputValue1 = it
                }
            },
        )

        Description("Focused/Unfocused with content", TextColor.OnSurface)

        var inputValue2 by rememberSaveable { mutableStateOf("Input") }
        InputText(
            "Label",
            inputText = inputValue2,
            onValueChanged = {
                if (it != null) {
                    inputValue2 = it
                }
            },

        )

        Description("Error", TextColor.OnSurface)
        var inputValue3 by rememberSaveable { mutableStateOf("") }
        InputText(
            "Label",
            state = InputShellState.ERROR,
            inputText = inputValue3,
            onValueChanged = {
                if (it != null) {
                    inputValue3 = it
                }
            },
        )

        Description("Error with content", TextColor.OnSurface)
        var inputValue4 by rememberSaveable { mutableStateOf("Input") }

        InputText(
            "Label",
            inputText = inputValue4,
            state = InputShellState.ERROR,
            onValueChanged = {
                if (it != null) {
                    inputValue4 = it
                }
            },
        )

        Description("Disabled", TextColor.OnSurface)
        var inputValue5 by rememberSaveable { mutableStateOf("") }
        InputText(
            "Label",
            state = InputShellState.DISABLED,
            inputText = inputValue5,
            onValueChanged = {
                if (it != null) {
                    inputValue5 = it
                }
            },
        )

        Description("Disabled with content", TextColor.OnSurface)
        var inputValue6 by rememberSaveable { mutableStateOf("Input") }
        InputText(
            "Label",
            inputText = inputValue6,
            state = InputShellState.DISABLED,
            onValueChanged = {
                if (it != null) {
                    inputValue6 = it
                }
            },
        )

        Description("Required field", TextColor.OnSurface)
        var inputValue7 by rememberSaveable { mutableStateOf("") }
        InputText(
            "Label",
            isRequiredField = true,
            inputText = inputValue7,
            onValueChanged = {
                if (it != null) {
                    inputValue7 = it
                }
            },
        )

        Description("Required field with error", TextColor.OnSurface)
        var inputValue8 by rememberSaveable { mutableStateOf("Input") }
        InputText(
            "Label",
            inputText = inputValue8,
            isRequiredField = true,
            state = InputShellState.ERROR,
            onValueChanged = {
                if (it != null) {
                    inputValue8 = it
                }
            },
        )

        SubTitle("Supporting text", TextColor.OnSurface)
        Description("Short text", TextColor.OnSurface)
        var inputValue9 by rememberSaveable { mutableStateOf("") }
        InputText(
            "Label",
            supportingText = listOf(
                SupportingTextData(
                    "Supporting text",
                    SupportingTextState.DEFAULT,
                ),
            ),
            inputText = inputValue9,
            onValueChanged = {
                if (it != null) {
                    inputValue9 = it
                }
            },
        )

        Description("Long  text", TextColor.OnSurface)
        var inputValue10 by rememberSaveable { mutableStateOf("") }
        InputText(
            "Label",
            supportingText = listOf(
                SupportingTextData(
                    "Lorem ipsum dolor sit amet," +
                        " consectetur adipiscing elit. Maecenas dolor lacus," +
                        " aliquam. Lorem ipsum dolor sit amet," +
                        " consectetur adipiscing elit. Maecenas dolor lacus," +
                        " aliquam.Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                    SupportingTextState.DEFAULT,
                ),
            ),
            inputText = inputValue10,
            onValueChanged = {
                if (it != null) {
                    inputValue10 = it
                }
            },
        )
        Description("Error", TextColor.OnSurface)
        var inputValue11 by rememberSaveable { mutableStateOf("Input") }
        InputText(
            "Label",
            inputText = inputValue11,
            supportingText = listOf(
                SupportingTextData(
                    "Supporting Text",
                    SupportingTextState.ERROR,
                ),
            ),
            state = InputShellState.ERROR,
            onValueChanged = {
                if (it != null) {
                    inputValue11 = it
                }
            },
        )
        Description("Warning", TextColor.OnSurface)
        var inputValue12 by rememberSaveable { mutableStateOf("Input") }
        InputText(
            "Label",
            inputText = inputValue12,
            supportingText = listOf(
                SupportingTextData(
                    "Supporting Text",
                    SupportingTextState.WARNING,
                ),
            ),
            state = InputShellState.WARNING,
            onValueChanged = {
                if (it != null) {
                    inputValue12 = it
                }
            },
        )
        Description("Error and Warning", TextColor.OnSurface)
        var inputValue13 by rememberSaveable { mutableStateOf("Input") }
        InputText(
            "Label",
            inputText = inputValue13,
            supportingText = listOf(
                SupportingTextData(
                    "Lorem ipsum dolor sit amet," +
                        " consectetur adipiscing elit. Maecenas dolor lacus," +
                        " aliquam. Lorem ipsum dolor sit amet," +
                        " consectetur adipiscing elit. Maecenas dolor lacus," +
                        " aliquam.Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                    SupportingTextState.DEFAULT,
                ),
                SupportingTextData(
                    "Supporting Text",
                    SupportingTextState.ERROR,
                ),
                SupportingTextData(
                    "Supporting Text",
                    SupportingTextState.WARNING,
                ),
            ),
            state = InputShellState.ERROR,
            onValueChanged = {
                if (it != null) {
                    inputValue13 = it
                }
            },
        )
        SubTitle("Form with legend", TextColor.OnSurface)

        Description("Just legend", TextColor.OnSurface)
        var inputValue14 by rememberSaveable { mutableStateOf("Input") }

        InputText(
            "Label",
            inputText = inputValue14,
            legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
            onValueChanged = {
                if (it != null) {
                    inputValue14 = it
                }
            },
        )
        var inputValue15 by rememberSaveable { mutableStateOf("Input") }
        InputText(
            "Label",
            inputText = inputValue15,
            legendData = LegendData(SurfaceColor.CustomYellow, "Legend"),
            onValueChanged = {
                if (it != null) {
                    inputValue15 = it
                }
            },
        )

        Description("Legend and supporting text", TextColor.OnSurface)
        var inputValue16 by rememberSaveable { mutableStateOf("Input") }
        InputText(
            "Label",
            inputText = inputValue16,
            legendData = LegendData(SurfaceColor.CustomYellow, "Legend"),
            supportingText = listOf(
                SupportingTextData(
                    "Supporting Text",
                    SupportingTextState.DEFAULT,
                ),
            ),
            imeAction = ImeAction.Done,
            onValueChanged = {
                if (it != null) {
                    inputValue16 = it
                }
            },
        )
    }
}
