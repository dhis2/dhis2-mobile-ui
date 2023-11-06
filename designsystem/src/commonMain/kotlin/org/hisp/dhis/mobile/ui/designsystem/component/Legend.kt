package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import org.hisp.dhis.mobile.ui.designsystem.theme.Border
import org.hisp.dhis.mobile.ui.designsystem.theme.InternalSizeValues
import org.hisp.dhis.mobile.ui.designsystem.theme.Ripple
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.hoverPointerIcon

@Composable
fun Legend(
    legendData: LegendData,
    modifier: Modifier = Modifier,
) {
    var showBottomSheetShell by rememberSaveable { mutableStateOf(false) }

    CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme) {
        val hasPopupLegendDescriptionData = legendData.popUpLegendDescriptionData.orEmpty().isNotEmpty()
        val clickableModifier = if (hasPopupLegendDescriptionData) {
            Modifier
                .clickable(
                    onClick = {
                        legendData.popUpLegendDescriptionData?.let {
                            showBottomSheetShell = true
                        }
                    },
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
                        imageVector = Icons.Outlined.HelpOutline,
                        contentDescription = "Legend Icon",
                        modifier = Modifier.size(InternalSizeValues.Size18),
                    )
                }
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(),
                thickness = Border.Regular,
                color = legendData.color,
            )
        }
    }

    if (showBottomSheetShell) {
        BottomSheetShell(
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

data class LegendDescriptionData(
    val color: Color,
    val text: String,
    val range: IntRange,
)

data class LegendData(
    val color: Color,
    val title: String,
    val popUpLegendDescriptionData: List<LegendDescriptionData>? = null,
)
