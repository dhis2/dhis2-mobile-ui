package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.NavigationBarTestTags.NAVIGATION_BAR
import org.hisp.dhis.mobile.ui.designsystem.component.NavigationBarTestTags.NAVIGATION_BAR_BORDER
import org.hisp.dhis.mobile.ui.designsystem.component.NavigationBarTestTags.NAVIGATION_BAR_CONTAINER
import org.hisp.dhis.mobile.ui.designsystem.component.NavigationBarTestTags.NAVIGATION_BAR_ITEM_BADGE_PREFIX
import org.hisp.dhis.mobile.ui.designsystem.component.NavigationBarTestTags.NAVIGATION_BAR_ITEM_LABEL_PREFIX
import org.hisp.dhis.mobile.ui.designsystem.component.NavigationBarTestTags.NAVIGATION_BAR_ITEM_PREFIX
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

object NavigationBarTestTags {
    const val NAVIGATION_BAR_CONTAINER = "NAVIGATION_BAR_CONTAINER"
    const val NAVIGATION_BAR_BORDER = "NAVIGATION_BAR_BORDER"
    const val NAVIGATION_BAR = "NAVIGATION_BAR"
    const val NAVIGATION_BAR_ITEM_PREFIX = "NAVIGATION_BAR_ITEM_"
    const val NAVIGATION_BAR_ITEM_LABEL_PREFIX = "NAVIGATION_BAR_ITEM_LABEL_"
    const val NAVIGATION_BAR_ITEM_BADGE_PREFIX = "NAVIGATION_BAR_ITEM_BADGE_"
}

@Composable
fun NavigationBar(
    modifier: Modifier = Modifier,
    items: List<NavigationBarItem>,
    selectedItemIndex: Int? = null,
    onItemClick: (Int) -> Unit,
) {
    Column(
        modifier = modifier.testTag(NAVIGATION_BAR_CONTAINER),
    ) {
        HorizontalDivider(
            modifier = Modifier.testTag(NAVIGATION_BAR_BORDER),
            thickness = 0.6.dp,
            color = Outline.Light,
        )
        androidx.compose.material3.NavigationBar(
            modifier = Modifier.testTag(NAVIGATION_BAR),
            containerColor = SurfaceColor.ContainerLowest,
        ) {
            items.forEachIndexed { index, item ->
                val selected = selectedItemIndex == index
                NavigationBarItem(
                    modifier = Modifier.testTag("$NAVIGATION_BAR_ITEM_PREFIX${item.label}"),
                    colors = navigationBarItemColors(),
                    icon = {
                        NavigationBarItemIcon(item = item, selected = selected)
                    },
                    label = {
                        Text(
                            modifier = Modifier.testTag("$NAVIGATION_BAR_ITEM_LABEL_PREFIX${item.label}"),
                            text = item.label,
                        )
                    },
                    enabled = item.enabled,
                    selected = selected,
                    onClick = {
                        onItemClick(index)
                    },
                )
            }
        }
    }
}

@Composable
fun NavigationBarItemIcon(item: NavigationBarItem, selected: Boolean) {
    Box {
        if (selected) {
            item.selectedIcon()
        } else {
            item.defaultIcon()
        }
        if (item.showBadge) {
            val badgeXOffset = if (!item.badgeText.isNullOrEmpty()) {
                4.dp * item.badgeText.length
            } else {
                0.dp
            }
            val badgeYOffset = if (!item.badgeText.isNullOrEmpty()) (-4).dp else 0.dp

            ErrorBadge(
                modifier = Modifier.testTag("$NAVIGATION_BAR_ITEM_BADGE_PREFIX${item.label}")
                    .align(Alignment.TopEnd)
                    .offset(
                        x = badgeXOffset,
                        y = badgeYOffset,
                    ),
                text = item.badgeText,
            )
        }
    }
}

@Composable
fun navigationBarItemColors() = NavigationBarItemColors(
    selectedIconColor = SurfaceColor.Primary,
    selectedTextColor = TextColor.OnSurfaceVariant,
    selectedIndicatorColor = SurfaceColor.Container,
    unselectedIconColor = TextColor.OnSurfaceVariant,
    unselectedTextColor = TextColor.OnSurfaceVariant,
    disabledIconColor = TextColor.OnDisabledSurface,
    disabledTextColor = TextColor.OnDisabledSurface,
)

data class NavigationBarItem(
    val defaultIcon: @Composable () -> Unit,
    val selectedIcon: @Composable () -> Unit,
    val label: String,
    val enabled: Boolean = true,
    val showBadge: Boolean = false,
    val badgeText: String? = null,
)
