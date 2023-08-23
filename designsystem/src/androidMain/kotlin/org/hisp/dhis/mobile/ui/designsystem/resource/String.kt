package org.hisp.dhis.mobile.ui.designsystem.resource

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun provideStringResource(id: String): String {
    val context = LocalContext.current
    val resourceId = context.resources.getIdentifier(id, "string", context.packageName)
    if (resourceId == 0) return id
    return context.getString(resourceId)
}
