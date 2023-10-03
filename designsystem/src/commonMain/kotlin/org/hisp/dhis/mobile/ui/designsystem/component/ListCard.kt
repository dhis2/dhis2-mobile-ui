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

@Composable
fun ListCard(
    listAvatar: (@Composable () -> Unit)? = null,
    title: String,
    lastUpdated: String? = null,
    additionalInfo: @Composable () -> Unit,
    actionButton: @Composable (() -> Unit)? = null,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme) {
        Box(
            modifier = modifier
                .background(color = TextColor.OnPrimary, shape = RoundedCornerShape(Radius.S)),
        ) {
            Row(
                modifier = modifier
                    .clickable(onClick = onClick)
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
                    additionalInfo.invoke()
                    actionButton?.invoke()
                }
            }
        }
    }
}

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

@Composable
fun AdditionalInfoColumn(
    modifier: Modifier = Modifier,
    hideableItems: List<AdditionalInfoItem>? = null,
    constantItems: List<AdditionalInfoItem>,
) {
    var sectionState by remember(SectionState.CLOSE) { mutableStateOf(SectionState.CLOSE) }

    var hideableItemList: MutableList<AdditionalInfoItem>
    val hiddenItemList = mutableListOf<AdditionalInfoItem>()

    Column(
        modifier = modifier,
    ) {
        if (hideableItems != null && hideableItems.size > 3) {
            hideableItemList = mutableListOf(hideableItems[0], hideableItems[1], hideableItems[2])
            KeyValueList(hideableItemList)
            hideableItems.forEach { item ->
                if (hideableItems.indexOf(item) > 3) {
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

        if (hideableItems != null) {
            if (hideableItems.size > 3) {
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

@Composable
fun KeyValue(
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

@Composable
fun KeyValueList(
    itemList: List<AdditionalInfoItem>,
) {
    itemList.forEach { item ->
        KeyValue(item)
        Spacer(Modifier.size(Spacing.Spacing4))
    }
}

/**
 * DHIS2 InputShellState,
 *  enum class to control the state [InputShell] component
 */
enum class ListAvatarStyle() {
    TEXT(),
    IMAGE(),
    METADATA(),
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
