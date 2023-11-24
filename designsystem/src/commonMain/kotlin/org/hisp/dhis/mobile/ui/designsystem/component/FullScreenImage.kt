package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.hisp.dhis.mobile.ui.designsystem.component.internal.image.ZoomableImage
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

/**
 * DHIS2 Full Screen Image. Wraps DHIS2 Internal [ZoomableImage].
 * @param painter controls the image painter to be drawn
 * @param title controls the text to be shown for the title
 * @param modifier allows a modifier to be passed externally
 * @param onDismiss is a callback to dismiss the full screen image component
 * @param onDownloadButtonClick is a callback to notify when the download button is clicked.
 * @param onShareButtonClick is a callback to notify when the share button is clicked.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullScreenImage(
    painter: Painter,
    title: String,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onDownloadButtonClick: () -> Unit,
    onShareButtonClick: () -> Unit,
) {
    var opened by remember { mutableStateOf(false) }
    val transition = updateTransition(opened)

    val animatedScale by transition.animateFloat(
        transitionSpec = { tween(300) },
    ) { isOpened ->
        if (isOpened) 1f else 0f
    }
    val animatedColor by transition.animateColor(
        transitionSpec = { tween(600) },
    ) { isOpened ->
        if (isOpened) Color.Black else Color.Transparent
    }

    Dialog(
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnClickOutside = false,
        ),
        onDismissRequest = onDismiss,
    ) {
        opened = true
        Scaffold(
            modifier = modifier,
            containerColor = animatedColor,
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF000000).copy(0.4f),
                    ),
                    navigationIcon = {
                        IconButton(
                            onClick = onDismiss,
                            modifier = Modifier.testTag("FULL_SCREEN_IMAGE_BACK_BUTTON"),
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.ArrowBack,
                                    contentDescription = "Back Button",
                                    tint = SurfaceColor.SurfaceBright,
                                )
                            },
                        )
                    },
                    title = {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleSmall,
                            color = SurfaceColor.SurfaceBright,
                        )
                    },
                    actions = {
                        IconButton(
                            onClick = onShareButtonClick,
                            modifier = Modifier.testTag("FULL_SCREEN_IMAGE_SHARE_BUTTON"),
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.Share,
                                    contentDescription = "Share Button",
                                    tint = SurfaceColor.SurfaceBright,
                                )
                            },
                        )
                        IconButton(
                            onClick = onDownloadButtonClick,
                            modifier = Modifier.testTag("FULL_SCREEN_IMAGE_DOWNLOAD_BUTTON"),
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.FileDownload,
                                    contentDescription = "Download Button",
                                    tint = SurfaceColor.SurfaceBright,
                                )
                            },
                        )
                    },
                )
            },
        ) {
            ZoomableImage(
                painter = painter,
                modifier = Modifier
                    .testTag("FULL_SCREEN_IMAGE")
                    .fillMaxSize()
                    .graphicsLayer {
                        scaleX = animatedScale
                        scaleY = animatedScale
                    },
            )
        }
    }
}
