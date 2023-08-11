package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import org.hisp.dhis.mobileui.designsystem.component.EmptyInput
import org.hisp.dhis.mobileui.designsystem.component.InputShell

@Composable
internal fun EmptyInputPreview() {
    EmptyInput()
}

@Composable
internal fun InputShellPreview() {
    InputShell("Label")
}
