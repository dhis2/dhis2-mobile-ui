package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class BarcodeBlockTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldNotRenderBarcodeBlockIfDataIsEmpty() {
        rule.setContent {
            BarcodeBlock(data = "")
        }

        rule.onNodeWithTag("BARCODE_BLOCK_CONTAINER").assertDoesNotExist()
    }

    @Test
    fun shouldRenderBarcodeBlockIfDataExists() {
        rule.setContent {
            BarcodeBlock(data = "1b8ea3ff-f0da-4101-b2a6-d51a3b228d67")
        }

        rule.onNodeWithTag("BARCODE_BLOCK_CONTAINER").assertExists()
    }
}
