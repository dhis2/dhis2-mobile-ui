package org.hisp.dhis.mobile.ui.designsystem.component.internal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

@Composable
internal actual fun keyboardAsState(): State<Keyboard> {
    return mutableStateOf(Keyboard.Closed)
}
