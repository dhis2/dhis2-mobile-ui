package org.hisp.dhis.common.screens.previews

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.Tag
import org.hisp.dhis.mobile.ui.designsystem.component.TagType

@Composable
fun TagsPreview() {
    Column(verticalArrangement = spacedBy(20.dp)) {
        TagType.values().forEach {
            Tag(label = "label", type = it)
        }
    }
}
