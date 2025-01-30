package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.model.Tab
import org.hisp.dhis.mobile.ui.designsystem.component.model.TabColorStyle
import org.hisp.dhis.mobile.ui.designsystem.component.model.TabStyle
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

@Composable
fun VerticalTabs(
    modifier: Modifier = Modifier,
    tabs: List<Tab>,
    tabStyle: TabStyle = TabStyle.LabelOnly,
    tabColorStyle: TabColorStyle = TabColorStyle.Tonal,
    initialSelectedTabIndex: Int = 0,
    backgroundShape: Shape = MaterialTheme.shapes.large,
    onSectionSelected: (String) -> Unit,
) {
    var selectedSection by remember { mutableStateOf(initialSelectedTabIndex) }
    val indicatorVerticalOffset by animateDpAsState(
        targetValue = VerticalTabsDefaults.tabHeight * selectedSection,
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
            modifier = Modifier.fillMaxSize(),
        ) {
            itemsIndexed(tabs) { index, tab ->
                VerticalTab(
                    label = tab.label,
                    selected = index == selectedSection,
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
                    color = MaterialTheme.colorScheme.primary,
                ),
            )
            .padding(horizontal = Spacing.Spacing16, vertical = Spacing.Spacing5),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = spacedBy(Spacing.Spacing2),
    ) {
        when (tabStyle) {
            TabStyle.IconAndLabel -> {
                Icon(
                    modifier = Modifier.padding(Spacing.Spacing6),
                    imageVector = Icons.Filled.Circle,
                    contentDescription = "",
                    tint = VerticalTabsDefaults.textColor(tabColorStyle, selected),
                )
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
                    Icon(
                        modifier = Modifier.padding(Spacing.Spacing6),
                        imageVector = Icons.Filled.Circle,
                        contentDescription = "",
                        tint = VerticalTabsDefaults.textColor(tabColorStyle, selected),
                    )
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
                .background(
                    color = color,
                    shape = RoundedCornerShape(width / 2),
                ),
        )
    }
}
