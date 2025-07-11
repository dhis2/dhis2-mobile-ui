package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

/**
 * DHIS2 Indicator. Wraps compose [Row].
 * A component designed to display indicators, each featuring a key and a value,
 * is complemented by a color badge for visual distinction.
 * This configuration allows for clear and efficient presentation of important data points.
 * @param title: the header to be displayed.
 * @param content: description to be displayed.
 * @param modifier: optional modifier.
 * @param indicatorColor: indicator main color.
 */
@Composable
fun Indicator(
    title: String,
    content: String,
    modifier: Modifier = Modifier,
    indicatorColor: Color = SurfaceColor.Container,
) {
    val backgroundColor = indicatorColor.copy(alpha = 0.1f)

    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .clip(RoundedCornerShape(Spacing.Spacing8))
                .background(backgroundColor),
    ) {
        Box(
            Modifier
                .padding(
                    horizontal = Spacing.Spacing16,
                    vertical = Spacing.Spacing8,
                ).weight(1f),
        ) {
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth(),
                )
                Text(
                    text = content,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }

        Box(Modifier.background(indicatorColor).requiredWidth(Spacing.Spacing16).fillMaxHeight())
    }
}
