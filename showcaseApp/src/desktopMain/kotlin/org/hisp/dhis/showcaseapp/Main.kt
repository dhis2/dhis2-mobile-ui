package org.hisp.dhis.showcaseapp

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        val size = calculateWindowSizeClass()
        App(sizeClass = size)
    }
}
