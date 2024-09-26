package org.hisp.dhis.mobile.ui.designsystem.component.internal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

enum class Keyboard {
    Opened, Closed
}

@Composable
internal expect fun keyboardAsState(): State<Keyboard>
