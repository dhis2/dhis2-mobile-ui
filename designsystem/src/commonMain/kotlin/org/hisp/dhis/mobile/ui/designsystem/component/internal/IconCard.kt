package org.hisp.dhis.mobile.ui.designsystem.component.internal

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import org.hisp.dhis.mobile.ui.designsystem.theme.DHISShapes
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.iconCardShadow

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun IconCard(
    enabled: Boolean = true,
    selected: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    val cardShadowColor = when {
        !selected && enabled -> {
            Outline.Light
        }
        else -> {
            Color.Unspecified
        }
    }

    val cardShadowModifier = Modifier.clip(DHISShapes.medium)
        .iconCardShadow(color = cardShadowColor)

    Card(
        modifier = modifier.then(cardShadowModifier),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) SurfaceColor.Container else SurfaceColor.SurfaceBright,
            disabledContainerColor = if (selected) SurfaceColor.DisabledSurface else SurfaceColor.DisabledSurfaceBright,
        ),
        border = BorderStroke(
            width = if (selected) Spacing.Spacing1 else Spacing.Spacing0,
            color = if (enabled) {
                if (selected) SurfaceColor.Primary else Color.Unspecified
            } else {
                if (selected) Outline.Medium else Color.Unspecified
            },
        ),
        enabled = enabled,
        onClick = onClick,
    ) {
        content()
    }
}

/**
 * Data model used for DHIS2  Metadata Icon component.
 * @param uid for item.
 * @param label option text.
 */
sealed class ImageCardData(open val uid: String, open val label: String) {
    data class IconCardData(
        override val uid: String,
        override val label: String,
        val iconRes: String,
        val iconTint: Color,
    ) : ImageCardData(uid, label)
    data class CustomIconData(
        override val uid: String,
        override val label: String,
        val image: ImageBitmap,
    ) : ImageCardData(uid, label)
}
