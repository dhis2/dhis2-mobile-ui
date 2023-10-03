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
import org.hisp.dhis.mobile.ui.designsystem.resource.provideDHIS2Icon
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.InternalSizeValues
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.Ripple
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.hisp.dhis.mobile.ui.designsystem.theme.hoverPointerIcon

/**
 * DHIS2 ListCard.
 * Component intended for TEI card display
 * @param title is the card title
 * @param lastUpdated shows the last time item was synchronized
 * @param additionalInfoExpandableList is a list of AdditionalInfoItem that
 * manages all the key value types that will be shown if there are more than three
 * a show more/less button will appear and the rest of items will be hidden
 * @param additionalInfoConstantList manages key value items that will always be shown
 * @param actionButton composable parameter for the sync button
 * @param onCardClick gives access to click event on the main container
 * @param modifier allows a modifier to be passed externally
 */
@Composable
fun ListCard(
    listAvatar: (@Composable () -> Unit)? = null,
    title: String,
    lastUpdated: String? = null,
    additionalInfoExpandableList: List<AdditionalInfoItem>? = null,
    additionalInfoConstantList: List<AdditionalInfoItem>,
    actionButton: @Composable (() -> Unit)? = null,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme) {
        Box(
            modifier = modifier
                .background(color = TextColor.OnPrimary, shape = RoundedCornerShape(Radius.S)),
        ) {
            Row(
                modifier = modifier
                    .clickable(onClick = onCardClick)
                    .padding(Spacing.Spacing8)
                    .hoverPointerIcon(true),
            ) {
                listAvatar?.invoke()
                Spacer(Modifier.size(Spacing.Spacing16))
                Column(Modifier.fillMaxWidth().weight(1f)) {
                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        // Row with header and last updated
                        ListCardTitle(text = title, modifier.weight(1f))
                        if (lastUpdated != null) {
                            ListCardLastUpdated(lastUpdated)
                        }
                    }
                    AdditionalInfoColumn(expandableItems = additionalInfoExpandableList, constantItems = additionalInfoConstantList)
                    actionButton?.invoke()
                }
            }
        }
    }
}

/**
 * DHIS2 ListAvatar,
 *  used to display the avatar composable in card,
 *  must be one of the three styles given as parameters
 * @param style not nullable parameter that manages the avatar style
 * @param textAvatar style must be TEXT, will show a single character as avatar
 * @param imageAvatar style must be IMAGE, will display an image as avatar
 * @param metadataAvatar style must be METADATA, composable should be DHIS2 [MetadataAvatar]
 * @param modifier allows a modifier to be passed externally
 */
@Composable
fun ListAvatar(
    textAvatar: String? = null,
    imageAvatar: (@Composable (image: Painter) -> Unit)? = null,
    metadataAvatar: (@Composable () -> Unit)? = null,
    style: ListAvatarStyle = ListAvatarStyle.TEXT,
    modifier: Modifier = Modifier,
) {
    when (style) {
        ListAvatarStyle.TEXT -> {
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
        ListAvatarStyle.METADATA -> {
            metadataAvatar?.let {
                metadataAvatar.invoke()
            }
        }
        ListAvatarStyle.IMAGE -> {
            imageAvatar?.let {
                Image(
                    painter = provideDHIS2Icon("dhis2_microscope_outline"),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop, // crop the image if it's not a square
                    modifier = modifier
                        .size(Spacing.Spacing64)
                        .clip(CircleShape), // clip to the circle shape
                )
            }
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
) {
    var sectionState by remember(SectionState.CLOSE) { mutableStateOf(SectionState.CLOSE) }

    var hideableItemList: MutableList<AdditionalInfoItem>
    val hiddenItemList = mutableListOf<AdditionalInfoItem>()

    Column(
        modifier = modifier,
    ) {
        if (expandableItems != null && expandableItems.size > 3) {
            hideableItemList = mutableListOf(expandableItems[0], expandableItems[1], expandableItems[2])
            KeyValueList(hideableItemList)
            expandableItems.forEach { item ->
                if (expandableItems.indexOf(item) > 3) {
                    hiddenItemList.add(item)
                }
            }

            AnimatedVisibility(
                visible = sectionState != SectionState.CLOSE,
                enter = expandVertically(expandFrom = Alignment.CenterVertically),
                exit = shrinkVertically(shrinkTowards = Alignment.CenterVertically),
            ) {
                KeyValueList(hiddenItemList)
            }
        }

        KeyValueList(constantItems)

        if (expandableItems != null) {
            if (expandableItems.size > 3) {
                val expandText = mutableStateOf(provideStringResource(if (sectionState == SectionState.OPEN) "show_less" else "show_more"))

                val iconVector = if (sectionState == SectionState.CLOSE) {
                    Icons.Filled.KeyboardArrowDown // it requires androidx.compose.material:material-icons-extended
                } else {
                    Icons.Filled.KeyboardArrowUp
                }
                Row(
                    Modifier
                        .clickable(onClick = {
                            sectionState = if (sectionState == SectionState.CLOSE) SectionState.OPEN else SectionState.CLOSE
                        }),
                ) {
                    Icon(
                        imageVector = iconVector,
                        contentDescription = "Button",
                        tint = SurfaceColor.Primary,
                    )
                    Spacer(modifier.size(Spacing.Spacing4))
                    Text(text = expandText.value, color = SurfaceColor.Primary, style = MaterialTheme.typography.bodyMedium)
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
    Row(modifier = modifier) {
        if (additionalInfoItem.icon != null) {
            Box(Modifier.background(color = Color.Transparent).size(InternalSizeValues.Size20)) {
                additionalInfoItem.icon.invoke()
            }
        } else {
            // Consider adding : manually
            val keyColor = additionalInfoItem.color ?: AdditionalInfoItemColor.DEFAULT_KEY.color
            additionalInfoItem.key?.let { Text(text = it, color = keyColor, style = MaterialTheme.typography.bodyMedium) }
        }
        Spacer(Modifier.size(Spacing.Spacing4))
        val valueColor = additionalInfoItem.color ?: AdditionalInfoItemColor.DEFAULT_VALUE.color
        ListCardValue(text = additionalInfoItem.value, color = valueColor)
    }
}

/**
 * DHIS2 KeyValueList,
 *  used to paint a list of AdditionalInfoItems
 */
@Composable
private fun KeyValueList(
    itemList: List<AdditionalInfoItem>,
) {
    itemList.forEach { item ->
        KeyValue(item)
        Spacer(Modifier.size(Spacing.Spacing4))
    }
}

enum class ListAvatarStyle {
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
)

enum class AdditionalInfoItemColor(val color: Color) {
    DEFAULT_KEY(TextColor.OnSurfaceLight),
    DEFAULT_VALUE(TextColor.OnSurface),
}
