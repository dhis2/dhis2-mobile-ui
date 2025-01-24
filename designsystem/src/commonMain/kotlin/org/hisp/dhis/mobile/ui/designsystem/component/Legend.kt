package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.HelpOutline
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import org.hisp.dhis.mobile.ui.designsystem.theme.Border
import org.hisp.dhis.mobile.ui.designsystem.theme.InternalSizeValues
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing.Spacing0
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.hoverPointerIcon

/**
 * DHIS2 Legend.
 * Used to display information on input value based on a range of values.
 * @param legendData data class with all parameters for component.
 * @param modifier optional modifier.
 * @param windowInsets optional window insets to be used by the bottom sheet.
 * @param bottomSheetLowerPadding optional bottom sheet lower padding.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Legend(
    legendData: LegendData,
    modifier: Modifier = Modifier,
    windowInsets: @Composable () -> WindowInsets = { BottomSheetDefaults.windowInsets },
    bottomSheetLowerPadding: Dp = Spacing0,
) {
    var showBottomSheetShell by rememberSaveable { mutableStateOf(false) }

    val hasPopupLegendDescriptionData = legendData.popUpLegendDescriptionData.orEmpty().isNotEmpty()
    val interactionSource = remember { MutableInteractionSource() }
    val clickableModifier = if (hasPopupLegendDescriptionData) {
        Modifier
            .clickable(
                onClick = {
                    legendData.popUpLegendDescriptionData?.let {
                        showBottomSheetShell = true
                    }
                },
                role = Role.Button,
                interactionSource = interactionSource,
                indication = ripple(
                    color = SurfaceColor.Primary,
                ),
            )
            .hoverPointerIcon(true)
    } else {
        Modifier
    }

    Column(
        modifier = modifier
            .then(clickableModifier)
            .testTag("LEGEND"),
    ) {
        Row(
            modifier = Modifier
                .padding(Spacing.Spacing16, Spacing.Spacing8, Spacing.Spacing8, Spacing.Spacing6),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.align(Alignment.Top)) {
                Spacer(modifier = Modifier.size(Spacing.Spacing4).padding(end = Spacing.Spacing8))
                Box(
                    modifier = Modifier.size(InternalSizeValues.Size12)
                        .clip(CircleShape)
                        .background(legendData.color),
                )
            }
            Text(
                legendData.title,
                Modifier.padding(start = Spacing.Spacing8)
                    .weight(2f, true),
                style = MaterialTheme.typography.bodyMedium,
            )

            if (hasPopupLegendDescriptionData) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.HelpOutline,
                    contentDescription = "Legend Icon",
                    modifier = Modifier.size(InternalSizeValues.Size18),
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(),
            thickness = Border.Regular,
            color = legendData.color,
        )
    }

    if (showBottomSheetShell) {
        BottomSheetShell(
            windowInsets = windowInsets,
            bottomPadding = bottomSheetLowerPadding,
            modifier = Modifier.testTag("LEGEND_BOTTOM_SHEET"),
            title = legendData.title,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Button",
                    tint = SurfaceColor.Primary,
                )
            },
            content = {
                legendData.popUpLegendDescriptionData?.let { LegendRange(it) }
            },
        ) {
            showBottomSheetShell = false
        }
    }
}

/**
 * DHIS2 Legend Description.
 * Used to display information on input value based on a range of values.
 * @param color: the legend item color
 * @param text: the item text.
 * @param range: the item range.
 * @param modifier: optional modifier.
 */
@Composable
internal fun LegendDescription(color: Color, text: String, range: IntRange, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(Spacing.Spacing0, Spacing.Spacing8, Spacing.Spacing8, Spacing.Spacing6),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top,
    ) {
        Column(modifier = Modifier.align(Alignment.Top).padding(end = Spacing.Spacing8, top = Spacing.Spacing4, bottom = Spacing.Spacing4)) {
            Box(
                modifier = Modifier.size(Spacing.Spacing12)
                    .clip(CircleShape)
                    .background(color),
            )
        }
        Text(
            text,
            Modifier.padding(start = Spacing.Spacing8, end = Spacing.Spacing16)
                .weight(2f, true),
            style = MaterialTheme.typography.bodyMedium,
        )
        Column(modifier = Modifier.align(Alignment.Top)) {
            LegendDescriptionRangeText(range.first.toString() + " - " + range.last.toString())
        }
    }
}

/**
 * DHIS2 Legend Range.
 * Used to a list of [LegendDescription] items.
 * @param legendDescriptionRangeDataList: list og [LegendDescriptionData]
 * to use for each Legend item.
 * @param modifier: optional modifier.
 */
@Composable
fun LegendRange(
    legendDescriptionRangeDataList: List<LegendDescriptionData>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        legendDescriptionRangeDataList.forEach { item ->
            LegendDescription(item.color, item.text, item.range)
        }
    }
}

/**
 * Data class used for DHIS2  [LegendRange] component.
 * @param color: of legend item.
 * @param text: of legend item.
 * @param range: range for item.
 */
data class LegendDescriptionData(
    val color: Color,
    val text: String,
    val range: IntRange,
)

/**
 * Data class used for DHIS2  [Legend] component.
 * @param color of legend.
 * @param title text to be displayed.
 * @param popUpLegendDescriptionData list of [LegendDescriptionData] with information for the
 * legend range description pop up.
 */
data class LegendData(
    val color: Color,
    val title: String,
    val popUpLegendDescriptionData: List<LegendDescriptionData>? = null,
)
