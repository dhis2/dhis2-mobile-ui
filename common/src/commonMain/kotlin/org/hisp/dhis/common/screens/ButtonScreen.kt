package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.hisp.dhis.common.designsystem.component.Dhis2ButtonPreview
import org.hisp.dhis.common.designsystem.component.Dhis2TextButtonPreview
import org.hisp.dhis.common.designsystem.component.IconButtonPreview
import org.hisp.dhis.common.designsystem.component.SquareIconButtonPreview

@Composable
fun ButtonScreen() {
    Column(modifier = Modifier.padding(10.dp)) {
        Dhis2TextButtonPreview()
        Dhis2ButtonPreview()
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = "SquareIconButton", fontWeight = FontWeight.Bold)
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                SquareIconButtonPreview()
                SquareIconButtonPreview(false)
            }
        }

        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = "IconButton", fontWeight = FontWeight.Bold)
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                IconButtonPreview()
                IconButtonPreview(false)
            }
        }
    }
}
