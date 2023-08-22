package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ButtonBlock(
    primaryButton: @Composable (() -> Unit)?,
    secondaryButton: @Composable (() -> Unit)? = null

) {
    Row(horizontalArrangement = Arrangement.Center) {
        primaryButton?.invoke()
        Spacer(modifier = Modifier.weight(2f))
        secondaryButton?.let {
            it.invoke()
        }
    }
}
