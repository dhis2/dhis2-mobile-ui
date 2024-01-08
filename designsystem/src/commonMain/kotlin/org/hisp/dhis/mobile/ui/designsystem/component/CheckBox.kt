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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import org.hisp.dhis.mobile.ui.designsystem.theme.InternalSizeValues
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline
import org.hisp.dhis.mobile.ui.designsystem.theme.Ripple
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.hisp.dhis.mobile.ui.designsystem.theme.hoverPointerIcon

/**
 * DHIS2 check box with or without text. Wraps Material 3 [Checkbox].
 *
 * @param checkBoxData Contains all data for controlling the inner state of the component. It's parameters are uid for
 * identifying the component, checked for controlling if the option is checked, enabled controls if the component is
 * clickable and textInput displaying the option text.
 * @param onCheckedChange notify the selection change in the item
 */

@Composable
fun CheckBox(
    checkBoxData: CheckBoxData,
    modifier: Modifier = Modifier,
    onCheckedChange: ((Boolean) -> Unit),
) {
    val interactionSource =
        if (checkBoxData.enabled) remember { MutableInteractionSource() } else MutableInteractionSource()
    val textColor = if (checkBoxData.enabled) {
        TextColor.OnSurface
    } else {
        TextColor.OnDisabledSurface
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(Spacing.Spacing0, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    if (checkBoxData.enabled) {
                        onCheckedChange.invoke(!checkBoxData.checked)
                    }
                },
                enabled = checkBoxData.enabled,
            )
            .hoverPointerIcon(checkBoxData.enabled),
    ) {
        CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme) {
            Checkbox(
                checked = checkBoxData.checked,
                onCheckedChange = {
                    if (checkBoxData.enabled) {
                        onCheckedChange.invoke(!checkBoxData.checked)
                    }
                },
                interactionSource = interactionSource,
                enabled = checkBoxData.enabled,
                modifier = Modifier
                    .size(InternalSizeValues.Size40)
                    .testTag("CHECK_BOX_${checkBoxData.uid}"),
                colors = CheckboxDefaults.colors(
                    checkedColor = SurfaceColor.Primary,
                    uncheckedColor = Outline.Dark,
                    disabledCheckedColor = TextColor.OnDisabledSurface,
                    disabledUncheckedColor = TextColor.OnDisabledSurface,
                ),
            )
        }
        checkBoxData.textInput?.let {
            Text(
                modifier = Modifier
                    .padding(top = Spacing.Spacing8, bottom = Spacing.Spacing8)
                    .hoverPointerIcon(checkBoxData.enabled),
                text = it,
                color = textColor,
                style = MaterialTheme.typography.bodyLarge,
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
 * @param onItemChange is a callback to notify which item has changed into the block
 */

@Composable
fun CheckBoxBlock(
    orientation: Orientation,
    content: List<CheckBoxData>,
    modifier: Modifier = Modifier,
    onItemChange: (CheckBoxData) -> Unit,
) {
    if (orientation == Orientation.HORIZONTAL) {
        FlowRowComponentsContainer(
            null,
            Spacing.Spacing16,
            modifier,
            content = {
                content.map { checkBoxData ->
                    CheckBox(checkBoxData) {
                        onItemChange.invoke(checkBoxData)
                    }
                }
            },
        )
    } else {
        FlowColumnComponentsContainer(
            null,
            Spacing.Spacing0,
            modifier,
            content = {
                content.map { checkBoxData ->
                    CheckBox(checkBoxData) {
                        onItemChange.invoke(checkBoxData)
                    }
                }
            },
        )
    }
}

data class CheckBoxData(
    val uid: String,
    val checked: Boolean,
    val enabled: Boolean,
    val textInput: String?,
)
