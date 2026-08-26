package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

/**
 * DHIS2 Image Block. Wraps compose [Image].
 * @param title controls the text to be shown for the title
 * @param load to load an image stored in the resource, device memory or from network
 * we can use loadPainter, loadImageBitmap, loadSvgPainter or loadXmlImageVector
 * @param painterFor is a composable function which controls how to paint the load param,
 * @param contentScaleFor is a composable function which controls how to scale the painted image.
 * @param modifier allows a modifier to be passed externally
 * @param downloadButtonVisible controls whether the download button is visible or not
 * @param downloadButtonVisibleFor is a composable function which controls whether the download
 * button is visible or not for the loaded item, defaults to [downloadButtonVisible].
 * @param clickableFor is a composable function which controls whether the image can be tapped
 * to open full screen, defaults to always clickable.
 * @param onDownloadButtonClick is a callback to notify when the download button is clicked.
 * @param onShareButtonClick is a callback to notify when the share button is clicked.
 */
@Composable
fun <T> ImageBlock(
    title: String,
    load: suspend () -> T,
    painterFor: @Composable (T) -> Painter,
    contentScaleFor: @Composable (T) -> ContentScale = { ContentScale.Crop },
    modifier: Modifier = Modifier,
    downloadButtonVisible: Boolean = true,
    downloadButtonVisibleFor: @Composable (T) -> Boolean = { downloadButtonVisible },
    clickableFor: @Composable (T) -> Boolean = { true },
    onDownloadButtonClick: () -> Unit,
    onShareButtonClick: () -> Unit,
) {
    var isFullScreen by remember { mutableStateOf(false) }

    val image: T? by produceState<T?>(null) {
        value =
            withContext(Dispatchers.IO) {
                try {
                    load()
                } catch (_: Exception) {
                    null
                }
            }
    }

    if (image != null) {
        val painter = painterFor(image!!)
        val contentScale = contentScaleFor(image!!)
        val showDownloadButton = downloadButtonVisibleFor(image!!)
        val isClickable = clickableFor(image!!)

        if (isFullScreen) {
            FullScreenImage(
                title = title,
                painter = painter,
                onDismiss = {
                    isFullScreen = !isFullScreen
                },
                onDownloadButtonClick = onDownloadButtonClick,
                onShareButtonClick = onShareButtonClick,
            )
        }
        Box(
            modifier =
                modifier
                    .padding(vertical = Spacing.Spacing8)
                    .testTag("IMAGE_BLOCK_CONTAINER"),
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = contentScale,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(Radius.S))
                        .height(160.dp)
                        .clickable(enabled = isClickable) {
                            isFullScreen = !isFullScreen
                        },
            )
            if (showDownloadButton) {
                SquareIconButton(
                    enabled = true,
                    modifier =
                        Modifier
                            .align(Alignment.BottomEnd)
                            .padding(Spacing.Spacing4),
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.FileDownload,
                            contentDescription = "File download Button",
                        )
                    },
                ) {
                    onDownloadButtonClick.invoke()
                }
            }
        }
    }
}
