package org.hisp.dhis.common.screens.actionInputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.DateTimeActionType
import org.hisp.dhis.mobile.ui.designsystem.component.InputDateTime
import org.hisp.dhis.mobile.ui.designsystem.component.InputDateTimeModel
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.SelectableDates
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.internal.DateTimeTransformation
import org.hisp.dhis.mobile.ui.designsystem.component.internal.DateTransformation
import org.hisp.dhis.mobile.ui.designsystem.component.internal.TimeTransformation

@Composable
fun InputDateTimeScreen() {
    ColumnComponentContainer {
        var date by remember { mutableStateOf(TextFieldValue("18122024", selection = TextRange(8))) }
        var time by remember { mutableStateOf(TextFieldValue("0930")) }
        var dateTime by remember { mutableStateOf(TextFieldValue("121119910230")) }
        var dateTime24hour by remember { mutableStateOf(TextFieldValue("121119911930")) }

        var dateTimenoInput by remember { mutableStateOf(TextFieldValue("11112014")) }
        var hour24time by remember { mutableStateOf(TextFieldValue("1630")) }

        SubTitle("Date Input (allowed dates from 01/09/2024 to 12/12/2024)")
        InputDateTime(
            InputDateTimeModel(
                title = "Label",
                inputTextFieldValue = date,
                visualTransformation = DateTransformation(),
                actionType = DateTimeActionType.DATE,
                onValueChanged = { date = it ?: TextFieldValue() },
                selectableDates = SelectableDates("01092024", "12122024"),
            ),
        )

        SubTitle("Time Input")

        InputDateTime(
            InputDateTimeModel(
                title = "Label",
                inputTextFieldValue = dateTimenoInput,
                visualTransformation = DateTransformation(),
                actionType = DateTimeActionType.DATE,
                onValueChanged = { dateTimenoInput = it ?: TextFieldValue() },
                allowsManualInput = false,
            ),
        )
        SubTitle("24 hour format Time Input")

        InputDateTime(
            InputDateTimeModel(

                title = "Label",
                inputTextFieldValue = hour24time,
                visualTransformation = TimeTransformation(),
                actionType = DateTimeActionType.TIME,
                onValueChanged = { hour24time = it ?: TextFieldValue() },
                is24hourFormat = true,
            ),

        )

        SubTitle("12 hour format Time Input")
        InputDateTime(
            InputDateTimeModel(

                title = "Label",
                inputTextFieldValue = time,
                visualTransformation = TimeTransformation(),
                actionType = DateTimeActionType.TIME,
                onValueChanged = { time = it ?: TextFieldValue() },

            ),

        )

        SubTitle("Date-Time Input")

        InputDateTime(
            InputDateTimeModel(

                title = "Label",
                inputTextFieldValue = dateTime,
                visualTransformation = DateTimeTransformation(),
                actionType = DateTimeActionType.DATE_TIME,
                onValueChanged = { dateTime = it ?: TextFieldValue() },
            ),
        )

        SubTitle("Date-Time Input 24 hour ")

        InputDateTime(
            InputDateTimeModel(

                title = "Label",
                inputTextFieldValue = dateTime24hour,
                visualTransformation = DateTimeTransformation(),
                actionType = DateTimeActionType.DATE_TIME,
                onValueChanged = { dateTime24hour = it ?: TextFieldValue() },
                is24hourFormat = true,
            ),
        )

        SubTitle("Disabled")

        InputDateTime(
            InputDateTimeModel(

                title = "Label",
                inputTextFieldValue = TextFieldValue(),
                state = InputShellState.DISABLED,
                onValueChanged = {
                    // no-op
                },

            ),
        )
        SubTitle("Error")

        InputDateTime(
            InputDateTimeModel(
                title = "Label",
                inputTextFieldValue = TextFieldValue(),
                isRequired = true,
                state = InputShellState.ERROR,
                onValueChanged = {
                    // no-op
                },
            ),
        )
    }
}
