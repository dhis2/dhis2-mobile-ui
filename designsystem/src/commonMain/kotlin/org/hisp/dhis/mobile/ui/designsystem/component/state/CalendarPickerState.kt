@file:OptIn(ExperimentalTime::class)

package org.hisp.dhis.mobile.ui.designsystem.component.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlin.time.Clock
import org.hisp.dhis.mobile.ui.designsystem.component.model.CalendarDate
import org.hisp.dhis.mobile.ui.designsystem.component.model.CalendarSystem
import kotlin.time.ExperimentalTime

enum class CalendarPickerViewMode { DAY, YEAR, MONTH }

/**
 * Holds the runtime state of a [CalendarPickerModal].
 *
 * - [displayYear] / [displayMonth] control which month is currently rendered in the grid.
 * - [selectedDate] is the user's current selection (null = nothing selected).
 * - [viewMode] switches between the day grid and the year/month picker overlay.
 * - [selectedEpochMillis] is the selection expressed as UTC epoch millis, suitable for
 *   storage or forwarding to the confirm callback.
 */
@Stable
interface CalendarPickerState {
    val calendarSystem: CalendarSystem
    var displayYear: Int
    var displayMonth: Int
    var selectedDate: CalendarDate?
    var viewMode: CalendarPickerViewMode

    val selectedEpochMillis: Long?
        get() = selectedDate?.let {
            calendarSystem.toEpochMillis(it.year, it.month, it.day)
        }
}

@Composable
fun rememberCalendarPickerState(
    calendarSystem: CalendarSystem,
    initialSelectedDate: CalendarDate? = null,
): CalendarPickerState = remember(calendarSystem, initialSelectedDate) {
    val display = initialSelectedDate
        ?: calendarSystem.fromEpochMillis(Clock.System.now().toEpochMilliseconds())
    CalendarPickerStateImpl(
        calendarSystem = calendarSystem,
        initialYear = display.year,
        initialMonth = display.month,
        initialSelectedDate = initialSelectedDate,
    )
}

internal class CalendarPickerStateImpl(
    override val calendarSystem: CalendarSystem,
    initialYear: Int,
    initialMonth: Int,
    initialSelectedDate: CalendarDate?,
) : CalendarPickerState {
    override var displayYear: Int by mutableStateOf(initialYear)
    override var displayMonth: Int by mutableStateOf(initialMonth)
    override var selectedDate: CalendarDate? by mutableStateOf(initialSelectedDate)
    override var viewMode: CalendarPickerViewMode by mutableStateOf(CalendarPickerViewMode.DAY)
}