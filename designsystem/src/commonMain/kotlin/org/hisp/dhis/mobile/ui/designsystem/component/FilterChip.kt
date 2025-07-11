package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
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
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntOffset
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.hisp.dhis.mobile.ui.designsystem.theme.customRippleConfiguration

/**
 * DHIS2 [FilterChip] button with generic icon slot.
 * wraps Material 3 [FilterChip].
 * Chips help people enter information, make selections,
 * filter content, or trigger actions.
 * @param label: the text to be shown.
 * @param selected: whether the chip is selected or not.
 * @param modifier: optional [Modifier].
 * @param onSelected: Will be called when the user taps the chip.
 * @param badge: the text to be displayed within the badge.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChip(
    modifier: Modifier = Modifier,
    label: String,
    selected: Boolean = false,
    onSelected: ((Boolean) -> Unit)? = null,
    badge: String? = null,
) {
    Box(modifier = modifier) {
        CompositionLocalProvider(LocalRippleConfiguration provides customRippleConfiguration()) {
            FilterChip(
                onClick = { onSelected?.invoke(!selected) },
                label = { Text(label, color = TextColor.OnSurfaceVariant) },
                selected = selected,
                colors =
                    FilterChipDefaults.filterChipColors(
                        containerColor = SurfaceColor.SurfaceBright,
                        selectedContainerColor = SurfaceColor.Container,
                    ),
                border =
                    FilterChipDefaults.filterChipBorder(
                        borderColor = Outline.Dark,
                        enabled = true,
                        selected = selected,
                    ),
                leadingIcon =
                    if (selected) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = "Done icon",
                                modifier = Modifier.size(FilterChipDefaults.IconSize),
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
                modifier =
                    Modifier
                        .align(Alignment.TopEnd)
                        .onSizeChanged { offset = IntOffset(it.width / 3, it.height / 3) }
                        .offset { offset },
                text = badge,
            )
        }
    }
}
