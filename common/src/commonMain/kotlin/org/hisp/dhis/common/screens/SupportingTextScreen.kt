package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import org.hisp.dhis.common.screens.previews.SupportingTextPreview
import org.hisp.dhis.mobileui.designsystem.component.SupportingTextState

@Composable
fun SupportingTextScreen() {
    Column {
        SupportingTextPreview("Supporting Text")
        SupportingTextPreview("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas dolor lacus, aliquam. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas dolor lacus, aliquam.Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
        SupportingTextPreview("Supporting Text", SupportingTextState.WARNING)
        SupportingTextPreview("Supporting Text", SupportingTextState.ERROR)
    }
}
