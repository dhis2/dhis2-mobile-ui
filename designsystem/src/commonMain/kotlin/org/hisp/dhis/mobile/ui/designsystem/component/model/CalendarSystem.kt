package org.hisp.dhis.mobile.ui.designsystem.component.model

/**
 * The day of the week that starts each column in the calendar grid.
 * Common locale conventions: [SUNDAY] (Americas, Japan), [MONDAY] (ISO 8601 / Europe),
 * [SATURDAY] (parts of the Middle East and North Africa).
 */
enum class WeekStart { SUNDAY, MONDAY, SATURDAY }

/**
 * Abstraction for a calendar system. Implement this to supply Nepali (Bikram Sambat),
 * Ethiopian (Ge'ez), Gregorian, or any other calendar to [CalendarPickerModal].
 *
 * All month and day indices are **1-based** throughout this interface.
 */
interface CalendarSystem {
    /** Unique identifier, e.g. "nepali", "ethiopian", "gregorian" */
    val id: String

    /**
     * Number of months in a year.
     * 12 for Nepali/Gregorian, **13** for Ethiopian (months 1–12 have 30 days each;
     * month 13 "Pagume" has 5 or 6 days depending on leap year).
     */
    val monthsPerYear: Int

    /** Number of days in a week — typically 7 */
    val daysPerWeek: Int

    /**
     * The day that occupies the first column of the calendar grid.
     * Implementations derive [weekDayNames] ordering and [firstWeekDayOfMonth] offset from this.
     * Defaults to [WeekStart.MONDAY] (ISO 8601).
     */
    val weekStart: WeekStart get() = WeekStart.MONDAY

    /**
     * Abbreviated weekday labels shown in the grid header row.
     * Length must equal [daysPerWeek]; the first entry is the first column rendered,
     * matching [weekStart].
     */
    val weekDayNames: List<String>

    /** Inclusive range of years available for selection in this calendar system */
    val yearRange: IntRange

    /** Full display name for the given 1-based [month] in [year] (e.g. "Meskerem", "Baisakh") */
    fun monthName(
        year: Int,
        month: Int,
    ): String

    /** Number of days in the given 1-based [month] for [year] */
    fun daysInMonth(
        year: Int,
        month: Int,
    ): Int

    /**
     * 0-based weekday index of the first day of [month]/[year].
     * 0 corresponds to the first entry in [weekDayNames].
     * Used to calculate the leading blank cells in the day grid.
     */
    fun firstWeekDayOfMonth(
        year: Int,
        month: Int,
    ): Int

    /** Convert a calendar date (year/month/day in this system) to UTC milliseconds since epoch */
    fun toEpochMillis(
        year: Int,
        month: Int,
        day: Int,
    ): Long

    /** Convert UTC milliseconds since epoch to a calendar date in this system */
    fun fromEpochMillis(millis: Long): CalendarDate
}

/** A date in a given [CalendarSystem]. All indices are 1-based. */
data class CalendarDate(
    val year: Int,
    val month: Int,
    val day: Int,
)
