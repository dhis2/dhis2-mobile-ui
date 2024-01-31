package org.hisp.dhis.mobile.ui.designsystem.component.parameter.model

import androidx.compose.runtime.Composable

sealed class ParameterSelectorItemModel {
    data class EmptyParameter(
        val label: String,
        val helper: String,
        val onClick: () -> Unit,
    ) : ParameterSelectorItemModel()

    data class InputParameter(
        val inputField: @Composable () -> Unit,
    ) : ParameterSelectorItemModel()
}
