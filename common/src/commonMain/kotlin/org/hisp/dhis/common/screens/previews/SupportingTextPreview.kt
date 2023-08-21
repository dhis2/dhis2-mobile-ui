package org.hisp.dhis.common.screens.previews

import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingText
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState

@Composable
internal fun SupportingTextPreview(text: String, state: SupportingTextState = SupportingTextState.DEFAULT) {
    SupportingText(text, state)
}
