package org.hisp.dhis.mobile.ui.designsystem.resource

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.useResource
import androidx.compose.ui.text.intl.Locale

@Composable
actual fun provideStringResource(id: String): String {
    val res by remember { mutableStateOf(getResources()) }
    return res[id] ?: "Key not found"
}

@Composable
actual fun provideQuantityStringResource(id: String, quantity: Int): String {
    val appendToId = when (quantity) {
        1 -> "one"
        else -> "other"
    }
    return provideStringResource("${id}_$appendToId").format(quantity)
}

private fun getResources(): Map<String, String> {
    val stringsResources = mutableMapOf<String, String>()
    val localePath = "values-${Locale.current.language}/strings.xml"
    val defaultPath = "values/strings.xml"

    try {
        useResource(localePath) { inputStream ->

            val regex = Regex("""<string name="(.+?)">(.+?)</string>""")

            inputStream.bufferedReader().useLines { lines ->
                lines.forEach { line ->
                    val matchResult = regex.find(line)
                    if (matchResult != null) {
                        val key = matchResult.groupValues[1]
                        val value = matchResult.groupValues[2]
                        stringsResources[key] = value
                    }
                }
            }
        }
    } catch (e: IllegalArgumentException) {
        useResource(defaultPath) { inputStream ->
            val regex = Regex("""<string name="(.+?)">(.+?)</string>""")

            inputStream.bufferedReader().useLines { lines ->
                lines.forEach { line ->
                    val matchResult = regex.find(line)
                    if (matchResult != null) {
                        val key = matchResult.groupValues[1]
                        val value = matchResult.groupValues[2]
                        stringsResources[key] = value
                    }
                }
            }
        }
    }
    return stringsResources
}

@Composable
actual fun resourceExists(resourceName: String, resourceType: String): Boolean {
    return try {
        ClassLoader.getSystemResource("$resourceType/$resourceName.xml") != null
    } catch (e: Exception) {
        false
    }
}
