package org.hisp.dhis.mobile.ui.designsystem.platform.dates

import platform.Foundation.NSCalendar
import platform.Foundation.NSCalendarUnitYear
import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterDecimalStyle
import platform.Foundation.NSTimeZone
import platform.Foundation.currentLocale
import platform.Foundation.localTimeZone
import platform.Foundation.numberWithInt
import platform.Foundation.numberWithLongLong

actual val currentDateInMillis: Long = (NSDate().timeIntervalSinceReferenceDate * 1000).toLong()

actual val currentYear: Int =
    run {
        val calendar = NSCalendar.currentCalendar
        val components =
            calendar.components(
                NSCalendarUnitYear,
                fromDate = NSDate(),
            )
        return@run components.year.toInt()
    }

actual fun formatDate(
    dateFormat: String,
    dateInMillis: Long,
): String {
    val formatter =
        NSDateFormatter().apply {
            this.dateFormat = dateFormat
            timeZone = NSTimeZone.localTimeZone
        }
    // Convert millis to seconds for NSDate
    val date = NSDate(dateInMillis / 1000.0)
    return formatter.stringFromDate(date)
}

internal actual fun getDate(
    milliSeconds: Long?,
    format: String,
): String {
    if (milliSeconds == null) return ""

    // Convert milliseconds to seconds
    val timeInSeconds = milliSeconds / 1000

    // Create date formatter
    val formatter =
        NSDateFormatter().apply {
            dateFormat = format
            timeZone = NSTimeZone.localTimeZone
        }

    // Format the date
    return formatter.stringFromDate(NSDate(timeInSeconds.toDouble()))
}

internal actual fun parseStringDateToMillis(
    dateString: String,
    pattern: String,
): Long {
    val formatter =
        NSDateFormatter().apply {
            this.dateFormat = pattern
            timeZone = NSTimeZone.localTimeZone
        }
    return (formatter.dateFromString(dateString)?.timeIntervalSinceReferenceDate?.toLong() ?: 0) * 1000
}

actual fun yearInDate(
    dateString: String,
    dateFormat: String,
): Int {
    val formatter =
        NSDateFormatter().apply {
            this.dateFormat = dateFormat
            timeZone = NSTimeZone.localTimeZone
        }
    val date = formatter.dateFromString(dateString) ?: return -1

    val calendar = NSCalendar.currentCalendar
    val components =
        calendar.components(
            NSCalendarUnitYear,
            fromDate = date,
        )
    return components.year.toInt()
}

actual fun isValidDate(text: String): Boolean {
    val formatter =
        NSDateFormatter().apply {
            this.dateFormat = "ddMMyyyy"
            timeZone = NSTimeZone.localTimeZone
        }
    val date = formatter.dateFromString(text)
    return date != null
}

internal actual fun normalizeToGregorian(input: String): String {
    if (input.isBlank()) {
        return ""
    }

    // This approach, like the desktop one, focuses on replacing digits,
    // assuming the input string's structure is Gregorian-like
    // but might use locale-specific digits (e.g., Eastern Arabic numerals).

    val currentLocale = NSLocale.currentLocale
    val numberFormatter =
        NSNumberFormatter().apply {
            locale = currentLocale
            numberStyle = NSNumberFormatterDecimalStyle // Ensures we get digit symbols
        }

    // Try to get the locale's zero digit. This is less direct in Foundation than in Java.
    // We can format "0" and see what character it is.
    val localeZeroCharString = numberFormatter.stringFromNumber(NSNumber.numberWithInt(0))
    if (localeZeroCharString == null || localeZeroCharString.length != 1) {
        // Fallback or error: cannot determine locale's zero digit
        // In this case, assume Latin digits or return input as is.
        // This scenario is unlikely for valid locales.
        println("Warning: Could not determine locale's zero digit. Assuming Latin digits for normalization.")
        return input.filter { it.isDigit() || it in listOf('/', '-', '.') } // Basic filter for common date chars
    }

    // If the locale's zero digit is already '0' (Latin), no digit conversion is needed.
    // However, the `localeUsesLatinDigits()` check below is more comprehensive for this.
    if (localeUsesLatinDigits()) { // Use the helper to see if conversion is needed
        return input // No digit normalization needed if locale already uses Latin digits for numbers
    }

    val output = StringBuilder()
    for (char_in in input) {
        var mappedChar = char_in
        if (char_in.isDigit()) { // Check if it's potentially a digit in any numeral system
            // Attempt to map locale-specific digit to Latin digit
            // This is tricky without direct access to DecimalFormatSymbols like in Java.
            // The mapping logic from desktop (zeroDigit + i) is based on contiguous digit characters.
            for (i in 0..9) {
                // Format each digit (0-9) using the locale-specific formatter
                // and see if the input character matches any of them.
                val localeDigitString = numberFormatter.stringFromNumber(NSNumber.numberWithInt(i))
                if (localeDigitString != null && localeDigitString.length == 1 && localeDigitString[0] == char_in) {
                    mappedChar = '0' + i // Convert to Latin digit '0' through '9'
                    break
                }
            }
            // If it wasn't a recognized locale-specific digit but `char_in.isDigit()` was true,
            // it might be a Latin digit already, or some other unicode digit. Keep it if so.
        }
        output.append(mappedChar)
    }
    return output.toString()
}

internal actual fun localeUsesLatinDigits(): Boolean {
    val currentLocale = NSLocale.currentLocale
    val numberFormatter =
        NSNumberFormatter().apply {
            locale = currentLocale
        }

    val testKotlinNumber = 1234567890L
    val testNSNumber = NSNumber.numberWithLongLong(testKotlinNumber) // Convert Kotlin Long to NSNumber

    val formattedNumber = numberFormatter.stringFromNumber(testNSNumber)

    if (formattedNumber == null) {
        println("Warning: Could not format test number to check for Latin digits. Assuming true.")
        return true
    }

    for (char_in in formattedNumber) {
        if (char_in.isDigit()) {
            if (char_in !in '0'..'9') {
                return false
            }
        }
    }
    return true
}
