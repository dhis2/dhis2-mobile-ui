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
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2Theme
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
                    checkedThumbColor = DHIS2Theme.colorScheme.onPrimary,
                    checkedTrackColor = DHIS2Theme.colorScheme.primary,
                    checkedBorderColor = DHIS2Theme.colorScheme.primary,
                    checkedIconColor = DHIS2Theme.colorScheme.primary,
                    uncheckedThumbColor = DHIS2Theme.colorScheme.outline,
                    uncheckedTrackColor = DHIS2Theme.colorScheme.surface,
                    uncheckedBorderColor = DHIS2Theme.colorScheme.outline,
                    uncheckedIconColor = DHIS2Theme.colorScheme.outline,
                    disabledCheckedThumbColor = DHIS2Theme.colorScheme.surface,
                    disabledCheckedTrackColor = DHIS2Theme.extendedColors.customGray,
                    disabledCheckedBorderColor = DHIS2Theme.extendedColors.customGray,
                    disabledCheckedIconColor = DHIS2Theme.extendedColors.onDisabledSurface,
                    disabledUncheckedThumbColor = DHIS2Theme.extendedColors.onDisabledSurface,
                    disabledUncheckedTrackColor = DHIS2Theme.extendedColors.disabledSurface,
                    disabledUncheckedBorderColor = DHIS2Theme.colorScheme.outlineVariant,
                    disabledUncheckedIconColor = DHIS2Theme.extendedColors.onDisabledSurface,
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
                        tint = DHIS2Theme.colorScheme.onPrimary,
                        modifier = Modifier.size(SwitchDefaults.IconSize),
                    )
                }
            },
        )
    }
}
