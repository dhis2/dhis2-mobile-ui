package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

sealed class InputStyle(
    val startIndent: Dp,
    val backGroundColor: Color,
    val disabledBackGroundColor: Color,
    val unfocusedIndicatorColor: Color?,
    val disabledIndicatorColor: Color?,
) {
    class DataInputStyle : InputStyle(
        startIndent = Spacing.Spacing0,
        backGroundColor = SurfaceColor.Surface,
        disabledBackGroundColor = SurfaceColor.DisabledSurface,
        unfocusedIndicatorColor = null,
        disabledIndicatorColor = null,
    )

    class ParameterInputStyle : InputStyle(
        startIndent = Spacing.Spacing40,
        backGroundColor = Color.Transparent,
        disabledBackGroundColor = Color.Transparent,
        unfocusedIndicatorColor = Outline.Light,
        disabledIndicatorColor = Outline.Light,
    )
}
