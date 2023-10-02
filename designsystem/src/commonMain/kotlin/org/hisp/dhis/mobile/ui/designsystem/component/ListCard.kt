package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

@Composable
fun ListCard(
    icon: (@Composable () -> Unit)? = null, //The avatar,
    title: String, //The card title
    lastUpdated: String? = null, //The time the item was updated last
    additionalInfo: @Composable ColumnScope.() -> Unit,
    actionButton: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        //listAvatar here
        Column(Modifier.fillMaxWidth().weight(1f)) {
            Row() {
                //Row with header and last updated
                Text(title)
                if (lastUpdated != null) {
                    Text(lastUpdated)
                }

            }
            //rest of  items here (KeyValue component)
        }
    }
}

@Composable
fun ListAvatar(
    textAvatar: String? = null, //The avatar,
    imageAvatar: (@Composable (image: Painter) -> Unit)? = null, //The avatar,
    metadataAvatar: (@Composable () -> Unit)? = null,

    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {

    }
}

@Composable
fun KeyValue(
    icon: (@Composable () -> Unit)? = null, //The avatar,
    title: String, //The card title
    lastUpdated: String? = null, //The time the item was updated last
    additionalInfo: @Composable ColumnScope.() -> Unit,
    actionButton: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {

    }
}

