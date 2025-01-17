package org.hisp.dhis.mobile.ui.designsystem.component.table.model.internal.extensions

import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableModel

internal fun TableModel.areAllValuesEmpty(): Boolean {
    this.tableRows.forEach { row ->
        val result = row.values.values.filterNot { it.value == "" }
        if (result.isNotEmpty()) {
            return false
        }
    }
    return true
}
