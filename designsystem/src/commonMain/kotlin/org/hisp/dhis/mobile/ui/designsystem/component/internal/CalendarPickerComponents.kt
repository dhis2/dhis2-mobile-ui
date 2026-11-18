package org.hisp.dhis.mobile.ui.designsystem.component.internal

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.model.CalendarDate
import org.hisp.dhis.mobile.ui.designsystem.component.state.CalendarPickerState
import org.hisp.dhis.mobile.ui.designsystem.component.state.CalendarPickerViewMode
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

private val DayCellSize = 40.dp
private val HeaderHeight = 120.dp

// ─── Header ───────────────────────────────────────────────────────────────────

/**
 * Picker header following the M3 date-picker anatomy: a smaller "title" label on top
 * and a large selected-date display below. Tapping the header has no action in this
 * skeleton — add text-input toggle here if needed.
 */
@Composable
internal fun CalendarPickerHeader(
    state: CalendarPickerState,
    title: String,
    modifier: Modifier = Modifier,
) {
    val cal = state.calendarSystem
    val selected = state.selectedDate

    Column(
        modifier =
            modifier
                .height(HeaderHeight)
                .padding(
                    start = Spacing.Spacing24,
                    end = Spacing.Spacing24,
                    top = Spacing.Spacing16,
                    bottom = Spacing.Spacing12,
                ),
        verticalArrangement = Arrangement.Bottom,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall,
            color = TextColor.OnSurfaceVariant,
        )

        Spacer(Modifier.height(Spacing.Spacing8))

        // TODO: format using CalendarSystem-specific conventions (weekday abbreviation, etc.)
        Text(
            text =
                if (selected != null) {
                    "${cal.monthName(selected.year, selected.month)} ${selected.day}, ${selected.year}"
                } else {
                    "— — —"
                },
            style = MaterialTheme.typography.displaySmall,
            color = TextColor.OnSurface,
        )
    }
}

// ─── Day view (month nav + weekday header + day grid) ─────────────────────────

@Composable
internal fun CalendarDayView(
    state: CalendarPickerState,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(top = Spacing.Spacing8)) {
        CalendarMonthNavigation(state = state, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(Spacing.Spacing8))
        CalendarWeekDayRow(
            weekDayNames = state.calendarSystem.weekDayNames,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(Spacing.Spacing4))
        CalendarDayGrid(state = state, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(Spacing.Spacing8))
    }
}

// ─── Month / year navigation row ──────────────────────────────────────────────

@Composable
private fun CalendarMonthNavigation(
    state: CalendarPickerState,
    modifier: Modifier = Modifier,
) {
    val cal = state.calendarSystem
    val canGoPrev = state.displayYear > cal.yearRange.first || state.displayMonth > 1
    val canGoNext = state.displayYear < cal.yearRange.last || state.displayMonth < cal.monthsPerYear

    Row(
        modifier = modifier.height(Spacing.Spacing40),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            enabled = canGoPrev,
            onClick = {
                if (state.displayMonth == 1) {
                    state.displayMonth = cal.monthsPerYear
                    state.displayYear--
                } else {
                    state.displayMonth--
                }
            },
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "Previous month",
            )
        }

        // Month chip — tapping switches to the month picker
        Row(
            modifier =
                Modifier
                    .clip(RoundedCornerShape(Radius.S))
                    .clickable(role = Role.Button) {
                        state.viewMode = CalendarPickerViewMode.MONTH
                    }.padding(horizontal = Spacing.Spacing4),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = cal.monthName(state.displayYear, state.displayMonth),
                style = MaterialTheme.typography.titleSmall,
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
            )
        }

        // Year chip — tapping switches to the year picker
        Row(
            modifier =
                Modifier
                    .clip(RoundedCornerShape(Radius.S))
                    .clickable(role = Role.Button) {
                        state.viewMode = CalendarPickerViewMode.YEAR
                    }.padding(horizontal = Spacing.Spacing4),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = state.displayYear.toString(),
                style = MaterialTheme.typography.titleSmall,
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
            )
        }

        Spacer(Modifier.weight(1f))

        IconButton(
            enabled = canGoNext,
            onClick = {
                if (state.displayMonth == cal.monthsPerYear) {
                    state.displayMonth = 1
                    state.displayYear++
                } else {
                    state.displayMonth++
                }
            },
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Next month",
            )
        }
    }
}

// ─── Weekday header row ────────────────────────────────────────────────────────

