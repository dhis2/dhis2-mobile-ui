package org.hisp.dhis.mobile.ui.designsystem.component.table.model

import kotlinx.serialization.Serializable

@Serializable
data class TableHeaderRow(val cells: List<TableHeaderCell>)
