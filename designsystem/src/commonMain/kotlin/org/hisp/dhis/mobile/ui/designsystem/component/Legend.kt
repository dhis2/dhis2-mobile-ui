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
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.mobile.ui.designsystem.theme.Ripple
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

@Composable
fun Legend(color: Color, text: String, onClick: () -> Unit) {
    CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme) {
        Column(
            modifier = Modifier
                .testTag("LEGEND")
                .clickable(onClick = onClick),
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
                        modifier = Modifier.size(Spacing.Spacing12)
                            .clip(CircleShape)
                            .background(color),
                    )
                }
                Text(
                    text,
                    Modifier.padding(start = Spacing.Spacing8)
                        .weight(2f, true),
                    style = MaterialTheme.typography.bodyMedium,
                )
                Icon(
                    imageVector = Icons.Outlined.HelpOutline,
                    contentDescription = "Legend Icon",
                    modifier = Modifier.size(Spacing.Spacing18),
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(),
                thickness = Spacing.Spacing2,
                color = color,
            )
        }
    }
}

@Composable
internal fun LegendDescription(color: Color, text: String, range: IntRange) {
    Row(
        modifier = Modifier
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
