package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.internal.conditional
import org.hisp.dhis.mobile.ui.designsystem.component.model.DraggableType
import org.hisp.dhis.mobile.ui.designsystem.component.modifier.draggableList
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing.Spacing16
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing.Spacing4
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.hisp.dhis.mobile.ui.designsystem.theme.dropShadow
import org.hisp.dhis.mobile.ui.designsystem.theme.hoverPointerIcon

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BaseCard(
    modifier: Modifier = Modifier,
    showShadow: Boolean,
    onCardClick: () -> Unit,
    paddingValues: PaddingValues,
    expandable: Boolean,
    itemVerticalPadding: Dp?,
    onSizeChanged: ((IntSize) -> Unit)?,
    selectionMode: SelectionState,
    onCardSelected: () -> Unit,
    content: @Composable () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier =
            modifier
                .conditional(
                    selectionMode != SelectionState.SELECTED && showShadow,
                    { dropShadow(RoundedCornerShape(Radius.S)) },
                ).background(
                    color =
                        when (selectionMode) {
                            SelectionState.SELECTED -> SurfaceColor.ContainerLow
                            else -> SurfaceColor.SurfaceBright
                        },
                    shape = RoundedCornerShape(Radius.S),
                ).clip(shape = RoundedCornerShape(Radius.S))
                .combinedClickable(
                    role = Role.Button,
                    interactionSource = interactionSource,
                    indication =
                        ripple(
                            color = SurfaceColor.Primary,
                        ),
                    onClick =
                        when {
                            selectionMode != SelectionState.NONE -> onCardSelected
                            else -> onCardClick
                        },
                    onLongClick = onCardSelected,
                ).hoverPointerIcon(true)
                .padding(paddingValues)
                .animateContentSize(),
    ) {
        if (expandable) {
            requireNotNull(itemVerticalPadding) { "If expandable is true itemVerticalPadding must not be null" }
            requireNotNull(onSizeChanged) { "If expandable is true onSizeChanged must not be null" }
            ExpandableBox(
                itemVerticalPadding = itemVerticalPadding,
                onSizeChanged = onSizeChanged,
            ) {
                content()
            }
        } else {
            content()
        }
    }
}

@Composable
fun <T> ExpandableItemColumn(
    modifier: Modifier = Modifier,
    itemList: List<T>,
    itemSpacing: Dp = Spacing16,
    contentPadding: Dp = Spacing16,
    itemLayout: @Composable (T, itemVerticalPadding: Dp, onSizeChanged: (IntSize) -> Unit) -> Unit,
) {
    val density = LocalDensity.current

    val itemCount = itemList.size

    var parentSize by remember {
        mutableIntStateOf(-1)
    }

    var childrenSize by remember(itemList) {
        mutableStateOf(mapOf<Int, Int>())
    }

    val itemVerticalPadding by remember(childrenSize) {
        derivedStateOf {
            val value =
                if (childrenSize.size == itemCount) {
                    var availableHeight =
                        parentSize -
                            childrenSize.values.sum() -
                            with(density) {
                                itemSpacing.toPx() * (itemCount - 1) + contentPadding.toPx() * 2
                            }
                    if (itemCount == 1) availableHeight /= 4
                    with(density) { (availableHeight / (2 * itemCount)).toDp() }.takeIf { it >= 16.dp }
                        ?: 16.dp
                } else {
                    16.dp
                }
            value
        }
    }

    val scrollState = rememberLazyListState()

    LazyColumn(
        modifier =
            modifier
                .onSizeChanged {
                    if (parentSize == -1) {
                        parentSize = it.height
                    }
                }.draggableList(
                    scrollState = scrollState,
                    draggableType = DraggableType.Vertical,
                ),
        state = scrollState,
        verticalArrangement = spacedBy(itemSpacing),
        contentPadding = PaddingValues(contentPadding),
    ) {
        itemList.forEachIndexed { index, item ->
            item {
                itemLayout(item, itemVerticalPadding) {
                    val childMap = childrenSize.toMutableMap()
                    childMap[index] = it.height
                    childrenSize = childMap
                }
            }
        }
    }
}

@Composable
internal fun ExpandableBox(
    itemVerticalPadding: Dp,
    onSizeChanged: (IntSize) -> Unit,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(
                    vertical = itemVerticalPadding,
                ).onSizeChanged(onSizeChanged),
        contentAlignment = Alignment.Center,
        content = content,
    )
}

@Composable
fun ToggleInfoTextButton(
    sectionState: SectionState,
    shrinkLabelText: String,
    expandLabelText: String,
    onClick: (newSectionState: SectionState) -> Unit,
) {
    val expandText =
        mutableStateOf(if (sectionState == SectionState.OPEN) shrinkLabelText else expandLabelText)
    val interactionSource = remember { MutableInteractionSource() }

    val iconVector = getIconVector(sectionState)
    val expandTextColor = TextColor.OnSurfaceLight
    Row(
        Modifier
            .clip(RoundedCornerShape(Radius.M))
            .clickable(
                onClick = {
                    onClick(getSectionState(sectionState))
                },
                role = Role.Button,
                interactionSource = interactionSource,
                indication =
                    ripple(
                        color = SurfaceColor.Primary,
                    ),
            ).padding(end = Spacing.Spacing2)
            .offset(x = (-3).dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = iconVector,
            contentDescription = "Button",
            tint = expandTextColor,
        )
        Text(
            text = expandText.value,
            color = expandTextColor,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = Spacing4),
        )
    }
}

private fun getSectionState(sectionState: SectionState): SectionState =
    if (sectionState == SectionState.CLOSE) SectionState.OPEN else SectionState.CLOSE

private fun getIconVector(sectionState: SectionState): ImageVector =
    if (sectionState == SectionState.CLOSE) {
        Icons.Filled.KeyboardArrowDown
    } else {
        Icons.Filled.KeyboardArrowUp
    }

@Composable
fun ColumnScope.ExpandShrinkAnimatedVisibility(
    expanded: Boolean,
    content:
        @Composable()
        (AnimatedVisibilityScope.() -> Unit),
) {
    AnimatedVisibility(
        visible = expanded,
        enter = expandVertically(expandFrom = Alignment.CenterVertically),
        exit = shrinkVertically(shrinkTowards = Alignment.CenterVertically),
        content = content,
    )
}
