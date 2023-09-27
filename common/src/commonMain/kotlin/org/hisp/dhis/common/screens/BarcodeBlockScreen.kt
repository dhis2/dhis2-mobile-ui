package org.hisp.dhis.common.screens

import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.BarcodeBlock
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.RowComponentContainer

@Composable
fun BarcodeBlockScreen() {
    ColumnComponentContainer {
        BarcodeBlock(data = "Barcode value")
        Divider()
        BarcodeBlock(data = "889026a1-d01e-4d34-8209-81e8ed5c614b")
        Divider()
        BarcodeBlock(data = "l;kw1jheoi1u23iop1")
        Divider()
        RowComponentContainer {
            BarcodeBlock(data = "563ce8df-8e0b-420c-a63c-fe000b1d1f11")
            BarcodeBlock(data = "378c472d-bb05-4174-9fe5-f6dbf8f5de36")
        }
    }
}
