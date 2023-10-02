package org.hisp.dhis.mobile.ui.designsystem.component

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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import org.hisp.dhis.mobile.ui.designsystem.resource.provideDHIS2Icon
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.InternalSizeValues
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun ListCard(
    listAvatar: (@Composable () -> Unit)? = null, // The avatar,
    title: String, // The card title
    lastUpdated: String? = null, // The time the item was updated last
    additionalInfo: @Composable () -> Unit,
    actionButton: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        // listAvatar here
        listAvatar?.invoke()
        Spacer(Modifier.size(Spacing.Spacing16))
        Column(Modifier.fillMaxWidth().weight(1f)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                // Row with header and last updated
                Text(
                    title,
                    color = TextColor.OnPrimaryContainer,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.weight(1f).padding(bottom = Spacing.Spacing4),
                )
                if (lastUpdated != null) {
                    Text(
                        lastUpdated,
                        color = TextColor.OnSurfaceLight,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier,
                    )
                }
            }
            // rest of  items here (KeyValue component)
            additionalInfo.invoke()
            actionButton?.invoke()
        }
    }
}

@Composable
fun ListAvatar(
    textAvatar: String? = null, // The avatar,
    imageAvatar: (@Composable (image: Painter) -> Unit)? = null, // The avatar,
    metadataAvatar: (@Composable () -> Unit)? = null,
    style: ListAvatarStyle = ListAvatarStyle.TEXT,
    modifier: Modifier = Modifier,
) {
    when (style) {
        ListAvatarStyle.TEXT -> {
            textAvatar?.let {
                Box(
                    Modifier
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
                    modifier = Modifier
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
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
    ) {
        hideableItems?.forEach { item ->
            KeyValue(item)
            Spacer(Modifier.size(Spacing.Spacing4))
        }
        constantItems.forEach { item ->
            KeyValue(item)
            Spacer(Modifier.size(Spacing.Spacing4))
        }
        if (hideableItems != null) {
            if (hideableItems.size > 3) {
                val expandText = mutableStateOf(provideStringResource(if (isExpanded) "show_less" else "show_more"))

                val iconVector = if (isExpanded) {
                    Icons.Filled.KeyboardArrowUp // it requires androidx.compose.material:material-icons-extended
                } else {
                    Icons.Filled.KeyboardArrowDown
                }
                Row(
                    Modifier
                        .clickable(onClick = {
                            isExpanded = !isExpanded
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
        Text(
            text = additionalInfoItem.value,
            color = valueColor,
            style = MaterialTheme.typography.bodyMedium,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
        )
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
