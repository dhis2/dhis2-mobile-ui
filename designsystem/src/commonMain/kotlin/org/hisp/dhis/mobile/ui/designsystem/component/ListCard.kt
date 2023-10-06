package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Sync
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import org.hisp.dhis.mobile.ui.designsystem.resource.provideDHIS2Icon
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.InternalSizeValues
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.Ripple
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing.Spacing4
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.hisp.dhis.mobile.ui.designsystem.theme.hoverPointerIcon

/**
 * DHIS2 ListCard.
 * Component intended for TEI card display
 * @param title is the card title
 * @param lastUpdated shows the last time item was synchronized
 * @param additionalInfoList is a list of AdditionalInfoItem that
 * manages all the key value types that will be shown
 * if there are more than three items that are not constant
 * a show more/less button will appear and the rest of items will be hidden
 * @param expandLabelText the text to be shown for expand button
 * @param shrinkLabelText the text to be shown for shrink button
 * @param actionButton composable parameter for the sync button
 * @param onCardClick gives access to click event on the main container
 * @param modifier allows a modifier to be passed externally
 */
@Composable
fun ListCard(
    listAvatar: (@Composable () -> Unit)? = null,
    title: String,
    lastUpdated: String? = null,
    additionalInfoList: List<AdditionalInfoItem>,
    actionButton: @Composable (() -> Unit)? = null,
    expandLabelText: String = provideStringResource("show_more"),
    shrinkLabelText: String = provideStringResource("show_less"),
    showLoading: Boolean = false,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val expandableItemList = mutableListOf<AdditionalInfoItem>()
    val constantItemList = mutableListOf<AdditionalInfoItem>()

    additionalInfoList.forEach {
            item ->
        if (item.isConstantItem) {
            constantItemList.add(item)
        } else {
            expandableItemList.add(item)
        }
    }
    CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme) {
        Row(
            modifier = modifier
                .background(color = TextColor.OnPrimary)
                .clip(shape = RoundedCornerShape(Radius.S))
                .clickable(onClick = onCardClick)
                .padding(Spacing.Spacing8)
                .hoverPointerIcon(true),
        ) {
            listAvatar?.let {
                it.invoke()
                Spacer(Modifier.size(Spacing.Spacing16))
            }
            Column(Modifier.fillMaxWidth().weight(1f)) {
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    // Row with header and last updated
                    ListCardTitle(text = title, modifier.weight(1f))
                    if (lastUpdated != null) {
                        ListCardLastUpdated(lastUpdated)
                    }
                }
                AdditionalInfoColumn(
                    expandableItems = expandableItemList,
                    constantItems = constantItemList,
                    modifier = Modifier.testTag("LIST_CARD_ADDITIONAL_INFO_COLUMN"),
                    expandLabelText = expandLabelText,
                    shrinkLabelText = shrinkLabelText,
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
                    showLoading = showLoading,

                )
                actionButton?.invoke()
            }
            // rest of  items here (KeyValue component)
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
    val expandableItemList = mutableListOf<AdditionalInfoItem>()
    val constantItemList = mutableListOf<AdditionalInfoItem>()

    additionalInfoList.forEach {
            item ->
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
                isDetailCard = true,
                expandableItems = expandableItemList,
                constantItems = constantItemList,
                modifier = Modifier.testTag("LIST_CARD_ADDITIONAL_INFO_COLUMN"),
                expandLabelText = expandLabelText,
                shrinkLabelText = shrinkLabelText,
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
                showLoading = showLoading,

            )
            actionButton?.invoke()
        }
    }
}

/**
 * DHIS2 Avatar,
 *  used to display the avatar composable in card,
 *  must be one of the three styles given as parameters
 * @param style not nullable parameter that manages the avatar style
 * @param textAvatar style must be TEXT, will show a single character as avatar
 * @param imagePainter style must be IMAGE, will display an image as avatar
 * @param metadataAvatar style must be METADATA, composable should be DHIS2 [MetadataAvatar]
 * @param modifier allows a modifier to be passed externally
 */
@Composable
fun Avatar(
    textAvatar: String? = null,
    imagePainter: Painter = provideDHIS2Icon("dhis2_microscope_outline"),
    metadataAvatar: (@Composable () -> Unit)? = null,
    style: AvatarStyle = AvatarStyle.TEXT,
    onImageClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    when (style) {
        AvatarStyle.TEXT -> {
            textAvatar?.let {
                Box(
                    modifier = modifier
                        .size(Spacing.Spacing40)
                        .background(color = SurfaceColor.PrimaryContainer, shape = RoundedCornerShape(Radius.Full)),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(text = textAvatar, color = SurfaceColor.Primary, style = MaterialTheme.typography.titleSmall)
                }
            }
        }
        AvatarStyle.METADATA -> {
            metadataAvatar?.let {
                metadataAvatar.invoke()
            }
        }
        AvatarStyle.IMAGE -> {
            Image(
                painter = imagePainter,
                contentDescription = "avatarImage",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(Spacing.Spacing40)
                    .clip(CircleShape)
                    .clickable(onClick = { onImageClick?.invoke() }),
            )
        }
    }
}

/**
 * DHIS2 AdditionalInfoColumn,
 *  used to display both key value lists, the constant one and the expandable one
 */
