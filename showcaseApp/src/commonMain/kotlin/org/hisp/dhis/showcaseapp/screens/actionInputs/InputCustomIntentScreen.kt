package org.hisp.dhis.showcaseapp.screens.actionInputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.CustomIntentState
import org.hisp.dhis.mobile.ui.designsystem.component.InputCustomIntent
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState

@Composable
fun InputCustomIntentScreen() {
    ColumnScreenContainer(title = ActionInputs.INPUT_CUSTOM_INTENT.label) {
        var inputCustomIntentState by remember { mutableStateOf(CustomIntentState.LAUNCH) }

        ColumnComponentContainer("Default state") {
            InputCustomIntent(
                title = "Label",
                values = emptyList(),
                buttonText = "launch",
                customIntentState = inputCustomIntentState,
                onLaunch = {
                    inputCustomIntentState = CustomIntentState.LOADED
                },
                onClear = {
                    inputCustomIntentState = CustomIntentState.LAUNCH
                },
            )
        }

        ColumnComponentContainer("Loading state") {
            InputCustomIntent(
                title = "Label",
                values = emptyList(),
                buttonText = "launch",
                customIntentState = CustomIntentState.LOADING,
                inputShellState = InputShellState.FOCUSED,
                onLaunch = {},
                onClear = {},
            )
        }

        ColumnComponentContainer("Selected state with one value") {
            InputCustomIntent(
                title = "Label",
                values = listOf("option 1"),
                buttonText = "launch",
                customIntentState = CustomIntentState.LOADED,
                onLaunch = {},
                onClear = {},
            )
        }

        ColumnComponentContainer("Selected state with one value") {
            InputCustomIntent(
                title = "Label",
                values = listOf("option 1", "option 2", "option 3"),
                buttonText = "launch",
                customIntentState = CustomIntentState.LOADED,
                onLaunch = {},
                onClear = {},
            )
        }

        ColumnComponentContainer("Disabled state") {
            InputCustomIntent(
                title = "Label",
                values = emptyList(),
                buttonText = "launch",
                inputShellState = InputShellState.DISABLED,
                onLaunch = {},
                onClear = {},
            )
        }

        ColumnComponentContainer("Disabled state with one value") {
            InputCustomIntent(
                title = "Label",
                values = listOf("option 1"),
                buttonText = "launch",
                inputShellState = InputShellState.DISABLED,
                customIntentState = CustomIntentState.LOADED,
                onLaunch = {},
                onClear = {},
            )
        }

        ColumnComponentContainer("Disabled state with more than one value") {
            InputCustomIntent(
                title = "Label",
                values = listOf("option 1", "option 2", "option 3"),
                buttonText = "launch",
                inputShellState = InputShellState.DISABLED,
                customIntentState = CustomIntentState.LOADED,
                onLaunch = {},
                onClear = {},
            )
        }
    }
}