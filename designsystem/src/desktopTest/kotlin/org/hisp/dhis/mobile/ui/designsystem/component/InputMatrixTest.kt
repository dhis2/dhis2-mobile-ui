package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertAll
import androidx.compose.ui.test.isEnabled
import androidx.compose.ui.test.isNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import org.hisp.dhis.mobile.ui.designsystem.component.internal.IconCardData
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.junit.Rule
import org.junit.Test

class InputMatrixTest {

    @get:Rule
    val rule = createComposeRule()

    private val data = listOf(
        IconCardData(
            uid = "7e0cb105-c276-4f12-9f56-a26af8314121",
            label = "Stethoscope",
            iconRes = "dhis2_stethoscope_positive",
            iconTint = Color(0xFFFF8400),
        ),
        IconCardData(
            uid = "72269f6b-6b99-4d2e-a667-09f20c2097e0",
            label = "Medicines",
            iconRes = "dhis2_medicines_positive",
            iconTint = Color(0xFFEB0085),
        ),
        IconCardData(
            uid = "37b81748-e9b4-4f74-a50a-59b945e54aa4",
            label = "Sayana press",
            iconRes = "dhis2_sayana_press_positive",
            iconTint = Color(0xFF1FDB00),
        ),
    )

    @Test
    fun shouldNotAllowMatrixUserInputWhenDisabled() {
        rule.setContent {
            InputMatrix(
                title = "Label",
                testTag = "TEST",
                data = data,
                selectedData = null,
                itemCount = 2,
                state = InputShellState.DISABLED,
                onSelectionChanged = {
                    // no-op
                },
            )
        }
        rule.onNodeWithTag("ICON_CARD_INPUT_MATRIX_TEST").assertExists()
        rule.onAllNodesWithTag("MATRIX_ICON_CARD_TEST").assertAll(isNotEnabled())
    }

    @Test
    fun shouldAllowMatrixUserInputWhenEnabled() {
        rule.setContent {
            InputMatrix(
                title = "Label",
                testTag = "TEST",
                data = data,
                selectedData = null,
                itemCount = 2,
                state = InputShellState.UNFOCUSED,
                onSelectionChanged = {
                    // no-op
                },
            )
        }
        rule.onNodeWithTag("ICON_CARD_INPUT_MATRIX_TEST").assertExists()
        rule.onAllNodesWithTag("MATRIX_ICON_CARD_TEST").assertAll(isEnabled())
    }

    @Test
    fun shouldShowMatrixLegendCorrectly() {
        rule.setContent {
            InputMatrix(
                title = "Label",
                testTag = "TEST",
                data = data,
                selectedData = null,
                itemCount = 2,
                state = InputShellState.UNFOCUSED,
                legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
                onSelectionChanged = {
                    // no-op
                },
            )
        }

        rule.onNodeWithTag("ICON_CARDS_INPUT_TEST_LEGEND").assertExists()
    }

    @Test
    fun shouldShowMatrixSupportingTextCorrectly() {
        rule.setContent {
            InputMatrix(
                title = "Label",
                testTag = "TEST",
                data = data,
                selectedData = null,
                state = InputShellState.UNFOCUSED,
                supportingText = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
                onSelectionChanged = {
                    // no-op
                },
            )
        }

        rule.onNodeWithTag("ICON_CARDS_INPUT_TEST_SUPPORTING_TEXT").assertExists()
    }
}
