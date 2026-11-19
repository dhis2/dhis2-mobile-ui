package org.hisp.dhis.mobile.ui.designsystem.platform.dates

internal expect val currentDateInMillis: Long
internal expect val currentYear: Int

expect fun formatDate(
    dateFormat: String,
    dateInMillis: Long = currentDateInMillis,
): String

internal expect fun yearInDate(
    dateString: String,
    dateFormat: String,
): Int

internal expect fun isValidDate(text: String): Boolean

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
