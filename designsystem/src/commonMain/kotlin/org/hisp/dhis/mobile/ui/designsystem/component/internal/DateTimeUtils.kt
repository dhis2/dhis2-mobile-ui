package org.hisp.dhis.mobile.ui.designsystem.component.internal

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerColors
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.DateTimeActionType
import org.hisp.dhis.mobile.ui.designsystem.component.InputDateTimeModel
import org.hisp.dhis.mobile.ui.designsystem.component.SelectableDates
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.state.InputDateTimeData
import org.hisp.dhis.mobile.ui.designsystem.component.state.InputDateTimeState
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@Suppress("DEPRECATION")
@Deprecated(
    "This function is deprecated and will be removed in the near future replace with." +
        " New implementation does not take format as a parameter.",
    replaceWith = ReplaceWith("dateIsInRange(date, allowedDates: SelectableDates)"),
)
internal fun dateIsInRange(date: Long, allowedDates: SelectableDates, format: String = "ddMMyyyy"): Boolean {
    return (
        date >= parseStringDateToMillis(allowedDates.initialDate) &&
            date <= parseStringDateToMillis(allowedDates.endDate)
        )
}

internal fun formatStoredDateToUI(textFieldValue: TextFieldValue, valueType: DateTimeActionType?): TextFieldValue {
    try {
        return when (valueType) {
            DateTimeActionType.DATE_TIME -> {
                val components = textFieldValue.text.split("T")
                if (components.size != 2) {
                    return textFieldValue
                }

                val date = components[0].split("-")
                if (date.size < 3) {
                    return textFieldValue
                }

                val year = date[0]
                val month = date[1]
                val day = date[2]

                val time = components[1].split(":")
                if (components.size != 2) {
                    return textFieldValue
                }

                val hours = time[0]
                val minutes = time[1].substring(0, 2)

                val returnValue = "$day$month$year$hours$minutes"
                TextFieldValue(returnValue, textFieldValue.selection, textFieldValue.composition)
            }

            DateTimeActionType.TIME -> {
                val components = textFieldValue.text.split(":")
                if (components.size != 2) {
                    return textFieldValue
                }
                val hours = components[0]
                val minutes = components[1]
                val timeValue = "$hours$minutes"

                TextFieldValue(timeValue, textFieldValue.selection, textFieldValue.composition)
            }

            else -> {
                val components = textFieldValue.text.split("-")
                if (components.size != 3) {
                    return textFieldValue
                }

                val year = components[0]
                val month = components[1]
                val day = components[2]
                val dateValue = "$day$month$year"
                TextFieldValue(dateValue, textFieldValue.selection, textFieldValue.composition)
            }
        }
    } catch (e: Exception) {
        return textFieldValue
    }
}

internal fun dateIsInRange(date: Long, allowedDates: SelectableDates): Boolean {
    return (
        date >= parseStringDateToMillis(allowedDates.initialDate) &&
            date <= parseStringDateToMillis(allowedDates.endDate)
        )
}

