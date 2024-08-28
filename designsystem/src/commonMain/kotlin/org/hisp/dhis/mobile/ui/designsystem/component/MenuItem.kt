package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun MenuItem(
    modifier: Modifier = Modifier,
    menuItemData: MenuItemData,
) {

}

data class MenuItemData(
    val label: String,
    val state: MenuItemState = MenuItemState.ENABLED,
    val style: MenuItemStyle = MenuItemStyle.DEFAULT,
    val leadingElement: LeadingElement? = null,
    val trailingElement: TrailingElement? = null,
    val supportingText: String? = null,
    val showDivider: Boolean = false,
)

/**
 * DHIS2 MenuItemState,
 *  enum class to control the [MenuItem] state
 */
enum class MenuItemState {
    ENABLED,
    HOVERED,
    SELECTED,
    DISABLED,
}

/**
 * DHIS2 MenuItemStyle,
 *  enum class to control the style [MenuItem] component
 */
enum class MenuItemStyle {
    DEFAULT,
    ALERT,
}

/**
 * DHIS2 MenuLeadingElement,
 * class to control the [MenuItem] leading element
 */
sealed class LeadingElement {
    data object Indent : LeadingElement()
    data class Icon(val icon: ImageVector) : LeadingElement()
}

/**
 * DHIS2 MenuTrailingElement,
 * class to control the [MenuItem] trailing element
 */
sealed class TrailingElement {
    data class Icon(val icon: ImageVector, val isSelected: Boolean = false) : TrailingElement()
    data class Text(val text: String, val isSelected: Boolean = false) : TrailingElement()
}