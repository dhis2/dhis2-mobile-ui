package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.junit.Rule
import org.junit.Test

class InputImageTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldDisplayInputImageCorrectly() {
        rule.setContent {
            InputImage(
                title = "Label",
                uploadState = UploadState.ADD,
                load = { },
                addButtonText = "ADD",
                testTag = "IMAGE",
                onDownloadButtonClick = {},
                onResetButtonClicked = {
                },
                onAddButtonClicked = {
                },
            )
        }
        rule.onNodeWithTag("INPUT_IMAGE").assertExists()
        rule.onNodeWithTag("INPUT_IMAGE_LEGEND").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_IMAGE_SUPPORTING_TEXT").assertDoesNotExist()
    }

    @Test
    fun shouldAllowAddImageWhenEnabled() {
        rule.setContent {
            InputImage(
                title = "Label",
                uploadState = UploadState.ADD,
                load = { },
                addButtonText = "ADD",
                testTag = "IMAGE",
                onDownloadButtonClick = {},
                onResetButtonClicked = {
                },
                onAddButtonClicked = {
                },
            )
        }
        rule.onNodeWithTag("INPUT_IMAGE").assertExists()
        rule.onNodeWithTag("INPUT_IMAGE_ADD_BUTTON").assertIsEnabled()
    }

    @Test
    fun shouldNotAllowAddImageWhenDisabled() {
        rule.setContent {
            InputImage(
                title = "Label",
                state = InputShellState.DISABLED,
                uploadState = UploadState.ADD,
                load = { },
                addButtonText = "ADD",
                testTag = "IMAGE",
                onDownloadButtonClick = {},
                onResetButtonClicked = {
                },
                onAddButtonClicked = {
                },
            )
        }
        rule.onNodeWithTag("INPUT_IMAGE").assertExists()
        rule.onNodeWithTag("INPUT_IMAGE_ADD_BUTTON").assertIsNotEnabled()
    }

    @Test
    fun shouldShowResetButtonWhenImageAdded() {
        rule.setContent {
            InputImage(
                title = "Label",
                uploadState = UploadState.LOADED,
                load = { },
                addButtonText = "ADD",
                testTag = "IMAGE",
                onDownloadButtonClick = {},
                onResetButtonClicked = {
                },
                onAddButtonClicked = {
                },
            )
        }
        rule.onNodeWithTag("INPUT_IMAGE").assertExists()
        rule.onNodeWithTag("INPUT_IMAGE_ADD_BUTTON").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_IMAGE_RESET_BUTTON").assertExists()
    }

    @Test
    fun shouldHideResetAndEditButtonWhenNoImageAdded() {
        rule.setContent {
            InputImage(
                title = "Label",
                uploadState = UploadState.ADD,
                load = { },
                addButtonText = "ADD",
                testTag = "IMAGE",
                onDownloadButtonClick = {},
                onResetButtonClicked = {
                },
                onAddButtonClicked = {
                },
            )
        }
        rule.onNodeWithTag("INPUT_IMAGE").assertExists()
        rule.onNodeWithTag("INPUT_IMAGE_ADD_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_IMAGE_RESET_BUTTON").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_IMAGE_EDIT_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldRemoveImageWhenResetButtonIsClickedAndHideResetAndEditButton() {
        rule.setContent {
            var currentState by rememberSaveable { mutableStateOf(UploadState.LOADED) }
            InputImage(
                title = "Label",
                uploadState = currentState,
                load = { },
                addButtonText = "ADD",
                testTag = "IMAGE",
                onDownloadButtonClick = {},
                onResetButtonClicked = {
                    currentState = UploadState.ADD
                },
                onAddButtonClicked = {
                },
            )
        }
        rule.onNodeWithTag("INPUT_IMAGE").assertExists()
        rule.onNodeWithTag("INPUT_IMAGE_RESET_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_IMAGE_RESET_BUTTON").performClick()

        rule.onNodeWithTag("INPUT_IMAGE_ADD_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_IMAGE_RESET_BUTTON").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_IMAGE_EDIT_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldHideResetAndEditButtonWhenDisabled() {
        rule.setContent {
            InputImage(
                title = "Label",
                uploadState = UploadState.ADD,
                load = { },
                addButtonText = "ADD",
                testTag = "IMAGE",
                onDownloadButtonClick = {},
                onResetButtonClicked = {
                },
                onAddButtonClicked = {
                },
            )
        }
        rule.onNodeWithTag("INPUT_IMAGE").assertExists()
        rule.onNodeWithTag("INPUT_IMAGE_RESET_BUTTON").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_IMAGE_EDIT_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldShowLegendCorrectly() {
        rule.setContent {
            InputImage(
                title = "Label",
                uploadState = UploadState.ADD,
                legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
                load = { },
                addButtonText = "ADD",
                testTag = "IMAGE",
                onDownloadButtonClick = {},
                onResetButtonClicked = {
                },
                onAddButtonClicked = {
                },
            )
        }
        rule.onNodeWithTag("INPUT_IMAGE").assertExists()
        rule.onNodeWithTag("INPUT_IMAGE_LEGEND").assertExists()
        rule.onNodeWithTag("INPUT_IMAGE_LEGEND").assertHasClickAction()
    }

    @Test
    fun shouldShowSupportingTextCorrectly() {
        rule.setContent {
            InputImage(
                title = "Label",
                uploadState = UploadState.ADD,
                supportingText = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
                load = { },
                addButtonText = "ADD",
                testTag = "IMAGE",
                onDownloadButtonClick = {},
                onResetButtonClicked = {
                },
                onAddButtonClicked = {
                },
            )
        }
        rule.onNodeWithTag("INPUT_IMAGE").assertExists()
        rule.onNodeWithTag("INPUT_IMAGE_SUPPORTING_TEXT").assertExists()
    }
}
