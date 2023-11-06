package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.internal.qr.rememberQrCodeGenerator
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

/**
 * Container to render QR code image and text for given
 * data
 *
 * @param data: Data to render QR code and text for
 */
@Composable
fun QrCodeBlock(
    data: String,
    modifier: Modifier = Modifier,
    isDataMatrix: Boolean = false,
    qrCodeSize: Dp = 240.dp,
) {
    if (data.isNotBlank()) {
        Column(
            modifier = modifier.padding(vertical = Spacing.Spacing16)
                .requiredWidthIn(max = qrCodeSize)
                .testTag("QR_CODE_BLOCK_CONTAINER"),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val qrCode by rememberQrCodeGenerator(data, isDataMatrix)

            Box(Modifier.size(qrCodeSize)) {
                qrCode?.let { bitmap ->
                    Image(
                        bitmap = bitmap,
                        contentDescription = null,
                        modifier = Modifier.matchParentSize(),
                        contentScale = ContentScale.FillBounds,
                    )
                }
            }

            Text(
                text = data,
                style = MaterialTheme.typography.titleMedium,
                color = TextColor.OnSurface,
                textAlign = TextAlign.Center,
            )
        }
    }
}
