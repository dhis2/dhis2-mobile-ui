package org.hisp.dhis.mobile.ui.designsystem.component.table.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.TableView
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TableActions(modifier: Modifier, title: String, actionIcons: @Composable () -> Unit) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Absolute.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // todo verify icon is correct
        Icon(
            imageVector = Icons.Outlined.TableView,
            contentDescription = "",
            tint = TableTheme.colors.primary,
        )
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            style = TextStyle(
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 10.sp,
            ),
        )
        actionIcons()
    }
}
