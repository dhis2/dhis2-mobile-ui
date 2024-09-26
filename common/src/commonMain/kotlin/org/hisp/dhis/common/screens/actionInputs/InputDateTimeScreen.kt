package org.hisp.dhis.common.screens.actionInputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.DateTimeActionType
import org.hisp.dhis.mobile.ui.designsystem.component.InputDateTime
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.SelectableDates
import org.hisp.dhis.mobile.ui.designsystem.component.model.DateTimeTransformation
import org.hisp.dhis.mobile.ui.designsystem.component.model.DateTransformation
import org.hisp.dhis.mobile.ui.designsystem.component.model.TimeTransformation
import org.hisp.dhis.mobile.ui.designsystem.component.state.InputDateTimeData
import org.hisp.dhis.mobile.ui.designsystem.component.state.rememberInputDateTimeState

@Composable
fun InputDateTimeScreen() {
    ColumnScreenContainer(title = ActionInputs.INPUT_DATE_TIME.label) {
        var date by remember { mutableStateOf(TextFieldValue("2024-11-12", selection = TextRange(8))) }
        var time by remember { mutableStateOf(TextFieldValue("09:30")) }
        var dateTime by remember { mutableStateOf(TextFieldValue("1991-11-12T02:30")) }
        var dateTime24hour by remember { mutableStateOf(TextFieldValue("1991-11-12T19:30")) }

        var dateTimenoInput by remember { mutableStateOf(TextFieldValue("09:30")) }
        var hour24time by remember { mutableStateOf(TextFieldValue("16:30")) }

        ColumnComponentContainer("Date Input (allowed dates from 01/09/2024 to 12/12/2024)") {
            InputDateTime(
                state = rememberInputDateTimeState(
                    inputDateTimeData =
                    InputDateTimeData(
                        title = "label",
                        visualTransformation = DateTransformation(),
                        actionType = DateTimeActionType.DATE,
                        selectableDates = SelectableDates("01092024", "12122024"),
                    ),
                    inputTextFieldValue = date,
                ),

                onValueChanged = { date = it ?: TextFieldValue() },

            )
        }

        ColumnComponentContainer("Time Input") {
            InputDateTime(
                state = rememberInputDateTimeState(
                    inputDateTimeData =
                    InputDateTimeData(
                        title = "label",
                        visualTransformation = TimeTransformation(),
                        actionType = DateTimeActionType.TIME,
                        allowsManualInput = false,
                    ),
                    inputTextFieldValue = time,
                ),

                onValueChanged = { dateTimenoInput = it ?: TextFieldValue() },

            )
        }

        ColumnComponentContainer("24 hour format Time Input") {
            InputDateTime(
                state = rememberInputDateTimeState(
                    inputDateTimeData =
                    InputDateTimeData(
                        title = "label",
                        visualTransformation = TimeTransformation(),
                        actionType = DateTimeActionType.TIME,
                        is24hourFormat = true,
                    ),
                    inputTextFieldValue = hour24time,
                ),

                onValueChanged = { hour24time = it ?: TextFieldValue() },
            )
        }

        ColumnComponentContainer("12 hour format Time Input") {
            InputDateTime(
                state = rememberInputDateTimeState(
                    inputDateTimeData =
                    InputDateTimeData(
                        title = "label",
                        visualTransformation = TimeTransformation(),
                        actionType = DateTimeActionType.TIME,
                        is24hourFormat = false,
                    ),
                    inputTextFieldValue = time,
                ),

                onValueChanged = { time = it ?: TextFieldValue() },
            )
        }

        ColumnComponentContainer("Date-Time Input") {
            InputDateTime(
                state = rememberInputDateTimeState(
                    inputDateTimeData =
                    InputDateTimeData(
                        title = "label",
                        visualTransformation = DateTimeTransformation(),
                        actionType = DateTimeActionType.DATE_TIME,
                    ),
                    inputTextFieldValue = dateTime,
                ),

                onValueChanged = { dateTime = it ?: TextFieldValue() },
            )
        }

        ColumnComponentContainer("Date-Time Input 24 hour ") {
            InputDateTime(
                state = rememberInputDateTimeState(
                    inputDateTimeData =
                    InputDateTimeData(
                        title = "label",
                        visualTransformation = DateTimeTransformation(),
                        actionType = DateTimeActionType.DATE_TIME,
                        is24hourFormat = true,
                    ),
                    inputTextFieldValue = dateTime24hour,
                ),

                onValueChanged = { dateTime24hour = it ?: TextFieldValue() },
            )
        }

        ColumnComponentContainer("Disabled") {
            InputDateTime(
                state = rememberInputDateTimeState(
                    inputDateTimeData =
                    InputDateTimeData(
                        title = "label",
                        visualTransformation = DateTimeTransformation(),
                        actionType = DateTimeActionType.DATE_TIME,
                    ),
                    inputTextFieldValue = TextFieldValue(),
                    inputState = InputShellState.DISABLED,
                ),

                onValueChanged = {
                    // no-op
                },
            )
        }

        ColumnComponentContainer("Error") {
            InputDateTime(
                state = rememberInputDateTimeState(
                    inputDateTimeData =
                    InputDateTimeData(
                        title = "label",
                        visualTransformation = DateTimeTransformation(),
                        actionType = DateTimeActionType.DATE_TIME,
                        isRequired = true,
                    ),
                    inputTextFieldValue = TextFieldValue(),
                    inputState = InputShellState.ERROR,
                ),

                onValueChanged = {
                    // no-op
                },
            )
        }
    }
}
