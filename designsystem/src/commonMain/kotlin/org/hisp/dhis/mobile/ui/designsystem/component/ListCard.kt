package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Sync
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.hisp.dhis.mobile.ui.designsystem.component.internal.conditional
import org.hisp.dhis.mobile.ui.designsystem.component.internal.modifiers.fadingEdges
import org.hisp.dhis.mobile.ui.designsystem.component.state.AdditionalInfoColumnState
import org.hisp.dhis.mobile.ui.designsystem.component.state.ListCardState
import org.hisp.dhis.mobile.ui.designsystem.component.state.rememberAdditionalInfoColumnState
import org.hisp.dhis.mobile.ui.designsystem.component.state.rememberListCardState
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2SCustomTextStyles
import org.hisp.dhis.mobile.ui.designsystem.theme.InternalSizeValues
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing.Spacing4
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

/**
 * DHIS2 ListCard.
 * Component intended for TEI/Event/DataSet card display.
 * @param modifier: allows a modifier to be passed externally.
 * @param listCardState: state containing all configurations for the card
 * @param listAvatar: composable element to be used as avatar.
 * @param actionButton: composable parameter for the sync button.
 * @param onCardClick: gives access to click event on the main container.
 */
@Composable
fun ListCard(
    modifier: Modifier = Modifier,
    listCardState: ListCardState,
    listAvatar: (@Composable () -> Unit)? = null,
    actionButton: @Composable (() -> Unit)? = null,
    onCardClick: () -> Unit,
    onSizeChanged: ((IntSize) -> Unit)? = null,
) {
    BaseCard(
        modifier = modifier,
        showShadow = listCardState.shadow,
        onCardClick = onCardClick,
        expandable = listCardState.expandable,
        itemVerticalPadding = listCardState.itemVerticalPadding,
        onSizeChanged = onSizeChanged,
        paddingValues = getPaddingValues(
            expandable = listCardState.expandable,
            hasShadow = listCardState.shadow,
            hasAvatar = listAvatar != null,
        ),
    ) {
        Row(horizontalArrangement = spacedBy(Spacing.Spacing16)) {
            listAvatar?.invoke()
            Column(Modifier.fillMaxWidth().weight(1f)) {
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    ListCardTitle(
                        title = listCardState.title,
                        Modifier.weight(1f)
                            .padding(bottom = if (listCardState.description?.text != null) Spacing.Spacing0 else Spacing4),
                    )
                    listCardState.lastUpdateBasedOnLoading()?.let { ListCardLastUpdated(it) }
                }
                listCardState.descriptionBasedOnLoading()?.let {
                    ListCardDescription(it, Modifier.padding(bottom = Spacing.Spacing8))
                }

                AdditionalInfoColumn(
                    additionalInfoColumnState = listCardState.additionalInfoColumnState,
                    modifier = Modifier.testTag("LIST_CARD_ADDITIONAL_INFO_COLUMN"),
                    loading = listCardState.loading,
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start,
                )
                actionButton?.invoke()
            }
        }
    }
}

/**
 * DHIS2 ListCard.
 * Component intended for TEI/Event/DataSet card display.
 * @param listAvatar: composable element to be used as avatar.
 * @param title: is the card title.
 * @param description: the text to be used in description.
 * @param lastUpdated: shows the last time item was synchronized.
 * @param additionalInfoList: is a list of AdditionalInfoItem that
 * manages all the key value types that will be shown
 * if there are more than three items that are not constant
 * a show more/less button will appear and the rest of items will be hidden.
 * @param expandLabelText: the text to be shown for expand button.
 * @param shrinkLabelText: the text to be shown for shrink button.
 * @param actionButton: composable parameter for the sync button.
 * @param onCardClick: gives access to click event on the main container.
 * @param loading: controls visibility of the loading item.
 * @param shadow: whether to show a shadow or not.
 * @param modifier: allows a modifier to be passed externally.
 */
