package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
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
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntOffset
import org.hisp.dhis.mobile.ui.designsystem.theme.Ripple
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chip(
    modifier: Modifier = Modifier,
    label: String,
    selected: Boolean = false,
    onSelected: ((Boolean) -> Unit)? = null,
    badge: String? = null,
) {
    Box(modifier = modifier) {
        CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme) {
            var isSelected by remember { mutableStateOf(selected) }

            FilterChip(
                onClick = {
                    isSelected = !isSelected
                    onSelected?.invoke(isSelected)
                },
                label = { Text(label) },
                selected = isSelected,
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = SurfaceColor.SurfaceBright,
                    selectedContainerColor = SurfaceColor.Container,
                ),
                leadingIcon = if (isSelected) {
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
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .onSizeChanged { offset = IntOffset(it.width / 3, it.height / 3) }
                    .offset { offset },
                text = badge,
            )
        }
    }
}
