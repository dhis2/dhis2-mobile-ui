package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.LocalTableColors
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableColors
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableTheme

internal sealed class CellStyle {

    /**
     * Style for header cells.
     *
     * @property backgroundColor The background color of the header cell.
     * @property textColor The text color of the header cell.
     */
    data class HeaderStyle(val backgroundColor: Color, val textColor: Color) : CellStyle()

    /**
     * Style for cells with borders.
     *
     * @property backgroundColor The background color of the cell.
     * @property borderColor The border color of the cell.
     */
    data class CellBorderStyle(val backgroundColor: Color, val borderColor: Color) : CellStyle()

    /**
     * Returns the background color of the cell.
     *
     * @return The background color.
     */
    fun backgroundColor() = when (this) {
        is CellBorderStyle -> backgroundColor
        is HeaderStyle -> backgroundColor
    }

    /**
     * Returns the main color of the cell (text or border color).
     *
     * @return The main color.
     */
    fun mainColor() = when (this) {
        is CellBorderStyle -> borderColor
        is HeaderStyle -> textColor
    }
}

/**
 * Returns the style for column header cells based on their selection state and index.
 *
 * @param isSelected Indicates if the column header is selected.
 * @param isParentSelected Indicates if the parent column header is selected.
 * @param columnIndex The index of the column.
 * @return The style for the column header cell.
 */
@Composable
internal fun styleForColumnHeader(
    isSelected: Boolean,
    isParentSelected: Boolean,
    columnIndex: Int,
): CellStyle = when {
    isSelected -> CellStyle.HeaderStyle(
        backgroundColor = LocalTableColors.current.primary,
        textColor = LocalTableColors.current.onPrimary,
    )
    isParentSelected -> CellStyle.HeaderStyle(
        backgroundColor = LocalTableColors.current.primaryLight,
        textColor = LocalTableColors.current.headerText,
    )
    columnIndex % 2 == 0 -> CellStyle.HeaderStyle(
        backgroundColor = LocalTableColors.current.headerBackground1,
        textColor = LocalTableColors.current.headerText,
    )
    else ->
        CellStyle.HeaderStyle(
            backgroundColor = LocalTableColors.current.headerBackground2,
            textColor = LocalTableColors.current.headerText,
        )
}

/**
 * Returns the style for column header cells based on their selection state and index.
 *
 * @param isSelected Indicates if the column header is selected.
 * @param isParentSelected Indicates if the parent column header is selected.
 * @param columnIndex The index of the column.
 * @return The style for the column header cell.
 */
@Composable
internal fun styleForRowHeader(isSelected: Boolean, isOtherRowSelected: Boolean): CellStyle = when {
    isSelected -> CellStyle.HeaderStyle(
        TableTheme.colors.primary,
        TableTheme.colors.onPrimary,
    )
    isOtherRowSelected -> CellStyle.HeaderStyle(
        TableTheme.colors.primaryLight,
        TableTheme.colors.primary,
    )
    else -> CellStyle.HeaderStyle(
        backgroundColor = TableTheme.colors.tableBackground,
        textColor = TableTheme.colors.primary,
    )
}

/**
 * Returns the style for table cells based on various states and properties.
 *
 * @param tableColorProvider A function providing the table colors.
 * @param isSelected Indicates if the cell is selected.
 * @param isParentSelected Indicates if the parent cell is selected.
 * @param hasError Indicates if the cell has an error.
 * @param hasWarning Indicates if the cell has a warning.
 * @param isEditable Indicates if the cell is editable.
 * @param legendColor The color of the legend, if any.
 * @return The style for the table cell.
 */
internal fun styleForCell(
    tableColorProvider: () -> TableColors,
    isSelected: Boolean,
    isParentSelected: Boolean,
    hasError: Boolean,
    hasWarning: Boolean,
    isEditable: Boolean,
    legendColor: Int?,
) = CellStyle.CellBorderStyle(
    borderColor = when {
        isSelected && hasError -> tableColorProvider().errorColor
        isSelected && hasWarning -> tableColorProvider().warningColor
        isSelected -> tableColorProvider().primary
        else -> Color.Transparent
    },
    backgroundColor = when {
        legendColor != null -> Color.Transparent
        !isEditable && isParentSelected -> tableColorProvider().disabledSelectedBackground
        isParentSelected -> tableColorProvider().primaryLight
        !isEditable -> tableColorProvider().disabledCellBackground
        isSelected -> tableColorProvider().tableBackground
        else -> tableColorProvider().tableBackground
    },
)
