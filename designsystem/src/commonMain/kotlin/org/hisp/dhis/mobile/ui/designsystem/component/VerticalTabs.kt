package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.model.DraggableType
import org.hisp.dhis.mobile.ui.designsystem.component.model.Tab
import org.hisp.dhis.mobile.ui.designsystem.component.model.TabColorStyle
import org.hisp.dhis.mobile.ui.designsystem.component.model.TabStyle
import org.hisp.dhis.mobile.ui.designsystem.component.modifier.draggableList
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

@Composable
fun VerticalTabs(
    modifier: Modifier = Modifier,
    tabs: List<Tab>,
    tabStyle: TabStyle = TabStyle.LabelOnly,
    tabColorStyle: TabColorStyle = TabColorStyle.Tonal,
    initialSelectedTabIndex: Int = 0,
    backgroundShape: Shape = MaterialTheme.shapes.medium,
    onSectionSelected: (String) -> Unit,
    contentPadding: PaddingValues = PaddingValues(),
) {
    val density = LocalDensity.current
    var selectedSection by remember { mutableStateOf(initialSelectedTabIndex) }

    val scrollState = rememberLazyListState()
    val scrollOffset by remember {
        derivedStateOf {
            VerticalTabsDefaults.tabHeight * scrollState.firstVisibleItemIndex + with(density) { scrollState.firstVisibleItemScrollOffset.toDp() }
        }
    }

    val indicatorVerticalOffset by animateDpAsState(
        targetValue = VerticalTabsDefaults.tabHeight * selectedSection - scrollOffset + contentPadding.calculateTopPadding(),
        label = "",
    )

    Box(
        modifier = modifier
            .background(
                color = VerticalTabsDefaults.backgroundColor(tabColorStyle),
                shape = backgroundShape,
            )
            .clip(backgroundShape),
    ) {
        LazyColumn(
            state = scrollState,
            modifier = Modifier.fillMaxSize()
                .draggableList(
                    scrollState = scrollState,
                    draggableType = DraggableType.Vertical,
                ),
            contentPadding = contentPadding,
        ) {
            itemsIndexed(tabs) { index, tab ->
                VerticalTab(
                    label = tab.label,
                    selected = index == selectedSection,
                    iconImage = tab.icon(index == selectedSection),
                    tabStyle = tabStyle,
                    tabColorStyle = tabColorStyle,
                    defaultVerticalTabHeight = VerticalTabsDefaults.tabHeight,
                    onTabClick = {
                        selectedSection = index
                        onSectionSelected(tab.id)
                    },
                )
            }
        }

        VerticalTabsDefaults.Indicator(
            modifier = Modifier.align(Alignment.TopEnd),
            verticalOffset = indicatorVerticalOffset,
            height = VerticalTabsDefaults.indicatorHeight,
            width = VerticalTabsDefaults.indicatorWidth,
            color = VerticalTabsDefaults.indicatorColor(tabColorStyle),
        )
    }
}

@Composable
internal fun VerticalTab(
    label: String,
    selected: Boolean,
    tabStyle: TabStyle,
    tabColorStyle: TabColorStyle,
    iconImage: ImageVector?,
    defaultVerticalTabHeight: Dp,
    onTabClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(defaultVerticalTabHeight)
            .clickable(
                onClick = onTabClick,
                role = Role.Tab,
                interactionSource = interactionSource,
                indication = ripple(
                    color = if (tabColorStyle is TabColorStyle.Primary) {
                        MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.16f)
                    } else {
                        MaterialTheme.colorScheme.primary
                    },
                ),
            )
            .padding(
                horizontal = if (tabStyle is TabStyle.IconOnly) {
                    Spacing.Spacing5
                } else {
                    Spacing.Spacing16
                },
                vertical = Spacing.Spacing5,
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = spacedBy(Spacing.Spacing4),
    ) {
        when (tabStyle) {
            TabStyle.IconAndLabel -> {
                iconImage?.let {
                    Icon(
                        imageVector = iconImage,
                        contentDescription = "",
                        tint = VerticalTabsDefaults.textColor(tabColorStyle, selected),
                    )
                }
                Text(
                    text = label,
                    color = VerticalTabsDefaults.textColor(tabColorStyle, selected),
                    style = MaterialTheme.typography.titleSmall,
                )
            }

            TabStyle.IconOnly ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    iconImage?.let {
                        Icon(
                            imageVector = iconImage,
                            contentDescription = "",
                            tint = VerticalTabsDefaults.textColor(tabColorStyle, selected),
                        )
                    }
                }

            TabStyle.LabelOnly ->
                Text(
                    modifier = Modifier,
                    text = label,
                    color = VerticalTabsDefaults.textColor(tabColorStyle, selected),
                    style = MaterialTheme.typography.titleSmall,
                )
        }
    }
}

object VerticalTabsDefaults {
    val tabHeight = 48.dp
    val indicatorWidth = 6.dp
    val indicatorHeight = tabHeight
    val indicatorVerticalPadding = 2.dp

    val textColor: Color
        @Composable get() = MaterialTheme.colorScheme.onSurfaceVariant
    val textColorVariant: Color
        @Composable get() = MaterialTheme.colorScheme.surfaceContainerHigh
    val selectedTextColor: Color
        @Composable get() = MaterialTheme.colorScheme.primary
    val selectedTextColorVariant: Color
        @Composable get() = MaterialTheme.colorScheme.surfaceBright
    val backgroundColor: Color
        @Composable get() = MaterialTheme.colorScheme.surface
    val backgroundColorVariant: Color
        @Composable get() = MaterialTheme.colorScheme.primary
    val indicatorColor: Color
        @Composable get() = MaterialTheme.colorScheme.primary
    val indicatorColorVariant: Color
        @Composable get() = MaterialTheme.colorScheme.surfaceBright

    @Composable
    fun textColor(tabColorStyle: TabColorStyle, selected: Boolean) = when {
        tabColorStyle == TabColorStyle.Primary && !selected -> textColorVariant
        tabColorStyle == TabColorStyle.Tonal && !selected -> textColor
        tabColorStyle == TabColorStyle.Primary && selected -> selectedTextColorVariant
        tabColorStyle == TabColorStyle.Tonal && selected -> selectedTextColor
        else -> textColor
    }

    @Composable
    fun backgroundColor(tabColorStyle: TabColorStyle) = when (tabColorStyle) {
        TabColorStyle.Primary -> backgroundColorVariant
        TabColorStyle.Tonal -> backgroundColor
    }

    @Composable
    fun indicatorColor(tabColorStyle: TabColorStyle) = when (tabColorStyle) {
        TabColorStyle.Primary -> indicatorColorVariant
        TabColorStyle.Tonal -> indicatorColor
    }

    @Composable
    fun Indicator(
        modifier: Modifier,
        height: Dp,
        width: Dp,
        color: Color,
        verticalOffset: Dp,
    ) {
        Spacer(
            modifier
                .offset(x = width / 2, y = verticalOffset)
                .requiredHeight(height)
                .requiredWidth(width)
                .padding(vertical = indicatorVerticalPadding / 2)
                .background(
                    color = color,
                    shape = RoundedCornerShape(width / 2),
                ),
        )
    }
}
