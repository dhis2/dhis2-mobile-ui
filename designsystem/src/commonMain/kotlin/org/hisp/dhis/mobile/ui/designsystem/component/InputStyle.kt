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
    var supportingTextLowerPadding: Dp,
    val disabledBackGroundColor: Color,
    val unfocusedIndicatorColor: Color?,
    val disabledIndicatorColor: Color?,
) {
    abstract fun supportingTextBackgroundColor(supportingText: List<SupportingTextData>?): Color

    class DataInputStyle :
        InputStyle(
            startIndent = Spacing.Spacing0,
            backGroundColor = SurfaceColor.Surface,
            supportingTextLowerPadding = Spacing.Spacing0,
            disabledBackGroundColor = SurfaceColor.DisabledSurface,
            unfocusedIndicatorColor = null,
            disabledIndicatorColor = null,
        ) {
        override fun supportingTextBackgroundColor(supportingText: List<SupportingTextData>?): Color = Color.Transparent
    }

    class ParameterInputStyle :
        InputStyle(
            startIndent = Spacing.Spacing40,
            supportingTextLowerPadding = Spacing.Spacing4,
            backGroundColor = Color.Transparent,
            disabledBackGroundColor = Color.Transparent,
            unfocusedIndicatorColor = Outline.Light,
            disabledIndicatorColor = Outline.Light,
        ) {
        override fun supportingTextBackgroundColor(supportingText: List<SupportingTextData>?): Color =
            when {
                supportingText?.any { it.state == SupportingTextState.ERROR } == true -> SupportingTextState.ERROR.backgroundColor
                supportingText?.any { it.state == SupportingTextState.WARNING } == true -> SupportingTextState.WARNING.backgroundColor
                supportingText?.any { it.state == SupportingTextState.INFO } == true -> SupportingTextState.INFO.backgroundColor
                else -> SupportingTextState.DEFAULT.backgroundColor
            }
    }
}
