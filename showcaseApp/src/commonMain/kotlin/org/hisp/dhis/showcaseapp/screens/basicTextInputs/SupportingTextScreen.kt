package org.hisp.dhis.showcaseapp.screens.basicTextInputs

import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingText
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState
import org.hisp.dhis.showcaseapp.screens.previews.lorem

@Composable
fun SupportingTextScreen() {
    ColumnScreenContainer(title = BasicTextInputs.SUPPORTING_TEXT.label) {
        ColumnComponentContainer("Standard Supporting Text") {
            SupportingText("Supporting Text")
        }
        ColumnComponentContainer("Standard Warning Supporting Text") {
            SupportingText("Supporting Text", SupportingTextState.WARNING)
        }
        ColumnComponentContainer("Standard Error Supporting Text") {
            SupportingText("Supporting Text", SupportingTextState.ERROR)
        }
        ColumnComponentContainer("Overflow Default Supporting Text") {
            SupportingText(lorem)
        }
        ColumnComponentContainer("Overflow Warning Supporting Text") {
            SupportingText(lorem, SupportingTextState.WARNING)
        }
        ColumnComponentContainer("Overflow Error Supporting Text") {
            SupportingText(lorem, SupportingTextState.ERROR)
        }
    }
}
