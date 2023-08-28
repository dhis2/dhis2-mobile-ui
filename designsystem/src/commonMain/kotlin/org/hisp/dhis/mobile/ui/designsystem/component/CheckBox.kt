package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    checked: Boolean,
    enabled: Boolean,
    textInput: String? = null,
) {
    var isChecked by remember {
        mutableStateOf(checked)
    }
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
                        isChecked = !isChecked
                    }
                },
                enabled = enabled,
            )
            .hoverPointerIcon(enabled),
    ) {
        CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = {
                    if (enabled) isChecked = it
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

/**
 * DHIS2 check box block.
 *
 * @param orientation Controls how the check boxes will be displayed, HORIZONTAL for rows or
 * VERTICAL for columns.
 * @param content Contains all the data that will be displayed, the list type is CheckBoxData,
 * this data class contains all data for [CheckBox] composable.
 */

@Composable
fun CheckBoxBlock(
    orientation: Orientation,
    content: List<CheckBoxData>
) {
    if (orientation == Orientation.HORIZONTAL) {
        FlowRowComponentsContainer(
            null,
            Spacing.Spacing16,
            content = {
                content.map {
                    val state by remember {
                        mutableStateOf(it)
                    }
                    CheckBox(it.checked, it.enabled, it.textInput)
                }
            }
        )
    } else {
        FlowColumnComponentsContainer(
            null,
            Spacing.Spacing0,
            content = {
                content.map {
                    val state by remember {
                        mutableStateOf(it)
                    }
                    CheckBox(it.checked, it.enabled, it.textInput)
                }
            }
        )
    }
}

data class CheckBoxData(
    val uid: String,
    val checked: Boolean,
    val enabled: Boolean,
    val textInput: String?
)

enum class Orientation {
    HORIZONTAL,
    VERTICAL
}
