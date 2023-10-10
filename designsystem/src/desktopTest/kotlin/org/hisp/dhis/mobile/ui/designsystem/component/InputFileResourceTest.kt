package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.InternalTestApi
import androidx.compose.ui.test.TestOwner
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.createTestContext
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.junit.Rule
import org.junit.Test
import java.util.Timer
import java.util.logging.Handler
import kotlin.coroutines.coroutineContext

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
                uploadFileState = mutableStateOf(UploadFileState.LOADED),
                onSelectFile = {},
                onUploadFile = {},
            )
        }

        rule.onNodeWithTag(inputFileTestTag + uploadButtonTestTag).performClick()
        rule.onNodeWithTag(inputFileTestTag + progressIndicatorTestTag).assertExists()

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

        rule.onNodeWithTag(inputFileTestTag + addButtonTestTag).performClick()
        rule.onNodeWithTag(inputFileTestTag + uploadButtonTestTag).assertExists()
        rule.onNodeWithTag(inputFileTestTag + uploadHelperTestTag).assertExists()
        rule.onNodeWithTag(inputFileTestTag + uploadHelperTestTag).assert(hasText(testFileName.value.toString() + " " + testFileWeight.value.toString()))

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

        rule.onNodeWithTag(inputFileTestTag + addButtonTestTag).assertExists()
        rule.onNodeWithTag(inputFileTestTag + addButtonTestTag).assert(hasText("select a file"))

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
                uploadFileState = mutableStateOf(UploadFileState.LOADED),
                onSelectFile = {
                    testFileName.value = newFileName
                    testFileWeight.value = newFileWeight
                },
                onUploadFile = {},
            )
        }
        rule.onNodeWithTag(inputFileTestTag + uploadHelperTestTag).assert(hasText("test.filename.extension 256kb"))
        rule.onNodeWithTag(inputFileTestTag + clearButtonTestTag).performClick()
        newFileName = "test_file"
        newFileWeight = "512gb"
        rule.onNodeWithTag(inputFileTestTag + addButtonTestTag).performClick()
        rule.onNodeWithTag(inputFileTestTag + uploadHelperTestTag).assert(hasText("test_file 512gb"))


    }

    @Test
    fun shouldAppearIconTextButtonWhenUploadIsCancelled() {

        rule.setContent {
            InputFileResource(
                title = "Label",
                buttonText = "add file",
                fileName = mutableStateOf("filename.extension"),
                fileWeight = mutableStateOf("524kb"),
                uploadFileState = mutableStateOf(UploadFileState.LOADED),
                onSelectFile = {},
                onUploadFile = {},
            )
        }

        rule.onNodeWithTag(inputFileTestTag + clearButtonTestTag).performClick()
        rule.onNodeWithTag(inputFileTestTag + addButtonTestTag).assertExists()
        rule.onNodeWithTag(inputFileTestTag + addButtonTestTag).assert(hasText("add file"))

    }

    @Test
    fun shouldDisplayToastAfterUploadFile() {

        val inputFileState = mutableStateOf(UploadFileState.LOADED)

        rule.setContent {
            InputFileResource(
                title = "Label",
                buttonText = "add file",
                fileName = mutableStateOf("filename.extension"),
                fileWeight = mutableStateOf("524kb"),
                uploadFileState = inputFileState,
                onSelectFile = {},
                onUploadFile = {
                    delay(5000)
                    inputFileState.value = UploadFileState.ADD
                },
            )
        }

        rule.onNodeWithTag(inputFileTestTag + uploadButtonTestTag).performClick()
        rule.onNodeWithTag(inputFileTestTag + addButtonTestTag).assertExists()

    }
}