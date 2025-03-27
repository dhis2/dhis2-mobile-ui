import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.DateTimeActionType
import org.hisp.dhis.mobile.ui.designsystem.component.InputDateTime
import org.hisp.dhis.mobile.ui.designsystem.component.SelectableDates
import org.hisp.dhis.mobile.ui.designsystem.component.model.DateTransformation
import org.hisp.dhis.mobile.ui.designsystem.component.model.TimeTransformation
import org.hisp.dhis.mobile.ui.designsystem.component.state.InputDateTimeData
import org.hisp.dhis.mobile.ui.designsystem.component.state.rememberInputDateTimeState
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
                state = rememberInputDateTimeState(
                    inputDateTimeData =
                    InputDateTimeData(
                        title = "label",
                        visualTransformation = DateTransformation(),
                        actionType = DateTimeActionType.DATE,
                    ),
                    inputTextFieldValue = input,
                ),

                onValueChanged = {
                    input = it ?: TextFieldValue()
                },

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
                state = rememberInputDateTimeState(
                    inputDateTimeData =
                    InputDateTimeData(
                        title = "label",
                        visualTransformation = DateTransformation(),
                        actionType = DateTimeActionType.DATE,
                    ),
                    inputTextFieldValue = input,
                ),

                onValueChanged = {
                    input = it ?: TextFieldValue()
                },

            )
        }

        rule.onNodeWithTag("INPUT_DATE_TIME_RESET_BUTTON").assertDoesNotExist()
    }

    @Test
    fun clickingOnResetButtonShouldClearInput() {
        var input by mutableStateOf(TextFieldValue("10:02"))

        rule.setContent {
            InputDateTime(
                state = rememberInputDateTimeState(
                    inputDateTimeData =
                    InputDateTimeData(
                        title = "label",
                        visualTransformation = TimeTransformation(),
                        actionType = DateTimeActionType.TIME,
                    ),
                    inputTextFieldValue = input,
                ),

                onValueChanged = {
                    input = it ?: TextFieldValue()
                },

            )
        }

        rule.onNodeWithTag("INPUT_DATE_TIME_RESET_BUTTON").performClick()

        assert(input.text.isBlank())
    }

    @Test
    fun clickingOnActionButtonForDateInputShouldShowDatePicker() {
        var input by mutableStateOf(TextFieldValue("1991-10-21"))

        rule.setContent {
            InputDateTime(
                state = rememberInputDateTimeState(
                    inputDateTimeData =
                    InputDateTimeData(
                        title = "label",
                        visualTransformation = DateTransformation(),
                        actionType = DateTimeActionType.DATE,
                        selectableDates = SelectableDates("01092024", "12122024"),
                    ),
                    inputTextFieldValue = input,
                ),

                onValueChanged = {
                    input = it ?: TextFieldValue()
                },

            )
        }

        rule.onNodeWithTag("INPUT_DATE_TIME_ACTION_BUTTON").performClick()
        rule.onNodeWithTag("DATE_PICKER").assertExists()
        rule.onNodeWithText("21 oct 1991").assertExists()
    }

    @Test
    fun clickingOnActionButtonForTimeInputShouldShowTimePicker() {
        var input by mutableStateOf(TextFieldValue("19:00"))

        rule.setContent {
            InputDateTime(
                state = rememberInputDateTimeState(
                    inputDateTimeData =
                    InputDateTimeData(
                        title = "label",
                        visualTransformation = TimeTransformation(),
                        actionType = DateTimeActionType.TIME,
                        selectableDates = SelectableDates("01092024", "12122024"),
                    ),
                    inputTextFieldValue = input,
                ),

                onValueChanged = {
                    input = it ?: TextFieldValue()
                },

            )
        }

        rule.onNodeWithTag("INPUT_DATE_TIME_ACTION_BUTTON").performClick()
        rule.onNodeWithTag("TIME_PICKER").assertExists()
        rule.onNodeWithText("19:00").assertExists()
    }
}
