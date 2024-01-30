package org.hisp.dhis.common.screens.parameter

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.InputEmail
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
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
            label = "Pristine search field",
            helper = "Optional",
            onClick = {},
        ),
        FilledModel(
            inputField = {
                InputText(
                    title = "Input Text search field",
                    state = InputShellState.UNFOCUSED,
                    inputText = "unfocused",
                    hasTransparentBackground = true,
                )
            }
        ),
        FilledModel(
            inputField = {
                InputText(
                    title = "Input Text search field",
                    state = InputShellState.FOCUSED,
                    inputText = "focused",
                    hasTransparentBackground = true,
                )
            }
        ),
        FilledModel(
            inputField = {
                InputEmail(
                    title = "Unfocused Email field",
                    state = InputShellState.UNFOCUSED,
                    inputText = "android@dhis2.org",
                    hasTransparentBackground = true,
                    onEmailActionCLicked = {}
                )
            }
        ),
        FilledModel(
            inputField = {
                InputEmail(
                    title = "Focused Email search field",
                    state = InputShellState.FOCUSED,
                    inputText = "android@dhis2.org",
                    hasTransparentBackground = true,
                    onEmailActionCLicked = {}
                )
            }
        )
    )

    Column {
        items.forEach {
            ParameterSelectorItem(
                model = it
            )
        }
    }
}