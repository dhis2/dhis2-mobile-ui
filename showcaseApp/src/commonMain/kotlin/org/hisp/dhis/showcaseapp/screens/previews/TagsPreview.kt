package org.hisp.dhis.showcaseapp.screens.previews

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.Tag
import org.hisp.dhis.mobile.ui.designsystem.component.TagType

@Composable
fun TagsPreview() {
    Column(verticalArrangement = spacedBy(20.dp)) {
        TagType.entries.forEach {
            Tag(label = "label", type = it)
        }
        Tag(
            label = "Low",
            type = TagType.DEFAULT,
            defaultBackgroundColor = Color(0xFFFADB14).copy(alpha = 0.5f),
            defaultTextColor = Color(0xFFFADB14),
        )
    }
}
