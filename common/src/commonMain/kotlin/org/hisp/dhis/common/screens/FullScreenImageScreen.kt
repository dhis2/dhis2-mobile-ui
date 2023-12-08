package org.hisp.dhis.common.screens

import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.FullScreenImage
import org.hisp.dhis.mobile.ui.designsystem.component.ImageBlock
import org.hisp.dhis.mobile.ui.designsystem.resource.provideDHIS2Icon
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun FullScreenImageScreen() {
    ColumnComponentContainer {
        val sampleImage1 = painterResource("drawable/sample.png")
        ImageBlock(
            title = "Image",
            load = { sampleImage1 },
            painterFor = { remember { it } },
            onDownloadButtonClick = {},
            onShareButtonClick = {},
        )

        val sampleImage2 = painterResource("drawable/sample_signature.jpeg")
        ImageBlock(
            title = "Signature",
            load = { sampleImage2 },
            painterFor = { remember { it } },
            onDownloadButtonClick = {},
            onShareButtonClick = {},
        )

        var showFullscreenDhis2Icon by remember { mutableStateOf(false) }
        val iconPainter = provideDHIS2Icon("dhis2_microscope_outline")
        Icon(
            painter = iconPainter,
            contentDescription = null,
            modifier = Modifier.clickable {
                showFullscreenDhis2Icon = !showFullscreenDhis2Icon
            },
        )
        if (showFullscreenDhis2Icon) {
            FullScreenImage(
                painter = iconPainter,
                title = "DHIS2 Icon",
                onDismiss = {
                    showFullscreenDhis2Icon = !showFullscreenDhis2Icon
                },
                onShareButtonClick = {},
                onDownloadButtonClick = {},
            )
        }
    }
}
