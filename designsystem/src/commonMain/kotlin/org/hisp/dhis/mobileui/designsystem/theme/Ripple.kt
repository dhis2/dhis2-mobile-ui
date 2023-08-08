package org.hisp.dhis.mobileui.designsystem.theme

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object Ripple {
    internal object CustomDHISRippleTheme : RippleTheme {

        @Composable
        override fun defaultColor(): Color = SurfaceColor.Primary

        @Composable
        override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
            SurfaceColor.Primary,
            lightTheme = true
        )
    }
}
