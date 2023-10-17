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

class InputSignatureTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldDisplayInputSignatureCorrectly() {
        rule.setContent {
            InputSignature(
                title = "Label",
                uploadState = UploadState.ADD,
                load = { },
                onDownloadButtonClick = {},
                onResetButtonClicked = {
                },
                onAddButtonClicked = {
                },
            )
        }
        rule.onNodeWithTag("INPUT_SIGNATURE").assertExists()
        rule.onNodeWithTag("INPUT_SIGNATURE_LEGEND").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_SIGNATURE_SUPPORTING_TEXT").assertDoesNotExist()
    }

    @Test
    fun shouldAllowAddSignatureWhenEnabled() {
        rule.setContent {
            InputSignature(
                title = "Label",
                uploadState = UploadState.ADD,
                load = { },
                onDownloadButtonClick = {},
                onResetButtonClicked = {
                },
                onAddButtonClicked = {
                },
            )
        }
        rule.onNodeWithTag("INPUT_SIGNATURE").assertExists()
        rule.onNodeWithTag("INPUT_SIGNATURE_ADD_BUTTON").assertIsEnabled()
    }

    @Test
    fun shouldNotAllowAddSignatureWhenDisabled() {
        rule.setContent {
            InputSignature(
                title = "Label",
                state = InputShellState.DISABLED,
                uploadState = UploadState.ADD,
                load = { },
                onDownloadButtonClick = {},
                onResetButtonClicked = {
                },
                onAddButtonClicked = {
                },
            )
        }
        rule.onNodeWithTag("INPUT_SIGNATURE").assertExists()
        rule.onNodeWithTag("INPUT_SIGNATURE_ADD_BUTTON").assertIsNotEnabled()
    }

    @Test
    fun shouldShowResetButtonWhenSignatureAdded() {
        rule.setContent {
            InputSignature(
                title = "Label",
                uploadState = UploadState.LOADED,
                load = { },
                onDownloadButtonClick = {},
                onResetButtonClicked = {
                },
                onAddButtonClicked = {
                },
            )
        }
        rule.onNodeWithTag("INPUT_SIGNATURE").assertExists()
        rule.onNodeWithTag("INPUT_SIGNATURE_ADD_BUTTON").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_SIGNATURE_RESET_BUTTON").assertExists()
    }

    @Test
    fun shouldHideResetAndEditButtonWhenNoSignatureAdded() {
        rule.setContent {
            InputSignature(
                title = "Label",
                uploadState = UploadState.ADD,
                load = { },
                onDownloadButtonClick = {},
                onResetButtonClicked = {
                },
                onAddButtonClicked = {
                },
            )
        }
        rule.onNodeWithTag("INPUT_SIGNATURE").assertExists()
        rule.onNodeWithTag("INPUT_SIGNATURE_ADD_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_SIGNATURE_RESET_BUTTON").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_SIGNATURE_EDIT_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldRemoveSignatureWhenResetButtonIsClickedAndHideResetAndEditButton() {
        rule.setContent {
            var currentState by rememberSaveable { mutableStateOf(UploadState.LOADED) }
            InputSignature(
                title = "Label",
                uploadState = currentState,
                load = { },
                onDownloadButtonClick = {},
                onResetButtonClicked = {
                    currentState = UploadState.ADD
                },
                onAddButtonClicked = {
                },
            )
        }
        rule.onNodeWithTag("INPUT_SIGNATURE").assertExists()
        rule.onNodeWithTag("INPUT_SIGNATURE_RESET_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_SIGNATURE_RESET_BUTTON").performClick()

        rule.onNodeWithTag("INPUT_SIGNATURE_ADD_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_SIGNATURE_RESET_BUTTON").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_SIGNATURE_EDIT_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldHideResetAndEditButtonWhenDisabled() {
        rule.setContent {
            InputSignature(
                title = "Label",
                uploadState = UploadState.ADD,
                load = { },
                onDownloadButtonClick = {},
                onResetButtonClicked = {
                },
                onAddButtonClicked = {
                },
            )
        }
        rule.onNodeWithTag("INPUT_SIGNATURE").assertExists()
        rule.onNodeWithTag("INPUT_SIGNATURE_RESET_BUTTON").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_SIGNATURE_EDIT_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldShowLegendCorrectly() {
        rule.setContent {
            InputSignature(
                title = "Label",
                uploadState = UploadState.ADD,
                legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
                load = { },
                onDownloadButtonClick = {},
                onResetButtonClicked = {
                },
                onAddButtonClicked = {
                },
            )
        }
        rule.onNodeWithTag("INPUT_SIGNATURE").assertExists()
        rule.onNodeWithTag("INPUT_SIGNATURE_LEGEND").assertExists()
        rule.onNodeWithTag("INPUT_SIGNATURE_LEGEND").assertHasClickAction()
    }

    @Test
    fun shouldShowSupportingTextCorrectly() {
        rule.setContent {
            InputSignature(
                title = "Label",
                uploadState = UploadState.ADD,
                supportingText = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
                load = { },
                onDownloadButtonClick = {},
                onResetButtonClicked = {
                },
                onAddButtonClicked = {
                },
            )
        }
        rule.onNodeWithTag("INPUT_SIGNATURE").assertExists()
        rule.onNodeWithTag("INPUT_SIGNATURE_SUPPORTING_TEXT").assertExists()
    }
}
