package org.hisp.dhis.common.screens.basicTextInputs

import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputNotSupported

@Composable
fun InputNotSupportedScreen() {
    ColumnComponentContainer {
        InputNotSupported(title = "Label")
    }
}
