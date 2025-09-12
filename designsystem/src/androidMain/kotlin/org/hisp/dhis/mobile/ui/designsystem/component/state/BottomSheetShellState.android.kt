package org.hisp.dhis.mobile.ui.designsystem.component.state

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

@Composable
internal actual fun safeLowerPadding(): Dp {
    val navInsets = WindowInsets.navigationBars
    return navInsets.asPaddingValues().calculateBottomPadding()
}
