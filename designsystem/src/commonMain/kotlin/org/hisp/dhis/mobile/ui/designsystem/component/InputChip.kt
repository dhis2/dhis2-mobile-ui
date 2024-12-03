package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntOffset
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.hisp.dhis.mobile.ui.designsystem.theme.customRippleConfiguration

/**
 * DHIS2 Input Chip. Wraps material 3 · [InputChip].
 * @param label: controls the text to be shown for the title.
 * @param selected: whether the chip is selected or not.
 * @param withTrailingIcon: whether a trailing icon is displayed or not.
 * @param enabled: Controls the enabled state of the button. When `false`, this button will not be
 *  * clickable and will appear disabled to accessibility services.
 * @param onSelected: callback to chip selected event.
 * @param onIconSelected: callback for delete icon tap.
 * @param badge: the text to be shown within the badge
 * @param hasTransparentBackground: whether the background is transparent or not.
 * @param focusRequester: component focus requester.
 * @param modifier: optional modifier.
 */
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
    focusRequester: FocusRequester? = null,
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

        CompositionLocalProvider(LocalRippleConfiguration provides customRippleConfiguration()) {
            androidx.compose.material3.InputChip(
                enabled = enabled,
                onClick = {
                    focusRequester?.requestFocus()
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
                    enabled = enabled,
                    selected = selected,
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
                                    focusRequester?.requestFocus()
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
