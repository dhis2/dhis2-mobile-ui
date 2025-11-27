package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileUpload
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class FullScreenImageTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldDisplayFullScreenImageCorrectly() {
        rule.setContent {
            FullScreenImage(
                title = "Label",
                painter = rememberVectorPainter(Icons.Outlined.FileUpload),
                onDownloadButtonClick = {},
                onShareButtonClick = {},
                onDismiss = {},
            )
        }
        rule.onNodeWithTag("FULL_SCREEN_IMAGE").assertExists()
        rule.onNodeWithTag("FULL_SCREEN_IMAGE_BACK_BUTTON").assertExists()
        rule.onNodeWithTag("FULL_SCREEN_IMAGE_SHARE_BUTTON").assertExists()
        rule.onNodeWithTag("FULL_SCREEN_IMAGE_DOWNLOAD_BUTTON").assertExists()
    }

    @Test
    fun shouldCloseFullScreenImageWhenBackButtonIsPressed() {
        rule.setContent {
            var showFullScreenImage by remember { mutableStateOf(true) }
            if (showFullScreenImage) {
                FullScreenImage(
                    title = "Label",
                    painter = rememberVectorPainter(Icons.Outlined.FileUpload),
                    onDownloadButtonClick = {},
                    onShareButtonClick = {},
                    onDismiss = {
                        showFullScreenImage = !showFullScreenImage
                    },
                )
            }
        }
        rule.onNodeWithTag("FULL_SCREEN_IMAGE_BACK_BUTTON").performClick()
        rule.onNodeWithTag("FULL_SCREEN_IMAGE").assertDoesNotExist()
    }
}
