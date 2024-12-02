package org.hisp.dhis.mobile.ui.designsystem.component.composetable.ui.extensions

fun String?.isNumeric() = this?.toDoubleOrNull() != null
