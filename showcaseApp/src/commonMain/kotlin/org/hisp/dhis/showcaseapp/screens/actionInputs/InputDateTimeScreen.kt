package org.hisp.dhis.showcaseapp.screens.actionInputs

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
import org.hisp.dhis.mobile.ui.designsystem.component.EthiopianCalendar
import org.hisp.dhis.mobile.ui.designsystem.component.InputDateTime
import org.hisp.dhis.mobile.ui.designsystem.component.InputRadioButton
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.NepaliCalendar
import org.hisp.dhis.mobile.ui.designsystem.component.Orientation
import org.hisp.dhis.mobile.ui.designsystem.component.RadioButtonData
import org.hisp.dhis.mobile.ui.designsystem.component.SelectableDates
import org.hisp.dhis.mobile.ui.designsystem.component.model.CalendarSystem
import org.hisp.dhis.mobile.ui.designsystem.component.model.DateTimeTransformation
import org.hisp.dhis.mobile.ui.designsystem.component.model.DateTransformation
import org.hisp.dhis.mobile.ui.designsystem.component.model.TimeTransformation
import org.hisp.dhis.mobile.ui.designsystem.component.state.InputDateTimeData
import org.hisp.dhis.mobile.ui.designsystem.component.state.rememberInputDateTimeState

@Composable
fun InputDateTimeScreen() {
    ColumnScreenContainer(title = ActionInputs.INPUT_DATE_TIME.label) {
        var date by remember {
            mutableStateOf(
                TextFieldValue(
                    "2024-11-12",
                    selection = TextRange(8)
                )
            )
        }
        var time by remember { mutableStateOf(TextFieldValue("09:30")) }
        var dateTime by remember { mutableStateOf(TextFieldValue("1991-11-12T02:30")) }
        var dateTime24hour by remember { mutableStateOf(TextFieldValue("1991-11-12T19:30")) }

        var dateTimeInput by remember { mutableStateOf(TextFieldValue("09:30")) }
        var hour24time by remember { mutableStateOf(TextFieldValue("16:30")) }
        var selectedCalendarSystem: CalendarSystem? by remember { mutableStateOf(null) }
        val calendarData by remember(selectedCalendarSystem) {
            mutableStateOf(
                listOf(
                    RadioButtonData(
                        uid = "gregorian",
                        selected = selectedCalendarSystem == null,
                        enabled = true,
                        textInput = "Gregorian"
                    ),
                    RadioButtonData(
                        uid = "nepali",
                        selected = selectedCalendarSystem is NepaliCalendar,
                        enabled = true,
                        textInput = "Nepali"
                    ),
                    RadioButtonData(
                        uid = "ethiopian",
                        selected = selectedCalendarSystem is EthiopianCalendar,
                        enabled = true,
                        textInput = "Ethiopian"
                    )
                )
            )
        }

        InputRadioButton(
            orientation = Orientation.HORIZONTAL,
            title = "Current calendar",
            radioButtonData = calendarData,
            state = InputShellState.UNFOCUSED,
            itemSelected = calendarData.find { it.selected },
            onItemChange = { data ->
                selectedCalendarSystem = when (data?.uid) {
                    "nepali" -> NepaliCalendar()
                    "ethiopian" -> EthiopianCalendar()
                    else -> null
                }
            }
        )

        ColumnComponentContainer("Date Input (allowed dates from 01/09/2024 to 12/12/2025)") {
            InputDateTime(
                state =
                    rememberInputDateTimeState(
                        inputDateTimeData =
                            InputDateTimeData(
                                title = "label",
                                visualTransformation = DateTransformation(),
                                actionType = DateTimeActionType.DATE,
                                selectableDates = SelectableDates("01092024", "12122025"),
                            ),
                        inputTextFieldValue = date,
                    ),
                onValueChanged = { date = it ?: TextFieldValue() },
                calendarSystem = selectedCalendarSystem,
            )
        }

        ColumnComponentContainer("Time Input") {
            InputDateTime(
                state =
                    rememberInputDateTimeState(
                        inputDateTimeData =
                            InputDateTimeData(
                                title = "label",
                                visualTransformation = TimeTransformation(),
                                actionType = DateTimeActionType.TIME,
                                allowsManualInput = false,
                            ),
                        inputTextFieldValue = dateTimeInput,
                    ),
                onValueChanged = { dateTimeInput = it ?: TextFieldValue() },
                calendarSystem = selectedCalendarSystem,
            )
        }

        ColumnComponentContainer("24 hour format Time Input") {
            InputDateTime(
                state =
                    rememberInputDateTimeState(
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
                calendarSystem = selectedCalendarSystem,
            )
        }

        ColumnComponentContainer("12 hour format Time Input") {
            InputDateTime(
                state =
                    rememberInputDateTimeState(
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
                calendarSystem = selectedCalendarSystem,
            )
        }

        ColumnComponentContainer("Date-Time Input") {
            InputDateTime(
                state =
                    rememberInputDateTimeState(
                        inputDateTimeData =
                            InputDateTimeData(
                                title = "label",
                                visualTransformation = DateTimeTransformation(),
                                actionType = DateTimeActionType.DATE_TIME,
                            ),
                        inputTextFieldValue = dateTime,
                    ),
                onValueChanged = { dateTime = it ?: TextFieldValue() },
                calendarSystem = selectedCalendarSystem,
            )
        }

        ColumnComponentContainer("Date-Time Input 24 hour ") {
            InputDateTime(
                state =
                    rememberInputDateTimeState(
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
                calendarSystem = selectedCalendarSystem,
            )
        }

        ColumnComponentContainer("Disabled") {
            InputDateTime(
                state =
                    rememberInputDateTimeState(
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
                calendarSystem = selectedCalendarSystem,
            )
        }

        ColumnComponentContainer("Error") {
            InputDateTime(
                state =
                    rememberInputDateTimeState(
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
                calendarSystem = selectedCalendarSystem,
            )
        }
    }
}
