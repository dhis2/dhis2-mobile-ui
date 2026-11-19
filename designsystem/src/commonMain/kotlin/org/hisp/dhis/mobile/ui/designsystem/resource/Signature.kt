package org.hisp.dhis.mobile.ui.designsystem.resource

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap

internal interface Signature {
    fun clear()

    fun getBitmap(): ImageBitmap
}

@Composable
internal expect fun SignatureCanvas(
    onReady: (Signature) -> Unit,
    onStartedSigning: () -> Unit,
)