@Composable
@Deprecated("Use rememberListCardState constructor")
fun ListCard(
    listAvatar: (@Composable () -> Unit)? = null,
    title: ListCardTitleModel,
    description: ListCardDescriptionModel? = null,
    lastUpdated: String? = null,
    additionalInfoList: List<AdditionalInfoItem>,
    actionButton: @Composable (() -> Unit)? = null,
    expandLabelText: String = provideStringResource("show_more"),
    shrinkLabelText: String = provideStringResource("show_less"),
    loading: Boolean = false,
    onCardClick: () -> Unit,
    shadow: Boolean = true,
    modifier: Modifier = Modifier,
    scrollableContent: Boolean = false,
    expandable: Boolean = false,
    itemVerticalPadding: Dp? = null,
    minItemsToShow: Int = 3,
    onSizeChanged: ((IntSize) -> Unit)? = null,
) {
    val syncProgressItem = AdditionalInfoItem(
        icon = {
            Icon(
                imageVector = Icons.Outlined.Sync,
                contentDescription = "Icon Button",
                tint = SurfaceColor.Primary,
            )
        },
        value = "Syncing...",
        color = SurfaceColor.Primary,
        isConstantItem = false,
    )

    val additionalInfoColumnState = rememberAdditionalInfoColumnState(
        additionalInfoList,
        syncProgressItem,
        expandLabelText,
        shrinkLabelText,
        minItemsToShow,
        scrollableContent,
    )
    val listCardState = rememberListCardState(
        title,
        description,
        lastUpdated,
        additionalInfoColumnState,
        loading,
        shadow,
        expandable,
        itemVerticalPadding,
    )

    ListCard(
        modifier = modifier,
        listCardState = listCardState,
        listAvatar = listAvatar,
        actionButton = actionButton,
        onCardClick = onCardClick,
        onSizeChanged = onSizeChanged,
    )
}

/**
 * DHIS2 VerticalListCard.
 * Component intended for Program/DataSet card display.
 * @param modifier: allows a modifier to be passed externally.
 * @param listCardState: state containing all configurations for the card
 * @param listAvatar: composable element to be used as avatar.
 * @param actionButton: composable parameter for the sync button.
 * @param onCardClick: gives access to click event on the main container.
 */
@Composable
fun VerticalInfoListCard(
    modifier: Modifier = Modifier,
    listCardState: ListCardState,
    listAvatar: (@Composable () -> Unit)? = null,
    actionButton: @Composable (() -> Unit)? = null,
    onCardClick: () -> Unit,
    onSizeChanged: ((IntSize) -> Unit)? = null,
) {
    BaseCard(
        modifier = modifier,
        showShadow = listCardState.shadow,
        onCardClick = onCardClick,
        paddingValues = getPaddingValues(
            listCardState.expandable,
            hasShadow = listCardState.shadow,
            hasAvatar = listAvatar != null,
        ),
        expandable = listCardState.expandable,
        itemVerticalPadding = listCardState.itemVerticalPadding,
        onSizeChanged = onSizeChanged,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = spacedBy(Spacing.Spacing16),
        ) {
            listAvatar?.invoke()
            Column(
                modifier = Modifier.wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = spacedBy(Spacing4),
            ) {
                ListCardTitle(
                    title = listCardState.title,
                    Modifier
                        .padding(bottom = if (listCardState.description?.text != null) Spacing.Spacing0 else Spacing4),
                )
                listCardState.descriptionBasedOnLoading()?.let {
                    ListCardDescription(it)
                }
                listCardState.lastUpdateBasedOnLoading()?.let {
                    ListCardLastUpdated(it)
                }

                AdditionalInfoColumn(
                    additionalInfoColumnState = listCardState.additionalInfoColumnState,
                    loading = listCardState.loading,
                    verticalArrangement = spacedBy(Spacing4),
                    horizontalAlignment = Alignment.CenterHorizontally,
                )

                actionButton?.invoke()
            }
        }
    }
}

/**
 * DHIS2 CardDetail.
 * Component intended for TEI Dashboard
 * @param title is the card title
 * @param additionalInfoList is a list of AdditionalInfoItem that
 * manages all the key value types that will be shown
 * if there are more than three items that are not constant
 * a show more/less button will appear and the rest of items will be hidden
 * @param expandLabelText the text to be shown for expand button
 * @param shrinkLabelText the text to be shown for shrink button
 * @param actionButton composable parameter for the sync button
 * @param modifier allows a modifier to be passed externally
 */
