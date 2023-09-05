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
import org.hisp.dhis.mobile.ui.designsystem.theme.InternalSizeValues
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline
import org.hisp.dhis.mobile.ui.designsystem.theme.Ripple
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.hisp.dhis.mobile.ui.designsystem.theme.hoverPointerIcon

/**
 * DHIS2 radio button with or without text. Wraps Material 3 [RadioButton].
 *
 * @param radioButtonData Contains all data for controlling the inner state of the component. It's parameters are uid for
 * identifying the component, selected for controlling which option is selected, enabled controls if the component is
 * clickable and textInput displaying the option text.
 * @param onClick Will be called when the user clicks the button.
 *
 */
@Composable
fun RadioButton(
    radioButtonData: RadioButtonData,
    modifier: Modifier = Modifier,
    onClick: (Boolean) -> Unit,
) {
    val interactionSource = if (radioButtonData.enabled) remember { MutableInteractionSource() } else MutableInteractionSource()
    Row(
        horizontalArrangement = Arrangement.spacedBy(Spacing.Spacing0, Alignment.Start),
        verticalAlignment = Alignment.Top,
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    if (radioButtonData.enabled) {
                        onClick.invoke(!radioButtonData.selected)
                    }
                },
                enabled = radioButtonData.enabled,
            ),
    ) {
        CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme) {
            RadioButton(
                selected = radioButtonData.selected,
                onClick = {
                    if (radioButtonData.enabled) {
                        onClick.invoke(!radioButtonData.selected)
                    }
                },
                enabled = radioButtonData.enabled,
                interactionSource = interactionSource,
                modifier = Modifier
                    .size(InternalSizeValues.Size40)
                    .hoverPointerIcon(radioButtonData.enabled),
                colors = RadioButtonDefaults.colors(
                    selectedColor = SurfaceColor.Primary,
                    unselectedColor = Outline.Dark,
                    disabledSelectedColor = TextColor.OnDisabledSurface,
                    disabledUnselectedColor = TextColor.OnDisabledSurface,
                ),
            )
        }
        radioButtonData.textInput?.let {
            Text(
                modifier = Modifier
                    .padding(top = Spacing.Spacing8, bottom = Spacing.Spacing8)
                    .hoverPointerIcon(radioButtonData.enabled),
                text = it,
                color = if (radioButtonData.enabled) {
                    TextColor.OnSurface
                } else {
                    TextColor.OnDisabledSurface
                },
            )
        }
    }
}

/**
* DHIS2 radio button block.
*
* @param orientation Controls how the radio buttons will be displayed, HORIZONTAL for rows or
* VERTICAL for columns.
* @param content Contains all the data that will be displayed, the list type is RadioButtonData,
* this data class contains all data for [RadioButton] composable.
 * @param itemSelected controls which item is selected.
* @param onItemChange is a callback to notify which item has changed into the block.
*/
@Composable
fun RadioButtonBlock(
    orientation: Orientation,
    content: List<RadioButtonData>,
    itemSelected: RadioButtonData,
    onItemChange: (RadioButtonData) -> Unit,
) {
    var currentItem by remember {
        mutableStateOf(itemSelected)
    }
    if (orientation == Orientation.HORIZONTAL) {
        FlowRowComponentsContainer(
            null,
            Spacing.Spacing16,
            content = {
                content.map { radioButtonData ->
                    RadioButton(
                        RadioButtonData(
                            radioButtonData.uid,
                            if (radioButtonData.enabled) radioButtonData == currentItem else radioButtonData.selected,
                            radioButtonData.enabled,
                            radioButtonData.textInput,
                        ),
                    ) {
                        currentItem = radioButtonData
                        onItemChange.invoke(radioButtonData)
                    }
                }
            },
        )
    } else {
        FlowColumnComponentsContainer(
            null,
            Spacing.Spacing0,
            content = {
                content.map { radioButtonData ->
                    RadioButton(
                        RadioButtonData(
                            radioButtonData.uid,
                            if (radioButtonData.enabled) radioButtonData == currentItem else radioButtonData.selected,
                            radioButtonData.enabled,
                            radioButtonData.textInput,
                        ),
                    ) {
                        currentItem = radioButtonData
                        onItemChange.invoke(radioButtonData)
                    }
                }
            },
        )
    }
}

data class RadioButtonData(
    val uid: String,
    val selected: Boolean,
    val enabled: Boolean,
    val textInput: String?,
)
