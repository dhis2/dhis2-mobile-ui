package org.hisp.dhis.mobile.ui.designsystem.component.model

import androidx.compose.ui.graphics.vector.ImageVector

data class Tab(
    val id: String,
    val label: String,
    private val iconData: IconData? = null,
) {
    fun icon(selected: Boolean) = iconData?.selectedIcon?.takeIf { selected } ?: iconData?.defaultIcon
}

data class IconData(
    val defaultIcon: ImageVector,
    val selectedIcon: ImageVector?,
)
