package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.extensions

internal fun String?.isNumeric() = this?.toDoubleOrNull() != null
