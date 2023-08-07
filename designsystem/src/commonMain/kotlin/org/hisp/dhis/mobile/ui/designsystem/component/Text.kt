package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

/**
 * DHIS2 Text with generic icon slot. Wraps Material 3 [Text]
 * used for the [Button] component
 * @param text The text to display within.
 * @param textColor The color of text to display within.
 * @param icon The icon content is optional.
 * Content will be centered and if there is an Icon
 * required Button component spacing will be applied
 */
@Composable
internal fun ButtonText(
    text: String,
    textColor: Color,
    icon: @Composable
    (() -> Unit)? = null
) {
    icon?.let {
        it.invoke()
        Spacer(Modifier.size(Spacing.Spacing8))
    }
    Text(text, color = textColor, textAlign = TextAlign.Center, style = MaterialTheme.typography.titleSmall)
}

/**
 * DHIS2 Text with generic icon slot. Wraps Material 3 [Text]
 * used for the [InputShell] component
 * @param text The text to display within.
 * @param textColor The color of text to display within.
 * Content will be aligned to the left
 * required Button component spacing will be applied
 */
@Composable
internal fun InputShellLabelText(
    text: String,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Text(text, modifier = modifier, color = textColor, style = MaterialTheme.typography.titleSmall, textAlign = TextAlign.Start)
}