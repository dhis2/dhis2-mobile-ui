package org.hisp.dhis.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import org.hisp.dhis.common.designsystem.theme.DHIS2Theme
import org.hisp.dhis.common.screens.ButtonScreen
import org.hisp.dhis.common.screens.Components
import org.hisp.dhis.common.screens.HomeScreen

@Composable
fun App() {
    val currentScreen = remember { mutableStateOf(Components.HOME) }

    DHIS2Theme {
        when (currentScreen.value) {
            Components.BUTTON -> ButtonScreen()
            else -> HomeScreen { currentScreen.value = it }
        }
    }
}
