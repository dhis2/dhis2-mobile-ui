package org.hisp.dhis.mobile.ui.designsystem.component.parameter.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class ParameterSelectorItemModel(
    val icon: ImageVector = Icons.Outlined.AddCircleOutline,
    val label: String,
    val helper: String,
    val inputField: @Composable () -> Unit,
    val status: Status = Status.CLOSED,
    val onExpand: () -> Unit,
) {
    enum class Status {
        FOCUSED,
        UNFOCUSED,
        CLOSED,
    }
}
