package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

/**
 * DHIS2 [Badge]) wraps Material 3's [Box].
 * Badges are used to convey dynamic information,
 * such as a count or status.
 * A badge can include text, labels, or numbers.
 * @param text: the text to be displayed within the component.
 * @param textColor: color for the text.
 * @param color: background color.
 */
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
            .background(color, CircleShape),
    ) {
        text?.let {
            Text(
                modifier = Modifier
                    .padding(horizontal = Spacing.Spacing4)
                    .padding(bottom = Spacing.Spacing1),
                text = it,
                textAlign = TextAlign.Center,
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
