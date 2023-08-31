package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.Description
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputText
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun FormShellsScreen() {
    ColumnComponentContainer(title = "Form Shells") {
        SubTitle("Outer frames for form elements", TextColor.OnSurface)
        Description("Focused/Unfocused", TextColor.OnSurface)
        InputText("Label")

        Description("Focused/Unfocused with content", TextColor.OnSurface)

        InputText(
            "Label",
            inputText = "Input",
        )

        Description("Error", TextColor.OnSurface)
        InputText(
            "Label",
            state = InputShellState.ERROR,
        )

        Description("Error with content", TextColor.OnSurface)

        InputText(
            "Label",
            inputText = "Input",
            state = InputShellState.ERROR,
        )

        Description("Disabled", TextColor.OnSurface)
        InputText(
            "Label",
            state = InputShellState.DISABLED,
        )

        Description("Disabled with content", TextColor.OnSurface)

        InputText(
            "Label",
            inputText = "Input",
            state = InputShellState.DISABLED,
        )

        Description("Required field", TextColor.OnSurface)
        InputText(
            "Label",
            isRequiredField = true,
        )

        Description("Required field with error", TextColor.OnSurface)

        InputText(
            "Label",
            inputText = "Input",
            isRequiredField = true,
            state = InputShellState.ERROR,
        )

        SubTitle("Supporting text", TextColor.OnSurface)
        Description("Short text", TextColor.OnSurface)

        InputText(
            "Label",
            supportingText = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
        )

        Description("Long  text", TextColor.OnSurface)
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
        )
        Description("Error", TextColor.OnSurface)
        InputText(
            "Label",
            inputText = "Input",
            supportingText = listOf(
                SupportingTextData(
                    "Supporting Text",
                    SupportingTextState.ERROR,
                ),
            ),
            state = InputShellState.ERROR,
        )
        Description("Warning", TextColor.OnSurface)
        InputText(
            "Label",
            inputText = "Input",
            supportingText = listOf(
                SupportingTextData(
                    "Supporting Text",
                    SupportingTextState.WARNING,
                ),
            ),
            state = InputShellState.WARNING,
        )
        Description("Error and Warning", TextColor.OnSurface)
        InputText(
            "Label",
            inputText = "Input",
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
        InputText(
            "Label",
            inputText = "Input",
            legendText = "Legend",
        )
    }
}
