package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun Badge(
    modifier: Modifier = Modifier,
    text: String? = null,
    color: Color = SurfaceColor.Primary,
    textColor: Color = TextColor.OnPrimary,
) {
    Box(
        modifier
            .defaultMinSize(Spacing.Spacing6, Spacing.Spacing6)
            .background(color, RoundedCornerShape(Spacing.Spacing24)),
    ) {
        text?.let {
            Text(
                modifier = Modifier
                    .padding(horizontal = Spacing.Spacing4)
                    .align(Alignment.Center),
                text = it,
                style = MaterialTheme.typography.labelSmall.copy(color = textColor),
            )
        }
    }
}

@Composable
fun ErrorBadge(
    modifier: Modifier = Modifier,
    text: String? = null,
) {
    Badge(modifier, text, SurfaceColor.Error, TextColor.OnError)
}
