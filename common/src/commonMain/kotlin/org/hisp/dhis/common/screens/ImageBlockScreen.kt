package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import org.hisp.dhis.mobile.ui.designsystem.component.ImageBlock

@Composable
fun ImageBlockScreen() {
    ImageBlock(
        painter = painterResource("image/sample.png"),
        onClick = {},
    )
}
