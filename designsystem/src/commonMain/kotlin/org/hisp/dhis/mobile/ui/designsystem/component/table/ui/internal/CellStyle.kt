package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.LocalTableColors
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableColors
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableTheme
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

internal sealed class CellStyle {
    /**
     * Style for header cells.
     *
     * @property backgroundColor The background color of the header cell.
     * @property textColor The text color of the header cell.
     * @property dividerColor The color of the bottom divider
     */
    data class HeaderStyle(
        val backgroundColor: Color,
        val textColor: Color,
        val dividerColor: Color,
    ) : CellStyle()

    /**
     * Style for cells with borders.
     *
     * @property backgroundColor The background color of the cell.
     * @property borderColor The border color of the cell.
     */
    data class CellBorderStyle(
        val backgroundColor: Color,
        val borderColor: Color,
    ) : CellStyle()

    /**
     * Returns the background color of the cell.
     *
     * @return The background color.
     */
    fun backgroundColor() =
        when (this) {
            is CellBorderStyle -> backgroundColor
            is HeaderStyle -> backgroundColor
        }

    /**
     * Returns the main color of the cell (text or border color).
     *
     * @return The main color.
     */
    fun mainColor() =
        when (this) {
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
    isDisabled: Boolean,
    isCornerSelected: Boolean,
    isSelected: Boolean,
    isParentSelected: Boolean,
    columnIndex: Int,
): CellStyle.HeaderStyle =
    when {
        isCornerSelected ->
            CellStyle.HeaderStyle(
                backgroundColor = TableTheme.colors.primaryLight,
                textColor = TableTheme.colors.primary,
                dividerColor = TableTheme.colors.primary,
            )

        isSelected ->
            CellStyle.HeaderStyle(
                backgroundColor = TableTheme.colors.primary,
                textColor = TableTheme.colors.onPrimary,
                dividerColor = TableTheme.colors.primary,
            )

        isParentSelected ->
            CellStyle.HeaderStyle(
                backgroundColor = TableTheme.colors.primaryLight,
                textColor = TableTheme.colors.headerText,
                dividerColor = TableTheme.colors.primary,
            )

        isDisabled ->
            CellStyle.HeaderStyle(
                backgroundColor = TableTheme.colors.disabledCellBackground,
                textColor = TableTheme.colors.disabledCellText,
                dividerColor = Outline.Light,
            )

        columnIndex % 2 == 0 ->
            CellStyle.HeaderStyle(
                backgroundColor = TableTheme.colors.headerBackground1,
                textColor = TableTheme.colors.headerText,
                dividerColor = TableTheme.colors.primary,
            )

        else ->
            CellStyle.HeaderStyle(
                backgroundColor = LocalTableColors.current.headerBackground2,
                textColor = LocalTableColors.current.headerText,
                dividerColor = TableTheme.colors.primary,
            )
    }

/**
 * Returns the style for column header cells based on their selection state and index.
 * @param isDisabled Indicates if the column header is disabled.
 * @param isCornerSelected Indicates if the corner column header is selected.
 * @param isSelected Indicates if the column header is selected.
 * @param isOtherRowSelected Indicates if another row is selected.
 * @return The style for the column header cell.
 */
@Composable
internal fun styleForRowHeader(
    isDisabled: Boolean,
    isCornerSelected: Boolean,
    isSelected: Boolean,
    isOtherRowSelected: Boolean,
): CellStyle =
    when {
        isCornerSelected ->
            CellStyle.HeaderStyle(
                backgroundColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                textColor = MaterialTheme.colorScheme.primary,
                dividerColor = Outline.Light,
            )

        isSelected ->
            CellStyle.HeaderStyle(
                TableTheme.colors.primary,
                TableTheme.colors.onPrimary,
                dividerColor = TableTheme.colors.primary,
            )

        isOtherRowSelected ->
            CellStyle.HeaderStyle(
                backgroundColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                textColor = TableTheme.colors.primary,
                dividerColor = Outline.Light,
            )

        isDisabled ->
            CellStyle.HeaderStyle(
                backgroundColor = TableTheme.colors.disabledCellBackground,
                textColor = TableTheme.colors.disabledCellText,
                dividerColor = Outline.Light,
            )

        else ->
            CellStyle.HeaderStyle(
                backgroundColor = TableTheme.colors.tableBackground,
                textColor = TableTheme.colors.primary,
                dividerColor = Outline.Light,
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
    borderColor =
        when {
            hasError -> SurfaceColor.Error
            hasWarning -> SurfaceColor.Warning
            else -> tableColorProvider().primary
        },
    backgroundColor =
        when {
            isParentSelected -> tableColorProvider().selectedCell
            hasError -> SurfaceColor.ErrorContainer
            hasWarning -> SurfaceColor.WarningContainer
            legendColor != null -> Color(legendColor).copy(alpha = 0.3f)
            !isEditable -> tableColorProvider().disabledCellBackground
            isSelected -> tableColorProvider().tableBackground
            else -> tableColorProvider().tableBackground
        },
)
