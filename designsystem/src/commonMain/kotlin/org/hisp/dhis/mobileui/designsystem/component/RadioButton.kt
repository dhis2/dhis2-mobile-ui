package org.hisp.dhis.mobileui.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobileui.designsystem.theme.DHISTypography
import org.hisp.dhis.mobileui.designsystem.theme.Outline
import org.hisp.dhis.mobileui.designsystem.theme.Spacing
import org.hisp.dhis.mobileui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobileui.designsystem.theme.TextColor

@Composable
fun RadioButton(
    selected: Boolean,
    enabled: Boolean,
    textInput: String?,
    onClick: () -> Unit) {
        if (textInput.isNullOrEmpty()) {
            RadioButton(
                selected = selected,
                onClick = onClick,
                enabled = enabled,
                modifier = Modifier
                    .width(Spacing.Spacing40)
                    .height(Spacing.Spacing40)
                    .padding(start = Spacing.Spacing8, top = Spacing.Spacing8, end = Spacing.Spacing8, bottom = Spacing.Spacing8),
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
                verticalAlignment = Alignment.Top,
            )  {
                RadioButton(
                    selected = selected,
                    onClick = onClick,
                    enabled = enabled,
                    modifier = Modifier
                        .width(Spacing.Spacing40)
                        .height(Spacing.Spacing40),
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
                    verticalAlignment = Alignment.Top,
                ) {
                    Text(
                        modifier = Modifier
                            .width(800.dp)
                            .height(Spacing.Spacing24),
                        text = textInput,
                        style = DHISTypography.bodyLarge
                    )
                }
            }
        }
}
