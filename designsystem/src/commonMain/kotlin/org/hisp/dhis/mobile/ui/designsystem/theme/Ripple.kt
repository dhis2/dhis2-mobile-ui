package org.hisp.dhis.mobile.ui.designsystem.theme

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object Ripple {
    internal class CustomDHISRippleTheme(
        private val color: Color = SurfaceColor.Primary,
    ) : RippleTheme {

        private val alpha = RippleAlpha(
            focusedAlpha = 0.16f,
            draggedAlpha = 0.16f,
            hoveredAlpha = 0.04f,
            pressedAlpha = 0.16f,
        )

        @Composable
        override fun defaultColor(): Color = color

        @Composable
        override fun rippleAlpha(): RippleAlpha = alpha
    }
}
