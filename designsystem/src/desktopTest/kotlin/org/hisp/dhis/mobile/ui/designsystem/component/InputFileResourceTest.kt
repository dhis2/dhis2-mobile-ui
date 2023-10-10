package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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
                fileName = mutableStateOf("filename.extension"),
                fileWeight = mutableStateOf("524kb"),
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
        val testFileName: MutableState<String?> = mutableStateOf("filename.extension")
        val testFileWeight: MutableState<String?> = mutableStateOf("524kb")

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
        rule.onNodeWithTag(INPUT_FILE_TEST_TAG + UPLOAD_HELPER_TEST_TAG).assertExists()
        rule.onNodeWithTag(INPUT_FILE_TEST_TAG + UPLOAD_HELPER_TEST_TAG).assert(hasText(testFileName.value.toString() + " " + testFileWeight.value.toString()))
    }

    @Test
    fun shouldDisplayButtonWithCustomText() {
        rule.setContent {
            InputFileResource(
                title = "Label",
                buttonText = "select a file",
                fileName = mutableStateOf("filename.extension"),
                fileWeight = mutableStateOf("524kb"),
                onSelectFile = {},
                onUploadFile = {},
            )
        }

        rule.onNodeWithTag(INPUT_FILE_TEST_TAG + ADD_BUTTON_TEST_TAG).assertExists()
        rule.onNodeWithTag(INPUT_FILE_TEST_TAG + ADD_BUTTON_TEST_TAG).assert(hasText("select a file"))
    }

    @Test
    fun shouldChangeFileNameAndFileWeightAfterModifyIt() {
        val testFileName: MutableState<String?> = mutableStateOf("test.filename.extension")
        val testFileWeight: MutableState<String?> = mutableStateOf("256kb")
        var newFileName: String? = null
        var newFileWeight: String? = null

        rule.setContent {
            InputFileResource(
                title = "Label",
                buttonText = provideStringResource("add_file"),
                fileName = testFileName,
                fileWeight = testFileWeight,
                uploadFileState = UploadFileState.LOADED,
                onSelectFile = {
                    testFileName.value = newFileName
                    testFileWeight.value = newFileWeight
                },
                onUploadFile = {},
            )
        }
        rule.onNodeWithTag(INPUT_FILE_TEST_TAG + UPLOAD_HELPER_TEST_TAG).assert(hasText("test.filename.extension 256kb"))
        rule.onNodeWithTag(INPUT_FILE_TEST_TAG + CLEAR_BUTTON_TEST_TAG).performClick()
        newFileName = "test_file"
        newFileWeight = "512gb"
        rule.onNodeWithTag(INPUT_FILE_TEST_TAG + ADD_BUTTON_TEST_TAG).performClick()
        rule.onNodeWithTag(INPUT_FILE_TEST_TAG + UPLOAD_HELPER_TEST_TAG).assert(hasText("test_file 512gb"))
    }

    @Test
    fun shouldAppearIconTextButtonWhenUploadIsCancelled() {
        rule.setContent {
            InputFileResource(
                title = "Label",
                buttonText = "add file",
                fileName = mutableStateOf("filename.extension"),
                fileWeight = mutableStateOf("524kb"),
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
