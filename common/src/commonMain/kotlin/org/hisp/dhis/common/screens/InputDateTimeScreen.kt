package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.DateTimeActionIconType
import org.hisp.dhis.mobile.ui.designsystem.component.InputDateTime
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.internal.DateTimeTransformation
import org.hisp.dhis.mobile.ui.designsystem.component.internal.DateTransformation
import org.hisp.dhis.mobile.ui.designsystem.component.internal.TimeTransformation

@Composable
fun InputDateTimeScreen() {
    ColumnComponentContainer {
        var date by remember { mutableStateOf("") }
        var time by remember { mutableStateOf("") }
        var dateTime by remember { mutableStateOf("") }

        InputDateTime(
            title = "Label",
            value = date,
            visualTransformation = DateTransformation(),
            actionIconType = DateTimeActionIconType.DATE,
            onActionClicked = {
                // no-op
            },
            onValueChanged = { date = it },
        )

        InputDateTime(
            title = "Label",
            value = time,
            visualTransformation = TimeTransformation(),
            actionIconType = DateTimeActionIconType.TIME,
            onActionClicked = {
                // no-op
            },
            onValueChanged = { time = it },
        )

        InputDateTime(
            title = "Label",
            value = dateTime,
            visualTransformation = DateTimeTransformation(),
            actionIconType = DateTimeActionIconType.DATE_TIME,
            onActionClicked = {
                // no-op
            },
            onValueChanged = { dateTime = it },
        )

        InputDateTime(
            title = "Label",
            value = "",
            state = InputShellState.DISABLED,
            onActionClicked = {
                // no-op
            },
            onValueChanged = {
                // no-op
            },
        )

        InputDateTime(
            title = "Label",
            value = "",
            isRequired = true,
            state = InputShellState.ERROR,
            onActionClicked = {
                // no-op
            },
            onValueChanged = {
                // no-op
            },
        )
    }
}
