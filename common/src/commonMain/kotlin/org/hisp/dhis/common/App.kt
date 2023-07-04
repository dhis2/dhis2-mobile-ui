package org.hisp.dhis.common

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import org.hisp.dhis.common.components.Components
import org.hisp.dhis.common.screens.ButtonScreen
import org.hisp.dhis.common.screens.HomeScreen

@Composable
fun App() {
    val currentScreen = remember { mutableStateOf(Components.HOME)}

    MaterialTheme {
        when(currentScreen.value) {
            Components.BUTTON -> ButtonScreen()
            else -> HomeScreen { currentScreen.value = it }
        }
    }
}
