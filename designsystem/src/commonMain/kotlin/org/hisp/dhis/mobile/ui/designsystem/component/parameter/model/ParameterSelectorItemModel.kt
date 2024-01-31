package org.hisp.dhis.mobile.ui.designsystem.component.parameter.model

import androidx.compose.runtime.Composable

data class ParameterSelectorItemModel(
    val label: String,
    val helper: String,
    val inputField: @Composable () -> Unit,
    val status: Status = Status.CLOSED,
) {
    enum class Status {
        OPENED,
        CLOSED,
    }
}
