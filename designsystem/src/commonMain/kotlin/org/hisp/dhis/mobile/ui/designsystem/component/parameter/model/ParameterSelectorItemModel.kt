package org.hisp.dhis.mobile.ui.designsystem.component.parameter.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

data class ParameterSelectorItemModel(
    val icon: @Composable (() -> Unit) = {
        Icon(
            imageVector = Icons.Outlined.AddCircleOutline,
            contentDescription = "Icon Button",
            tint = SurfaceColor.Primary,
        )
    },
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