@Composable
fun CardDetail(
    avatar: (@Composable () -> Unit)? = null,
    title: String,
    additionalInfoList: List<AdditionalInfoItem>,
    actionButton: @Composable (() -> Unit)? = null,
    expandLabelText: String = provideStringResource("show_more"),
    shrinkLabelText: String = provideStringResource("show_less"),
    showLoading: Boolean = false,
    modifier: Modifier = Modifier,
) {
    val additionalInfoColumnState = rememberAdditionalInfoColumnState(
        additionalInfoList = additionalInfoList,
        syncProgressItem = AdditionalInfoItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Sync,
                    contentDescription = "Icon Button",
                    tint = SurfaceColor.Primary,
                )
            },
            value = "Syncing...",
            color = SurfaceColor.Primary,
            isConstantItem = false,
        ),
        expandLabelText = expandLabelText,
        shrinkLabelText = shrinkLabelText,
    )

    val expandableItemList = mutableListOf<AdditionalInfoItem>()
    val constantItemList = mutableListOf<AdditionalInfoItem>()

    additionalInfoList.forEach { item ->
        if (item.isConstantItem) {
            constantItemList.add(item)
        } else {
            expandableItemList.add(item)
        }
    }
    Row(
        modifier = modifier
            .background(color = TextColor.OnPrimary)
            .clip(shape = RoundedCornerShape(Radius.S))
            .padding(Spacing.Spacing16),
    ) {
        Column(Modifier.fillMaxWidth().weight(1f)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                // Row with header and last updated
                TEIDetailTitle(text = title, modifier.weight(1f))

                avatar?.let {
                    Box(Modifier.align(Alignment.CenterVertically)) {
                        Spacer(Modifier.size(Spacing.Spacing16))
                        it.invoke()
                    }
                }
            }
            AdditionalInfoColumn(
                additionalInfoColumnState = additionalInfoColumnState,
                isDetailCard = true,
                loading = showLoading,
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
            )
            actionButton?.invoke()
        }
    }
}

/**
 * DHIS2 AdditionalInfoColumn,
 *  used to display both key value lists, the constant one and the expandable one
 */
@Composable
private fun ColumnScope.AdditionalInfoColumn(
    modifier: Modifier = Modifier,
    additionalInfoColumnState: AdditionalInfoColumnState,
    loading: Boolean,
    isDetailCard: Boolean = false,
    verticalArrangement: Arrangement.Vertical,
    horizontalAlignment: Alignment.Horizontal,
) {
    val loadingSectionState by remember(loading) { mutableStateOf(loading) }
    val scrollState = rememberScrollState()

    val columnModifier by remember(additionalInfoColumnState) {
        derivedStateOf {
            when {
                !additionalInfoColumnState.scrollableContent -> modifier
                additionalInfoColumnState.isExpanded() -> modifier.verticalScroll(scrollState)
                    .weight(1f)

                else -> modifier
            }
        }
    }

    val items = when (additionalInfoColumnState.currentSectionState()) {
        SectionState.CLOSE -> additionalInfoColumnState.visibleExpandableItemList()
        SectionState.OPEN -> additionalInfoColumnState.expandableItemList()
        else -> null
    } ?: emptyList()

    KeyValueList(
        modifier = columnModifier
            .fillMaxWidth()
            .testTag("LIST_CARD_ADDITIONAL_INFO_COLUMN")
            .conditional(
                additionalInfoColumnState.isExpanded() and additionalInfoColumnState.scrollableContent,
                {
                    fadingEdges(scrollState)
                },
            ),
        itemList = items,
        isDetailCard = isDetailCard,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
    )

    ExpandShrinkAnimatedVisibility(
        expanded = loadingSectionState,
    ) {
        KeyValue(additionalInfoColumnState.syncProgressItem)
    }
    KeyValueList(
        modifier = Modifier.testTag("LIST_CARD_ADDITIONAL_INFO_CONSTANT_COLUMN"),
        itemList = additionalInfoColumnState.constantItemList(),
        isDetailCard = isDetailCard,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
    )

    if (additionalInfoColumnState.showExpandableContent()) {
        ToggleInfoTextButton(
            sectionState = additionalInfoColumnState.currentSectionState(),
            shrinkLabelText = additionalInfoColumnState.shrinkLabelText,
            expandLabelText = additionalInfoColumnState.expandLabelText,
            onClick = additionalInfoColumnState::updateSectionState,
        )
    }
//    }
}

