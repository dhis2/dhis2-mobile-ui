package org.hisp.dhis.common.screens.basicTextInputs

import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputNotSupported

@Composable
fun InputNotSupportedScreen() {
    ColumnScreenContainer(title = BasicTextInputs.INPUT_NOT_SUPPORTED.label) {
        ColumnComponentContainer {
            InputNotSupported(title = "Label")
        }
    }
}
