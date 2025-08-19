package org.hisp.dhis.mobile.ui.designsystem.platform.dates

expect val currentDateInMillis: Long
expect val currentYear: Int

expect fun formatDate(
    dateFormat: String,
    dateInMillis: Long = currentDateInMillis,
): String

expect fun yearInDate(
    dateString: String,
    dateFormat: String,
): Int

expect fun isValidDate(text: String): Boolean

internal expect fun parseStringDateToMillis(
    dateString: String,
    pattern: String = "ddMMyyyy",
): Long

internal expect fun getDate(
    milliSeconds: Long?,
    format: String = "ddMMyyyy",
): String

internal expect fun normalizeToGregorian(input: String): String

internal expect fun localeUsesLatinDigits(): Boolean
