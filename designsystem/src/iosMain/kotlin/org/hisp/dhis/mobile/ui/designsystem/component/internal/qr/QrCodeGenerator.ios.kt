package org.hisp.dhis.mobile.ui.designsystem.component.internal.qr

import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.cinterop.BetaInteropApi
import org.hisp.dhis.mobile.ui.designsystem.component.internal.toImageBitmap
import platform.CoreImage.CIFilter
import platform.CoreImage.filterWithName
import platform.Foundation.NSString
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.create
import platform.Foundation.dataUsingEncoding
import platform.Foundation.setValue

internal actual class QrCodeGenerator actual constructor() {
    @OptIn(BetaInteropApi::class)
    actual fun generate(
        data: String,
        isDataMatrix: Boolean,
    ): ImageBitmap? {
        // 1. Convert the input String to NSData
        val nsString: NSString? = NSString.create(data)
        val nsData = nsString?.dataUsingEncoding(NSUTF8StringEncoding) ?: return null

        // 2. Create the Core Image filter
        val filterName = if (isDataMatrix) "CIDataMatrixCodeGenerator" else "CIQRCodeGenerator"
        val filter = CIFilter.filterWithName(filterName) ?: return null

        // 3. Set the filter parameters
        filter.setValue(nsData, forKey = "inputMessage")

        if (!isDataMatrix) {
            // For QR Codes, you can also set the correction level (L, M, Q, H)
            // L = Low (7%), M = Medium (15%), Q = Quartile (25%), H = High (30%)
            filter.setValue("M", forKey = "inputCorrectionLevel")
        }

        // 4. Get the output CIImage
        val outputCIImage = filter.outputImage ?: return null

        // 5. Convert CIImage to ImageBitmap
        // The raw outputCIImage can be very small. For a clear QR code, you'll want to scale it.
        // The .toImageBitmap() helper should ideally handle rendering it at a decent size.
        // If not, you might need to apply a transform to scale the CIImage before converting.

        // Example of applying a scale transform if needed before converting to ImageBitmap:
        // val transform = CGAffineTransformMakeScale(10.0f, 10.0f) // Scale by 10x for example
        // val scaledCIImage = outputCIImage.imageByApplyingTransform(transform)
        // return scaledCIImage.toImageBitmap()

        return outputCIImage.toImageBitmap()
    }
}
