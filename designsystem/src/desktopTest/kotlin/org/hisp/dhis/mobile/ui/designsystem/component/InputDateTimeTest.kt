import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.DateTimeActionType
import org.hisp.dhis.mobile.ui.designsystem.component.InputDateTime
import org.hisp.dhis.mobile.ui.designsystem.component.InputDateTimeModel
import org.hisp.dhis.mobile.ui.designsystem.component.SelectableDates
import org.hisp.dhis.mobile.ui.designsystem.component.internal.DateTransformation
import org.junit.Rule
import org.junit.Test

class InputDateTimeTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun dateTimeFieldChangesShouldWorkCorrectly() {
        var input by mutableStateOf(TextFieldValue())
        rule.setContent {
            InputDateTime(
                InputDateTimeModel(
                    title = "Label",
                    inputTextFieldValue = input,
                    onValueChanged = {
                        input = it ?: TextFieldValue()
                    },
                    format = "ddMMYYYY",
                ),

            )
        }

        rule.onNodeWithTag("INPUT_DATE_TIME_TEXT_FIELD").performTextInput("1002")

        assert(input.text == "1002")
    }

    @Test
    fun resetButtonShouldNotBeShownWhenTextIsEmpty() {
        var input by mutableStateOf(TextFieldValue())

        rule.setContent {
            InputDateTime(
                InputDateTimeModel(
                    title = "Label",
                    inputTextFieldValue = input,
                    onValueChanged =
                    {
                        input = it ?: TextFieldValue()
                    },
                    format = "ddMMYYYY",

                ),

            )
        }

        rule.onNodeWithTag("INPUT_DATE_TIME_RESET_BUTTON").assertDoesNotExist()
    }

    @Test
    fun clickingOnResetButtonShouldClearInput() {
        var input by mutableStateOf(TextFieldValue("1002"))

        rule.setContent {
            InputDateTime(
                InputDateTimeModel(
                    title = "Label",
                    inputTextFieldValue = input,
                    onValueChanged = {
                        input = it ?: TextFieldValue()
                    },
                    format = "HHMM",
                    actionType = DateTimeActionType.TIME,
                ),

            )
        }

        rule.onNodeWithTag("INPUT_DATE_TIME_RESET_BUTTON").performClick()

        assert(input.text.isBlank())
    }

    @Test
    fun clickingOnActionButtonForDateInputShouldShowDatePicker() {
        var input by mutableStateOf(TextFieldValue("10021991"))

        rule.setContent {
            InputDateTime(
                InputDateTimeModel(
                    title = "Label",
                    inputTextFieldValue = input,
                    visualTransformation = DateTransformation(),
                    actionType = DateTimeActionType.DATE,
                    onValueChanged = { input = it ?: TextFieldValue() },
                    format = "ddMMyyyy",
                    selectableDates = SelectableDates("01092024", "12122024"),
                ),
            )
        }

        rule.onNodeWithTag("INPUT_DATE_TIME_ACTION_BUTTON").performClick()
        rule.onNodeWithTag("DATE_PICKER").assertExists()
    }

    @Test
    fun clickingOnActionButtonForTimeInputShouldShowTimePicker() {
        var input by mutableStateOf(TextFieldValue("100219911900"))

        rule.setContent {
            InputDateTime(
                InputDateTimeModel(
                    title = "Label",
                    inputTextFieldValue = input,
                    visualTransformation = DateTransformation(),
                    actionType = DateTimeActionType.TIME,
                    onValueChanged = { input = it ?: TextFieldValue() },
                    format = "ddMMyyyy",
                    selectableDates = SelectableDates("01092024", "12122024"),
                ),
            )
        }

        rule.onNodeWithTag("INPUT_DATE_TIME_ACTION_BUTTON").performClick()
        rule.onNodeWithTag("TIME_PICKER").assertExists()
    }
}
