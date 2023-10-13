package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputSignature
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.Title
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun InputSignatureScreen() {
    ColumnComponentContainer {
        Title("Input Signature", textColor = TextColor.OnSurfaceVariant)

        SubTitle("Basic Input Signature ", textColor = TextColor.OnSurfaceVariant)
        var signatureCaptured by rememberSaveable { mutableStateOf(false) }
        InputSignature(
            title = "Label",
            signatureAdded = signatureCaptured,
            onResetButtonClicked = {
                signatureCaptured = false
            },
            onUpdateButtonClicked = {
                signatureCaptured = true
            },
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Disabled Input Signature without data ", textColor = TextColor.OnSurfaceVariant)
        var signatureCaptured1 by rememberSaveable { mutableStateOf(false) }
        InputSignature(
            title = "Label",
            state = InputShellState.DISABLED,
            signatureAdded = signatureCaptured1,
            onResetButtonClicked = {
                signatureCaptured1 = false
            },
            onUpdateButtonClicked = {
                signatureCaptured1 = true
            },
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Disabled Input Signature with data ", textColor = TextColor.OnSurfaceVariant)
        var signatureCaptured2 by rememberSaveable { mutableStateOf(true) }
        InputSignature(
            title = "Label",
            state = InputShellState.DISABLED,
            signatureAdded = signatureCaptured2,
            onResetButtonClicked = {
                signatureCaptured2 = false
            },
            onUpdateButtonClicked = {
                signatureCaptured2 = true
            },
        )
    }
}