@Deprecated("Use additionalInfoColumn constructor")
@Composable
private fun ColumnScope.AdditionalInfoColumn(
    modifier: Modifier = Modifier,
    expandableItems: List<AdditionalInfoItem>? = null,
    constantItems: List<AdditionalInfoItem>,
    syncProgressItem: AdditionalInfoItem,
    loading: Boolean,
    isDetailCard: Boolean = false,
    expandLabelText: String,
    shrinkLabelText: String,
    scrollableContent: Boolean,
    minItemsToShow: Int = 3,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
) {
    val additionalInfoColumnState = rememberAdditionalInfoColumnState(
        additionalInfoList = (expandableItems ?: emptyList()) + constantItems,
        syncProgressItem = syncProgressItem,
        expandLabelText = expandLabelText,
        shrinkLabelText = shrinkLabelText,
        minItemsToShow = minItemsToShow,
        scrollableContent = scrollableContent,
    )

    AdditionalInfoColumn(
        modifier,
        additionalInfoColumnState,
        loading,
        isDetailCard,
        verticalArrangement,
        horizontalAlignment,
    )

    val loadingSectionState by remember(loading) { mutableStateOf(loading) }
    var listState by remember(AdditionInfoState.CLOSED) { mutableStateOf(AdditionInfoState.CLOSED) }

    val scrollState = rememberScrollState()
    val scrollableModifier = Modifier.verticalScroll(scrollState)

    val columnModifier by remember(listState) {
        derivedStateOf {
            when {
                !scrollableContent -> Modifier
                listState == AdditionInfoState.OPEN -> scrollableModifier.weight(1f)
                else -> Modifier
            }
        }
    }

    val items = when (listState) {
        AdditionInfoState.CLOSED -> expandableItems?.take(3)
        AdditionInfoState.OPEN -> expandableItems
    } ?: emptyList()

    KeyValueList(
        modifier = columnModifier
            .fillMaxWidth()
            .testTag("LIST_CARD_ADDITIONAL_INFO_COLUMN")
            .conditional(
                listState == AdditionInfoState.OPEN,
                {
                    fadingEdges(scrollState)
                },
            ),
        itemList = items,
        isDetailCard = isDetailCard,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
    )

    AnimatedVisibility(
        visible = loadingSectionState,
        enter = expandVertically(expandFrom = Alignment.CenterVertically),
        exit = shrinkVertically(shrinkTowards = Alignment.CenterVertically),
    ) {
        KeyValue(syncProgressItem)
    }

    KeyValueList(
        modifier = Modifier.testTag("LIST_CARD_ADDITIONAL_INFO_CONSTANT_COLUMN"),
        itemList = constantItems,
        isDetailCard = isDetailCard,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
    )

    if ((expandableItems?.size ?: 0) > 3) {
        ExpandShrinkButton(listState, shrinkLabelText, expandLabelText) {
            listState = getSectionState(listState)
        }
    }
}

enum class AdditionInfoState {
    OPEN, CLOSED;

    fun getNextState(): AdditionInfoState {
        return when (this) {
            OPEN -> CLOSED
            CLOSED -> OPEN
        }
    }
}

