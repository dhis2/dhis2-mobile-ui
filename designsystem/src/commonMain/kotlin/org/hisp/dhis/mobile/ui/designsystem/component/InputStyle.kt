package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

sealed class InputStyle(
    val startIndent: Dp,
    val backGroundColor: Color,
) {
    class DataInputStyle : InputStyle(
        startIndent = Spacing.Spacing0,
        backGroundColor = SurfaceColor.Surface,
    )

    class ParameterInputStyle : InputStyle(
        startIndent = Spacing.Spacing40,
        backGroundColor = Color.Transparent,
    )
}
