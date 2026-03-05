package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties

internal actual fun fullScreenDialogProperties(): DialogProperties =
    DialogProperties(
        usePlatformDefaultWidth = false,
        dismissOnClickOutside = false,
    )

@Composable
internal actual fun ConfigureDialogSystemBars() {
    // No-op on iOS
}
