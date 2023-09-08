package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
actual fun BottomSheetShell(
    title: String,
    subtitle: String?,
    description: String?,
    icon: @Composable (() -> Unit)?,
    searchBar: @Composable (() -> Unit)?,
    buttonBlock: @Composable (() -> Unit)?,
    content: @Composable (() -> Unit)?,
    onDismiss: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Component not implemented")
    }
}
