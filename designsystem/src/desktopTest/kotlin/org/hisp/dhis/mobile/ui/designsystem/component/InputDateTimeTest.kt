import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.InputDateTime
import org.hisp.dhis.mobile.ui.designsystem.component.InputDateTimeModel
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
                    inputTextFieldValue = input.,
                    onActionClicked = {
                        // no-op
                    },
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
                    onActionClicked = {
                        // no-op
                    },
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
                    onActionClicked = {
                        // no-op
                    },
                    onValueChanged = {
                        input = it ?: TextFieldValue()
                    },
                    format = "ddMMYYYY",

                ),

            )
        }

        rule.onNodeWithTag("INPUT_DATE_TIME_RESET_BUTTON").performClick()

        assert(input.text.isBlank())
    }
}
