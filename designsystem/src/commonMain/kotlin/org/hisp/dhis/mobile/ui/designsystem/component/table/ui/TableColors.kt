package org.hisp.dhis.mobile.ui.designsystem.component.table.ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

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
 * @property errorColor The color used to indicate errors.
 * @property warningColor The color used to indicate warnings.
 * @property tableBackground The background color of the table.
 * @property iconColor The color of icons.
 * @property onPrimary The color used for text/icons on primary color.
 */
@Immutable
data class TableColors(
    val primary: Color = Color(0xFF2C98F0),
    val primaryLight: Color = Color(0x332C98F0),
    val headerText: Color = Color(0x8A000000),
    val headerBackground1: Color = Color(0x05000000),
    val headerBackground2: Color = Color(0x0A000000),
    val cellText: Color = Color(0xDE000000),
    val disabledCellText: Color = Color(0x61000000),
    val disabledCellBackground: Color = Color(0x0A000000),
    val disabledSelectedBackground: Color = Color(0x1F164C78),
    val errorColor: Color = Color(0xFFE91E63),
    val warningColor: Color = Color(0xFFFF9800),
    val tableBackground: Color = Color(0xFFFFFFFF),
    val iconColor: Color = Color.LightGray,
    val onPrimary: Color = Color.White,
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
        hasError -> errorColor
        hasWarning -> warningColor
        !isEditable -> disabledCellText
        else -> cellText
    }

    /**
     * Returns the appropriate color for the mandatory icon based on the cell value state.
     *
     * @param hasValue Indicates if the cell has a value.
     * @return The color to be used for the mandatory icon.
     */
    fun cellMandatoryIconColor(hasValue: Boolean) = when (hasValue) {
        true -> iconColor
        false -> errorColor
    }
}

/**
 * CompositionLocal to provide [TableColors] throughout the Compose hierarchy.
 */
val LocalTableColors = staticCompositionLocalOf { TableColors() }
