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
import org.hisp.dhis.mobile.ui.designsystem.component.AvatarSize.Large
import org.hisp.dhis.mobile.ui.designsystem.component.AvatarSize.Normal
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

/**
 * DHIS2 Metadata Avatar.
 *
 * @param icon: Icon content.
 * @param iconTint: Color of the icon.
 * @param backgroundColor: Background color of the avatar. By default it's icon tine with 10% alpha.
 * @param size: size of the component [AvatarSize].
 * @param modifier: optional modifier.
 */
@Composable
fun MetadataAvatar(
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    iconTint: Color = Color.Unspecified,
    backgroundColor: Color = Color.Unspecified,
    size: AvatarSize = Normal,
) {
    val backgroundPadding = when (size) {
        Normal -> Spacing.Spacing0
        Large -> Spacing.Spacing4
    }
    val cornerRadius = when (size) {
        Normal -> Radius.XS
        Large -> Radius.S
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

enum class AvatarSize {
    Normal, Large
}