@Composable
private fun AdditionalInfoColumn(
    modifier: Modifier = Modifier,
    expandableItems: List<AdditionalInfoItem>? = null,
    constantItems: List<AdditionalInfoItem>,
    syncProgressItem: AdditionalInfoItem,
    showLoading: Boolean,
    isDetailCard: Boolean = false,
    expandLabelText: String,
    shrinkLabelText: String,
) {
    val loadingSectionState by remember(showLoading) { mutableStateOf(showLoading) }
    var sectionState by remember(SectionState.CLOSE) { mutableStateOf(SectionState.CLOSE) }

    var expandableItemList: List<AdditionalInfoItem>
    var hiddenItemList: List<AdditionalInfoItem>
    Column(
        modifier = modifier,
    ) {
        if (expandableItems != null && expandableItems.size > 3) {
            expandableItemList = expandableItems.take(3).toMutableList()
            KeyValueList(expandableItemList, isDetailCard = isDetailCard)
            hiddenItemList = expandableItems.drop(3).toMutableList()

            AnimatedVisibility(
                visible = sectionState != SectionState.CLOSE,
                enter = expandVertically(expandFrom = Alignment.CenterVertically),
                exit = shrinkVertically(shrinkTowards = Alignment.CenterVertically),
            ) {
                KeyValueList(hiddenItemList, isDetailCard = isDetailCard)
            }
        } else {
            expandableItems?.let {
                KeyValueList(expandableItems, isDetailCard = isDetailCard)
            }
        }
        AnimatedVisibility(
            visible = loadingSectionState,
            enter = expandVertically(expandFrom = Alignment.CenterVertically),
            exit = shrinkVertically(shrinkTowards = Alignment.CenterVertically),
        ) {
            KeyValue(syncProgressItem)
        }
        KeyValueList(constantItems)

        if (expandableItems != null && expandableItems.size > 3) {
            val expandText = mutableStateOf(if (sectionState == SectionState.OPEN) shrinkLabelText else expandLabelText)

            val iconVector = if (sectionState == SectionState.CLOSE) {
                Icons.Filled.KeyboardArrowDown
            } else {
                Icons.Filled.KeyboardArrowUp
            }
            val verticalPadding = if (isDetailCard) Spacing.Spacing10 else Spacing.Spacing0
            val expandTextColor = if (isDetailCard) TextColor.OnSurfaceLight else SurfaceColor.Primary
            CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme) {
                Row(
                    Modifier
                        .clip(RoundedCornerShape(Radius.M))
                        .clickable(onClick = {
                            sectionState =
                                if (sectionState == SectionState.CLOSE) SectionState.OPEN else SectionState.CLOSE
                        })
                        .padding(top = verticalPadding, end = Spacing.Spacing2, bottom = verticalPadding),
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
        }
    }
}

/**
 * DHIS2 KeyValue,
 *  used to paint each individual KeyValueItem
 */
@Composable
private fun KeyValue(
    additionalInfoItem: AdditionalInfoItem,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
    ) {
        if (additionalInfoItem.action != null) {
            // Consider adding : manually
            val keyColor = AdditionalInfoItemColor.DEFAULT_KEY.color
            additionalInfoItem.key?.let {
                Text(
                    text = it,
                    color = keyColor,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier,
                )
            }
            Row(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(Radius.XS))
                    .clickable(onClick = additionalInfoItem.action),
            ) {
                Spacer(Modifier.size(Spacing4))
                if (additionalInfoItem.icon != null) {
                    Box(
                        Modifier.background(color = Color.Transparent).size(InternalSizeValues.Size20),
                    ) {
                        additionalInfoItem.icon.invoke()
                    }
                }

                Spacer(Modifier.size(Spacing4))
                val valueColor = SurfaceColor.Primary
                ListCardValue(text = additionalInfoItem.value, color = valueColor)
                Spacer(Modifier.size(Spacing4))
            }
        } else {
            if (additionalInfoItem.icon != null) {
                Box(
                    Modifier.background(color = Color.Transparent).size(InternalSizeValues.Size20),
                ) {
                    additionalInfoItem.icon.invoke()
                }
                Spacer(Modifier.size(Spacing4))
            } else {
                val keyColor = additionalInfoItem.color ?: AdditionalInfoItemColor.DEFAULT_KEY.color
                additionalInfoItem.key?.let {
                    Text(
                        text = it,
                        color = keyColor,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(end = Spacing4),
                    )
                }
            }
            val valueColor = additionalInfoItem.color ?: AdditionalInfoItemColor.DEFAULT_VALUE.color
            ListCardValue(text = additionalInfoItem.value, color = valueColor)
        }
    }
}

/**
 * DHIS2 KeyValueList,
 *  used to paint a list of AdditionalInfoItems
 */
@Composable
private fun KeyValueList(
    itemList: List<AdditionalInfoItem>,
    isDetailCard: Boolean = false,
) {
    Column {
        itemList.forEach { item ->
            KeyValue(item)
            Spacer(Modifier.size(if (isDetailCard) Spacing.Spacing8 else Spacing4))
        }
    }
}

enum class
AvatarStyle {
    TEXT,
    IMAGE,
    METADATA,
}

data class AdditionalInfoItem(
    val icon: (@Composable () -> Unit)? = null, // The avatar,
    val key: String? = null,
    val value: String,
    val isConstantItem: Boolean = false,
    val color: Color? = null,
    val action: (() -> Unit)? = null,
)

enum class AdditionalInfoItemColor(val color: Color) {
    DEFAULT_KEY(TextColor.OnSurfaceLight),
    DEFAULT_VALUE(TextColor.OnSurface),
    ERROR(SurfaceColor.Error),
    WARNING(TextColor.OnWarning),
    DISABLED(TextColor.OnDisabledSurface),
    SUCCESS(SurfaceColor.CustomGreen),
}