@Composable
private fun ExpandShrinkButton(
    listState: AdditionInfoState,
    shrinkLabelText: String,
    expandLabelText: String,
    onClick: () -> Unit,
) {
    val expandText =
        mutableStateOf(if (listState == AdditionInfoState.OPEN) shrinkLabelText else expandLabelText)
    val interactionSource = remember { MutableInteractionSource() }

    val iconVector = getIconVector(listState)
    val expandTextColor = TextColor.OnSurfaceLight
    Row(
        Modifier
            .clip(RoundedCornerShape(Radius.M))
            .clickable(
                onClick = onClick,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = rememberRipple(
                    color = SurfaceColor.Primary,
                ),
            )
            .padding(end = Spacing.Spacing2)
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

private fun getSectionState(listState: AdditionInfoState): AdditionInfoState {
    return if (listState == AdditionInfoState.CLOSED) {
        AdditionInfoState.OPEN
    } else {
        AdditionInfoState.CLOSED
    }
}

private fun getIconVector(listState: AdditionInfoState): ImageVector {
    return if (listState == AdditionInfoState.CLOSED) {
        Icons.Filled.KeyboardArrowDown
    } else {
        Icons.Filled.KeyboardArrowUp
    }
}

/**
 * DHIS2 KeyValue,
 *  used to paint each individual KeyValueItem
 */
@Composable
private fun KeyValue(
    additionalInfoItem: AdditionalInfoItem,
    isDetailCard: Boolean = false,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(modifier = modifier) {
        val maxKeyWidth = maxWidth / 2 - Spacing.Spacing16
        ProvideKeyValueItem(additionalInfoItem, maxKeyWidth, isDetailCard)
    }
}

@Composable
fun ProvideKeyValueItem(
    additionalInfoItem: AdditionalInfoItem,
    maxKeyWidth: Dp,
    isDetailCard: Boolean,
) {
    val keyText = additionalInfoItem.key
    val interactionSource = remember { MutableInteractionSource() }

    val textMeasurer = rememberTextMeasurer()

    val keyTrimmedText = getKeyTrimmedText(keyText ?: "", maxKeyWidth, textMeasurer)

    val finalAnnotatedString: AnnotatedString =
        getKeyValueAnnotatedString(keyTrimmedText, additionalInfoItem, isDetailCard)

    val inlineContent = mapOf(
        Pair(
            "ItemIcon",
            InlineTextContent(

                Placeholder(
                    width = 20.sp,
                    height = 20.sp,
                    placeholderVerticalAlign = PlaceholderVerticalAlign.Center,
                ),
            ) {
                Box(
                    Modifier.background(color = Color.Transparent).size(InternalSizeValues.Size20),
                ) {
                    additionalInfoItem.icon?.invoke()
                }
            },
        ),
    )

    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(Radius.XS))
            .conditional(additionalInfoItem.action != null && isDetailCard, {
                clickable(
                    role = Role.Button,
                    interactionSource = interactionSource,
                    indication = rememberRipple(
                        color = SurfaceColor.Primary,
                    ),
                    onClick = additionalInfoItem.action ?: {},
                )
            }),
    ) {
        Text(
            inlineContent = inlineContent,
            text = finalAnnotatedString,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyMedium,
            overflow = when {
                additionalInfoItem.truncate -> TextOverflow.Ellipsis
                else -> TextOverflow.Clip
            },
            maxLines = when {
                additionalInfoItem.truncate -> 2
                else -> Int.MAX_VALUE
            },
            modifier = Modifier,

        )
    }
}

fun getKeyValueAnnotatedString(
    key: String,
    additionalInfoItem: AdditionalInfoItem?,
    isDetailCard: Boolean,
): AnnotatedString {
    val valueText = additionalInfoItem?.value
    val keyStyle =
        DHIS2SCustomTextStyles.listCardKey.copy(color = AdditionalInfoItemColor.DEFAULT_KEY.color)

    val valueColor = getValueColor(additionalInfoItem)
    val valueStyle = DHIS2SCustomTextStyles.listCardValue.copy(color = valueColor)
    return buildAnnotatedString {
        withStyle(
            style = ParagraphStyle(lineHeight = 20.sp),
        ) {
            if (additionalInfoItem?.icon != null && !isDetailCard) {
                appendInlineContent("ItemIcon", "[icon]")
            }
            withStyle(
                style = keyStyle,
            ) {
                append(formatText(key, additionalInfoItem?.icon != null && !isDetailCard))
            }
            if (additionalInfoItem?.icon != null && isDetailCard) {
                appendInlineContent("ItemIcon", "[icon]")
            }
            withStyle(
                style = valueStyle,
            ) {
                append(formatText(valueText, key.isNotEmpty()))
            }
        }
    }
}

