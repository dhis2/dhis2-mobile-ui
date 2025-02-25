package org.hisp.dhis.mobile.ui.designsystem.component.table.ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

/**
 * Data class representing the colors used in the table component.
 *
 * @property primary The primary color.
 * @property primaryLight The light variant of the primary color.
 * @property headerText The color of the header text.
 * @property headerBackground1 The first background color for the header.
 * @property headerBackground2 The second background color for the header.
 * @property cellText The color of the cell text.
 * @property disabledCellText The color of the text in disabled cells.
 * @property disabledCellBackground The background color of disabled cells.
 * @property disabledSelectedBackground The background color of selected disabled cells.
 * @property tableBackground The background color of the table.
 * @property onPrimary The color used for text/icons on primary color.
 */
@Immutable
data class TableColors(
    val primary: Color = SurfaceColor.Primary,
    val primaryLight: Color = SurfaceColor.ContainerHighest,
    val headerText: Color = TextColor.OnSurfaceLight,
    val headerBackground1: Color = SurfaceColor.ContainerLow,
    val headerBackground2: Color = SurfaceColor.Container,
    val cellText: Color = TextColor.OnSurfaceVariant,
    val disabledCellText: Color = TextColor.OnDisabledSurface,
    val disabledCellBackground: Color = SurfaceColor.DisabledSurfaceBright,
    val disabledSelectedBackground: Color = SurfaceColor.DisabledSurface,
    val tableBackground: Color = SurfaceColor.SurfaceBright,
    val onPrimary: Color = TextColor.OnPrimary,
    val selectedCell: Color = SurfaceColor.ContainerHighest,
) {

    /**
     * Returns the appropriate cell text color based on error, warning, and editability states.
     *
     * @param hasError Indicates if the cell has an error.
     * @param hasWarning Indicates if the cell has a warning.
     * @param isEditable Indicates if the cell is editable.
     * @return The color to be used for the cell text.
     */
    fun cellTextColor(hasError: Boolean, hasWarning: Boolean, isEditable: Boolean) = when {
        hasError -> TextColor.OnErrorContainer
        hasWarning -> TextColor.OnWarningContainer
        !isEditable -> disabledCellText
        else -> cellText
    }
}

/**
 * CompositionLocal to provide [TableColors] throughout the Compose hierarchy.
 */
val LocalTableColors = staticCompositionLocalOf { TableColors() }
