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

class InputCoordinateTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldDisplayInputCoordinateCorrectly() {
        rule.setContent {
            InputCoordinate(
                title = "Label",
                onResetButtonClicked = {},
                onUpdateButtonClicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_COORDINATE").assertExists()
        rule.onNodeWithTag("INPUT_COORDINATE_LEGEND").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_COORDINATE_SUPPORTING_TEXT").assertDoesNotExist()
    }

    @Test
    fun shouldAllowAddCoordinateWhenEnabled() {
        rule.setContent {
            InputCoordinate(
                title = "Label",
                onResetButtonClicked = {},
                onUpdateButtonClicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_COORDINATE").assertExists()
        rule.onNodeWithTag("INPUT_COORDINATE_ADD_BUTTON").assertIsEnabled()
    }

    @Test
    fun shouldNotAllowAddCoordinateWhenDisabled() {
        rule.setContent {
            InputCoordinate(
                title = "Label",
                state = InputShellState.DISABLED,
                onResetButtonClicked = {},
                onUpdateButtonClicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_COORDINATE").assertExists()
        rule.onNodeWithTag("INPUT_COORDINATE_ADD_BUTTON").assertIsNotEnabled()
    }

    @Test
    fun shouldShowResetAndEditButtonWhenCoordinateAdded() {
        rule.setContent {
            InputCoordinate(
                title = "Label",
                coordinates = Coordinates(latitude = 39.46263, longitude = -0.33617),
                onResetButtonClicked = {},
                onUpdateButtonClicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_COORDINATE").assertExists()
        rule.onNodeWithTag("INPUT_COORDINATE_ADD_BUTTON").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_COORDINATE_RESET_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_COORDINATE_EDIT_BUTTON").assertExists()
    }

    @Test
    fun shouldHideResetAndEditButtonWhenNoCoordinateAdded() {
        rule.setContent {
            InputCoordinate(
                title = "Label",
                onResetButtonClicked = {},
                onUpdateButtonClicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_COORDINATE").assertExists()
        rule.onNodeWithTag("INPUT_COORDINATE_ADD_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_COORDINATE_RESET_BUTTON").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_COORDINATE_EDIT_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldRemoveCoordinateWhenResetButtonIsClickedAndHideResetAndEditButton() {
        rule.setContent {
            var coordinates by rememberSaveable {
                mutableStateOf<Coordinates?>(Coordinates(latitude = 39.46263, longitude = -0.33617))
            }

            InputCoordinate(
                title = "Label",
                coordinates = coordinates,
                onResetButtonClicked = {
                    coordinates = null
                },
                onUpdateButtonClicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_COORDINATE").assertExists()
        rule.onNodeWithTag("INPUT_COORDINATE_RESET_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_COORDINATE_RESET_BUTTON").performClick()

        rule.onNodeWithTag("INPUT_COORDINATE_ADD_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_COORDINATE_RESET_BUTTON").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_COORDINATE_EDIT_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldHideResetAndEditButtonWhenDisabled() {
        rule.setContent {
            InputCoordinate(
                title = "Label",
                state = InputShellState.DISABLED,
                onResetButtonClicked = {},
                onUpdateButtonClicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_COORDINATE").assertExists()
        rule.onNodeWithTag("INPUT_COORDINATE_RESET_BUTTON").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_COORDINATE_EDIT_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldShowLegendCorrectly() {
        rule.setContent {
            InputCoordinate(
                title = "Label",
                legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
                onResetButtonClicked = {},
                onUpdateButtonClicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_COORDINATE").assertExists()
        rule.onNodeWithTag("INPUT_COORDINATE_LEGEND").assertExists()
        rule.onNodeWithTag("INPUT_COORDINATE_LEGEND").assertHasNoClickAction()
    }

    @Test
    fun shouldShowSupportingTextCorrectly() {
        rule.setContent {
            InputCoordinate(
                title = "Label",
                supportingText = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
                onResetButtonClicked = {},
                onUpdateButtonClicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_COORDINATE").assertExists()
        rule.onNodeWithTag("INPUT_COORDINATE_SUPPORTING_TEXT").assertExists()
    }
}
