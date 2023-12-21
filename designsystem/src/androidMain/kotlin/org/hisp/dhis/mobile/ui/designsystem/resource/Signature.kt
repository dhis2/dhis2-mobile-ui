package org.hisp.dhis.mobile.ui.designsystem.resource

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import se.warting.signaturepad.SignaturePadAdapter
import se.warting.signaturepad.SignaturePadView

class AndroidSignature(
    private val signaturePadAdapter: SignaturePadAdapter
) : Signature {
    override fun clear() {
        signaturePadAdapter.clear()
    }

    override fun getBitmap(): ImageBitmap {
        return signaturePadAdapter.getSignatureBitmap().asImageBitmap()
    }
}

@Composable
actual fun SignatureCanvas(
    onReady: (Signature) -> Unit,
    onStartedSigning: () -> Unit,
) {
    SignaturePadView(
        onReady = {
            onReady(AndroidSignature(it))
        },
        onStartSigning = onStartedSigning
    )
}