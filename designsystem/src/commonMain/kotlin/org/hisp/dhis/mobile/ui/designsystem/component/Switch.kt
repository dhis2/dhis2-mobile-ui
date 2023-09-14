package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline
import org.hisp.dhis.mobile.ui.designsystem.theme.Ripple
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun Switch(
    modifier: Modifier = Modifier,
    isChecked: Boolean = false,
    enabled: Boolean = true,
    onCheckedChange: (Boolean) -> Unit,
) {
    CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme) {
        Switch(
            modifier = modifier,
            checked = isChecked,
            onCheckedChange = { onCheckedChange.invoke(isChecked) },
            enabled = enabled,
            colors = SwitchDefaults.colors(
                checkedThumbColor = TextColor.OnPrimary,
                checkedTrackColor = SurfaceColor.Primary,
                checkedBorderColor = SurfaceColor.Primary,
                checkedIconColor = SurfaceColor.Primary,
                uncheckedThumbColor = Outline.Dark,
                uncheckedTrackColor = SurfaceColor.Surface,
                uncheckedBorderColor = Outline.Dark,
                uncheckedIconColor = Outline.Dark,
                disabledCheckedThumbColor = SurfaceColor.Surface,
                disabledCheckedTrackColor = SurfaceColor.CustomGray,
                disabledCheckedBorderColor = SurfaceColor.CustomGray,
                disabledCheckedIconColor = TextColor.OnDisabledSurface,
                disabledUncheckedThumbColor = TextColor.OnDisabledSurface,
                disabledUncheckedTrackColor = SurfaceColor.DisabledSurface,
                disabledUncheckedBorderColor = Outline.Light,
                disabledUncheckedIconColor = TextColor.OnDisabledSurface,
            ),
            thumbContent = {
                if (isChecked) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize),
                    )
                } else {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = null,
                        tint = TextColor.OnPrimary,
                        modifier = Modifier.size(SwitchDefaults.IconSize),
                    )
                }
            },
        )
    }
}
