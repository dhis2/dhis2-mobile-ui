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
 * DHIS2 age field helper ([TimeUnitSelector]).
 *
 * @param orientation: Controls how the radio buttons will be displayed, HORIZONTAL for rows or
 * VERTICAL for columns.
 * @param optionSelected: controls which [TimeUnitValues] is selected.
 * @param enabled: manages the enabled state
 * @param onClick: is a callback to notify when [TimeUnitValues] is changed.
 * @param modifier: optional modifier.
 */
@Composable
internal fun TimeUnitSelector(
    orientation: Orientation,
    optionSelected: TimeUnitValues,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: (TimeUnitValues) -> Unit,
) {
    val backgroundColor =
        if (enabled) {
            SurfaceColor.Surface
        } else {
            SurfaceColor.DisabledSurface
        }

    val options =
        TimeUnitValues.entries.map {
            RadioButtonData(it.name, optionSelected == it, enabled, provideStringResource(it.value))
        }

    var selectedOption by remember {
        mutableStateOf(options.find { it.selected } ?: options[0])
    }

    RowComponentContainer(
        modifier =
            modifier
                .background(color = backgroundColor, Shape.SmallBottom)
                .padding(
                    start = Spacing.Spacing8,
                    end = Spacing.Spacing8,
                ),
    ) {
        RadioButtonBlock(orientation, options, selectedOption) {
            selectedOption = it
            val selectedTimeUnit = TimeUnitValues.entries.first { it.name == selectedOption.uid }
            onClick.invoke(selectedTimeUnit)
        }
    }
}

enum class TimeUnitValues(
    val value: String,
) {
    YEARS("years"),
    MONTHS("months"),
    DAYS("days"),
}
