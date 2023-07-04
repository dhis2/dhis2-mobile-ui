package org.hisp.dhis.common.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Dhis2Button(
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick
    ) {
        leadingIcon?.let {
            it.invoke()
            Spacer(modifier = Modifier.size(8.dp))
        }
        Text(
            text = text,
            fontSize = 12.sp
        )
    }
}

@Composable
fun Dhis2ButtonPreview() {
    Dhis2Button(
        leadingIcon = {
            Icon(imageVector = Icons.Default.Add, contentDescription = "")
        },
        text = "Dhis2ButtonPreview"
    ) {}
}
