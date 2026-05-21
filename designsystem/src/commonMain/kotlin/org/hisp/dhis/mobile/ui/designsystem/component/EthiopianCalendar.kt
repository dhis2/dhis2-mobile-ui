package org.hisp.dhis.mobile.ui.designsystem.component

import org.hisp.dhis.mobile.ui.designsystem.component.model.CalendarDate
import org.hisp.dhis.mobile.ui.designsystem.component.model.CalendarSystem
import org.hisp.dhis.mobile.ui.designsystem.component.model.WeekStart

class EthiopianCalendar(
    override val weekStart: WeekStart = WeekStart.SUNDAY,
) : CalendarSystem {

    private val monthNames = listOf(
        "መስከረም",
        "ጥቅምት",
        "ኅዳር",
        "ታኅሣሥ",
        "ጥር",
        "የካቲት",
        "መጋቢት",
        "ሚያዝያ",
        "ግንቦት",
        "ሰኔ",
        "ሐምሌ",
        "ነሐሴ",
        "ጳጐሜን/ጳጉሜ",
    )

    // Canonical order: Mon=0, Tue=1, Wed=2, Thu=3, Fri=4, Sat=5, Sun=6
    private val canonicalDayNames = listOf("ሰኞ", "ማክሰኞ", "ረቡዕ", "ሐሙስ", "ዓርብ", "ቅዳሜ", "እሑድ")

    // Index of weekStart in the canonical Mon-first order
    private val weekStartIndex: Int = when (weekStart) {
        WeekStart.MONDAY -> 0
        WeekStart.SATURDAY -> 5
        WeekStart.SUNDAY -> 6
    }

    override val id: String = "ethiopian"
    override val monthsPerYear: Int = monthNames.size
    override val daysPerWeek: Int = 7
    override val weekDayNames: List<String> =
        List(7) { i -> canonicalDayNames[(weekStartIndex + i) % 7] }
    override val yearRange: IntRange = IntRange(1900, 2200)

    override fun monthName(year: Int, month: Int) = monthNames[month - 1]

    override fun daysInMonth(year: Int, month: Int) = when {
        (month in 1..12) -> 30
        isLeapYear(year) -> 6
        else -> 5
    }

    // Ethiopian leap year: year % 4 == 3 (Pagume has 6 days instead of 5)
    private fun isLeapYear(year: Int): Boolean = year % 4 == 3

    override fun firstWeekDayOfMonth(year: Int, month: Int): Int {
        val gregorian = EthiopianDateConverter.toGregorianDate(year, month, 1)
        val jdn = gregorianToJdn(gregorian.year, gregorian.month, gregorian.day)
        // JDN % 7 gives canonical index: 0=Monday … 5=Saturday, 6=Sunday.
        // Subtract weekStartIndex so that 0 aligns with weekDayNames[0].
        return (jdn % 7 - weekStartIndex + 7) % 7
    }

    override fun toEpochMillis(year: Int, month: Int, day: Int): Long {
        val gregorian = EthiopianDateConverter.toGregorianDate(year, month, day)
        val jdn = gregorianToJdn(gregorian.year, gregorian.month, gregorian.day)
        return (jdn - UNIX_EPOCH_JDN) * MILLIS_PER_DAY
    }

    override fun fromEpochMillis(millis: Long): CalendarDate {
        val jdn = (millis / MILLIS_PER_DAY).toInt() + UNIX_EPOCH_JDN
        val gregorian = jdnToGregorian(jdn)
        return EthiopianDateConverter.toEthiopianDate(gregorian)
    }

    private fun gregorianToJdn(year: Int, month: Int, day: Int): Int {
        val a = (14 - month) / 12
        val y = year + 4800 - a
        val m = month + 12 * a - 3
        return day + (153 * m + 2) / 5 + 365 * y + y / 4 - y / 100 + y / 400 - 32045
    }

    private fun jdnToGregorian(jdn: Int): CalendarDate {
        var r = jdn + 68569
        val n = 4 * r / 146097
        r -= (146097 * n + 3) / 4
        var year = 4000 * (r + 1) / 1461001
        r = r - 1461 * year / 4 + 31
        var month = 80 * r / 2447
        val day = r - 2447 * month / 80
        r = month / 11
        month = month + 2 - 12 * r
        year += 100 * (n - 49) + r
        return CalendarDate(year, month, day)
    }

    companion object {
        private const val UNIX_EPOCH_JDN = 2440588 // JDN of 1970-01-01
        private const val MILLIS_PER_DAY = 86_400_000L
    }
}
