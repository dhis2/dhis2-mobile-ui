package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.material3.DividerDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import kotlinx.coroutines.launch
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableCell
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableCellContent
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.LocalTableSelection
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableSelection
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableTheme
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.compositions.LocalInteraction
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.cells.CheckBoxCell
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.cells.MandatoryIcon
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.cells.MandatoryIconStyle
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.cells.TextCell
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.modifiers.cellBorder
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.cellSelected
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.cellTestTag
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.hasError
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.isBlocked
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.rowBackground

/**
 * Composable function to display a table cell.
 *
 * @param tableId The ID of the table.
 * @param cell The cell to be displayed.
 * @param maxLines The maximum number of lines to be displayed in the cell.
 * @param headerExtraSize The extra size to be added to the header.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun TableCell(
    tableId: String,
    cell: TableCell,
    maxLines: Int,
    headerExtraSize: Int,
) {
    val localInteraction = LocalInteraction.current
    val tableSelection = LocalTableSelection.current

    val bringIntoViewRequester = remember { BringIntoViewRequester() }

    val backgroundColor = TableTheme.colors.disabledCellBackground
    val coroutineScope = rememberCoroutineScope()
    val isSelected = tableSelection.isCellSelected(tableId, cell.column, cell.row ?: -1)
    val isParentSelected =
        tableSelection.isCellParentSelected(
            selectedTableId = tableId,
            columnIndex = cell.column,
            rowIndex = cell.row ?: -1,
        )
    val colors = TableTheme.colors

    val style by remember(cell.value, isSelected, isParentSelected) {
        derivedStateOf {
            styleForCell(
                tableColorProvider = { colors },
                isSelected = isSelected,
                isParentSelected = isParentSelected,
                hasError = cell.error != null,
                hasWarning = cell.warning != null,
                isEditable = cell.editable,
                legendColor = cell.legendColor,
            )
        }
    }
    val localDensity = LocalDensity.current
    val dimensions = TableTheme.dimensions
    val config = TableTheme.configuration

    val cellWidth by remember(dimensions) {
        derivedStateOf {
            with(localDensity) {
                dimensions
                    .columnWidthWithTableExtra(
                        groupedTables = config.groupTables,
                        tableId = tableId,
                        column = cell.column,
                    ).plus(headerExtraSize)
                    .toDp()
            }
        }
    }

    var currentCellHeight = 0

    Box(
        modifier =
            Modifier
                .onSizeChanged { currentCellHeight = it.height }
                .width(cellWidth)
                .fillMaxHeight()
                .defaultMinSize(minHeight = dimensions.defaultCellHeight)
                .semantics {
                    testTag = cellTestTag(tableId, cell.id)
                    rowBackground = style.backgroundColor()
                    cellSelected = isSelected
                    hasError = cell.hasErrorOrWarning()
                    isBlocked = style.backgroundColor() == backgroundColor
                }.cellBorder(
                    selected = isSelected,
                    borderColor = style.mainColor(),
                    backgroundColor = style.backgroundColor(),
                    dividerColor = cell.legendColor?.let { Color(it) } ?: DividerDefaults.color,
                ).bringIntoViewRequester(bringIntoViewRequester)
                .focusable()
                .clickable(cell.editable) {
                    localInteraction.onSelectionChange(
                        TableSelection.CellSelection(
                            tableId = tableId,
                            columnIndex = cell.column,
                            rowIndex = cell.row ?: -1,
                            globalIndex = 0,
                        ),
                    )
                    localInteraction.onClick(cell)
                },
    ) {
        when (cell.content) {
            is TableCellContent.Checkbox -> {
                CheckBoxCell(cell = cell)
            }

            else -> {
                TextCell(cell = cell, maxLines = maxLines)
            }
        }
        if (cell.mandatory == true) {
            val mandatoryStyle =
                when (cell.content) {
                    is TableCellContent.Checkbox ->
                        when {
                            cell.content.isChecked -> MandatoryIconStyle.FilledMandatoryStyle
                            else -> MandatoryIconStyle.ComponentMandatoryStyle
                        }

                    is TableCellContent.Text ->
                        when {
                            cell.value?.isNotEmpty() == true -> MandatoryIconStyle.FilledMandatoryStyle
                            else -> MandatoryIconStyle.DefaultMandatoryStyle
                        }
                }

            MandatoryIcon(
                style = mandatoryStyle,
                modifier = Modifier.align(mandatoryStyle.alignment),
            )
        }
    }

    LaunchedEffect(key1 = isSelected) {
        if (isSelected) {
            val marginCoordinates =
                Rect(
                    0f,
                    0f,
                    dimensions.defaultCellWidth * 2f,
                    dimensions.textInputHeight.toFloat() + currentCellHeight,
                )
            coroutineScope.launch {
                bringIntoViewRequester.bringIntoView(marginCoordinates)
            }
        }
    }
}
