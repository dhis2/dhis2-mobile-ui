package org.hisp.dhis.common.screens.actionInputs

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.Coordinates
import org.hisp.dhis.mobile.ui.designsystem.component.InputCoordinate
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.Title
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun InputCoordinateScreen() {
    ColumnComponentContainer {
        Title("Input Coordinates", textColor = TextColor.OnSurfaceVariant)

        SubTitle("Basic Input Coordinates ", textColor = TextColor.OnSurfaceVariant)
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
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Disabled Input Coordinates without data ", textColor = TextColor.OnSurfaceVariant)
        var coordinates1 by remember {
            mutableStateOf<Coordinates?>(null)
        }
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

        SubTitle("Disabled Input Coordinates with data ", textColor = TextColor.OnSurfaceVariant)
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
        Spacer(Modifier.size(Spacing.Spacing18))
    }
}
