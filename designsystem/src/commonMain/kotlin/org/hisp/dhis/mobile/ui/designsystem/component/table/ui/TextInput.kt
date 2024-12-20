package org.hisp.dhis.mobile.ui.designsystem.component.table.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

enum class Keyboard {
    Opened, Closed
}

// todo find a multiplatform way to manage LocalVIew.current. and onGlobal Layout listener
@Composable
fun keyboardAsState(): State<Keyboard> {
    val keyboardState = remember { mutableStateOf(Keyboard.Closed) }
    // val view = LocalView.current
    // DisposableEffect(view) {
//        val onGlobalListener = ViewTreeObserver.OnGlobalLayoutListener {
//            val rect = Rect()
//            view.getWindowVisibleDisplayFrame(rect)
//            val screenHeight = view.rootView.height
//            val keypadHeight = screenHeight - rect.bottom
//            keyboardState.value = if (keypadHeight > screenHeight * 0.15) {
//                Keyboard.Opened
//            } else {
//                Keyboard.Closed
//            }
//        }
//        view.viewTreeObserver.addOnGlobalLayoutListener(onGlobalListener)
//
//        onDispose {
//            view.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalListener)
    // }
    //  }

    return keyboardState
}
