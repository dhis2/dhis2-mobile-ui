package org.hisp.dhis.mobile.ui.designsystem.resource

import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.designsystem.generated.resources.Res
import org.hisp.dhis.mobile.designsystem.generated.resources.allDrawableResources
import org.hisp.dhis.mobile.designsystem.generated.resources.allStringResources
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun provideStringResource(resourceName: String): String =
    if (resourceExists(resourceName, ResourceType.STRING)) {
        stringResource(Res.allStringResources[resourceName]!!)
    } else {
        "Key not found"
    }

@OptIn(ExperimentalResourceApi::class)
@Composable
fun provideQuantityStringResource(
    resourceName: String,
    quantity: Int,
): String {
    val formattedName =
        when (quantity) {
            1 -> "${resourceName}_one"
            else -> "${resourceName}_other"
        }
    return if (resourceExists(formattedName, ResourceType.STRING)) {
        stringResource(Res.allStringResources[formattedName]!!, quantity)
    } else {
        "Key not found"
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun resourceExists(
    resourceName: String,
    resourceType: ResourceType = ResourceType.DRAWABLE,
): Boolean =
    when (resourceType) {
        ResourceType.DRAWABLE -> Res.allDrawableResources.containsKey(resourceName)
        ResourceType.STRING -> Res.allStringResources.containsKey(resourceName)
    }

enum class ResourceType {
    DRAWABLE,
    STRING,
}
