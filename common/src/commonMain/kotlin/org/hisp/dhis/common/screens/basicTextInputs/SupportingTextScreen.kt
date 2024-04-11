package org.hisp.dhis.common.screens.basicTextInputs

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hisp.dhis.common.screens.previews.lorem
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingText
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

@Composable
fun SupportingTextScreen() {
    ColumnComponentContainer(title = "Supporting Text") {
        SubTitle("Standard Supporting Text")
        SupportingText("Supporting text")
        Spacer(Modifier.size(Spacing.Spacing18))
        SubTitle("Standard Warning Supporting Text")
        SupportingText("Supporting Text", SupportingTextState.WARNING)
        Spacer(Modifier.size(Spacing.Spacing18))
        SubTitle("Standard Error Supporting Text")
        SupportingText("Supporting Text", SupportingTextState.ERROR)
        Spacer(Modifier.size(Spacing.Spacing18))
        SubTitle("Overflow Default Supporting Text")
        SupportingText(
            lorem,
        )
        SubTitle("Overflow Warning Supporting Text")
        SupportingText(
            lorem,
            SupportingTextState.WARNING,
        )
        Spacer(Modifier.size(Spacing.Spacing18))
        SubTitle("Overflow Error Supporting Text")
        SupportingText(
            lorem,
            SupportingTextState.ERROR,
        )
    }
}
