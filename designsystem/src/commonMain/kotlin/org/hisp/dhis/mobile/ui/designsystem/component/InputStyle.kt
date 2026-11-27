package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import org.hisp.dhis.mobile.ui.designsystem.component.internal.themedIfDefault
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2Theme
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

/**
 * DHIS2 InputShell style.
 * Used internally by [InputShell].
 * @param startIndent: value of the indent to be used at start.
 * @param backGroundColor: component background color.
 * @param disabledBackGroundColor: disabled component background color.
 * @param unfocusedIndicatorColor: unfocused indicator color.
 * @param disabledIndicatorColor: disabled indicator color.
 */
@Immutable
sealed class InputStyle(
    val startIndent: Dp,
    val backGroundColor: Color,
    val supportingTextLowerPadding: Dp,
    val disabledBackGroundColor: Color,
    val unfocusedIndicatorColor: Color?,
    val disabledIndicatorColor: Color?,
) {
    abstract fun supportingTextBackgroundColor(supportingText: List<SupportingTextData>?): Color

    /** Theme-aware [backGroundColor]; a value changed by the consumer is kept as is. */
    @Composable
    internal open fun themedBackGroundColor(): Color = backGroundColor

    /** Theme-aware [disabledBackGroundColor]; a value changed by the consumer is kept as is. */
    @Composable
    internal open fun themedDisabledBackGroundColor(): Color = disabledBackGroundColor

    /** Theme-aware [unfocusedIndicatorColor]. */
    @Composable
    internal open fun themedUnfocusedIndicatorColor(): Color? = unfocusedIndicatorColor

    /** Theme-aware [disabledIndicatorColor]. */
    @Composable
    internal open fun themedDisabledIndicatorColor(): Color? = disabledIndicatorColor

    /**
     * Theme-aware [supportingTextBackgroundColor]. Light supporting text backgrounds are mapped to their
     * current theme equivalent; any other color is returned unchanged.
     */
    @Composable
    internal fun themedSupportingTextBackgroundColor(supportingText: List<SupportingTextData>?): Color {
        val color = supportingTextBackgroundColor(supportingText)
        return SupportingTextState.entries.firstOrNull { it.backgroundColor == color }?.themedBackgroundColor ?: color
    }

    data object DarkInputStyle :
        InputStyle(
            startIndent = Spacing.Spacing0,
            backGroundColor = SurfaceColor.Surface,
            supportingTextLowerPadding = Spacing.Spacing0,
            disabledBackGroundColor = SurfaceColor.DisabledSurface,
            unfocusedIndicatorColor = null,
            disabledIndicatorColor = null,
        ) {
        @Deprecated("Use InputStyle.DarkInputStyle", ReplaceWith("InputStyle.DarkInputStyle"))
        operator fun invoke(): DarkInputStyle = this

        override fun supportingTextBackgroundColor(supportingText: List<SupportingTextData>?): Color = Color.Transparent

        @Composable
        override fun themedBackGroundColor(): Color = backGroundColor.themedIfDefault(SurfaceColor.Surface, DHIS2Theme.colorScheme.surface)

        @Composable
        override fun themedDisabledBackGroundColor(): Color =
            disabledBackGroundColor.themedIfDefault(SurfaceColor.DisabledSurface, DHIS2Theme.extendedColors.disabledSurface)
    }

    data object BrightInputStyle :
        InputStyle(
            startIndent = Spacing.Spacing0,
            supportingTextLowerPadding = Spacing.Spacing0,
            backGroundColor = SurfaceColor.SurfaceBright,
            disabledBackGroundColor = SurfaceColor.SurfaceBright,
            unfocusedIndicatorColor = null,
            disabledIndicatorColor = null,
        ) {
        @Deprecated("Use InputStyle.BrightInputStyle", ReplaceWith("InputStyle.BrightInputStyle"))
        operator fun invoke(): BrightInputStyle = this

        override fun supportingTextBackgroundColor(supportingText: List<SupportingTextData>?): Color = Color.Transparent

        @Composable
        override fun themedBackGroundColor(): Color =
            backGroundColor.themedIfDefault(SurfaceColor.SurfaceBright, DHIS2Theme.colorScheme.surfaceBright)

        @Composable
        override fun themedDisabledBackGroundColor(): Color =
            disabledBackGroundColor.themedIfDefault(SurfaceColor.SurfaceBright, DHIS2Theme.colorScheme.surfaceBright)
    }

    open class CustomInputStyle(
        startIndent: Dp,
        backGroundColor: Color,
        supportingTextLowerPadding: Dp,
        disabledBackGroundColor: Color,
        unfocusedIndicatorColor: Color?,
        disabledIndicatorColor: Color?,
    ) : InputStyle(
            startIndent = startIndent,
            supportingTextLowerPadding = supportingTextLowerPadding,
            backGroundColor = backGroundColor,
            disabledBackGroundColor = disabledBackGroundColor,
            unfocusedIndicatorColor = unfocusedIndicatorColor,
            disabledIndicatorColor = disabledIndicatorColor,
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
