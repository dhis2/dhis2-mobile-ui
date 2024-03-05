package org.hisp.dhis.mobile.ui.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

internal actual fun Modifier.shadow(
    elevation: Dp,
    blur: Dp,
    radius: Dp,
    spotColor: Color,
): Modifier = this.then(
    this.shadow(elevation = elevation, shape = RoundedCornerShape(radius), spotColor = spotColor),
)
