package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
        val inputValue1 by rememberSaveable { mutableStateOf("Input") }

        InputText("Label", inputText = inputValue1)

        Description("Focused/Unfocused with content", TextColor.OnSurface)

        val inputValue2 by rememberSaveable { mutableStateOf("Input") }
        InputText(
            "Label",
            inputText = inputValue2,
        )

        Description("Error", TextColor.OnSurface)
        val inputValue3 by rememberSaveable { mutableStateOf("") }
        InputText(
            "Label",
            state = InputShellState.ERROR,
            inputText = inputValue3,
        )

        Description("Error with content", TextColor.OnSurface)
        val inputValue4 by rememberSaveable { mutableStateOf("Input") }

        InputText(
            "Label",
            inputText = inputValue4,
            state = InputShellState.ERROR,
        )

        Description("Disabled", TextColor.OnSurface)
        val inputValue5 by rememberSaveable { mutableStateOf("") }
        InputText(
            "Label",
            state = InputShellState.DISABLED,
            inputText = inputValue5,
        )

        Description("Disabled with content", TextColor.OnSurface)
        val inputValue6 by rememberSaveable { mutableStateOf("Input") }
        InputText(
            "Label",
            inputText = inputValue6,
            state = InputShellState.DISABLED,
        )

        Description("Required field", TextColor.OnSurface)
        val inputValue7 by rememberSaveable { mutableStateOf("") }
        InputText(
            "Label",
            isRequiredField = true,
            inputText = inputValue7,
        )

        Description("Required field with error", TextColor.OnSurface)
        val inputValue8 by rememberSaveable { mutableStateOf("Input") }
        InputText(
            "Label",
            inputText = inputValue8,
            isRequiredField = true,
            state = InputShellState.ERROR,
        )

        SubTitle("Supporting text", TextColor.OnSurface)
        Description("Short text", TextColor.OnSurface)
        val inputValue9 by rememberSaveable { mutableStateOf("") }
        InputText(
            "Label",
            supportingText = listOf(
                SupportingTextData(
                    "Supporting text",
                    SupportingTextState.DEFAULT,
                ),
            ),
            inputText = inputValue9,
        )

        Description("Long  text", TextColor.OnSurface)
        val inputValue10 by rememberSaveable { mutableStateOf("") }
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
        )
        Description("Error", TextColor.OnSurface)
        val inputValue11 by rememberSaveable { mutableStateOf("Input") }
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
        )
        Description("Warning", TextColor.OnSurface)
        val inputValue12 by rememberSaveable { mutableStateOf("Input") }
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
        )
        Description("Error and Warning", TextColor.OnSurface)
        val inputValue13 by rememberSaveable { mutableStateOf("Input") }
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
        )
        SubTitle("Form with legend", TextColor.OnSurface)

        Description("Just legend", TextColor.OnSurface)
        val inputValue14 by rememberSaveable { mutableStateOf("Input") }

        InputText(
            "Label",
            inputText = inputValue14,
            legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
        )
        val inputValue15 by rememberSaveable { mutableStateOf("Input") }
        InputText(
            "Label",
            inputText = inputValue15,
            legendData = LegendData(SurfaceColor.CustomYellow, "Legend"),
        )

        Description("Legend and supporting text", TextColor.OnSurface)
        val inputValue16 by rememberSaveable { mutableStateOf("Input") }
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
            isLastField = true,
        )
    }
}
