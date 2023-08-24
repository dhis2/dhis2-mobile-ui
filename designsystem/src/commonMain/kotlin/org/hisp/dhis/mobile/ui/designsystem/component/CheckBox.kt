package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline
import org.hisp.dhis.mobile.ui.designsystem.theme.Ripple
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.hisp.dhis.mobile.ui.designsystem.theme.hoverPointerIcon

/**
 * DHIS2 check box with or without text. Wraps Material 3 [Checkbox].
 *
 * @param checked Controls if the option is checked.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param textInput The checkbox option text.
 */
@Composable
fun CheckBox(
    checked: MutableState<Boolean>,
    enabled: Boolean,
    textInput: String? = null,
) {
    val interactionSource = if (enabled) remember { MutableInteractionSource() } else MutableInteractionSource()
    val textColor = if (enabled) {
        TextColor.OnSurface
    } else {
        TextColor.OnDisabledSurface
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(Spacing.Spacing0, Alignment.Start),
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    if (enabled) {
                        checked.value = !checked.value
                    }
                },
                enabled = enabled,
            )
            .hoverPointerIcon(enabled),
    ) {
        CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme) {
            Checkbox(
                checked = checked.value,
                onCheckedChange = {
                    if (enabled) checked.value = it
                },
                interactionSource = interactionSource,
                enabled = enabled,
                modifier = Modifier
                    .size(Spacing.Spacing40),
                colors = CheckboxDefaults.colors(
                    checkedColor = SurfaceColor.Primary,
                    uncheckedColor = Outline.Dark,
                    disabledCheckedColor = TextColor.OnDisabledSurface,
                    disabledUncheckedColor = TextColor.OnDisabledSurface,
                ),
            )
        }
        textInput?.let {
            Text(
                modifier = Modifier
                    .padding(top = Spacing.Spacing8, bottom = Spacing.Spacing8),
                text = it,
                color = textColor,
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CheckBoxBlock(
    orientation: Orientation,
    content: List<CheckBoxState>) {
    if (orientation == Orientation.HORIZONTAL) {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(Spacing.Spacing16, Alignment.Start),
            verticalAlignment = Alignment.Top,
        ) {
            content.map {
                CheckBox(it.checked, it.enabled, it.textInput)
            }
        }
    } else {
        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing.Spacing0, Alignment.Top),
            horizontalAlignment = Alignment.Start,
        ) {
            content.map {
                CheckBox(it.checked, it.enabled, it.textInput)
            }
        }
    }
}

data class CheckBoxState(
    val checked: MutableState<Boolean>,
    val enabled: Boolean,
    val textInput: String?
)

enum class Orientation {
    HORIZONTAL,
    VERTICAL
}
