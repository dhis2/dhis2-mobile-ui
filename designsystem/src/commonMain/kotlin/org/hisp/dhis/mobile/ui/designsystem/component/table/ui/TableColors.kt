package org.hisp.dhis.mobile.ui.designsystem.component.table.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2Theme
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
    val errorCellText: Color = TextColor.OnErrorContainer,
    val warningCellText: Color = TextColor.OnWarningContainer,
) {
    /**
     * Returns the appropriate cell text color based on error, warning, and editability states.
     *
     * @param hasError Indicates if the cell has an error.
     * @param hasWarning Indicates if the cell has a warning.
     * @param isEditable Indicates if the cell is editable.
     * @return The color to be used for the cell text.
     */
    fun cellTextColor(
        hasError: Boolean,
        hasWarning: Boolean,
        isEditable: Boolean,
    ) = when {
        hasError -> errorCellText
        hasWarning -> warningCellText
        !isEditable -> disabledCellText
        else -> cellText
    }
}

/**
 * CompositionLocal to provide [TableColors] throughout the Compose hierarchy.
 */
internal val DefaultTableColors = TableColors()

internal val LocalTableColors = staticCompositionLocalOf { DefaultTableColors }

/**
 * [TableColors] built from the current DHIS2 theme. Used by [TableTheme] when no custom colors are given.
 */
@Composable
@ReadOnlyComposable
internal fun themedTableColors(): TableColors =
    TableColors(
        primary = DHIS2Theme.colorScheme.primary,
        primaryLight = DHIS2Theme.colorScheme.surfaceContainerHighest,
        headerText = DHIS2Theme.extendedColors.onSurfaceLight,
        headerBackground1 = DHIS2Theme.colorScheme.surfaceContainerLow,
        headerBackground2 = DHIS2Theme.colorScheme.surfaceContainer,
        cellText = DHIS2Theme.colorScheme.onSurfaceVariant,
        disabledCellText = DHIS2Theme.extendedColors.onDisabledSurface,
        disabledCellBackground = DHIS2Theme.extendedColors.disabledSurfaceBright,
        disabledSelectedBackground = DHIS2Theme.extendedColors.disabledSurface,
        tableBackground = DHIS2Theme.colorScheme.surfaceBright,
        onPrimary = DHIS2Theme.colorScheme.onPrimary,
        selectedCell = DHIS2Theme.colorScheme.surfaceContainerHighest,
        errorCellText = DHIS2Theme.colorScheme.onErrorContainer,
        warningCellText = DHIS2Theme.extendedColors.onWarningContainer,
    )
