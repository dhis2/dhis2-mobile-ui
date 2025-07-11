package org.hisp.dhis.mobile.ui.designsystem.theme

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RippleConfiguration
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
internal fun customRippleConfiguration(
    color: Color = SurfaceColor.Primary,
    alpha: RippleAlpha =
        RippleAlpha(
            focusedAlpha = 0.16f,
            draggedAlpha = 0.16f,
            hoveredAlpha = 0.04f,
            pressedAlpha = 0.16f,
        ),
) = RippleConfiguration(
    color,
    alpha,
)
