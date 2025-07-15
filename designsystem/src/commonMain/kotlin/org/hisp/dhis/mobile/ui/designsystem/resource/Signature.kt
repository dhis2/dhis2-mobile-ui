package org.hisp.dhis.mobile.ui.designsystem.resource

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap

interface Signature {
    fun clear()

    fun getBitmap(): ImageBitmap
}

@Composable
expect fun SignatureCanvas(
    onReady: (Signature) -> Unit,
    onStartedSigning: () -> Unit,
)
