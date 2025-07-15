package org.hisp.dhis.mobile.ui.designsystem.component.internal

import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.DateTimeActionType
import org.hisp.dhis.mobile.ui.designsystem.component.SelectableDates
import org.junit.Test

class DateTimeUtilsTest {
    @Test
    fun shouldReturnTrueIfDateIsWithinSelectedDatesRangeAndFalseIfNot() {
        var selectedDates =
            SelectableDates(
                initialDate = "01011990",
                endDate = "01012040",
            )
        assert(dateIsInRange(System.currentTimeMillis(), selectedDates))
        selectedDates =
            SelectableDates(
                initialDate = "01011990",
                endDate = "01011993",
            )
        assert(!dateIsInRange(System.currentTimeMillis(), selectedDates))
    }

    @Test
    fun shouldFormatDateTimeValueTypeStoredDateToUiCorrectly() {
        var storedValue = TextFieldValue("2022-10-12T20:25")
        assert(formatStoredDateToUI(storedValue, DateTimeActionType.DATE_TIME).text == "121020222025")

        storedValue = TextFieldValue("2022-10-1")
        assert(formatStoredDateToUI(storedValue, DateTimeActionType.DATE_TIME).text == "2022-10-1")

        storedValue = TextFieldValue("2022-10-1")
        assert(formatStoredDateToUI(storedValue, DateTimeActionType.DATE_TIME).text == "2022-10-1")

        storedValue = TextFieldValue("2022-10-10T20")
        assert(formatStoredDateToUI(storedValue, DateTimeActionType.DATE_TIME).text == "2022-10-10T20")
    }

    @Test
    fun shouldFormatDateValueTypeStoredDateToUiCorrectly() {
        var storedValue = TextFieldValue("2022-10-12")
        assert(formatStoredDateToUI(storedValue, DateTimeActionType.DATE).text == "12102022")

        storedValue = TextFieldValue("2022-10")
        assert(formatStoredDateToUI(storedValue, DateTimeActionType.DATE).text == "2022-10")

        storedValue = TextFieldValue("2022-10-1")
        assert(formatStoredDateToUI(storedValue, DateTimeActionType.DATE).text == "1102022")

        storedValue = TextFieldValue("2022-10-10")
        assert(formatStoredDateToUI(storedValue, DateTimeActionType.DATE).text == "10102022")
    }

    @Test
    fun shouldFormatTimeValueTypeStoredDateToUiCorrectly() {
        var storedValue = TextFieldValue("20:00")
        assert(formatStoredDateToUI(storedValue, DateTimeActionType.TIME).text == "2000")
        storedValue = TextFieldValue("20")
        assert(formatStoredDateToUI(storedValue, DateTimeActionType.TIME).text == "20")
        storedValue = TextFieldValue("20:0")
        assert(formatStoredDateToUI(storedValue, DateTimeActionType.TIME).text == "200")
    }

    @Test
    fun shouldNotAllowInvalidFormatDates() {
        assert(!isValidDate("Function…"))
        assert(isValidDate("28022020"))
        assert(!isValidDate("99999999"))
        assert(isValidDate("12119999"))
        assert(!isValidDate("12559999"))
        assert(!isValidDate("55129999"))
        assert(isValidDate("12111991"))
    }

    @Test
    fun shouldFormatUiValueToStoredCorrectly() {
        assert(
            formatUIDateToStored(
                convertStringToTextFieldValue("2002"),
                DateTimeActionType.TIME,
            ).text == "20:02",
        )

        assert(
            formatUIDateToStored(
                convertStringToTextFieldValue("200"),
                DateTimeActionType.TIME,
            ).text == "200",
        )

        assert(
            formatUIDateToStored(
                convertStringToTextFieldValue("1230"),
                DateTimeActionType.TIME,
            ).text == "12:30",
        )

        assert(
            formatUIDateToStored(
                convertStringToTextFieldValue("12111991"),
                DateTimeActionType.DATE,
            ).text == "1991-11-12",
        )
        assert(
            formatUIDateToStored(
                convertStringToTextFieldValue("1211199"),
                DateTimeActionType.DATE,
            ).text == "1211199",
        )

        assert(
            formatUIDateToStored(
                convertStringToTextFieldValue("121119911730"),
                DateTimeActionType.DATE_TIME,
            ).text == "1991-11-12T17:30",
        )

        assert(
            formatUIDateToStored(
                convertStringToTextFieldValue("12111991173"),
                DateTimeActionType.DATE_TIME,
            ).text == "12111991173",
        )
    }
}
