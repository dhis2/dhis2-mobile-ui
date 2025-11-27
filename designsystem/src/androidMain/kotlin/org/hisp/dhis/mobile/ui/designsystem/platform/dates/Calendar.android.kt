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
): String = SimpleDateFormat(dateFormat, Locale.getDefault()).format(dateInMillis)

internal actual fun getDate(
    milliSeconds: Long?,
    format: String,
): String {
    if (milliSeconds == null) return ""

    val calendar = GregorianCalendar(TimeZone.getTimeZone("UTC"), Locale.getDefault())
    calendar.timeInMillis = milliSeconds

    val formatter = SimpleDateFormat(format, Locale.getDefault())
    formatter.timeZone = TimeZone.getTimeZone("UTC")
    formatter.calendar = calendar

    val date = Date(milliSeconds)
    return formatter.format(date)
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
    val format = SimpleDateFormat("ddMMyyyy", Locale.getDefault())
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
