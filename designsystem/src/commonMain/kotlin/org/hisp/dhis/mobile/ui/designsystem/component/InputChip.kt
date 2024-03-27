package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntOffset
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline
import org.hisp.dhis.mobile.ui.designsystem.theme.Ripple
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputChip(
    modifier: Modifier = Modifier,
    label: String,
    selected: Boolean = false,
    withTrailingIcon: Boolean = false,
    enabled: Boolean = true,
    onSelected: ((Boolean) -> Unit)? = null,
    onIconSelected: (() -> Unit)? = null,
    badge: String? = null,
    hasTransparentBackground: Boolean = false,
) {
    Box(modifier = modifier) {
        val filterChipColors = if (hasTransparentBackground) {
            FilterChipDefaults.filterChipColors(
                containerColor = Color.Transparent,
                selectedContainerColor = Color.Transparent,
            )
        } else {
            FilterChipDefaults.filterChipColors(
                containerColor = SurfaceColor.SurfaceBright,
                selectedContainerColor = SurfaceColor.Container,
            )
        }

        CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme()) {
            androidx.compose.material3.InputChip(
                enabled = enabled,
                onClick = {
                    onSelected?.invoke(!selected)
                },
                label = {
                    Text(
                        label,
                        color = if (enabled) {
                            TextColor.OnSurfaceVariant
                        } else {
                            TextColor.OnDisabledSurface
                        },
                    )
                },
                selected = selected,
                colors = filterChipColors,
                border = FilterChipDefaults.filterChipBorder(
                    borderColor = Outline.Dark,
                    disabledBorderColor = Outline.Medium,
                ),
                trailingIcon = if (withTrailingIcon && enabled) {
                    {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            tint = TextColor.OnSurfaceVariant,
                            contentDescription = "Close icon",
                            modifier = Modifier
                                .size(FilterChipDefaults.IconSize)
                                .clickable {
                                    onIconSelected?.invoke()
                                },
                        )
                    }
                } else {
                    null
                },
            )
        }
        badge?.let {
            var offset by remember { mutableStateOf(IntOffset(0, 0)) }
            Badge(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .onSizeChanged { offset = IntOffset(it.width / 3, it.height / 3) }
                    .offset { offset },
                text = badge,
            )
        }
    }
}
