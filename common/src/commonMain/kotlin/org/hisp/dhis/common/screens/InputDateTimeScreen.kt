package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.DateTimeActionType
import org.hisp.dhis.mobile.ui.designsystem.component.InputDateTime
import org.hisp.dhis.mobile.ui.designsystem.component.InputDateTimeModel
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.internal.DateTimeTransformation
import org.hisp.dhis.mobile.ui.designsystem.component.internal.DateTransformation
import org.hisp.dhis.mobile.ui.designsystem.component.internal.TimeTransformation

@Composable
fun InputDateTimeScreen() {
    ColumnComponentContainer {
        var date by remember { mutableStateOf(TextFieldValue("12111991")) }
        var time by remember { mutableStateOf(TextFieldValue("0930")) }
        var dateTime by remember { mutableStateOf(TextFieldValue("121119910230")) }
        var dateTime24hour by remember { mutableStateOf(TextFieldValue("121119911930")) }

        var dateTimenoInput by remember {mutableStateOf (TextFieldValue("11112014")) }
        var hour24time by remember {mutableStateOf(TextFieldValue("1630"))  }

        SubTitle("Date Input")
        InputDateTime(
            InputDateTimeModel(
                title = "Label",
                inputTextFieldValue = date,
                visualTransformation = DateTransformation(),
                actionIconType = DateTimeActionType.DATE,
                onActionClicked = {
                    // no-op
                },
                onValueChanged = { date = it ?: TextFieldValue() },
                format = "ddMMYYYYY",
            ),
        )

        SubTitle("Time Input")

        InputDateTime(
            InputDateTimeModel(
                title = "Label",
                value = dateTimenoInput,
                visualTransformation = DateTransformation(),
                actionIconType = DateTimeActionType.DATE,
                onActionClicked = {
                    // no-op
                },
                onValueChanged = { dateTimenoInput = it },
                format = "ddMMYYYYY",
                allowsManualInput = false,
            ),
        )
        SubTitle("24 hour format Time Input")

        InputDateTime(
            InputDateTimeModel(

                title = "Label",
                inputTextFieldValue = hour24time,
                visualTransformation = TimeTransformation(),
                actionIconType = DateTimeActionType.TIME,
                onActionClicked = {
                    // no-op
                },
                onValueChanged = { hour24time = it ?: TextFieldValue() },
                format = "HHMM",
                is24hourFormat = true,
            ),

        )

        SubTitle("12 hour format Time Input")
        InputDateTime(
            InputDateTimeModel(

                title = "Label",
                inputTextFieldValue = time,
                visualTransformation = TimeTransformation(),
                actionIconType = DateTimeActionType.TIME,
                onActionClicked = {
                    // no-op
                },
                onValueChanged = { time = it?: TextFieldValue() },
                format = "HHMM",

            ),

        )

        SubTitle("Date-Time Input")

        InputDateTime(
            InputDateTimeModel(

                title = "Label",
                inputTextFieldValue = dateTime,
                visualTransformation = DateTimeTransformation(),
                actionIconType = DateTimeActionType.DATE_TIME,
                onActionClicked = {
                    // no-op
                },
                onValueChanged = { dateTime = it ?: TextFieldValue() },
                format = "ddMMYYYYHHMM",
            ),
        )

        SubTitle("Date-Time Input 24 hour ")

        InputDateTime(
            InputDateTimeModel(

                title = "Label",
                inputTextFieldValue = dateTime24hour,
                visualTransformation = DateTimeTransformation(),
                actionIconType = DateTimeActionType.DATE_TIME,
                onActionClicked = {
                    // no-op
                },
                onValueChanged = { dateTime24hour = it ?: TextFieldValue() },
                format = "ddMMYYYYHHMM",
                is24hourFormat = true,
            ),
        )

        SubTitle("Disabled")

        InputDateTime(
            InputDateTimeModel(

                title = "Label",
                inputTextFieldValue = TextFieldValue(),
                state = InputShellState.DISABLED,
                onActionClicked = {
                    // no-op
                },
                onValueChanged = {
                    // no-op
                },
                format = "ddMMYYYY",

            ),
        )
        SubTitle("Error")

        InputDateTime(
            InputDateTimeModel(
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
                format = "ddMMYYYYHHMM",
            ),
        )
    }
}
