package org.hisp.dhis.mobile.ui.designsystem.component.parameter

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.mobile.ui.designsystem.component.InputStyle
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState
import org.hisp.dhis.mobile.ui.designsystem.component.internal.themedIfDefault
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2Theme
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

data object ParameterInputStyle :
    InputStyle.CustomInputStyle(
        startIndent = Spacing.Spacing40,
        supportingTextLowerPadding = Spacing.Spacing4,
        backGroundColor = Color.Transparent,
        disabledBackGroundColor = Color.Transparent,
        unfocusedIndicatorColor = Outline.Light,
        disabledIndicatorColor = Outline.Light,
    ) {
    @Deprecated("Use ParameterInputStyle", ReplaceWith("ParameterInputStyle"))
    operator fun invoke(): ParameterInputStyle = this

    @Composable
    override fun themedUnfocusedIndicatorColor(): Color? =
        unfocusedIndicatorColor?.themedIfDefault(Outline.Light, DHIS2Theme.colorScheme.outlineVariant)

    @Composable
    override fun themedDisabledIndicatorColor(): Color? =
        disabledIndicatorColor?.themedIfDefault(Outline.Light, DHIS2Theme.colorScheme.outlineVariant)

    override fun supportingTextBackgroundColor(supportingText: List<SupportingTextData>?): Color =
        when {
            supportingText?.any { it.state == SupportingTextState.ERROR } == true -> SupportingTextState.ERROR.backgroundColor
            supportingText?.any { it.state == SupportingTextState.WARNING } == true -> SupportingTextState.WARNING.backgroundColor
            supportingText?.any { it.state == SupportingTextState.INFO } == true -> SupportingTextState.INFO.backgroundColor
            else -> SupportingTextState.DEFAULT.backgroundColor
        }
}
