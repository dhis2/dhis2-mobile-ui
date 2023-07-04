package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hisp.dhis.common.components.Dhis2ButtonPreview
import org.hisp.dhis.common.components.Dhis2TextButtonPreview

@Composable
fun ButtonScreen() {
    Column(modifier = Modifier.padding(10.dp)) {
        Dhis2ButtonPreview()
        Dhis2TextButtonPreview()
    }
}
