package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.hisp.dhis.mobile.ui.designsystem.theme.customRippleConfiguration

/**
 * DHIS2 Switch wraps Material 3 [Switch].
 * @param modifier: optional modifier.
 * @param isChecked: whether switch is selected or not
 * @param enabled: controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param onCheckedChange: access to the on checked changed event.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Switch(
    modifier: Modifier = Modifier,
    isChecked: Boolean = false,
    enabled: Boolean = true,
    onCheckedChange: (Boolean) -> Unit,
) {
    CompositionLocalProvider(LocalRippleConfiguration provides customRippleConfiguration()) {
        Switch(
            modifier = modifier.testTag("SWITCH"),
            checked = isChecked,
            onCheckedChange = { onCheckedChange.invoke(isChecked) },
            enabled = enabled,
            colors =
                SwitchDefaults.colors(
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
