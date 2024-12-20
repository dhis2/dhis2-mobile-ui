package org.hisp.dhis.common.screens.table

import androidx.compose.runtime.Composable
import kotlinx.serialization.json.Json
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableModel
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.DataTable
import java.io.InputStream

@Composable
fun TableScreen() {
    DataTable(
        tableList = getTableData(),
    )
}

fun getTableData(): List<TableModel> {
    val jsonString = readJsonFile("multi_header_table_list.json")
    val data = parseJson(jsonString)
    return data
}

fun readJsonFile(fileName: String): String {
    val classLoader = Thread.currentThread().contextClassLoader
    val inputStream: InputStream = classLoader!!.getResourceAsStream(fileName)
        ?: throw IllegalArgumentException("File not found: $fileName")
    return inputStream.bufferedReader().use { it.readText() }
}

fun parseJson(jsonString: String): List<TableModel> {
    return Json.decodeFromString(jsonString)
}
