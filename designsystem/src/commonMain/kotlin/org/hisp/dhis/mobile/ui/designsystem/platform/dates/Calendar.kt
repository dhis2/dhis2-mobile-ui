@file:OptIn(ExperimentalTime::class)

package org.hisp.dhis.mobile.ui.designsystem.platform.dates

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atTime
import kotlinx.datetime.number
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

val currentDateInMillis: Long
    get() = Clock.System.now().toEpochMilliseconds()

val currentYear: Int
    get() = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).year

fun formatDate(
    dateFormat: String,
    dateInMillis: Long = currentDateInMillis,
): String {
    val instant = Instant.fromEpochMilliseconds(dateInMillis)
    val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

    // kotlinx-datetime does not have a pattern-based formatter yet. This is a basic implementation.
    return dateFormat
        .replace("dd", localDateTime.day.toString().padStart(2, '0'))
        .replace("MM", localDateTime.month.number.toString().padStart(2, '0'))
        .replace("yyyy", localDateTime.year.toString())
}

fun yearInDate(
    dateString: String,
    dateFormat: String,
): Int {
    // This is highly dependent on the format. We assume ddMMyyyy as a common case.
    return try {
        if (dateFormat == "ddMMyyyy" && dateString.length == 8) {
            dateString.substring(4, 8).toInt()
        } else {
            LocalDate.parse(dateString).year // Assumes ISO format YYYY-MM-DD
        }
    } catch (e: Exception) {
        currentYear // return current year as a fallback
    }
}

fun isValidDate(text: String): Boolean {
    return try {
        LocalDate.parse(text) // Assumes ISO format YYYY-MM-DD
        true
    } catch (e: Exception) {
        false
    }
}

internal fun parseStringDateToMillis(
    dateString: String,
    pattern: String = "ddMMyyyy",
): Long {
    return try {
        if (pattern == "ddMMyyyy" && dateString.length == 8) {
            val day = dateString.substring(0, 2).toInt()
            val month = dateString.substring(2, 4).toInt()
            val year = dateString.substring(4, 8).toInt()
            val instant: Instant = LocalDate(year, month, day).atTime(LocalTime(0, 0)).toInstant(TimeZone.UTC)
            instant.toEpochMilliseconds()
        } else {
            val instant: Instant = LocalDate.parse(dateString).atTime(LocalTime(0, 0)).toInstant(TimeZone.UTC)
            instant.toEpochMilliseconds()
        }
    } catch (e: Exception) {
        -1L // Indicate failure
    }
}

internal fun getDate(
    milliSeconds: Long?,
    format: String = "ddMMyyyy",
): String {
    val millis = milliSeconds ?: currentDateInMillis
    return formatDate(format, millis)
}

internal fun normalizeToGregorian(input: String): String {
    return input
}

internal fun localeUsesLatinDigits(): Boolean {
    return true
}