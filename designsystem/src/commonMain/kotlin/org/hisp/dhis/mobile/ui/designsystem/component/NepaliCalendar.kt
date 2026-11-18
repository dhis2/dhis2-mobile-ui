package org.hisp.dhis.mobile.ui.designsystem.component

import org.hisp.dhis.mobile.ui.designsystem.component.model.CalendarDate
import org.hisp.dhis.mobile.ui.designsystem.component.model.CalendarSystem
import org.hisp.dhis.mobile.ui.designsystem.component.model.WeekStart

class NepaliCalendar(
    override val weekStart: WeekStart = WeekStart.SUNDAY,
) : CalendarSystem {
    private val monthNames =
        listOf(
            "बैशाख",
            "जेठ",
            "असार",
            "साउन",
            "भदौ",
            "असोज",
            "कार्तिक",
            "मंसिर",
            "पौष",
            "माघ",
            "फागुन",
            "चैत",
        )

    // Canonical order: Mon=0, Tue=1, Wed=2, Thu=3, Fri=4, Sat=5, Sun=6
    private val canonicalDayNames =
        listOf("सोम", "मंगल", "बुध", "बिही", "शुक्र", "शनि", "आइत")

    // Index of weekStart in the canonical Mon-first order
    private val weekStartIndex: Int =
        when (weekStart) {
            WeekStart.MONDAY -> 0
            WeekStart.SATURDAY -> 5
            WeekStart.SUNDAY -> 6
        }

    override val id: String = "nepali"
    override val monthsPerYear = monthNames.size
    override val daysPerWeek = 7
    override val weekDayNames: List<String> =
        List(7) { i -> canonicalDayNames[(weekStartIndex + i) % 7] }
    override val yearRange: IntRange =
        IntRange(NEPALI_CALENDAR_DATA.keys.min(), NEPALI_CALENDAR_DATA.keys.max())

    override fun monthName(
        year: Int,
        month: Int,
    ) = monthNames[month - 1]

    override fun daysInMonth(
        year: Int,
        month: Int,
    ) = NEPALI_CALENDAR_DATA[year]?.get(month) ?: 0

    override fun firstWeekDayOfMonth(
        year: Int,
        month: Int,
    ): Int {
        val millis = toEpochMillis(year, month, 1)
        val jdn = (millis / MILLIS_PER_DAY).toInt() + UNIX_EPOCH_JDN
        // JDN % 7: 0=Monday … 5=Saturday, 6=Sunday. Subtract weekStartIndex so 0 aligns with weekDayNames[0].
        return (jdn % 7 - weekStartIndex + 7) % 7
    }

    override fun toEpochMillis(
        year: Int,
        month: Int,
        day: Int,
    ): Long {
        var days = 0
        for (y in yearRange.first until year) {
            val yearData = NEPALI_CALENDAR_DATA[y] ?: break
            for (m in 1..monthsPerYear) days += yearData[m]
        }
        val yearData = NEPALI_CALENDAR_DATA[year]!!
        for (m in 1 until month) days += yearData[m]
        days += day - 1
        return (BS_EPOCH_JDN.toLong() + days - UNIX_EPOCH_JDN) * MILLIS_PER_DAY
    }

    override fun fromEpochMillis(millis: Long): CalendarDate {
        val jdn = millis.div(MILLIS_PER_DAY).toInt() + UNIX_EPOCH_JDN
        var remaining = jdn - BS_EPOCH_JDN
        for (year in yearRange) {
            val yearData = NEPALI_CALENDAR_DATA[year] ?: break
            val daysInYear = (1..monthsPerYear).sumOf { yearData[it] }
            if (remaining < daysInYear) {
                for (month in 1..monthsPerYear) {
                    val dim = yearData[month]
                    if (remaining < dim) return CalendarDate(year, month, remaining + 1)
                    remaining -= dim
                }
            }
            remaining -= daysInYear
        }
        throw IllegalArgumentException("Date out of range for Nepali calendar")
    }

    companion object {
        // JDN of BS 1969/01/01 = April 12, 1912 Gregorian (first year in NEPALI_CALENDAR_DATA)
        private const val BS_EPOCH_JDN = 2_419_505
        private const val UNIX_EPOCH_JDN = 2_440_588
        private const val MILLIS_PER_DAY = 86_400_000L
    }
}
