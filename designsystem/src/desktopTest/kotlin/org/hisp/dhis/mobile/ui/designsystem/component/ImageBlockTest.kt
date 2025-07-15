package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileUpload
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.hisp.dhis.mobile.ui.designsystem.component.internal.image.provideImage
import org.junit.Rule
import org.junit.Test
import java.io.File

class ImageBlockTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldNotRenderImageBlockIfFileIsNotValid() {
        rule.setContent {
            ImageBlock(
                title = "title",
                load = { provideImage(File("")) },
                painterFor = { BitmapPainter(it!!) },
                onDownloadButtonClick = {},
                onShareButtonClick = {},
            )
        }

        rule.onNodeWithTag("IMAGE_BLOCK_CONTAINER").assertDoesNotExist()
    }

    @Test
    fun shouldOpenImageInFullScreenWhenClicked() {
        rule.setContent {
            ImageBlock(
                title = "title",
                load = { (Icons.Outlined.FileUpload) },
                painterFor = { rememberVectorPainter(it) },
                onDownloadButtonClick = {},
                onShareButtonClick = {},
            )
        }

        rule.onNodeWithTag("IMAGE_BLOCK_CONTAINER").assertExists()
        rule.onNodeWithTag("IMAGE_BLOCK_CONTAINER").performClick()
        rule.onNodeWithTag("FULL_SCREEN_IMAGE").assertExists()
    }

    @Test
    fun shouldCloseFullScreenImageOnBackButtonClick() {
        rule.setContent {
            ImageBlock(
                title = "title",
                load = { (Icons.Outlined.FileUpload) },
                painterFor = { rememberVectorPainter(it) },
                onDownloadButtonClick = {},
                onShareButtonClick = {},
            )
        }

        rule.onNodeWithTag("IMAGE_BLOCK_CONTAINER").assertExists()
        rule.onNodeWithTag("IMAGE_BLOCK_CONTAINER").performClick()
        rule.onNodeWithTag("FULL_SCREEN_IMAGE").assertExists()

        rule.onNodeWithTag("FULL_SCREEN_IMAGE_BACK_BUTTON").assertExists()
        rule.onNodeWithTag("FULL_SCREEN_IMAGE_BACK_BUTTON").performClick()
        rule.onNodeWithTag("FULL_SCREEN_IMAGE").assertDoesNotExist()
    }
}
