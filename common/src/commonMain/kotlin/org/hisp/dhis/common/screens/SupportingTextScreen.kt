package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hisp.dhis.common.screens.previews.SupportingTextPreview
import org.hisp.dhis.mobileui.designsystem.component.SupportingTextState
import org.hisp.dhis.mobileui.designsystem.theme.Spacing

@Composable
fun SupportingTextScreen() {
    Column(verticalArrangement = Arrangement.spacedBy(Spacing.Spacing10)) {
        Spacer(Modifier.size(Spacing.Spacing24))
        Text("Standard Supporting Text")
        SupportingTextPreview("Supporting text")
        Text("Standard Warning Supporting Text")
        SupportingTextPreview("Supporting Text", SupportingTextState.WARNING)
        Text("Standard Error Supporting Text")
        SupportingTextPreview("Supporting Text", SupportingTextState.ERROR)
        Text("Overflow Default Supporting Text")
        SupportingTextPreview(
            "Lorem ipsum dolor sit amet," +
                " consectetur adipiscing elit. Maecenas dolor lacus," +
                " aliquam. Lorem ipsum dolor sit amet," +
                " consectetur adipiscing elit. Maecenas dolor lacus," +
                " aliquam.Lorem ipsum dolor sit amet, consectetur adipiscing elit."
        )
        Text("Overflow Warning Supporting Text")
        SupportingTextPreview(
            "Lorem ipsum dolor sit amet," +
                " consectetur adipiscing elit. Maecenas dolor lacus," +
                " aliquam. Lorem ipsum dolor sit amet," +
                " consectetur adipiscing elit. Maecenas dolor lacus," +
                " aliquam.Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            SupportingTextState.WARNING
        )
        Text("Overflow Error Supporting Text")
        SupportingTextPreview(
            "Lorem ipsum dolor sit amet," +
                " consectetur adipiscing elit. Maecenas dolor lacus," +
                " aliquam. Lorem ipsum dolor sit amet," +
                " consectetur adipiscing elit. Maecenas dolor lacus," +
                " aliquam.Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            SupportingTextState.ERROR
        )
    }
}
