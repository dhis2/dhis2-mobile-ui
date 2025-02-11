package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.cells

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import org.hisp.dhis.mobile.ui.designsystem.component.menu.DropDownMenu
import org.hisp.dhis.mobile.ui.designsystem.component.menu.MenuItemData
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.DropdownOption
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableCell
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.LocalTableColors
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableSelection
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableTheme
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.compositions.LocalInteraction
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.MultiOptionSelector
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.extensions.isNumeric
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.CELL_VALUE_TEST_TAG

@Composable
fun TextCell(
    tableId: String,
    cellValue: String,
    maxLines: Int,
    cell: TableCell,
    options: List<DropdownOption>,
    headerLabel: String,
    modifier: Modifier = Modifier,
) {
    val localInteraction = LocalInteraction.current

    val (dropDownExpanded, setExpanded) = remember { mutableStateOf(false) }
    val (showMultiSelector, setShowMultiSelector) = remember { mutableStateOf(false) }

    Text(
        modifier = modifier
            .fillMaxSize()
            .testTag(CELL_VALUE_TEST_TAG)
            .clickable(cell.editable) {
                localInteraction.onSelectionChange(
                    TableSelection.CellSelection(
                        tableId = tableId,
                        columnIndex = cell.column,
                        rowIndex = cell.row ?: -1,
                        globalIndex = 0,
                    ),
                )
                when {
                    options.isNotEmpty() -> when {
                        cell.isMultiText -> setShowMultiSelector(true)
                        else -> setExpanded(true)
                    }

                    else -> {
                        localInteraction.onClick(cell)
                    }
                }
            }.padding(TableTheme.dimensions.cellPaddingValues)
            .wrapContentHeight(Alignment.CenterVertically),
        text = cellValue,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        style = TextStyle.Default.copy(
            fontSize = TableTheme.dimensions.defaultCellTextSize,
            textAlign = if (cellValue.isNumeric()) TextAlign.End else TextAlign.Start,
            color = LocalTableColors.current.cellTextColor(
                hasError = cell.error != null,
                hasWarning = cell.warning != null,
                isEditable = cell.editable,
            ),
        ),
    )
    if (options.isNotEmpty()) {
        DropDownMenu(
            expanded = dropDownExpanded,
            onDismissRequest = { setExpanded(false) },
            items = options.map {
                MenuItemData(
                    id = it.code,
                    label = it.name,
                )
            },
            onItemClick = { code ->
                setExpanded(false)
                val label = options.first { it.code == code }.name
                localInteraction.onOptionSelected(
                    cell.copy(value = label),
                    code,
                    label,
                )
            },
        )
        if (showMultiSelector) {
            MultiOptionSelector(
                options = options,
                cell = cell,
                title = headerLabel,
                onSave = { codes, values ->
                    localInteraction.onOptionSelected(cell, codes, values)
                    setShowMultiSelector(false)
                },
                onDismiss = {
                    setShowMultiSelector(false)
                },
            )
        }
    }
}
