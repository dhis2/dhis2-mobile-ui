package org.hisp.dhis.mobile.ui.designsystem.component.internal

import org.hisp.dhis.mobile.ui.designsystem.component.SelectableDates
import org.hisp.dhis.mobile.ui.designsystem.component.parseDate
import org.hisp.dhis.mobile.ui.designsystem.component.parseStringDateToMillis
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar

internal fun dateIsInRange(date: Long, allowedDates: SelectableDates, format: String = "ddMMyyyy"): Boolean {
    return (
        date >= parseStringDateToMillis(allowedDates.initialDate, format) &&
            date <= parseStringDateToMillis(allowedDates.endDate, format)
        )
}

internal fun yearIsInRange(date: String, pattern: String, yearRange: IntRange): Boolean {
    val cal = Calendar.getInstance()
    return date.parseDate(pattern)?.let {
        cal.time = it
        yearRange.contains(cal.get(Calendar.YEAR))
    } ?: false
}

internal fun isValidHourFormat(timeString: String): Boolean {
    val hourRange = IntRange(0, 24)
    val minuteRange = IntRange(0, 60)

    return timeString.length == 4 && hourRange.contains(timeString.substring(0, 2).toInt()) &&
        minuteRange.contains(timeString.substring(2, 4).toInt())
}

internal fun isValidDate(text: String): Boolean {
    if (text.length != 8) return false
    val format = SimpleDateFormat("ddMMyyyy")
    format.isLenient = false
    return try {
        format.parse(text)
        true
    } catch (e: ParseException) {
        false
    }
}
