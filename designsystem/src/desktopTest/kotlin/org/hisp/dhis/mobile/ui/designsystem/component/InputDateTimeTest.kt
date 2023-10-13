import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.hisp.dhis.mobile.ui.designsystem.component.InputDateTime
import org.junit.Rule
import org.junit.Test

class InputDateTimeTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun dateTimeFieldChangesShouldWorkCorrectly() {
        var input by mutableStateOf("")
        rule.setContent {
            InputDateTime(
                title = "Label",
                value = null,
                onActionClicked = {
                    // no-op
                },
            ) {
                input = it
            }
        }

        rule.onNodeWithTag("INPUT_DATE_TIME_TEXT_FIELD").performTextInput("1002")

        assert(input == "1002")
    }

    @Test
    fun clickingOnRestButtonShouldResetMode() {
        var input by mutableStateOf("")

        rule.setContent {
            InputDateTime(
                title = "Label",
                value = null,
                onActionClicked = {
                    // no-op
                },
            ) {
                input = it
            }
        }

        rule.onNodeWithTag("INPUT_DATE_TIME_RESET_BUTTON").performClick()

        assert(input.isBlank())
    }
}