@Composable
private fun CalendarWeekDayRow(
    weekDayNames: List<String>,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        weekDayNames.forEach { label ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(DayCellSize),
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextColor.OnSurfaceVariant,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

// ─── Day grid ─────────────────────────────────────────────────────────────────

/**
 * Renders the day cells for [state].displayYear / [state].displayMonth.
 *
 * Leading blank cells are inserted for the weekday offset returned by
 * [CalendarSystem.firstWeekDayOfMonth], making the grid fully configurable: Ethiopian
 * month 13 (5–6 days) will simply produce a single short row.
 */
@OptIn(ExperimentalTime::class)
@Composable
private fun CalendarDayGrid(
    state: CalendarPickerState,
    modifier: Modifier = Modifier,
) {
    val cal = state.calendarSystem
    val year = state.displayYear
    val month = state.displayMonth
    val daysInMonth = cal.daysInMonth(year, month)
    val firstWeekDay = cal.firstWeekDayOfMonth(year, month)
    val daysPerWeek = cal.daysPerWeek
    val weekCount = (firstWeekDay + daysInMonth + daysPerWeek - 1) / daysPerWeek

    Column(modifier = modifier) {
        repeat(weekCount) { week ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                repeat(daysPerWeek) { weekDay ->
                    val day = week * daysPerWeek + weekDay - firstWeekDay + 1
                    if (day in 1..daysInMonth) {
                        val now =
                            cal.fromEpochMillis(
                                Clock.System.now().toEpochMilliseconds(),
                            )
                        CalendarDayCell(
                            day = day,
                            isSelected = state.selectedDate == CalendarDate(year, month, day),
                            isToday = (now.day == day && now.month == month && now.year == year),
                            isEnabled = true,
                            onClick = { state.selectedDate = CalendarDate(year, month, day) },
                        )
                    } else {
                        Box(modifier = Modifier.size(DayCellSize))
                    }
                }
            }
        }
    }
}

// ─── Day cell ─────────────────────────────────────────────────────────────────

@Composable
private fun CalendarDayCell(
    day: Int,
    isSelected: Boolean,
    isToday: Boolean,
    isEnabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val background = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
    val todayBorderMod =
        if (isToday && !isSelected) {
            Modifier.border(width = 1.dp, color = MaterialTheme.colorScheme.primary, shape = CircleShape)
        } else {
            Modifier
        }
    val textColor =
        when {
            !isEnabled -> TextColor.OnSurface.copy(alpha = 0.38f)
            isSelected -> MaterialTheme.colorScheme.onPrimary
            isToday -> MaterialTheme.colorScheme.primary
            else -> TextColor.OnSurface
        }

    Box(
        contentAlignment = Alignment.Center,
        modifier =
            modifier
                .size(DayCellSize)
                .clip(CircleShape)
                .then(todayBorderMod)
                .background(color = background, shape = CircleShape)
                .clickable(enabled = isEnabled, role = Role.Button, onClick = onClick),
    ) {
        Text(
            text = day.toString(),
            style = MaterialTheme.typography.bodySmall,
            color = textColor,
            textAlign = TextAlign.Center,
        )
    }
}

// ─── Year picker view ─────────────────────────────────────────────────────────

/** Scrollable grid of years; tapping a year moves to the month picker. */
@Composable
internal fun CalendarYearView(
    state: CalendarPickerState,
    modifier: Modifier = Modifier,
) {
    val cal = state.calendarSystem
    val yearCount = cal.yearRange.last - cal.yearRange.first + 1

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier =
            modifier
                .height(Spacing.Spacing200)
                .padding(vertical = Spacing.Spacing8),
    ) {
        itemsIndexed(
            items = List(yearCount) { cal.yearRange.first + it },
        ) { _, year ->
            CalendarYearCell(
                year = year,
                isSelected = year == state.displayYear,
                onClick = {
                    state.displayYear = year
                    state.viewMode = CalendarPickerViewMode.MONTH
                },
            )
        }
    }
}

// ─── Month picker view ────────────────────────────────────────────────────────

/** Grid of month names for [state].displayYear; tapping a month moves to the day view. */
@Composable
internal fun CalendarMonthView(
    state: CalendarPickerState,
    modifier: Modifier = Modifier,
) {
    val cal = state.calendarSystem

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier =
            modifier
                .height(Spacing.Spacing200)
                .padding(vertical = Spacing.Spacing8),
    ) {
        itemsIndexed(
            items = List(cal.monthsPerYear) { it + 1 },
        ) { _, month ->
            CalendarMonthCell(
                label = cal.monthName(state.displayYear, month),
                isSelected = month == state.displayMonth,
                onClick = {
                    state.displayMonth = month
                    state.viewMode = CalendarPickerViewMode.DAY
                },
            )
        }
    }
}

@Composable
private fun CalendarYearCell(
    year: Int,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val background = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
    val textColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else TextColor.OnSurface

    Box(
        contentAlignment = Alignment.Center,
        modifier =
            modifier
                .padding(Spacing.Spacing4)
                .clip(RoundedCornerShape(Radius.Full))
                .background(color = background)
                .clickable(role = Role.Button, onClick = onClick)
                .padding(vertical = Spacing.Spacing8),
    ) {
        Text(
            text = year.toString(),
            style = MaterialTheme.typography.bodyMedium,
            color = textColor,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun CalendarMonthCell(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val background = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
    val textColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else TextColor.OnSurface

    Box(
        contentAlignment = Alignment.Center,
        modifier =
            modifier
                .padding(Spacing.Spacing4)
                .clip(RoundedCornerShape(Radius.Full))
                .background(color = background)
                .clickable(role = Role.Button, onClick = onClick)
                .padding(vertical = Spacing.Spacing8),
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = textColor,
            textAlign = TextAlign.Center,
        )
    }
}
