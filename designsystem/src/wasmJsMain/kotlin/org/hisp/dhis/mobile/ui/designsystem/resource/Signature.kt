package org.hisp.dhis.mobile.ui.designsystem.resource

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun SignatureCanvas(
    onReady: (Signature) -> Unit,
    onStartedSigning: () -> Unit,
) {
    // No-op for wasm
}
