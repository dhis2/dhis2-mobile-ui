package org.hisp.dhis.mobile.ui.designsystem.platform.dates

import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar
import java.util.Locale
import java.util.TimeZone

actual val currentDateInMillis = Calendar.getInstance().timeInMillis

actual val currentYear: Int
    get() = Calendar.getInstance().get(Calendar.YEAR)

actual fun formatDate(
    dateFormat: String,
    dateInMillis: Long,
): String = SimpleDateFormat(dateFormat).format(dateInMillis)

internal actual fun getDate(
    milliSeconds: Long?,
    format: String,
): String {
    val cal = Calendar.getInstance()
    val currentTimeZone: TimeZone = cal.getTimeZone()
    val currentDt: Calendar = GregorianCalendar(currentTimeZone, Locale.getDefault())
    var gmtOffset: Int =
        currentTimeZone.getOffset(
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

internal actual fun parseStringDateToMillis(
    dateString: String,
    pattern: String,
): Long {
    val cal = Calendar.getInstance()
    return dateString.parseDate(pattern)?.let {
        cal.time = it
        cal.timeInMillis
    } ?: 0L
}

internal fun String.parseDate(pattern: String): Date? =
    if (isNotEmpty() && length == pattern.length) {
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        sdf.parse(this)
    } else {
        null
    }

actual fun yearInDate(
    dateString: String,
    dateFormat: String,
): Int {
    val cal = Calendar.getInstance()
    return dateString.parseDate(dateFormat)?.let {
        cal.time = it
        cal.get(Calendar.YEAR)
    } ?: -1
}

actual fun isValidDate(text: String): Boolean {
    if (text.length != 8) return false
    val format = SimpleDateFormat("ddMMyyyy")
    format.isLenient = false
    return try {
        format.parse(text)
        true
    } catch (_: ParseException) {
        false
    }
}

internal actual fun normalizeToGregorian(input: String): String {
    val symbols = DecimalFormatSymbols(Locale.getDefault())
    val zeroDigit = symbols.zeroDigit
    val arabicToGregorianMap =
        (0..9).associate {
            (zeroDigit + it) to ('0' + it)
        }
    return input.map { arabicToGregorianMap[it] ?: it }.joinToString("")
}

internal actual fun localeUsesLatinDigits(): Boolean {
    val locale = Locale.getDefault()
    val numberFormat = NumberFormat.getInstance(locale)
    val formatted = numberFormat.format(1234567890)
    val digitsOnly = formatted.filter { it.isDigit() }
    return digitsOnly.all { it in '0'..'9' }
}
