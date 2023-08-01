package org.hisp.dhis.common.designsystem.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Dhis2TextButton(
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    TextButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled
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
internal fun Dhis2TextButtonPreview() {
    Dhis2TextButton(
        leadingIcon = {
            Icon(imageVector = Icons.Default.Add, contentDescription = "")
        },
        text = "Dhis2TextButtonPreview"
    ) {}
}
