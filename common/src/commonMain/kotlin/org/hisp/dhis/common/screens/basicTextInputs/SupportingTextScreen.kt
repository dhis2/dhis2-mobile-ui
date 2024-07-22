package org.hisp.dhis.common.screens.basicTextInputs

import androidx.compose.runtime.Composable
import org.hisp.dhis.common.screens.previews.lorem
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentItemContainer
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingText
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState

@Composable
fun SupportingTextScreen() {
    ColumnComponentContainer(title = BasicTextInputs.SUPPORTING_TEXT.label) {
        ColumnComponentItemContainer("Standard Supporting Text") {
            SupportingText("Supporting Text")
        }
        ColumnComponentItemContainer("Standard Warning Supporting Text") {
            SupportingText("Supporting Text", SupportingTextState.WARNING)
        }
        ColumnComponentItemContainer("Standard Error Supporting Text") {
            SupportingText("Supporting Text", SupportingTextState.ERROR)
        }
        ColumnComponentItemContainer("Overflow Default Supporting Text") {
            SupportingText(lorem)
        }
        ColumnComponentItemContainer("Overflow Warning Supporting Text") {
            SupportingText(lorem, SupportingTextState.WARNING)
        }
        ColumnComponentItemContainer("Overflow Error Supporting Text") {
            SupportingText(lorem, SupportingTextState.ERROR)
        }
    }
}
