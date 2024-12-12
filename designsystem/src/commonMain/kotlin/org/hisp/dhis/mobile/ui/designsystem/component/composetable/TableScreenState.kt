package org.hisp.dhis.mobile.ui.designsystem.component.composetable

import org.hisp.dhis.mobile.ui.designsystem.component.composetable.model.TableModel
import java.util.UUID

data class TableScreenState(
    val tables: List<TableModel>,
    val id: UUID = UUID.randomUUID(),
    val state: TableState = TableState.LOADING,
)
// todo review if this class is still needed
data class TableConfigurationState(
    val overwrittenTableWidth: Map<String, Float>? = null,
    val overwrittenRowHeaderWidth: Map<String, Float>? = null,
    val overwrittenColumnWidth: Map<String, Map<Int, Float>>? = null,
) {
    fun isResized() = !overwrittenTableWidth.isNullOrEmpty() or
        !overwrittenRowHeaderWidth.isNullOrEmpty() or
        !overwrittenColumnWidth.isNullOrEmpty()
}

enum class TableState {
    LOADING,
    SUCCESS,
}
