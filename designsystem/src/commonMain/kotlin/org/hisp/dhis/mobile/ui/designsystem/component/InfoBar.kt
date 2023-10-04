package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.Ripple
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

/**

 */
@Composable
fun InfoBar(
    text: String,
    icon: @Composable (() -> Unit)? = null,
    color: Color,
    backgroundColor: Color,
    actionText: String = provideStringResource("sync"),
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clip(shape = RoundedCornerShape(Radius.Full))
            .background(color = backgroundColor)
            .padding(start = Spacing.Spacing8)
            .fillMaxWidth()
            .height(Spacing.Spacing40),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon?.invoke()
        Spacer(Modifier.size(Spacing.Spacing8))
        Text(color = color, text = text, style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.weight(1f))
        onClick?.let {
            CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme) {
                Row(
                    Modifier
                        .clip(shape = RoundedCornerShape(Radius.L))
                        .clickable(onClick = onClick)
                        .padding(Spacing.Spacing10),
                ) {
                    Text(
                        color = SurfaceColor.Primary,
                        text = actionText,
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
            }
        }
    }
}
