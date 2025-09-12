package org.hisp.dhis.mobile.ui.designsystem.component.state

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.UIKit.UIApplication

@OptIn(ExperimentalForeignApi::class)
@Composable
internal actual fun safeLowerPadding(): Dp {
    val window = UIApplication.sharedApplication.keyWindow ?: return 0.dp
    val bottomInset = window.safeAreaInsets.useContents { bottom }
    return bottomInset.dp
}
