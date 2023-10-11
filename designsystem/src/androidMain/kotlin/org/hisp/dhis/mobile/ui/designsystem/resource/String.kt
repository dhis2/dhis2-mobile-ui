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

@Composable
actual fun provideQuantityStringResource(id: String, quantity: Int): String {
    val appendToId = when (quantity) {
        1 -> "one"
        else -> "other"
    }
    return provideStringResource("${id}_$appendToId").format(quantity)
}

@Composable
actual fun resourceExists(resourceName: String, resourceType: String): Boolean {
    val context = LocalContext.current
    val resourceId = context.resources.getIdentifier(resourceName, resourceType, context.packageName)
    return resourceId != 0
}
