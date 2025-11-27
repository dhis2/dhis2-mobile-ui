package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

/**
 * DHIS2 Input Shell style.
 * Used internally by [InputShell].
 * @param startIndent: value of the indent to be used at start.
 * @param backGroundColor: component background color.
 * @param disabledBackGroundColor: disabled component background color.
 * @param unfocusedIndicatorColor: unfocused indicator color.
 * @param disabledIndicatorColor: disabled indicator color.
 */
sealed class InputStyle(
    val startIndent: Dp,
    var backGroundColor: Color,
    val disabledBackGroundColor: Color,
    val unfocusedIndicatorColor: Color?,
    val disabledIndicatorColor: Color?,
) {
    class DataInputStyle :
        InputStyle(
            startIndent = Spacing.Spacing0,
            backGroundColor = SurfaceColor.Surface,
            disabledBackGroundColor = SurfaceColor.DisabledSurface,
            unfocusedIndicatorColor = null,
            disabledIndicatorColor = null,
        )

    class ParameterInputStyle :
        InputStyle(
            startIndent = Spacing.Spacing40,
            backGroundColor = Color.Transparent,
            disabledBackGroundColor = Color.Transparent,
            unfocusedIndicatorColor = Outline.Light,
            disabledIndicatorColor = Outline.Light,
        )
}
