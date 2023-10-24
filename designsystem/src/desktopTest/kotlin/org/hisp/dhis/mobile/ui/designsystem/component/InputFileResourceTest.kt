package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.junit.Rule
import org.junit.Test

class InputFileResourceTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldShowLoaderAfterUploadFile() {
        rule.setContent {
            InputFileResource(
                title = "Label",
                buttonText = provideStringResource("add_file"),
                fileName = "filename.extension",
                fileWeight = "524kb",
                uploadFileState = UploadFileState.LOADED,
                onSelectFile = {},
                onUploadFile = {},
            )
        }

        rule.onNodeWithTag(INPUT_FILE_TEST_TAG + UPLOAD_BUTTON_TEST_TAG).performClick()
        rule.onNodeWithTag(INPUT_FILE_TEST_TAG + PROGRESS_INDICATOR_TEST_TAG).assertExists()
    }

    @Test
    fun shouldShowClearButtonAndHelperWhenFileIsSelected() {
        val testFileName = "filename.extension"
        val testFileWeight = "524kb"

        rule.setContent {
            InputFileResource(
                title = "Label",
                buttonText = provideStringResource("add_file"),
                fileName = testFileName,
                fileWeight = testFileWeight,
                onSelectFile = {},
                onUploadFile = {},
            )
        }

        rule.onNodeWithTag(INPUT_FILE_TEST_TAG + ADD_BUTTON_TEST_TAG).performClick()
        rule.onNodeWithTag(INPUT_FILE_TEST_TAG + UPLOAD_BUTTON_TEST_TAG).assertExists()
        rule.onNodeWithTag(INPUT_FILE_TEST_TAG + UPLOAD_TEXT_FILE_NAME_TEST_TAG).assertExists()
        rule.onNodeWithTag(INPUT_FILE_TEST_TAG + UPLOAD_TEXT_FILE_WEIGHT_TEST_TAG).assertExists()
        rule.onNodeWithTag(INPUT_FILE_TEST_TAG + UPLOAD_TEXT_FILE_NAME_TEST_TAG).assert(hasText(testFileName))
    }

    @Test
    fun shouldDisplayButtonWithCustomText() {
        rule.setContent {
            InputFileResource(
                title = "Label",
                buttonText = "select a file",
                fileName = "filename.extension",
                fileWeight = "524kb",
                onSelectFile = {},
                onUploadFile = {},
            )
        }

        rule.onNodeWithTag(INPUT_FILE_TEST_TAG + ADD_BUTTON_TEST_TAG).assertExists()
        rule.onNodeWithTag(INPUT_FILE_TEST_TAG + ADD_BUTTON_TEST_TAG).assert(hasText("select a file"))
    }

    @Test
    fun shouldChangeFileNameAndFileWeightAfterModifyIt() {
        val newFileName = "test_file"
        val newFileWeight = "512gb"

        rule.setContent {
            var testFileName by rememberSaveable { mutableStateOf("filename.extension") }
            var testFileWeight by rememberSaveable { mutableStateOf("524kb") }

            InputFileResource(
                title = "Label",
                buttonText = provideStringResource("add_file"),
                fileName = testFileName,
                fileWeight = testFileWeight,
                uploadFileState = UploadFileState.LOADED,
                onSelectFile = {
                    testFileName = newFileName
                    testFileWeight = newFileWeight
                },
                onUploadFile = {
                },
            )
        }
        rule.onNodeWithTag(INPUT_FILE_TEST_TAG + UPLOAD_TEXT_FILE_NAME_TEST_TAG).assert(hasText("filename.extension"))
        rule.onNodeWithTag(INPUT_FILE_TEST_TAG + UPLOAD_TEXT_FILE_WEIGHT_TEST_TAG).assert(hasText(" 524kb"))
        rule.onNodeWithTag(INPUT_FILE_TEST_TAG + CLEAR_BUTTON_TEST_TAG).performClick()
        rule.onNodeWithTag(INPUT_FILE_TEST_TAG + ADD_BUTTON_TEST_TAG).performClick()
        rule.onNodeWithTag(INPUT_FILE_TEST_TAG + UPLOAD_TEXT_FILE_NAME_TEST_TAG).assert(hasText("test_file"))
        rule.onNodeWithTag(INPUT_FILE_TEST_TAG + UPLOAD_TEXT_FILE_WEIGHT_TEST_TAG).assert(hasText(" 512gb"))
    }

    @Test
    fun shouldAppearIconTextButtonWhenUploadIsCancelled() {
        rule.setContent {
            InputFileResource(
                title = "Label",
                buttonText = "add file",
                fileName = "filename.extension",
                fileWeight = "524kb",
                uploadFileState = UploadFileState.LOADED,
                onSelectFile = {},
                onUploadFile = {},
            )
        }

        rule.onNodeWithTag(INPUT_FILE_TEST_TAG + CLEAR_BUTTON_TEST_TAG).performClick()
        rule.onNodeWithTag(INPUT_FILE_TEST_TAG + ADD_BUTTON_TEST_TAG).assertExists()
        rule.onNodeWithTag(INPUT_FILE_TEST_TAG + ADD_BUTTON_TEST_TAG).assert(hasText("add file"))
    }
}
