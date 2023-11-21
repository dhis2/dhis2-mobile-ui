package org.hisp.dhis.mobile.ui.designsystem.component.internal.image

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import org.hisp.dhis.mobile.ui.designsystem.component.IconButton
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor


@Composable
internal fun FullScreenImage(
    painter: Painter,
    title: String,
    modifier: Modifier,
    onDismiss: () -> Unit,
    onDownloadButtonCLick: () -> Unit,
    onShareButtonClick: () -> Unit,
) {
    val animatedProgress = remember { Animatable(0f) }
    val animatedColor = remember { Animatable(Color.Transparent) }

    LaunchedEffect(animatedProgress, animatedColor) {
        launch {
            awaitAll(
                async {
                    animatedProgress.animateTo(
                        1f,
                        animationSpec = tween(500)
                    )
                },
                async {
                    animatedColor.animateTo(
                        Color.Black,
                        animationSpec = tween(500)
                    )
                }

            )
        }
    }

    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = { /* implement */ }
    ) {
        Box(
            modifier
                .fillMaxSize()
                .background(color = animatedColor.value),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        scaleX = animatedProgress.value
                        scaleY = animatedProgress.value
                    }
            )

            TopAppBar(
                title = title,
                modifier = Modifier
                    .align(Alignment.TopStart),
                onBack = onDismiss,
                onShare = onShareButtonClick,
                onDownload = onDownloadButtonCLick
            )
        }
    }
}

@Composable
private fun TopAppBar(
    title: String,
    modifier: Modifier,
    onBack: () -> Unit,
    onShare: () -> Unit,
    onDownload: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color(0xFF000000).copy(0.4f))
            .padding(vertical = Spacing.Spacing8)
    ) {
        IconButton(
            onClick = onBack,
            modifier = Modifier.testTag("FULL_SCREEN_IMAGE_BACK_BUTTON"),
            icon = {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Reset Button",
                    tint = SurfaceColor.SurfaceBright
                )
            },
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = SurfaceColor.SurfaceBright,
            modifier = Modifier.weight(1.0f)
        )
        IconButton(
            onClick = onShare,
            modifier = Modifier.testTag("FULL_SCREEN_IMAGE_SHARE_BUTTON"),
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Share,
                    contentDescription = "Reset Button",
                    tint = SurfaceColor.SurfaceBright
                )
            },
        )
        IconButton(
            onClick = onDownload,
            modifier = Modifier.testTag("FULL_SCREEN_IMAGE_DOWNLOAD_BUTTON"),
            icon = {
                Icon(
                    imageVector = Icons.Outlined.FileDownload,
                    contentDescription = "Reset Button",
                    tint = SurfaceColor.SurfaceBright
                )
            },
        )
    }
}