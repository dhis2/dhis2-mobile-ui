package org.hisp.dhis.mobile.ui.designsystem.component.parameter

import androidx.compose.ui.graphics.Color
import org.hisp.dhis.mobile.ui.designsystem.component.InputStyle
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

class ParameterInputStyle :
    InputStyle.CustomInputStyle(
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
