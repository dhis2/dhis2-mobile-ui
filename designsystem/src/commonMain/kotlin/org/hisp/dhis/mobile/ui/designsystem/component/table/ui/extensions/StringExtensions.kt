package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.extensions

internal fun String?.isNumeric() = this?.toDoubleOrNull() != null
