package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test
import java.io.File

class ImageBlockTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldNotRenderImageBlockIfFileIsNotValid() {
        rule.setContent {
            ImageBlock(file = File("")) {}
        }

        rule.onNodeWithTag("IMAGE_BLOCK_CONTAINER").assertDoesNotExist()
    }
}
