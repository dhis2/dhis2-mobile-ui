package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileUpload
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

class BasicInputImageTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldDisplayInputImageCorrectly() {
        rule.setContent {
            BasicInputImage(
                title = "Label",
                uploadState = UploadState.ADD,
                load = { },
                addButtonText = "ADD",
                addButtonIcon = Icons.Outlined.FileUpload,
                testTag = "IMAGE",
                onDownloadButtonClick = {},
                onResetButtonClicked = {
                },
                onAddButtonClicked = {
                },
                onImageClick = {},
            )
        }
        rule.onNodeWithTag("INPUT_IMAGE").assertExists()
        rule.onNodeWithTag("INPUT_IMAGE_LEGEND").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_IMAGE_SUPPORTING_TEXT").assertDoesNotExist()
    }

    @Test
    fun shouldAllowAddImageWhenEnabled() {
        rule.setContent {
            BasicInputImage(
                title = "Label",
                uploadState = UploadState.ADD,
                load = { },
                addButtonText = "ADD",
                addButtonIcon = Icons.Outlined.FileUpload,
                testTag = "IMAGE",
                onDownloadButtonClick = {},
                onResetButtonClicked = {
                },
                onAddButtonClicked = {
                },
                onImageClick = {},
            )
        }
        rule.onNodeWithTag("INPUT_IMAGE").assertExists()
        rule.onNodeWithTag("INPUT_IMAGE_ADD_BUTTON").assertIsEnabled()
    }

    @Test
    fun shouldNotAllowAddImageWhenDisabled() {
        rule.setContent {
            BasicInputImage(
                title = "Label",
                state = InputShellState.DISABLED,
                uploadState = UploadState.ADD,
                load = { },
                addButtonText = "ADD",
                addButtonIcon = Icons.Outlined.FileUpload,
                testTag = "IMAGE",
                onDownloadButtonClick = {},
                onResetButtonClicked = {
                },
                onAddButtonClicked = {
                },
                onImageClick = {},
            )
        }
        rule.onNodeWithTag("INPUT_IMAGE").assertExists()
        rule.onNodeWithTag("INPUT_IMAGE_ADD_BUTTON").assertIsNotEnabled()
    }

    @Test
    fun shouldShowResetButtonWhenImageAdded() {
        rule.setContent {
            BasicInputImage(
                title = "Label",
                uploadState = UploadState.LOADED,
                load = { },
                addButtonText = "ADD",
                addButtonIcon = Icons.Outlined.FileUpload,
                testTag = "IMAGE",
                onDownloadButtonClick = {},
                onResetButtonClicked = {
                },
                onAddButtonClicked = {
                },
                onImageClick = {},
            )
        }
        rule.onNodeWithTag("INPUT_IMAGE").assertExists()
        rule.onNodeWithTag("INPUT_IMAGE_ADD_BUTTON").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_IMAGE_RESET_BUTTON").assertExists()
    }

    @Test
    fun shouldHideResetAndEditButtonWhenNoImageAdded() {
        rule.setContent {
            BasicInputImage(
                title = "Label",
                uploadState = UploadState.ADD,
                load = { },
                addButtonText = "ADD",
                addButtonIcon = Icons.Outlined.FileUpload,
                testTag = "IMAGE",
                onDownloadButtonClick = {},
                onResetButtonClicked = {
                },
                onAddButtonClicked = {
                },
                onImageClick = {},

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
            BasicInputImage(
                title = "Label",
                uploadState = currentState,
                load = { },
                addButtonText = "ADD",
                addButtonIcon = Icons.Outlined.FileUpload,
                testTag = "IMAGE",
                onDownloadButtonClick = {},
                onResetButtonClicked = {
                    currentState = UploadState.ADD
                },
                onAddButtonClicked = {
                },
                onImageClick = {},

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
            BasicInputImage(
                title = "Label",
                uploadState = UploadState.ADD,
                load = { },
                addButtonText = "ADD",
                addButtonIcon = Icons.Outlined.FileUpload,
                testTag = "IMAGE",
                onDownloadButtonClick = {},
                onResetButtonClicked = {
                },
                onAddButtonClicked = {
                },
                onImageClick = {},

            )
        }
        rule.onNodeWithTag("INPUT_IMAGE").assertExists()
        rule.onNodeWithTag("INPUT_IMAGE_RESET_BUTTON").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_IMAGE_EDIT_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldShowLegendCorrectly() {
        rule.setContent {
            BasicInputImage(
                title = "Label",
                uploadState = UploadState.ADD,
                legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
                load = { },
                addButtonText = "ADD",
                addButtonIcon = Icons.Outlined.FileUpload,
                testTag = "IMAGE",
                onDownloadButtonClick = {},
                onResetButtonClicked = {
                },
                onAddButtonClicked = {
                },
                onImageClick = {},
            )
        }
        rule.onNodeWithTag("INPUT_IMAGE").assertExists()
        rule.onNodeWithTag("INPUT_IMAGE_LEGEND").assertExists()
        rule.onNodeWithTag("INPUT_IMAGE_LEGEND").assertHasNoClickAction()
    }

    @Test
    fun shouldShowSupportingTextCorrectly() {
        rule.setContent {
            BasicInputImage(
                title = "Label",
                uploadState = UploadState.ADD,
                supportingText = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
                load = { },
                addButtonText = "ADD",
                addButtonIcon = Icons.Outlined.FileUpload,
                testTag = "IMAGE",
                onDownloadButtonClick = {},
                onResetButtonClicked = {
                },
                onAddButtonClicked = {
                },
                onImageClick = {},
            )
        }
        rule.onNodeWithTag("INPUT_IMAGE").assertExists()
        rule.onNodeWithTag("INPUT_IMAGE_SUPPORTING_TEXT").assertExists()
    }
}
