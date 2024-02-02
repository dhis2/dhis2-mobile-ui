package org.hisp.dhis.mobile.ui.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow

internal actual fun Modifier.listCardShadow(modifier: Modifier): Modifier = this.then(
    modifier.shadow(elevation = Spacing.Spacing10, shape = RoundedCornerShape(Radius.S), spotColor = SurfaceColor.Container),
)
