package org.hisp.dhis.common.screens.table

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.serialization.json.Json
import mobile_ui.common.generated.resources.Res
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
        bytes = Res.readBytes("files/multi_header_table_list.json")
    }
    val jsonString = bytes.decodeToString()
    if (jsonString.isNotEmpty()) {
        val data = parseJson(jsonString)
        DataTable(
            tableList = data,
        )
    }
}

fun parseJson(jsonString: String): List<TableModel> {
    return Json.decodeFromString(jsonString)
}
