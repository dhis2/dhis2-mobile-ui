package org.hisp.dhis.common.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingText
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState

@Composable
fun SupportingTextScreen() {
    ColumnComponentContainer {
        Text("Standard Supporting Text")
        SupportingText("Supporting text")
        Text("Standard Warning Supporting Text")
        SupportingText("Supporting Text", SupportingTextState.WARNING)
        Text("Standard Error Supporting Text")
        SupportingText("Supporting Text", SupportingTextState.ERROR)
        Text("Overflow Default Supporting Text")
        SupportingText(
            "Lorem ipsum dolor sit amet," +
                " consectetur adipiscing elit. Maecenas dolor lacus," +
                " aliquam. Lorem ipsum dolor sit amet," +
                " consectetur adipiscing elit. Maecenas dolor lacus," +
                " aliquam.Lorem ipsum dolor sit amet, consectetur adipiscing elit."
        )
        Text("Overflow Warning Supporting Text")
        SupportingText(
            "Lorem ipsum dolor sit amet," +
                " consectetur adipiscing elit. Maecenas dolor lacus," +
                " aliquam. Lorem ipsum dolor sit amet," +
                " consectetur adipiscing elit. Maecenas dolor lacus," +
                " aliquam.Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            SupportingTextState.WARNING
        )
        Text("Overflow Error Supporting Text")
        SupportingText(
            "Lorem ipsum dolor sit amet," +
                " consectetur adipiscing elit. Maecenas dolor lacus," +
                " aliquam. Lorem ipsum dolor sit amet," +
                " consectetur adipiscing elit. Maecenas dolor lacus," +
                " aliquam.Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            SupportingTextState.ERROR
        )
    }
}
