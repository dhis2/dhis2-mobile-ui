package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hisp.dhis.common.designsystem.component.Dhis2ButtonPreview
import org.hisp.dhis.common.designsystem.component.Dhis2TextButtonPreview
import org.hisp.dhis.common.designsystem.component.SquareIconButtonPreview

@Composable
fun ButtonScreen() {
    Column(modifier = Modifier.padding(10.dp)) {
        Dhis2TextButtonPreview()
        Dhis2ButtonPreview()
        SquareIconButtonPreview()
    }
}
