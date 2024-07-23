package org.hisp.dhis.common.screens.actionInputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputPolygon
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState

@Composable
fun InputPolygonScreen() {
    ColumnScreenContainer(title = ActionInputs.INPUT_POLYGON.label) {
        ColumnComponentContainer("Basic Input Polygon ") {
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
        }

        ColumnComponentContainer("Disabled Input Polygon without data ") {
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
        }

        ColumnComponentContainer("Disabled Input Polygon with data ") {
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
}
