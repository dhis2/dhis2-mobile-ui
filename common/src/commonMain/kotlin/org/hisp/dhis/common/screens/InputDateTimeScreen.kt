package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
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
        var date by remember { mutableStateOf(TextFieldValue()) }
        var time by remember { mutableStateOf(TextFieldValue()) }
        var dateTime by remember { mutableStateOf(TextFieldValue()) }

        InputDateTime(
            title = "Label",
            inputTextFieldValue = date,
            visualTransformation = DateTransformation(),
            actionIconType = DateTimeActionIconType.DATE,
            onActionClicked = {
                // no-op
            },
            onValueChanged = { date = it },
        )

        InputDateTime(
            title = "Label",
            inputTextFieldValue = time,
            visualTransformation = TimeTransformation(),
            actionIconType = DateTimeActionIconType.TIME,
            onActionClicked = {
                // no-op
            },
            onValueChanged = { time = it },
        )

        InputDateTime(
            title = "Label",
            inputTextFieldValue = dateTime,
            visualTransformation = DateTimeTransformation(),
            actionIconType = DateTimeActionIconType.DATE_TIME,
            onActionClicked = {
                // no-op
            },
            onValueChanged = { dateTime = it },
        )

        InputDateTime(
            title = "Label",
            inputTextFieldValue = TextFieldValue(),
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
            inputTextFieldValue = TextFieldValue(),
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
