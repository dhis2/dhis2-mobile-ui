package org.hisp.dhis.showcaseapp.screens.actionInputs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.DatePicker
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.TimePicker
import org.hisp.dhis.mobile.ui.designsystem.platform.dates.formatDate
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PickersScreen() {
    var showTimePicker by remember { mutableStateOf(false) }
    var timePickerValue by remember { mutableStateOf("") }
    val timePickerState = rememberTimePickerState()

    var showDatePicker by remember { mutableStateOf(false) }
    var datePickerState = rememberDatePickerState()
    var datePickerValue by remember { mutableStateOf("") }

    ColumnScreenContainer(title = "Date and time pickers") {
        SubTitle("Date picker")

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Spacing.Spacing8),
        ) {
            Button(style = ButtonStyle.FILLED, text = "Show date picker") {
                showDatePicker = true
            }
            Text("Selected date: $datePickerValue")
        }

        Row(horizontalArrangement = Arrangement.spacedBy(Spacing.Spacing8)) {
            Button(style = ButtonStyle.FILLED, text = "Show time picker") {
                showTimePicker = true
            }
            Text("Selected Time: $timePickerValue")
        }
    }
    if (showDatePicker) {
        DatePicker(
            onConfirm = { updatedState ->
                showDatePicker = false
                datePickerState = updatedState
                datePickerState.selectedDateMillis?.let {
                    datePickerValue = formatDate(
                        dateFormat = "",
                        dateInMillis = datePickerState.selectedDateMillis!!
                    )
                }
            },
            onCancel = { showDatePicker = false },
            onDismissRequest = { showDatePicker = false },
            state = datePickerState,
            title = "Select a date",
            acceptText = "Accept",
            cancelText = "Cancel",
        )
    }

    if (showTimePicker) {
        TimePicker(
            state = timePickerState,
            title = "Select a time",
            onDismissRequest = { showTimePicker = false },
            onCancel = { showTimePicker = false },
            onAccept = {
                showTimePicker = false
                timePickerValue =
                    formatTime(
                        timePickerState,
                    )
            },
            acceptText = "Accept",
            cancelText = "Cancel",
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
private fun formatTime(timePickerState: TimePickerState): String =
    with(timePickerState) {
        "${hour.toString().padStart(2,'0')}${minute.toString().padStart(2,'0')}"
    }
