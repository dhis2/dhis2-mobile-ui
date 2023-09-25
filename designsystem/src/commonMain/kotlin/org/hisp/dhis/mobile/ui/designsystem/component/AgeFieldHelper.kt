package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
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
fun TimeUnitSelector(
    orientation: Orientation,
    optionSelected: String,
    onClick: (RadioButtonData) -> Unit,
) {
    RowComponentContainer(
        modifier = Modifier
            .padding(
                start = Spacing.Spacing8,
                end = Spacing.Spacing8,
            )
            .background(color = SurfaceColor.Surface, Shape.SmallBottom),
    ) {
        val options = TimeUnitValues.values().map {
            RadioButtonData(it.value, optionSelected == it.value, true, provideStringResource(it.value))
        }
        val selectedItem = options.find {
            it.selected
        }
        RadioButtonBlock(orientation, options, selectedItem ?: options[0]) {
            onClick.invoke(it)
        }
    }
}

enum class TimeUnitValues(val value: String) {
    YEARS("years"),
    MONTHS("months"),
    DAYS("days"),
}