fun formatText(text: String?, withSpace: Boolean): String {
    return if (withSpace) " $text" else text ?: ""
}

fun getValueColor(additionalInfoItem: AdditionalInfoItem?): Color {
    var valueColor = additionalInfoItem?.color ?: AdditionalInfoItemColor.DEFAULT_VALUE.color
    valueColor = if (additionalInfoItem?.action != null) SurfaceColor.Primary else valueColor
    return valueColor
}

/**
 * DHIS2 KeyValueList,
 *  used to paint a list of AdditionalInfoItems
 */
@Composable
private fun KeyValueList(
    modifier: Modifier = Modifier,
    itemList: List<AdditionalInfoItem>,
    isDetailCard: Boolean = false,
    verticalArrangement: Arrangement.Vertical,
    horizontalAlignment: Alignment.Horizontal,
) {
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
    ) {
        itemList.forEach { item ->
            KeyValue(item, isDetailCard)
            Spacer(Modifier.size(if (isDetailCard) Spacing.Spacing8 else Spacing4))
        }
    }
}

@Composable
private fun getPaddingValues(
    expandable: Boolean,
    hasShadow: Boolean,
    hasAvatar: Boolean,
): PaddingValues {
    val expandedVerticalPadding = if (expandable) {
        0.dp
    } else {
        null
    }
    return when {
        !hasShadow -> PaddingValues(
            vertical = expandedVerticalPadding ?: Spacing.Spacing8,
            horizontal = Spacing.Spacing8,
        )

        hasShadow && hasAvatar -> PaddingValues(
            start = if (expandable) Spacing.Spacing16 else Spacing.Spacing8,
            top = expandedVerticalPadding ?: Spacing.Spacing16,
            end = Spacing.Spacing16,
            bottom = expandedVerticalPadding ?: Spacing.Spacing16,
        )

        else -> PaddingValues(
            vertical = expandedVerticalPadding ?: Spacing.Spacing16,
            horizontal = Spacing.Spacing16,
        )
    }
}

@Composable
fun measureTextWidth(text: String, textMeasurer: TextMeasurer): Dp {
    val widthInPixels = textMeasurer.measure(text, MaterialTheme.typography.bodyMedium).size.width
    return with(LocalDensity.current) { widthInPixels.toDp() }
}

@Composable
fun getKeyTrimmedText(text: String, maxKeyWidth: Dp, textMeasurer: TextMeasurer): String {
    return if (measureTextWidth(text, textMeasurer) > maxKeyWidth) {
        var lastCharIndex = 2
        var trimmedText = remember { text.substring(IntRange(0, lastCharIndex)) }
        var newKeyWidth = measureTextWidth(trimmedText, textMeasurer)

        while (newKeyWidth < maxKeyWidth && lastCharIndex < text.length) {
            lastCharIndex++
            trimmedText = text.substring(IntRange(0, lastCharIndex))
            newKeyWidth = measureTextWidth(trimmedText, textMeasurer)
        }
        trimmedText.dropLast(1).trimEnd() + "...: "
    } else {
        text + if (text.isNotEmpty()) ": " else ""
    }
}

data class AdditionalInfoItem(
    val icon: (@Composable () -> Unit)? = null, // The avatar,
    val key: String? = null,
    val value: String,
    val isConstantItem: Boolean = false,
    val color: Color? = null,
    val truncate: Boolean = true,
    val action: (() -> Unit)? = null,
)

data class ListCardTitleModel(
    val style: TextStyle? = null,
    val color: Color? = TextColor.OnPrimaryContainer,
    val text: String,
    val modifier: Modifier = Modifier,
)

data class ListCardDescriptionModel(
    val style: TextStyle? = null,
    val color: Color? = TextColor.OnSurface,
    val text: String? = null,
    val modifier: Modifier = Modifier,
)

enum class AdditionalInfoItemColor(val color: Color) {
    DEFAULT_KEY(TextColor.OnSurfaceLight),
    DEFAULT_VALUE(TextColor.OnSurface),
    ERROR(SurfaceColor.Error),
    WARNING(SurfaceColor.Warning),
    DISABLED(TextColor.OnDisabledSurface),
    SUCCESS(SurfaceColor.CustomGreen),
}
