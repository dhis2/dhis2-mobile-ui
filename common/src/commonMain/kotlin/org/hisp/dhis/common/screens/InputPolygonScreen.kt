package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputPolygon
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.Title
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun InputPolygonScreen() {
    ColumnComponentContainer {
        Title("Input Polygon", textColor = TextColor.OnSurfaceVariant)

        SubTitle("Basic Input Polygon ", textColor = TextColor.OnSurfaceVariant)
        var polygonCaptured by rememberSaveable { mutableStateOf(false) }
        InputPolygon(
            title = "Label",
            polygonAdded = polygonCaptured,
            onResetButtonClicked = {
                polygonCaptured = false
            },
            onUpdateButtonClicked = {
                polygonCaptured = true
            },
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Disabled Input Polygon without data ", textColor = TextColor.OnSurfaceVariant)
        var polygonCaptured1 by rememberSaveable { mutableStateOf(false) }
        InputPolygon(
            title = "Label",
            state = InputShellState.DISABLED,
            polygonAdded = polygonCaptured1,
            onResetButtonClicked = {
                polygonCaptured1 = false
            },
            onUpdateButtonClicked = {
                polygonCaptured1 = true
            },
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Disabled Input Polygon with data ", textColor = TextColor.OnSurfaceVariant)
        var polygonCaptured2 by rememberSaveable { mutableStateOf(true) }
        InputPolygon(
            title = "Label",
            state = InputShellState.DISABLED,
            polygonAdded = polygonCaptured2,
            onResetButtonClicked = {
                polygonCaptured2 = false
            },
            onUpdateButtonClicked = {
                polygonCaptured2 = true
            },
        )
    }
}
