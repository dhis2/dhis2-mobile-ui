package org.hisp.dhis.mobile.ui.designsystem.resource

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.useResource

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
    // for translation we could use Locale.current.language to find the proper xml
    useResource("values/strings_en.xml") { inputStream ->

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
    return stringsResources
}

@Composable
actual fun resourceExists(resourceName: String, resourceType: String): Boolean {
    return false
}
