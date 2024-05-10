package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
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
import org.hisp.dhis.mobile.ui.designsystem.theme.Ripple
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

/**
 * DHIS2 [AssistChip] button with custom icon slot.
 * wraps Material 3 [AssistChip].
 * AssistChips are used to trigger actions.
 * @param label: the text to be shown.
 * @param icon: custom leading icon to be shown, use AssistChipDefaults in the
 * Icon composable modifier to set the size of the icon.
 * @param enabled: controls the enabled state. "False" will disabled the component
 * @param modifier: optional [Modifier].
 * @param onClick: Will be called when the user taps the chip.
 * @param badge: the text to be displayed within the badge.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssistChip(
    modifier: Modifier = Modifier,
    label: String,
    icon: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    onClick: (() -> Unit),
    badge: String? = null,
) {

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    Box(modifier = modifier) {
        CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme()) {
            AssistChip(
                onClick = { onClick.invoke() },
                label = { Text(label, color = TextColor.OnSurfaceVariant) },
                enabled = enabled,
                colors = if (isPressed) {
                    AssistChipDefaults.assistChipColors(
                        containerColor = SurfaceColor.Container,
                        leadingIconContentColor = TextColor.OnSurfaceVariant
                    )
                } else {
                    AssistChipDefaults.assistChipColors(
                        containerColor = SurfaceColor.SurfaceBright,
                        leadingIconContentColor = TextColor.OnSurfaceVariant
                    )
                       },
                border = AssistChipDefaults.assistChipBorder(
                            borderColor = Outline.Dark,
                    ),
                leadingIcon = {
                    icon?.invoke()
                }

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