package org.hisp.dhis.mobile.ui.designsystem.component.table.ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Data class representing the configuration settings for the table component.
 *
 * @property headerActionsEnabled Indicates if header actions are enabled.
 * @property editable Indicates if the table cells are editable.
 * @property textInputViewMode Indicates if the text input view mode is enabled.
 */
@Immutable
data class TableConfiguration(
    val headerActionsEnabled: Boolean = true,
    val editable: Boolean = true,
    val textInputViewMode: Boolean = true,
)

/**
 * CompositionLocal to provide [TableConfiguration] throughout the Compose hierarchy.
 */
val LocalTableConfiguration = staticCompositionLocalOf { TableConfiguration() }
