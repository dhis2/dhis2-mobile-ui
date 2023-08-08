package org.hisp.dhis.mobileui.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobileui.designsystem.theme.CustomDHISRippleTheme
import org.hisp.dhis.mobileui.designsystem.theme.DHISTypography
import org.hisp.dhis.mobileui.designsystem.theme.Outline
import org.hisp.dhis.mobileui.designsystem.theme.Spacing
import org.hisp.dhis.mobileui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobileui.designsystem.theme.TextColor

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
    selected: Boolean,
    enabled: Boolean,
    textInput: String?,
    onClick: () -> Unit
) {
    CompositionLocalProvider(LocalRippleTheme provides CustomDHISRippleTheme) {
        if (textInput.isNullOrEmpty()) {
            RadioButton(
                selected = selected,
                onClick = onClick,
                enabled = enabled,
                modifier = Modifier
                    .size(Spacing.Spacing40)
                    .padding(Spacing.Spacing8),
                colors = RadioButtonDefaults.colors(
                    selectedColor = SurfaceColor.Primary,
                    unselectedColor = Outline.Dark,
                    disabledSelectedColor = TextColor.OnDisabledSurface,
                    disabledUnselectedColor = TextColor.OnDisabledSurface
                )
            )
        } else {
            Row(
                modifier = Modifier.width(840.dp).height(Spacing.Spacing40),
                horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.Start),
                verticalAlignment = Alignment.Top
            ) {
                RadioButton(
                    selected = selected,
                    onClick = onClick,
                    enabled = enabled,
                    modifier = Modifier
                        .size(Spacing.Spacing40),
                    colors = RadioButtonDefaults.colors(
                        selectedColor = SurfaceColor.Primary,
                        unselectedColor = Outline.Dark,
                        disabledSelectedColor = TextColor.OnDisabledSurface,
                        disabledUnselectedColor = TextColor.OnDisabledSurface
                    )
                )
                Row(
                    modifier = Modifier
                        .width(800.dp)
                        .height(40.dp)
                        .padding(top = Spacing.Spacing8, bottom = Spacing.Spacing8),
                    horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.Start),
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        modifier = Modifier
                            .width(800.dp)
                            .height(Spacing.Spacing24)
                            .clickable { onClick() },
                        text = textInput,
                        style = DHISTypography.bodyLarge,
                        color = if (enabled) {
                            TextColor.OnSurface
                        } else {
                            TextColor.OnDisabledSurface
                        }
                    )
                }
            }
        }
    }
}
