package org.hisp.dhis.mobile.ui.designsystem.component.navigationBar

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationBarItem<T>(
    val id: T,
    val icon: ImageVector,
    val selectedIcon: ImageVector = icon,
    val label: String,
    val enabled: Boolean = true,
    val showBadge: Boolean = false,
    val badgeText: String? = null,
)
