package org.hisp.dhis.showcaseapp.screens.basicTextInputs

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.Description
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputText
import org.hisp.dhis.mobile.ui.designsystem.component.LegendData
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2Theme
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.showcaseapp.screens.previews.LOREM
import org.hisp.dhis.showcaseapp.screens.previews.regularLegendList

@Composable
fun FormShellsScreen() {
    ColumnScreenContainer(title = BasicTextInputs.FORM_SHELLS.label) {
        SubTitle("Outer frames for form elements", DHIS2Theme.colorScheme.onSurface)
        Description("Focused/Unfocused", DHIS2Theme.colorScheme.onSurface)
        var inputValue1 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("Input")) }

        InputText(
            "Label",
            inputTextFieldValue = inputValue1,
            onValueChanged = {
                if (it != null) {
                    inputValue1 = it
                }
            },
            state = InputShellState.UNFOCUSED,
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        Description("Focused/Unfocused with content", DHIS2Theme.colorScheme.onSurface)

        var inputValue2 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("Input")) }
        InputText(
            "Label",
            inputTextFieldValue = inputValue2,
            onValueChanged = {
                if (it != null) {
                    inputValue2 = it
                }
            },
            state = InputShellState.UNFOCUSED,
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        Description("Error", DHIS2Theme.colorScheme.onSurface)
        var inputValue3 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }
        InputText(
            "Label",
            state = InputShellState.ERROR,
            inputTextFieldValue = inputValue3,
            onValueChanged = {
                if (it != null) {
                    inputValue3 = it
                }
            },
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        Description("Error with content", DHIS2Theme.colorScheme.onSurface)
        var inputValue4 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("Input")) }

        InputText(
            "Label",
            inputTextFieldValue = inputValue4,
            state = InputShellState.ERROR,
            onValueChanged = {
                if (it != null) {
                    inputValue4 = it
                }
            },
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        Description("Disabled", DHIS2Theme.colorScheme.onSurface)
        var inputValue5 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }
        InputText(
            "Label",
            state = InputShellState.DISABLED,
            inputTextFieldValue = inputValue5,
            onValueChanged = {
                if (it != null) {
                    inputValue5 = it
                }
            },
            supportingText = listOf(SupportingTextData("Disabled description should be displayed", SupportingTextState.DEFAULT)),
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        Description("Disabled with content", DHIS2Theme.colorScheme.onSurface)
        var inputValue6 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("Input")) }
        InputText(
            "Label",
            inputTextFieldValue = inputValue6,
            state = InputShellState.DISABLED,
            onValueChanged = {
                if (it != null) {
                    inputValue6 = it
                }
            },
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        Description("Required field", DHIS2Theme.colorScheme.onSurface)
        var inputValue7 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }
        InputText(
            "Label",
            isRequiredField = true,
            inputTextFieldValue = inputValue7,
            onValueChanged = {
                if (it != null) {
                    inputValue7 = it
                }
            },
            state = InputShellState.ERROR,
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        Description("Required field with error", DHIS2Theme.colorScheme.onSurface)
        var inputValue8 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }
        InputText(
            "Label",
            inputTextFieldValue = inputValue8,
            isRequiredField = true,
            state = InputShellState.ERROR,
            onValueChanged = {
                if (it != null) {
                    inputValue8 = it
                }
            },
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        Description("Reset button not displayed", DHIS2Theme.colorScheme.onSurface)
        var inputValue17 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("Input")) }

        InputText(
            "Label",
            inputTextFieldValue = inputValue17,
            onValueChanged = {
                if (it != null) {
                    inputValue17 = it
                }
            },
            state = InputShellState.UNFOCUSED,
            showDeleteButton = false,
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Supporting text", DHIS2Theme.colorScheme.onSurface)
        Description("Short text", DHIS2Theme.colorScheme.onSurface)
        var inputValue9 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }
        InputText(
            "Label",
            supportingText =
                listOf(
                    SupportingTextData(
                        "Supporting text",
                        SupportingTextState.DEFAULT,
                    ),
                ),
            inputTextFieldValue = inputValue9,
            onValueChanged = {
                if (it != null) {
                    inputValue9 = it
                }
            },
            state = InputShellState.UNFOCUSED,
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        Description("Long  text", DHIS2Theme.colorScheme.onSurface)
        var inputValue10 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
        InputText(
            "Label",
            supportingText =
                listOf(
                    SupportingTextData(
                        LOREM,
                        SupportingTextState.DEFAULT,
                    ),
                ),
            inputTextFieldValue = inputValue10,
            onValueChanged = {
                if (it != null) {
                    inputValue10 = it
                }
            },
            state = InputShellState.UNFOCUSED,
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        Description("Error", DHIS2Theme.colorScheme.onSurface)
        var inputValue11 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("Input")) }
        InputText(
            "Label",
            inputTextFieldValue = inputValue11,
            supportingText =
                listOf(
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
        Spacer(Modifier.size(Spacing.Spacing18))

        Description("Warning", DHIS2Theme.colorScheme.onSurface)
        var inputValue12 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("Input")) }
        InputText(
            "Label",
            inputTextFieldValue = inputValue12,
            supportingText =
                listOf(
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
        Spacer(Modifier.size(Spacing.Spacing18))

        Description("Error and Warning", DHIS2Theme.colorScheme.onSurface)
        var inputValue13 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("Input")) }
        InputText(
            "Label",
            inputTextFieldValue = inputValue13,
            supportingText =
                listOf(
                    SupportingTextData(
                        LOREM,
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
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Form with legend", DHIS2Theme.colorScheme.onSurface)

        Description("Just legend", DHIS2Theme.colorScheme.onSurface)
        var inputValue14 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("Input")) }

        InputText(
            "Label",
            inputTextFieldValue = inputValue14,
            legendData = LegendData(DHIS2Theme.extendedColors.customGreen, "Legend", popUpLegendDescriptionData = regularLegendList),
            onValueChanged = {
                if (it != null) {
                    inputValue14 = it
                }
            },
            state = InputShellState.UNFOCUSED,
        )

        var inputValue15 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("Input")) }
        InputText(
            "Label",
            inputTextFieldValue = inputValue15,
            legendData = LegendData(DHIS2Theme.extendedColors.customYellow, "Legend", popUpLegendDescriptionData = regularLegendList),
            onValueChanged = {
                if (it != null) {
                    inputValue15 = it
                }
            },
            state = InputShellState.UNFOCUSED,
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        Description("Legend and supporting text", DHIS2Theme.colorScheme.onSurface)
        var inputValue16 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("Input")) }
        InputText(
            "Label",
            inputTextFieldValue = inputValue16,
            legendData = LegendData(DHIS2Theme.extendedColors.customYellow, "Legend", popUpLegendDescriptionData = regularLegendList),
            supportingText =
                listOf(
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
            state = InputShellState.UNFOCUSED,
        )
        Spacer(Modifier.size(Spacing.Spacing18))
    }
}
