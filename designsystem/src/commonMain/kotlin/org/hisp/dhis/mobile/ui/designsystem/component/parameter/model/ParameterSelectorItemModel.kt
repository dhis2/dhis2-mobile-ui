package org.hisp.dhis.mobile.ui.designsystem.component.parameter.model

import androidx.compose.runtime.Composable

sealed class ParameterSelectorItemModel {
    data class PristineModel(
        val label: String,
        val helper: String,
        val onClick: () -> Unit,
    ) : ParameterSelectorItemModel()

    data class FilledModel(
        val inputField: @Composable () -> Unit,
    ) : ParameterSelectorItemModel()
}
