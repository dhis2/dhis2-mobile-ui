package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import org.hisp.dhis.mobile.ui.designsystem.component.internal.ImageCardData
import org.hisp.dhis.mobile.ui.designsystem.resource.provideDHIS2Icon
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

@Deprecated("Use data class AvatarStyle")
enum class AvatarStyle {
    TEXT,
    IMAGE,
    METADATA,
}

sealed class AvatarStyleData() {
    data class Text(val textAvatar: String) : AvatarStyleData()
    data class Image(val imagePainter: Painter) : AvatarStyleData()
    data class Metadata(
        val imageCardData: ImageCardData,
        val avatarSize: MetadataAvatarSize,
        val tintColor: Color,
    ) : AvatarStyleData()
}

/**
 * DHIS2 Avatar,
 *  used to display the avatar composable in card,
 *  must be one of the three styles given as parameters
 * @param style not nullable parameter that manages the avatar style
 * @param modifier allows a modifier to be passed externally
 */
@Composable
fun Avatar(
    modifier: Modifier = Modifier,
    style: AvatarStyleData = AvatarStyleData.Text(""),
    onImageClick: (() -> Unit)? = null,
) {
    when (style) {
        is AvatarStyleData.Text -> {
            Box(
                modifier = modifier
                    .size(Spacing.Spacing40)
                    .background(
                        color = SurfaceColor.PrimaryContainer,
                        shape = RoundedCornerShape(Radius.Full),
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = style.textAvatar,
                    color = SurfaceColor.Primary,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }

        is AvatarStyleData.Metadata -> {
            MetadataAvatar(
                modifier = modifier,
                icon = { requiredSize ->
                    MetadataIcon(
                        modifier = Modifier.size(requiredSize),
                        imageCardData = style.imageCardData,
                    )
                },
                iconTint = style.tintColor,
                size = style.avatarSize,
            )
        }

        is AvatarStyleData.Image -> {
            Image(
                painter = style.imagePainter,
                contentDescription = "avatarImage",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(Spacing.Spacing40)
                    .clip(CircleShape)
                    .clickable(onClick = { onImageClick?.invoke() }),
            )
        }
    }
}

/**
 * DHIS2 Avatar,
 *  used to display the avatar composable in card,
 *  must be one of the three styles given as parameters
 * @param style not nullable parameter that manages the avatar style
 * @param textAvatar style must be TEXT, will show a single character as avatar
 * @param imagePainter style must be IMAGE, will display an image as avatar
 * @param metadataAvatar style must be METADATA, composable should be DHIS2 [MetadataAvatar]
 * @param modifier allows a modifier to be passed externally
 */
@Suppress("DEPRECATION")
@Deprecated("Use new Avatar constructor")
@Composable
fun Avatar(
    textAvatar: String? = null,
    imagePainter: Painter = provideDHIS2Icon("dhis2_microscope_outline"),
    metadataAvatar: (@Composable () -> Unit)? = null,
    style: AvatarStyle = AvatarStyle.TEXT,
    onImageClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    when (style) {
        AvatarStyle.TEXT -> {
            textAvatar?.let {
                Box(
                    modifier = modifier
                        .size(Spacing.Spacing40)
                        .background(
                            color = SurfaceColor.PrimaryContainer,
                            shape = RoundedCornerShape(Radius.Full),
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = textAvatar,
                        color = SurfaceColor.Primary,
                        style = MaterialTheme.typography.titleSmall,
                    )
                }
            }
        }

        AvatarStyle.METADATA -> {
            metadataAvatar?.let {
                metadataAvatar.invoke()
            }
        }

        AvatarStyle.IMAGE -> {
            Image(
                painter = imagePainter,
                contentDescription = "avatarImage",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(Spacing.Spacing40)
                    .clip(CircleShape)
                    .clickable(onClick = { onImageClick?.invoke() }),
            )
        }
    }
}
