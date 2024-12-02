package org.hisp.dhis.mobile.ui.designsystem.component.composetable.model

import kotlinx.serialization.Serializable

@Serializable
data class TableHeaderRow(val cells: List<TableHeaderCell>)
