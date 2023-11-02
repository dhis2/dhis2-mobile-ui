package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.junit.Rule
import org.junit.Test

class InputPolygonTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldDisplayInputPolygonCorrectly() {
        rule.setContent {
            InputPolygon(
                title = "Label",
                onResetButtonClicked = {},
                onUpdateButtonClicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_POLYGON").assertExists()
        rule.onNodeWithTag("INPUT_POLYGON_LEGEND").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_POLYGON_SUPPORTING_TEXT").assertDoesNotExist()
    }

    @Test
    fun shouldAllowAddPolygonWhenEnabled() {
        rule.setContent {
            InputPolygon(
                title = "Label",
                onResetButtonClicked = {},
                onUpdateButtonClicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_POLYGON").assertExists()
        rule.onNodeWithTag("INPUT_POLYGON_ADD_BUTTON").assertIsEnabled()
    }

    @Test
    fun shouldNotAllowAddPolygonWhenDisabled() {
        rule.setContent {
            InputPolygon(
                title = "Label",
                state = InputShellState.DISABLED,
                onResetButtonClicked = {},
                onUpdateButtonClicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_POLYGON").assertExists()
        rule.onNodeWithTag("INPUT_POLYGON_ADD_BUTTON").assertIsNotEnabled()
    }

    @Test
    fun shouldShowResetAndEditButtonWhenPolygonAdded() {
        rule.setContent {
            InputPolygon(
                title = "Label",
                polygonAdded = true,
                onResetButtonClicked = {},
                onUpdateButtonClicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_POLYGON").assertExists()
        rule.onNodeWithTag("INPUT_POLYGON_ADD_BUTTON").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_POLYGON_RESET_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_POLYGON_EDIT_BUTTON").assertExists()
    }

    @Test
    fun shouldHideResetAndEditButtonWhenNoPolygonAdded() {
        rule.setContent {
            InputPolygon(
                title = "Label",
                onResetButtonClicked = {},
                onUpdateButtonClicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_POLYGON").assertExists()
        rule.onNodeWithTag("INPUT_POLYGON_ADD_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_POLYGON_RESET_BUTTON").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_POLYGON_EDIT_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldRemovePolygonWhenResetButtonIsClickedAndHideResetAndEditButton() {
        rule.setContent {
            var polygonAdded by rememberSaveable { mutableStateOf(true) }

            InputPolygon(
                title = "Label",
                polygonAdded = polygonAdded,
                onResetButtonClicked = {
                    polygonAdded = false
                },
                onUpdateButtonClicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_POLYGON").assertExists()
        rule.onNodeWithTag("INPUT_POLYGON_RESET_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_POLYGON_RESET_BUTTON").performClick()

        rule.onNodeWithTag("INPUT_POLYGON_ADD_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_POLYGON_RESET_BUTTON").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_POLYGON_EDIT_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldHideResetAndEditButtonWhenDisabled() {
        rule.setContent {
            InputPolygon(
                title = "Label",
                state = InputShellState.DISABLED,
                onResetButtonClicked = {},
                onUpdateButtonClicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_POLYGON").assertExists()
        rule.onNodeWithTag("INPUT_POLYGON_RESET_BUTTON").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_POLYGON_EDIT_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldShowLegendCorrectly() {
        rule.setContent {
            InputPolygon(
                title = "Label",
                legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
                onResetButtonClicked = {},
                onUpdateButtonClicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_POLYGON").assertExists()
        rule.onNodeWithTag("INPUT_POLYGON_LEGEND").assertExists()
        rule.onNodeWithTag("INPUT_POLYGON_LEGEND").assertHasNoClickAction()
    }

    @Test
    fun shouldShowSupportingTextCorrectly() {
        rule.setContent {
            InputPolygon(
                title = "Label",
                supportingText = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
                onResetButtonClicked = {},
                onUpdateButtonClicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_POLYGON").assertExists()
        rule.onNodeWithTag("INPUT_POLYGON_SUPPORTING_TEXT").assertExists()
    }
}
