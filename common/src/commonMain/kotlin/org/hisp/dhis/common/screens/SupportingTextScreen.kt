package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingText
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState

@Composable
fun SupportingTextScreen() {
    ColumnComponentContainer(title = "Supporting Text") {
        SubTitle("Standard Supporting Text")
        SupportingText("Supporting text")
        SubTitle("Standard Warning Supporting Text")
        SupportingText("Supporting Text", SupportingTextState.WARNING)
        SubTitle("Standard Error Supporting Text")
        SupportingText("Supporting Text", SupportingTextState.ERROR)
        SubTitle("Overflow Default Supporting Text")
        SupportingText(
            "Lorem ipsum dolor sit amet," +
                " consectetur adipiscing elit. Maecenas dolor lacus," +
                " aliquam. Lorem ipsum dolor sit amet," +
                " consectetur adipiscing elit. Maecenas dolor lacus," +
                " aliquam.Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        )
        SubTitle("Overflow Warning Supporting Text")
        SupportingText(
            "Lorem ipsum dolor sit amet," +
                " consectetur adipiscing elit. Maecenas dolor lacus," +
                " aliquam. Lorem ipsum dolor sit amet," +
                " consectetur adipiscing elit. Maecenas dolor lacus," +
                " aliquam.Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            SupportingTextState.WARNING,
        )
        SubTitle("Overflow Error Supporting Text")
        SupportingText(
            "Lorem ipsum dolor sit amet," +
                " consectetur adipiscing elit. Maecenas dolor lacus," +
                " aliquam. Lorem ipsum dolor sit amet," +
                " consectetur adipiscing elit. Maecenas dolor lacus," +
                " aliquam.Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            SupportingTextState.ERROR,
        )
    }
}
