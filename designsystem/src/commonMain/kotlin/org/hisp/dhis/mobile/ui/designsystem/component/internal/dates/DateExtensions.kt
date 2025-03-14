package org.hisp.dhis.mobile.ui.designsystem.component.internal.dates

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

internal fun currentDate() = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

internal fun dateTimeFormatLength() = 8

internal fun getDate(milliSeconds: Long?, format: String = "ddMMyyyy"): String {
    val cal = Calendar.getInstance()
    val currentTimeZone: TimeZone = cal.getTimeZone()
    val currentDt: Calendar = GregorianCalendar(currentTimeZone, Locale.getDefault())
    var gmtOffset: Int = currentTimeZone.getOffset(
        currentDt[Calendar.ERA],
        currentDt[Calendar.YEAR],
        currentDt[Calendar.MONTH],
        currentDt[Calendar.DAY_OF_MONTH],
        currentDt[Calendar.DAY_OF_WEEK],
        currentDt[Calendar.MILLISECOND],
    )
    gmtOffset /= (60 * 60 * 1000)
    cal.add(Calendar.HOUR_OF_DAY, +gmtOffset)
    return if (milliSeconds != null) {
        cal.timeInMillis = milliSeconds
        val formater = SimpleDateFormat(format)
        if (gmtOffset < 0) {
            var day = formater.format(cal.time).substring(0, 2).toInt()
            day += 1
            formater.format(cal.time).replaceRange(0, 2, String.format("%02d", day))
        } else {
            formater.format(cal.time)
        }
    } else {
        ""
    }
}