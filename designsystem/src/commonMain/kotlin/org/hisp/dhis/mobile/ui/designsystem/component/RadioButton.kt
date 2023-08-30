package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
 * DHIS2 radio button with or without text. Wraps Material 3 [RadioButton].
 *
 * @param selected Controls the selected option state for multiple options.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param textInput The button option text.
 * @param onClick Will be called when the user clicks the button.
 */
@Composable
fun RadioButton(
    radioButtonData: RadioButtonData,
    onClick: (Boolean) -> Unit
) {
    val interactionSource = if (radioButtonData.enabled) remember { MutableInteractionSource() } else MutableInteractionSource()
    Row(
        horizontalArrangement = Arrangement.spacedBy(Spacing.Spacing0, Alignment.Start),
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    if (radioButtonData.enabled) {
                        onClick.invoke(true)
                    }
                },
                enabled = radioButtonData.enabled
            )
    ) {
        CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme) {
            RadioButton(
                selected = radioButtonData.selected,
                onClick = {
                    if (radioButtonData.enabled) {
                        onClick.invoke(true)
                    }
                },
                enabled = radioButtonData.enabled,
                interactionSource = interactionSource,
                modifier = Modifier
                    .size(Spacing.Spacing40)
                    .hoverPointerIcon(radioButtonData.enabled),
                colors = RadioButtonDefaults.colors(
                    selectedColor = SurfaceColor.Primary,
                    unselectedColor = Outline.Dark,
                    disabledSelectedColor = TextColor.OnDisabledSurface,
                    disabledUnselectedColor = TextColor.OnDisabledSurface
                ),
            )
        }
        if (!radioButtonData.textInput.isNullOrEmpty()) {
            Text(
                modifier = Modifier
                    .padding(top = Spacing.Spacing8, bottom = Spacing.Spacing8)
                    .hoverPointerIcon(radioButtonData.enabled)
                ,
                text = radioButtonData.textInput,
                color = if (radioButtonData.enabled) {
                    TextColor.OnSurface
                } else {
                    TextColor.OnDisabledSurface
                }
            )
        }
    }
}

@Composable
fun RadioButtonBlock(
    orientation: Orientation,
    content: List<RadioButtonData>,
    onItemChange: (RadioButtonData) -> Unit
) {
    if (orientation == Orientation.HORIZONTAL) {
        FlowRowComponentsContainer(
            null,
            Spacing.Spacing16,
            content = {
                content.map { radioButtonData ->
                    RadioButton(radioButtonData) {
                        onItemChange.invoke(radioButtonData)
                    }
                }
            }
        )
    } else {
        FlowColumnComponentsContainer(
            null,
            Spacing.Spacing0,
            content = {
                content.map { radioButtonData ->
                    RadioButton(radioButtonData) {
                        onItemChange.invoke(radioButtonData)
                    }
                }
            }
        )
    }
}

data class RadioButtonData(
    val uid: String,
    val selected: Boolean,
    val enabled: Boolean,
    val textInput: String?
)

enum class Orientation {
    HORIZONTAL,
    VERTICAL
}
