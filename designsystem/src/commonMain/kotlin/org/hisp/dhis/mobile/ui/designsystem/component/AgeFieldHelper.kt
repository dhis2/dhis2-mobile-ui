package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.Shape
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

/**
 * DHIS2 age field helper.
 *
 * @param orientation Controls how the radio buttons will be displayed, HORIZONTAL for rows or
 * VERTICAL for columns.
 * @param optionSelected controls which item is selected.
 * @param onClick is a callback to notify which item has changed into the block.
 */
@Composable
internal fun TimeUnitSelector(
    orientation: Orientation,
    optionSelected: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: (RadioButtonData) -> Unit,
) {
    val backgroundColor = if (enabled) {
        SurfaceColor.Surface
    } else {
        SurfaceColor.DisabledSurface
    }

    RowComponentContainer(
        modifier = modifier
            .background(color = backgroundColor, Shape.SmallBottom)
            .padding(
                start = Spacing.Spacing8,
                end = Spacing.Spacing8,
            ),
    ) {
        val options = TimeUnitValues.values().map {
            RadioButtonData(it.value, optionSelected == it.value, enabled, provideStringResource(it.value))
        }
        val selectedItem = options.find {
            it.selected
        }
        var currentItem by remember {
            mutableStateOf(selectedItem ?: options[0])
        }
        RadioButtonBlock(orientation, options, currentItem) {
            currentItem = it
            onClick.invoke(it)
        }
    }
}

enum class TimeUnitValues(val value: String) {
    YEARS("years"),
    MONTHS("months"),
    DAYS("days"),
}
