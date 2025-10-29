package org.hisp.dhis.mobile.ui.designsystem.component.internal.barcode

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

internal actual class BarcodeGenerator actual constructor() {
    @OptIn(BetaInteropApi::class)
    actual fun generate(data: String): ImageBitmap? {
        // 1. Convert the input String to NSData
        val nsString: NSString? = NSString.create(data)
        val nsData = nsString?.dataUsingEncoding(NSUTF8StringEncoding) ?: return null

        // 2. Create the Core Image filter for a specific barcode type.
        // Core Image supports several. "CICode128BarcodeGenerator" is common and versatile.
        // Other options include:
        // - "CIPDF417BarcodeGenerator" (2D, but often listed with barcodes)
        // - "CIAztecCodeGenerator" (2D)
        // You might need to make the filter type configurable if you support multiple barcode symbologies.
        // For this example, let's stick with Code 128.
        val filterName = "CICode128BarcodeGenerator"
        val filter = CIFilter.filterWithName(filterName) ?: return null

        // 3. Set the filter parameters
        filter.setValue(nsData, forKey = "inputMessage")

        // Some barcode filters have additional options, e.g., for quiet space:
        // filter.setValue(10.0, forKey = "inputQuietSpace") // Example, value depends on filter

        // Barcode height for some filters can be controlled via "inputBarcodeHeight"
        // However, scaling the CIImage often provides more consistent results across types.
        // filter.setValue(50.0, forKey = "inputBarcodeHeight") // Example

        // 4. Get the output CIImage
        val outputCIImage = filter.outputImage ?: return null

        // 5. Convert CIImage to ImageBitmap, applying scaling
        // Pass desired dimensions or let the helper use defaults.
        // For barcodes, width is variable, height is more controllable.
        return outputCIImage.toImageBitmap(targetHeight = 150.0) // Example: target height of 150px
    }
}
