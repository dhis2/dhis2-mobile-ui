package org.hisp.dhis.mobile.ui.designsystem.component.menuItem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import org.hisp.dhis.mobile.ui.designsystem.component.menuItem.MenuItemTestTags.MENU_ITEM_CONTAINER
import org.hisp.dhis.mobile.ui.designsystem.component.menuItem.MenuItemTestTags.MENU_ITEM_DIVIDER
import org.hisp.dhis.mobile.ui.designsystem.component.menuItem.MenuItemTestTags.MENU_ITEM_LEADING_ICON
import org.hisp.dhis.mobile.ui.designsystem.component.menuItem.MenuItemTestTags.MENU_ITEM_LEADING_INDENT
import org.hisp.dhis.mobile.ui.designsystem.component.menuItem.MenuItemTestTags.MENU_ITEM_SUPPORTING_TEXT
import org.hisp.dhis.mobile.ui.designsystem.component.menuItem.MenuItemTestTags.MENU_ITEM_TEXT
import org.hisp.dhis.mobile.ui.designsystem.component.menuItem.MenuItemTestTags.MENU_ITEM_TRAILING_ICON
import org.hisp.dhis.mobile.ui.designsystem.component.menuItem.MenuItemTestTags.MENU_ITEM_TRAILING_TEXT
import org.hisp.dhis.mobile.ui.designsystem.theme.Border
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.hisp.dhis.mobile.ui.designsystem.theme.hoverPointerIcon

/**
 * DHIS2 [MenuItem] Used for dropdown menu.
 * @param modifier: allows a modifier to be passed externally.
 * @param menuItemData: manages the [MenuItemData]
 * @param onItemClick: callback to when menu item is clicked.
 */
@Composable
fun MenuItem(
    modifier: Modifier = Modifier,
    menuItemData: MenuItemData,
    onItemClick: () -> Unit,
) {
    val itemContainerBackground = when (menuItemData.state) {
        MenuItemState.SELECTED -> {
            if (menuItemData.style == MenuItemStyle.ALERT) {
                SurfaceColor.ErrorContainer
            } else {
                SurfaceColor.Container
            }
        }

        else -> Color.Transparent
    }

    Column(
        modifier = modifier.testTag(MENU_ITEM_CONTAINER),
    ) {
        Row(
            modifier = Modifier
                .background(itemContainerBackground)
                .alpha(if (menuItemData.state != MenuItemState.DISABLED) 1f else 0.38f)
                .clickable(
                    enabled = menuItemData.state != MenuItemState.DISABLED,
                    onClick = {
                        onItemClick.invoke()
                    },
                )
                .hoverPointerIcon(menuItemData.state != MenuItemState.DISABLED)
                .height(Spacing.Spacing48)
                .padding(horizontal = Spacing.Spacing12),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            MenuItemLeadingElement(
                leadingElement = menuItemData.leadingElement,
                style = menuItemData.style,
                state = menuItemData.state,
            )
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    maxLines = if (!menuItemData.supportingText.isNullOrEmpty()) 1 else 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.testTag(MENU_ITEM_TEXT),
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (menuItemData.style == MenuItemStyle.ALERT) SurfaceColor.Error else TextColor.OnSurface,
                    text = menuItemData.label,
                )
                if (!menuItemData.supportingText.isNullOrEmpty()) {
                    Text(
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.testTag(MENU_ITEM_SUPPORTING_TEXT),
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (menuItemData.style == MenuItemStyle.ALERT) TextColor.OnErrorContainer else TextColor.OnSurfaceVariant,
                        text = menuItemData.supportingText,
                    )
                }
            }

            MenuItemTrailingElement(
                trailingElement = menuItemData.trailingElement,
                state = menuItemData.state,
            )
        }
        if (menuItemData.showDivider) {
            HorizontalDivider(
                modifier = modifier
                    .testTag(MENU_ITEM_DIVIDER)
                    .padding(vertical = Spacing.Spacing8),
                thickness = Border.Thin,
                color = Outline.Medium,
            )
        }
    }
}

@Composable
private fun MenuItemLeadingElement(
    leadingElement: MenuLeadingElement? = null,
    style: MenuItemStyle,
    state: MenuItemState,
) {
    when (leadingElement) {
        is MenuLeadingElement.Indent -> {
            Box(
                modifier = Modifier
                    .testTag(MENU_ITEM_LEADING_INDENT)
                    .padding(end = Spacing.Spacing12)
                    .size(Spacing.Spacing24),
            )
        }

        is MenuLeadingElement.Icon -> {
            val iconTint = when (state) {
                MenuItemState.SELECTED -> {
                    if (style == MenuItemStyle.ALERT) {
                        leadingElement.selectedErrorTintColor
                    } else {
                        leadingElement.selectedTintColor
                    }
                }

                else -> if (style == MenuItemStyle.ALERT) {
                    leadingElement.defaultErrorTintColor
                } else {
                    leadingElement.defaultTintColor
                }
            }
            Icon(
                imageVector = leadingElement.icon,
                modifier = Modifier
                    .testTag(MENU_ITEM_LEADING_ICON)
                    .padding(end = Spacing.Spacing12)
                    .size(Spacing.Spacing24),
                contentDescription = null,
                tint = iconTint,
            )
        }

        else -> {}
    }
}

