package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.extensions

fun String?.isNumeric() = this?.toDoubleOrNull() != null
