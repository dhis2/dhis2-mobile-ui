package org.hisp.dhis.common.screens.actionInputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.Coordinates
import org.hisp.dhis.mobile.ui.designsystem.component.InputCoordinate
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState

@Composable
fun InputCoordinateScreen() {
    ColumnScreenContainer(title = ActionInputs.INPUT_COORDINATE.label) {
        ColumnComponentContainer("Basic Input Coordinates ") {
            var coordinates by remember { mutableStateOf<Coordinates?>(null) }
            InputCoordinate(
                title = "Label",
                state = InputShellState.UNFOCUSED,
                coordinates = coordinates,
                onResetButtonClicked = {
                    coordinates = null
                },
                onUpdateButtonClicked = {
                    coordinates = Coordinates(latitude = 39.46263, longitude = -0.33617)
                },
            )
        }

        ColumnComponentContainer("Disabled Input Coordinates without data ") {
            var coordinates1 by remember { mutableStateOf<Coordinates?>(null) }
            InputCoordinate(
                title = "Label",
                state = InputShellState.DISABLED,
                coordinates = coordinates1,
                onResetButtonClicked = {
                    coordinates1 = null
                },
                onUpdateButtonClicked = {
                    coordinates1 = Coordinates(latitude = 39.46263, longitude = -0.33617)
                },
            )
        }

        ColumnComponentContainer("Disabled Input Coordinates with data ") {
            var coordinates2 by remember {
                mutableStateOf<Coordinates?>(Coordinates(latitude = 39.46263, longitude = -0.33617))
            }
            InputCoordinate(
                title = "Label",
                state = InputShellState.DISABLED,
                coordinates = coordinates2,
                onResetButtonClicked = {
                    coordinates2 = null
                },
                onUpdateButtonClicked = {
                    coordinates2 = Coordinates(latitude = 39.46263, longitude = -0.33617)
                },
            )
        }
    }
}
