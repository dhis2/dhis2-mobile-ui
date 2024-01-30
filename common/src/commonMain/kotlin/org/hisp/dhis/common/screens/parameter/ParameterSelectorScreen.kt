package org.hisp.dhis.common.screens.parameter

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.InputEmail
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputStyle
import org.hisp.dhis.mobile.ui.designsystem.component.InputText
import org.hisp.dhis.mobile.ui.designsystem.component.parameter.ParameterSelectorItem
import org.hisp.dhis.mobile.ui.designsystem.component.parameter.model.ParameterSelectorItemModel.FilledModel
import org.hisp.dhis.mobile.ui.designsystem.component.parameter.model.ParameterSelectorItemModel.PristineModel

@Composable
fun ParameterSelectorScreen() {
//    val focusRequester = remember { FocusRequester() }
//    val modifier = Modifier.focusRequester(focusRequester)

    val items = listOf(
        PristineModel(
            label = "Label",
            helper = "Optional",
            onClick = {},
        ),
        PristineModel(
            label = "Label",
            helper = "Optional",
            onClick = {},
        ),
        FilledModel(
            inputField = {
                InputText(
                    title = "Input Text search field",
                    state = InputShellState.UNFOCUSED,
                    inputText = "unfocused",
                    inputStyle = InputStyle.ParameterInputStyle(),
                )
            },
        ),
        FilledModel(
            inputField = {
                InputText(
                    title = "Input Text search field",
                    state = InputShellState.FOCUSED,
                    inputText = "focused",
                    inputStyle = InputStyle.ParameterInputStyle(),
                )
            },
        ),
        FilledModel(
            inputField = {
                InputEmail(
                    title = "Unfocused Email field",
                    state = InputShellState.UNFOCUSED,
                    inputText = "android@dhis2.org",
                    inputStyle = InputStyle.ParameterInputStyle(),
                    onEmailActionCLicked = {},
                )
            },
        ),
        FilledModel(
            inputField = {
                InputEmail(
                    title = "Focused Email search field",
                    state = InputShellState.FOCUSED,
                    inputText = "android@dhis2.org",
                    inputStyle = InputStyle.ParameterInputStyle(),
                    onEmailActionCLicked = {},
                )
            },
        ),
    )

    Column {
        items.forEach {
            ParameterSelectorItem(
                model = it,
            )
        }
    }
}
