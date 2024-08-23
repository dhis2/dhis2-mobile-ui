package org.hisp.dhis.mobile.ui.designsystem.component.navigationBar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.Badge
import org.hisp.dhis.mobile.ui.designsystem.component.ErrorBadge
import org.hisp.dhis.mobile.ui.designsystem.component.navigationBar.NavigationBarTestTags.NAVIGATION_BAR
import org.hisp.dhis.mobile.ui.designsystem.component.navigationBar.NavigationBarTestTags.NAVIGATION_BAR_BORDER
import org.hisp.dhis.mobile.ui.designsystem.component.navigationBar.NavigationBarTestTags.NAVIGATION_BAR_CONTAINER
import org.hisp.dhis.mobile.ui.designsystem.component.navigationBar.NavigationBarTestTags.NAVIGATION_BAR_ITEM_BADGE_PREFIX
import org.hisp.dhis.mobile.ui.designsystem.component.navigationBar.NavigationBarTestTags.NAVIGATION_BAR_ITEM_DEFAULT_ICON_SUFFIX
import org.hisp.dhis.mobile.ui.designsystem.component.navigationBar.NavigationBarTestTags.NAVIGATION_BAR_ITEM_LABEL_PREFIX
import org.hisp.dhis.mobile.ui.designsystem.component.navigationBar.NavigationBarTestTags.NAVIGATION_BAR_ITEM_PREFIX
import org.hisp.dhis.mobile.ui.designsystem.component.navigationBar.NavigationBarTestTags.NAVIGATION_BAR_ITEM_SELECTED_ICON_SUFFIX
import org.hisp.dhis.mobile.ui.designsystem.resource.provideFontResource
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun <T> NavigationBar(
    modifier: Modifier = Modifier,
    items: List<NavigationBarItem<T>>,
    selectedItemIndex: Int? = null,
    onItemClick: (T) -> Unit,
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
                            style = if (selected) {
                                MaterialTheme.typography.labelMedium.copy(
                                    fontFamily = provideFontResource("roboto_bold"),
                                    fontWeight = FontWeight.Bold,
                                )
                            } else {
                                MaterialTheme.typography.labelMedium.copy(
                                    color = TextColor.OnSurfaceVariant,
                                )
                            },
                            modifier = Modifier.testTag("$NAVIGATION_BAR_ITEM_LABEL_PREFIX${item.label}"),
                            text = item.label,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    },
                    enabled = item.enabled,
                    selected = selected,
                    onClick = {
                        onItemClick(item.id)
                    },
                )
            }
        }
    }
}

@Composable
private fun <T> NavigationBarItemIcon(item: NavigationBarItem<T>, selected: Boolean) {
    Box {
        Icon(
            modifier = Modifier.testTag(
                item.iconTestTag(selected),
            ),
            imageVector = if (selected) {
                item.selectedIcon
            } else {
                item.icon
            },
            contentDescription = item.label,
            tint = if (selected) {
                SurfaceColor.Primary
            } else {
                TextColor.OnSurfaceVariant
            },
        )
        if (item.showBadge) {
            val badgeXOffset = if (!item.badgeText.isNullOrEmpty()) {
                4.dp * item.badgeText.length
            } else {
                0.dp
            }
            val badgeYOffset = if (!item.badgeText.isNullOrEmpty()) (-4).dp else 0.dp

            Badge(
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

private fun <T> NavigationBarItem<T>.iconTestTag(selected: Boolean): String {
    val iconTestId = if (selected) {
        NAVIGATION_BAR_ITEM_SELECTED_ICON_SUFFIX
    } else {
        NAVIGATION_BAR_ITEM_DEFAULT_ICON_SUFFIX
    }
    return "${NAVIGATION_BAR_ITEM_PREFIX}${iconTestId}$label"
}

@Composable
fun navigationBarItemColors() = NavigationBarItemDefaults.colors(
    selectedIconColor = SurfaceColor.Primary,
    indicatorColor = SurfaceColor.Container,
    unselectedIconColor = TextColor.OnSurfaceVariant,
    disabledIconColor = TextColor.OnDisabledSurface,
    disabledTextColor = TextColor.OnDisabledSurface,
)
