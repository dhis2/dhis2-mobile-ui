package org.hisp.dhis.mobile.ui.designsystem.component.internal

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.theme.DHISShapes
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.bottomShadow

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun IconCard(
    enabled: Boolean = true,
    selected: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    val cardShadowModifier = if (!selected && enabled) {
        Modifier.clip(DHISShapes.medium)
            .bottomShadow(color = mutableStateOf(Outline.Light))
            .offset(y = (-2).dp)
    } else {
        Modifier
    }

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

data class IconCardData(
    val uid: String,
    val label: String,
    val iconRes: String,
    val iconTint: Color,
)
