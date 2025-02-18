package org.hisp.dhis.common.screens.table

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import mobile_ui.common.generated.resources.Res
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.InputDialog
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputText
import org.hisp.dhis.mobile.ui.designsystem.component.table.actions.TableInteractions
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableCell
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableModel
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.DataTable
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun TableScreen() {
    var bytes by remember {
        mutableStateOf(ByteArray(0))
    }
    LaunchedEffect(Unit) {
        bytes = Res.readBytes("files/json/table_demo.json")
    }
    val jsonString = bytes.decodeToString()
    if (jsonString.isNotEmpty()) {
        var data by remember {
            mutableStateOf(parseJson(jsonString))
        }
        var selectedTableCell by remember {
            mutableStateOf<TableCell?>(null)
        }
        val scope = rememberCoroutineScope()

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter,
        ) {
            DataTable(
                tableList = data,
                tableInteractions = object : TableInteractions {
                    override fun onClick(tableCell: TableCell) {
                        super.onClick(tableCell)
                        selectedTableCell = if (selectedTableCell?.id == tableCell.id) {
                            null
                        } else {
                            tableCell
                        }
                    }
                },
            )

            selectedTableCell?.let {
                val savedTextSelection by remember(it) {
                    mutableStateOf(
                        TextRange(it.value?.length ?: 0),
                    )
                }
                var textFieldValue by remember(it) {
                    mutableStateOf(
                        TextFieldValue(
                            text = it.value ?: "",
                            selection = savedTextSelection,
                        ),
                    )
                }
                InputDialog(
                    input = {
                        InputText(
                            modifier = Modifier,
                            title = "Label",
                            inputTextFieldValue = textFieldValue,
                            onValueChanged = { fieldValue ->
                                textFieldValue = fieldValue ?: TextFieldValue()
                                scope.launch {
                                    data = data.map { table ->
                                        val tableRows = table.tableRows.map { tableRowModel ->
                                            val cell =
                                                tableRowModel.values.values.find { tableCell ->
                                                    tableCell.id == it.id
                                                }
                                            if (cell != null) {
                                                val updatedValues =
                                                    tableRowModel.values.toMutableMap()
                                                updatedValues[cell.column] = cell.copy(
                                                    value = textFieldValue.text.takeIf { value -> value.isNotEmpty() },
                                                )
                                                tableRowModel.copy(values = updatedValues)
                                            } else {
                                                tableRowModel
                                            }
                                        }
                                        table.copy(tableRows = tableRows)
                                    }
                                }
                            },
                            state = InputShellState.FOCUSED,
                            onFocusChanged = {
                            },
                        )
                    },
                    onDismiss = {
                        selectedTableCell = null
                    },
                    actionButton = {
                        Button(
                            style = ButtonStyle.FILLED,
                            text = "Done",
                            onClick = {
                                selectedTableCell = null
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Done,
                                    contentDescription = "Done",
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                )
            }
        }
    }
}

fun parseJson(jsonString: String): List<TableModel> {
    return Json.decodeFromString(jsonString)
}
