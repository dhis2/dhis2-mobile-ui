package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

sealed class InputStyle(
    val startIndent: Dp,
    val backGroundColor: Color,
    val unfocusedIndicatorColor: Color?,

) {
    class DataInputStyle : InputStyle(
        startIndent = Spacing.Spacing0,
        backGroundColor = SurfaceColor.Surface,
        unfocusedIndicatorColor = null,
    )

    class ParameterInputStyle : InputStyle(
        startIndent = Spacing.Spacing40,
        backGroundColor = Color.Transparent,
        unfocusedIndicatorColor = Outline.Light,
    )
}
