package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.text.font.FontWeight
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.Ripple
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

/**

 */
@Composable
fun InfoBar(
    infoBarData: InfoBarData,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clip(shape = RoundedCornerShape(Radius.Full))
            .background(color = infoBarData.backgroundColor)
            .padding(start = Spacing.Spacing8)
            .fillMaxWidth()
            .height(Spacing.Spacing40),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        infoBarData.icon?.invoke()
        Spacer(Modifier.size(Spacing.Spacing8))
        Text(color = infoBarData.color, text = infoBarData.text, style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.weight(1f))
        if (infoBarData.onClick != null && infoBarData.actionText?.isNotEmpty() == true) {
            CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme()) {
                Column(
                    Modifier
                        .clip(shape = RoundedCornerShape(Radius.L))
                        .clickable(onClick = infoBarData.onClick)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        color = SurfaceColor.Primary,
                        text = infoBarData.actionText,
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold),
                        modifier = Modifier.padding(start = Spacing.Spacing8, end = Spacing.Spacing16, bottom = Spacing.Spacing2),
                    )
                }
            }
        }
    }
}

data class InfoBarData(
    val text: String,
    val icon: @Composable (() -> Unit)? = null,
    val color: Color,
    val backgroundColor: Color,
    val actionText: String? = null,
    val onClick: (() -> Unit)? = null,
)
