package org.hisp.dhis.common.screens.parameter

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.InputEmail
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputStyle
import org.hisp.dhis.mobile.ui.designsystem.component.InputText
import org.hisp.dhis.mobile.ui.designsystem.component.parameter.ParameterSelectorItem
import org.hisp.dhis.mobile.ui.designsystem.component.parameter.model.ParameterSelectorItemModel.EmptyParameter
import org.hisp.dhis.mobile.ui.designsystem.component.parameter.model.ParameterSelectorItemModel.InputParameter

@Composable
fun ParameterSelectorScreen() {
//    val focusRequester = remember { FocusRequester() }
//    val modifier = Modifier.focusRequester(focusRequester)

    val items = listOf(
        EmptyParameter(
            label = "Label",
            helper = "Optional",
            onClick = {},
        ),
        EmptyParameter(
            label = "Label",
            helper = "Optional",
            onClick = {},
        ),
        InputParameter(
            inputField = {
                InputText(
                    title = "Input Text search field",
                    state = InputShellState.UNFOCUSED,
                    inputText = "unfocused",
                    inputStyle = InputStyle.ParameterInputStyle(),
                )
            },
        ),
        InputParameter(
            inputField = {
                InputText(
                    title = "Input Text search field",
                    state = InputShellState.FOCUSED,
                    inputText = "focused",
                    inputStyle = InputStyle.ParameterInputStyle(),
                )
            },
        ),
        InputParameter(
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
        InputParameter(
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
