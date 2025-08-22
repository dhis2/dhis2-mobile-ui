package org.hisp.dhis.mobile.ui.designsystem.component.internal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.cinterop.BetaInteropApi
import platform.Foundation.NSNotificationCenter
import platform.Foundation.NSOperationQueue
import platform.UIKit.UIKeyboardDidHideNotification
import platform.UIKit.UIKeyboardDidShowNotification

@OptIn(BetaInteropApi::class)
@Composable
internal actual fun keyboardAsState(): State<Keyboard> {
    val keyboardState = remember { mutableStateOf(Keyboard.Closed) }

    DisposableEffect(Unit) {
        val notificationCenter = NSNotificationCenter.defaultCenter

        val showObserver =
            notificationCenter.addObserverForName(
                name = UIKeyboardDidShowNotification, // Or UIKeyboardWillShowNotification
                `object` = null, // Observe from any sender
                queue = NSOperationQueue.mainQueue, // Ensure updates on the main thread
                usingBlock = { notification ->
                    keyboardState.value = Keyboard.Opened
                },
            )

        val hideObserver =
            notificationCenter.addObserverForName(
                name = UIKeyboardDidHideNotification, // Or UIKeyboardWillHideNotification
                `object` = null,
                queue = NSOperationQueue.mainQueue,
                usingBlock = { notification ->
                    keyboardState.value = Keyboard.Closed
                },
            )

        onDispose {
            notificationCenter.removeObserver(showObserver)
            notificationCenter.removeObserver(hideObserver)
        }
    }

    return keyboardState
}
