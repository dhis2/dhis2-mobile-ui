package org.hisp.dhis.mobile.ui.designsystem.component.navigationBar

import androidx.compose.runtime.Composable

data class NavigationBarItem(
    val defaultIcon: @Composable () -> Unit,
    val selectedIcon: @Composable () -> Unit,
    val label: String,
    val enabled: Boolean = true,
    val showBadge: Boolean = false,
    val badgeText: String? = null,
)