fun parseStringDateToMillis(dateString: String): Long {
    val cal = Calendar.getInstance()
    return dateString.parseDate("ddMMyyyy")?.let {
        cal.time = it
        cal.timeInMillis
    } ?: 0L
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

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun provideDatePickerState(inputTextFieldValue: TextFieldValue?, data: InputDateTimeData): DatePickerState {
    return inputTextFieldValue?.text?.takeIf {
        it.isNotEmpty() &&
            yearIsInRange(it, getDefaultFormat(data.actionType), data.yearRange)
    }?.let {
        rememberDatePickerState(
            initialSelectedDateMillis = parseStringDateToMillis(
                dateString = it,
            ),
            yearRange = data.yearRange,
            selectableDates = getSelectableDates(data.selectableDates),
        )
    } ?: rememberDatePickerState(selectableDates = getSelectableDates(data.selectableDates))
}

internal fun getDefaultFormat(actionType: DateTimeActionType): String {
    return when (actionType) {
        DateTimeActionType.DATE -> "ddMMyyyy"
        DateTimeActionType.TIME -> "HHmm"
        DateTimeActionType.DATE_TIME -> "ddMMyyyyHHmm"
    }
}

internal fun formatUIDateToStored(textFieldValue: TextFieldValue, valueType: DateTimeActionType?): TextFieldValue {
    val inputDateString = textFieldValue.text
    return when (valueType) {
        DateTimeActionType.DATE_TIME -> {
            if (inputDateString.length != 12) {
                textFieldValue
            } else {
                val minutes = inputDateString.substring(10, 12)
                val hours = inputDateString.substring(8, 10)
                val year = inputDateString.substring(4, 8)
                val month = inputDateString.substring(2, 4)
                val day = inputDateString.substring(0, 2)
                val dateTimeValue = "$year-$month-$day" + "T$hours:$minutes"
                TextFieldValue(dateTimeValue, textFieldValue.selection, textFieldValue.composition)
            }
        }

        DateTimeActionType.TIME -> {
            if (inputDateString.length != 4 && inputDateString.length != 12) {
                textFieldValue
            } else {
                val minutes = inputDateString.substring(2, 4)
                val hours = inputDateString.substring(0, 2)
                val timeValue = "$hours:$minutes"
                TextFieldValue(timeValue, textFieldValue.selection, textFieldValue.composition)
            }
        }

        else -> {
            if (inputDateString.length != 8) {
                textFieldValue
            } else {
                val year = inputDateString.substring(4, 8)
                val month = inputDateString.substring(2, 4)
                val day = inputDateString.substring(0, 2)
                val dateValue = "$year-$month-$day"
                TextFieldValue(dateValue, textFieldValue.selection, textFieldValue.composition)
            }
        }
    }
}

fun String.parseDate(pattern: String): Date? {
    return if (isNotEmpty() && length == pattern.length) {
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        sdf.parse(this)
    } else {
        null
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun timePickerColors(): TimePickerColors {
    return TimePickerDefaults.colors(
        containerColor = SurfaceColor.Container,
        clockDialColor = SurfaceColor.ContainerHigh,
        clockDialUnselectedContentColor = TextColor.OnSurface,
        clockDialSelectedContentColor = TextColor.OnPrimary,
        timeSelectorSelectedContentColor = TextColor.OnPrimaryContainer,
        timeSelectorUnselectedContainerColor = SurfaceColor.ContainerHigh,
        timeSelectorUnselectedContentColor = TextColor.OnSurface,
        periodSelectorSelectedContainerColor = SurfaceColor.WarningContainer,
        periodSelectorUnselectedContentColor = TextColor.OnSurfaceVariant,
        periodSelectorSelectedContentColor = SurfaceColor.Warning,
        periodSelectorUnselectedContainerColor = SurfaceColor.Container,
        selectorColor = SurfaceColor.Primary,
        timeSelectorSelectedContainerColor = SurfaceColor.ContainerLow,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
internal fun getTime(timePickerState: TimePickerState, format: String? = "HHmm"): String {
    val cal = Calendar.getInstance()
    cal.set(Calendar.HOUR_OF_DAY, timePickerState.hour)
    cal.set(Calendar.MINUTE, timePickerState.minute)
    cal.set(Calendar.SECOND, 0)
    cal.set(Calendar.MILLISECOND, 0)

    val formater = SimpleDateFormat(format)
    return formater.format(cal.time)
}

@Suppress("deprecation")
@Deprecated("This function is deprecated and will be removed once new implementation is added to the capture app. ")
@OptIn(ExperimentalMaterial3Api::class)
fun getSelectableDates(uiModel: InputDateTimeModel): androidx.compose.material3.SelectableDates {
    return object : androidx.compose.material3.SelectableDates {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            return dateIsInRange(utcTimeMillis, uiModel.selectableDates, uiModel.format)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
fun getSelectableDates(selectableDates: SelectableDates): androidx.compose.material3.SelectableDates {
    return object : androidx.compose.material3.SelectableDates {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            return dateIsInRange(utcTimeMillis, selectableDates)
        }
    }
}

@Deprecated("This function is deprecated and will be removed in the next release. Use overloaded fun  instead.")
@Suppress("DEPRECATION")
fun getSupportingTextList(uiModel: InputDateTimeModel, dateOutOfRangeItem: SupportingTextData, incorrectHourFormatItem: SupportingTextData, incorrectDateFormatItem: SupportingTextData): List<SupportingTextData> {
    val supportingTextList = mutableListOf<SupportingTextData>()

    uiModel.supportingText?.forEach { item ->
        supportingTextList.add(item)
    }
    if (!uiModel.inputTextFieldValue?.text.isNullOrEmpty()) {
        val dateIsInRange: Boolean
        val dateIsInYearRange: Boolean
        val isValidHourFormat: Boolean
        val isValidDateFormat: Boolean

        when (uiModel.actionType) {
            DateTimeActionType.TIME -> {
                if (uiModel.inputTextFieldValue?.text!!.length == 4) {
                    isValidHourFormat = isValidHourFormat(uiModel.inputTextFieldValue.text)
                    if (!isValidHourFormat) supportingTextList.add(incorrectHourFormatItem)
                    uiModel.supportingText
                }
            }
            DateTimeActionType.DATE_TIME -> {
                if (uiModel.inputTextFieldValue?.text!!.length == 12) {
                    dateIsInRange = dateIsInRange(
                        parseStringDateToMillis(
                            uiModel.inputTextFieldValue.text.substring(0, uiModel.inputTextFieldValue.text.length - 4),
                        ),
                        uiModel.selectableDates, uiModel.format,
                    )
                    dateIsInYearRange = yearIsInRange(uiModel.inputTextFieldValue.text, getDefaultFormat(uiModel.actionType), uiModel.yearRange)
                    isValidHourFormat = isValidHourFormat(uiModel.inputTextFieldValue.text.substring(8, 12))
                    isValidDateFormat = isValidDate(uiModel.inputTextFieldValue.text.substring(0, 8))
                    if (!dateIsInRange || !dateIsInYearRange) supportingTextList.add(dateOutOfRangeItem)
                    if (!isValidDateFormat) supportingTextList.add(incorrectDateFormatItem)
                    if (!isValidHourFormat) supportingTextList.add(incorrectHourFormatItem)
                }
            }
            DateTimeActionType.DATE -> {
                if (uiModel.inputTextFieldValue?.text!!.length == 8) {
                    dateIsInRange = dateIsInRange(parseStringDateToMillis(uiModel.inputTextFieldValue.text), uiModel.selectableDates, uiModel.format)
                    isValidDateFormat = isValidDate(uiModel.inputTextFieldValue.text)
                    dateIsInYearRange = yearIsInRange(uiModel.inputTextFieldValue.text, getDefaultFormat(uiModel.actionType), uiModel.yearRange)
                    if (!dateIsInRange || !dateIsInYearRange) supportingTextList.add(dateOutOfRangeItem)
                    if (!isValidDateFormat) supportingTextList.add(incorrectDateFormatItem)
                }
            }
        }
    }
    return supportingTextList.toList()
}

fun getSupportingTextList(
    state: InputDateTimeState,
    uiValue: TextFieldValue,
    data: InputDateTimeData,
    dateOutOfRangeItem: SupportingTextData,
    incorrectHourFormatItem: SupportingTextData,
    incorrectDateFormatItem: SupportingTextData,

): List<SupportingTextData> {
    val supportingTextList = state.supportingText?.toMutableList() ?: mutableListOf()

    if (uiValue.text.isNotEmpty()) {
        when (data.actionType) {
            DateTimeActionType.TIME -> {
                getTimeSupportingTextList(uiValue, supportingTextList, incorrectHourFormatItem)
            }
            DateTimeActionType.DATE_TIME -> {
                getDateTimeSupportingTextList(uiValue, dateOutOfRangeItem, incorrectDateFormatItem, incorrectHourFormatItem, state, data, supportingTextList)
            }
            DateTimeActionType.DATE -> {
                getDateSupportingText(uiValue, data, supportingTextList, dateOutOfRangeItem, incorrectDateFormatItem)
            }
        }
    }
    return supportingTextList.toList()
}

fun getDateSupportingText(uiValue: TextFieldValue, data: InputDateTimeData, supportingTextList: MutableList<SupportingTextData>, dateOutOfRangeItem: SupportingTextData, incorrectDateFormatItem: SupportingTextData): List<SupportingTextData> {
    if (uiValue.text.length == 8) {
        val dateIsInRange = dateIsInRange(parseStringDateToMillis(uiValue.text), data.selectableDates)
        val isValidDateFormat = isValidDate(uiValue.text)
        val dateIsInYearRange = yearIsInRange(uiValue.text, getDefaultFormat(data.actionType), data.yearRange)
        if (!dateIsInRange || !dateIsInYearRange) supportingTextList.add(dateOutOfRangeItem)
        if (!isValidDateFormat) supportingTextList.add(incorrectDateFormatItem)
    }
    return supportingTextList
}

fun getDateTimeSupportingTextList(
    uiValue: TextFieldValue,
    dateOutOfRangeItem: SupportingTextData,
    incorrectDateFormatItem: SupportingTextData,
    incorrectHourFormatItem: SupportingTextData,
    state: InputDateTimeState,
    data: InputDateTimeData,
    supportingTextList: MutableList<SupportingTextData>,
): List<SupportingTextData> {
    if (uiValue.text.length == 12) {
        val dateIsInRange = dateIsInRange(
            parseStringDateToMillis(
                state.inputTextFieldValue!!.text.substring(0, state.inputTextFieldValue!!.text.length - 4),
            ),
            data.selectableDates,
        )
        val dateIsInYearRange = yearIsInRange(uiValue.text, getDefaultFormat(data.actionType), data.yearRange)
        val isValidHourFormat = isValidHourFormat(uiValue.text.substring(8, 12))
        val isValidDateFormat = isValidDate(uiValue.text.substring(0, 8))
        if (!dateIsInRange || !dateIsInYearRange) supportingTextList.add(dateOutOfRangeItem)
        if (!isValidDateFormat) supportingTextList.add(incorrectDateFormatItem)
        if (!isValidHourFormat) supportingTextList.add(incorrectHourFormatItem)
    }
    return supportingTextList
}

fun getTimeSupportingTextList(inputTextFieldValue: TextFieldValue?, supportingTextList: MutableList<SupportingTextData>, incorrectHourFormatItem: SupportingTextData): List<SupportingTextData> {
    if (inputTextFieldValue?.text!!.length == 4 && !isValidHourFormat(inputTextFieldValue.text)) {
        supportingTextList.add(incorrectHourFormatItem)
    }
    return supportingTextList
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun getTimePickerState(state: InputDateTimeState, uiData: InputDateTimeData): TimePickerState {
    return if (state.inputTextFieldValue?.text?.isNotEmpty() == true && uiData.actionType == DateTimeActionType.TIME && isValidHourFormat(state.inputTextFieldValue?.text ?: "")) {
        rememberTimePickerState(
            initialHour = state.inputTextFieldValue!!.text.substring(0, 2)
                .toInt(),
            state.inputTextFieldValue?.text!!.substring(2, 4).toInt(),
            is24Hour = uiData.is24hourFormat,
        )
    } else if (state.inputTextFieldValue?.text?.length == 12 && isValidHourFormat(state.inputTextFieldValue!!.text.substring(8, 12))) {
        rememberTimePickerState(
            initialHour = state.inputTextFieldValue?.text?.substring(state.inputTextFieldValue!!.text.length - 4, state.inputTextFieldValue!!.text.length - 2)!!
                .toInt(),
            state.inputTextFieldValue!!.text.substring(state.inputTextFieldValue!!.text.length - 2, state.inputTextFieldValue!!.text.length).toInt(),
            is24Hour = uiData.is24hourFormat,
        )
    } else {
        rememberTimePickerState(0, 0, is24Hour = uiData.is24hourFormat)
    }
}
