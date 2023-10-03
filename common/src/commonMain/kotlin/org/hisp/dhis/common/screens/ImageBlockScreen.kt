package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.ImageBlock
import java.io.File

@Composable
fun ImageBlockScreen() {
    ImageBlock(
        file = File("/data/data/org.hisp.dhis.android/files/sample.jpg"),
        onClick = {},
    )
}
