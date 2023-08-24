package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

@Composable
fun ButtonBlock(
    primaryButton: @Composable (() -> Unit)? = null,
    secondaryButton: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Row(horizontalArrangement = Arrangement.Center, modifier = modifier) {
        if (secondaryButton != null) {
            Box(modifier = Modifier.weight(0.5f)) {
                primaryButton?.invoke()
            }
            Spacer(Modifier.size(Spacing.Spacing16))
            Box(modifier = Modifier.weight(0.5f)) {
                secondaryButton.invoke()
            }
        } else {
            primaryButton?.invoke()
        }
    }
}
