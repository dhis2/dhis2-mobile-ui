package org.hisp.dhis.mobile.ui.designsystem.component.internal.dates

import kotlinx.datetime.LocalDate.Companion.Format
import kotlinx.datetime.LocalDateTime.Companion.Format as DateTimeFormat
import kotlinx.datetime.LocalTime.Companion.Format as TimeFormat

internal val dateFormat = Format {
    dayOfMonth()
    monthNumber()
    year()
}

internal val timeFormat = TimeFormat {
    hour()
    minute()
}

internal val dateTimeFormat = DateTimeFormat {
    dayOfMonth()
    monthNumber()
    year()
    hour()
    minute()
}
