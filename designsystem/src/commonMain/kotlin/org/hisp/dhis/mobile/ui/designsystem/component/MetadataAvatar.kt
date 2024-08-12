package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import org.hisp.dhis.mobile.ui.designsystem.theme.InternalSizeValues
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

/**
 * DHIS2 Metadata Avatar.
 *
 * @param icon: Icon content.
 * @param iconTint: Color of the icon.
 * @param backgroundColor: Background color of the avatar. By default it's icon tine with 10% alpha.
 * @param size: size of the component [MetadataAvatarSize].
 * @param modifier: optional modifier.
 */
@Composable
fun MetadataAvatar(
    icon: @Composable (expectedSize: Dp) -> Unit,
    modifier: Modifier = Modifier,
    iconTint: Color = Color.Unspecified,
    backgroundColor: Color = Color.Unspecified,
    size: MetadataAvatarSize,
) {
    val boxBackgroundColor = if (backgroundColor != Color.Unspecified) {
        backgroundColor
    } else {
        iconTint.copy(alpha = 0.1f)
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(size.cornerRadius))
            .background(color = Color.White)
            .background(color = boxBackgroundColor)
            .padding(size.padding)
            .size(size.size),
        contentAlignment = Alignment.Center,
    ) {
        CompositionLocalProvider(
            LocalContentColor provides iconTint,
        ) {
            Box(modifier = Modifier.clip(RoundedCornerShape(Radius.XS))) {
                icon(size.iconSize)
            }
        }
    }
}

sealed class MetadataAvatarSize(
    val name: String,
    val size: Dp,
    val iconSize: Dp,
    val cornerRadius: Dp,
    val padding: Dp,
) {
    data class XS(val xsNameLabel: String = "VerySmall") :
        MetadataAvatarSize(
            xsNameLabel,
            InternalSizeValues.Size24,
            InternalSizeValues.Size24,
            Radius.XS,
            Spacing.Spacing0,
        )

    data class S(val sNameLabel: String = "Small") :
        MetadataAvatarSize(
            sNameLabel,
            InternalSizeValues.Size40,
            InternalSizeValues.Size40,
            Radius.XS,
            Spacing.Spacing0,
        )

    data class M(val mNameLabel: String = "Medium") :
        MetadataAvatarSize(
            mNameLabel,
            InternalSizeValues.Size48,
            InternalSizeValues.Size48,
            Radius.S,
            Spacing.Spacing4,
        )

    data class L(val lNameLabel: String = "Large") :
        MetadataAvatarSize(
            lNameLabel,
            InternalSizeValues.Size60,
            InternalSizeValues.Size60,
            Radius.S,
            Spacing.Spacing0,
        )

    data class XL(val xlNameLabel: String = "VeryLarge") :
        MetadataAvatarSize(
            xlNameLabel,
            InternalSizeValues.Size120,
            InternalSizeValues.Size120,
            Radius.M,
            Spacing.Spacing0,
        )
}

/**
 * DHIS2 Metadata Avatar.
 *
 * @param icon: Icon content.
 * @param iconTint: Color of the icon.
 * @param backgroundColor: Background color of the avatar. By default it's icon tine with 10% alpha.
 * @param size: size of the component [AvatarSize].
 * @param modifier: optional modifier.
 */
@Suppress("DEPRECATION")
@Composable
@Deprecated("Use new MetadataAvatar")
fun MetadataAvatar(
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    iconTint: Color = Color.Unspecified,
    backgroundColor: Color = Color.Unspecified,
    size: AvatarSize = AvatarSize.Normal,
) {
    val backgroundPadding = when (size) {
        AvatarSize.Normal -> Spacing.Spacing0
        AvatarSize.Large -> Spacing.Spacing4
    }
    val cornerRadius = when (size) {
        AvatarSize.Normal -> Radius.XS
        AvatarSize.Large -> Radius.S
    }
    val boxBackgroundColor = if (backgroundColor != Color.Unspecified) {
        backgroundColor
    } else {
        iconTint.copy(alpha = 0.1f)
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(color = Color.White)
            .background(color = boxBackgroundColor)
            .padding(backgroundPadding)
            .size(Spacing.Spacing40),
        contentAlignment = Alignment.Center,
    ) {
        CompositionLocalProvider(
            LocalContentColor provides iconTint,
        ) {
            Box(modifier = Modifier.clip(RoundedCornerShape(Radius.XS))) {
                icon()
            }
        }
    }
}

@Deprecated("Use new MetadataAvatarSize data class")
enum class AvatarSize {
    Normal, Large
}
