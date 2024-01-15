package org.hisp.dhis.mobile.ui.designsystem.theme

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object Ripple {
    internal object CustomDHISRippleTheme : RippleTheme {

        private val alpha = RippleAlpha(
            focusedAlpha = 0.16f,
            draggedAlpha = 0.16f,
            hoveredAlpha = 0.04f,
            pressedAlpha = 0.16f,
        )

        @Composable
        override fun defaultColor(): Color = SurfaceColor.Primary

        @Composable
        override fun rippleAlpha(): RippleAlpha = alpha
    }

    internal object CustomDHISWarningRippleTheme : RippleTheme {

        private val alpha = RippleAlpha(
            focusedAlpha = 0.16f,
            draggedAlpha = 0.16f,
            hoveredAlpha = 0.04f,
            pressedAlpha = 0.16f,
        )

        @Composable
        override fun defaultColor(): Color = SurfaceColor.Error

        @Composable
        override fun rippleAlpha(): RippleAlpha = alpha
    }
}
