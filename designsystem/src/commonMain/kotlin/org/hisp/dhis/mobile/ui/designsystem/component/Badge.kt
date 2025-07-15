package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

/**
 * DHIS2 [Badge]) wraps [androidx.compose.material3.Badge].
 * Badges are used to convey dynamic information,
 * such as a count or status.
 * A badge can include text, labels, or numbers.
 * @param text: the text to be displayed within the component.
 * @param textColor: color for the text.
 * @param color: background color.
 * @param modifier: optional modifier.
 */
@Composable
fun Badge(
    modifier: Modifier = Modifier,
    text: String? = null,
    color: Color = SurfaceColor.Primary,
    textColor: Color = TextColor.OnPrimary,
) {
    androidx.compose.material3.Badge(
        modifier = modifier,
        containerColor = color,
        content =
            if (text != null) {
                {
                    Text(
                        text = text,
                        color = textColor,
                    )
                }
            } else {
                null
            },
    )
}

@Composable
fun ErrorBadge(
    modifier: Modifier = Modifier,
    text: String? = null,
) {
    Badge(modifier, text, SurfaceColor.Error, TextColor.OnError)
}
