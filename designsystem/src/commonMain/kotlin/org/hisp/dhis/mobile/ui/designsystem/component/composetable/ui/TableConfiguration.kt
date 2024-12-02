package org.hisp.dhis.mobile.ui.designsystem.component.composetable.ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf

@Immutable
data class TableConfiguration(
    val headerActionsEnabled: Boolean = true,
    val editable: Boolean = true,
    val textInputViewMode: Boolean = true,
)

val LocalTableConfiguration = staticCompositionLocalOf { TableConfiguration() }