@Composable
private fun MenuItemTrailingElement(
    trailingElement: MenuTrailingElement? = null,
    state: MenuItemState,
) {
    when (trailingElement) {
        is MenuTrailingElement.Icon -> {
            Icon(
                modifier = Modifier
                    .testTag(MENU_ITEM_TRAILING_ICON)
                    .padding(start = Spacing.Spacing12)
                    .size(Spacing.Spacing24),
                imageVector = trailingElement.icon,
                contentDescription = null,
                tint = if (state == MenuItemState.SELECTED) trailingElement.selectedTintColor else trailingElement.defaultTintColor,
            )
        }

        is MenuTrailingElement.Text -> {
            Text(
                modifier = Modifier
                    .testTag(MENU_ITEM_TRAILING_TEXT)
                    .padding(start = Spacing.Spacing12),
                style = MaterialTheme.typography.bodyLarge,
                color = if (state == MenuItemState.SELECTED) TextColor.OnSurface else TextColor.OnSurfaceVariant,
                text = trailingElement.text,
            )
        }

        else -> {}
    }
}

/**
 * DHIS2 [MenuItemData],
 * class to control the [MenuItem]
 * @param label: controls the text to be shown.
 * @param state: controls the [MenuItem] state.
 * @param style: controls the [MenuItem] style.
 * @param leadingElement: controls the [MenuLeadingElement].
 * @param trailingElement: controls the [MenuTrailingElement].
 * @param supportingText: controls the supporting text to be shown.
 * @param showDivider: controls whether a divider should be shown.
 */
data class MenuItemData(
    val label: String,
    val state: MenuItemState = MenuItemState.ENABLED,
    val style: MenuItemStyle = MenuItemStyle.DEFAULT,
    val leadingElement: MenuLeadingElement? = null,
    val trailingElement: MenuTrailingElement? = null,
    val supportingText: String? = null,
    val showDivider: Boolean = false,
)

/**
 * DHIS2 MenuItemState,
 *  enum class to control the [MenuItem] state
 */
enum class MenuItemState {
    ENABLED,
    SELECTED,
    DISABLED,
}

/**
 * DHIS2 MenuItemStyle,
 *  enum class to control the [MenuItem] style
 */
enum class MenuItemStyle {
    DEFAULT,
    ALERT,
}

/**
 * DHIS2 [MenuLeadingElement],
 * class to control the [MenuItem] leading element
 */
sealed class MenuLeadingElement {
    /**
     * DHIS2 [Indent],
     * class to control the [MenuLeadingElement] trailing element indent.
     */
    data object Indent : MenuLeadingElement()

    /**
     * DHIS2 [Icon],
     * class to control the [MenuLeadingElement] trailing element icon.
     * @param icon: controls the icon to be shown.
     * @param defaultErrorTintColor: controls the error style tint color.
     * @param defaultTintColor: controls the default tint color.
     * @param selectedErrorTintColor: controls the error style tint color when selected.
     * @param selectedTintColor: controls the tint color when selected.
     */
    data class Icon(
        val icon: ImageVector,
        val defaultErrorTintColor: Color = SurfaceColor.Error,
        val defaultTintColor: Color = SurfaceColor.Primary,
        val selectedErrorTintColor: Color = TextColor.OnErrorContainer,
        val selectedTintColor: Color = TextColor.OnPrimaryContainer,
    ) : MenuLeadingElement()
}

/**
 * DHIS2 [MenuTrailingElement],
 * class to control the [MenuItem] trailing element
 */
sealed class MenuTrailingElement {
    /**
     * DHIS2 [Icon],
     * class to control the [MenuTrailingElement] trailing element icon.
     * @param icon: controls the icon to be shown.
     * @param defaultTintColor: controls the default tint color.
     * @param selectedTintColor: controls the tint color when selected.
     */
    data class Icon(
        val icon: ImageVector,
        val defaultTintColor: Color = TextColor.OnSurfaceVariant,
        val selectedTintColor: Color = TextColor.OnSurface,
    ) : MenuTrailingElement()

    /**
     * DHIS2 [Text],
     * class to control the [MenuTrailingElement] trailing element text.
     * @param text: controls the text to be shown.
     */
    data class Text(
        val text: String,
    ) : MenuTrailingElement()
}
