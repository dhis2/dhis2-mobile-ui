package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

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
    icon: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
) {
    icon?.let {
        Box(
            Modifier
                .size(Spacing.Spacing18)
                .alpha(if (enabled) 1f else 0.34f),
        ) {
            it.invoke()
        }
        Spacer(Modifier.size(Spacing.Spacing8))
    }
    Text(text, color = textColor, textAlign = TextAlign.Center, style = MaterialTheme.typography.labelLarge)
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
    title: String,
    textColor: Color,
    isRequiredField: Boolean,
    modifier: Modifier = Modifier,
) {
    if (title.isNotEmpty()) {
        val titleText = if (isRequiredField) "$title *" else title
        Text(
            titleText,
            modifier = modifier.padding(vertical = Spacing.Spacing2),
            color = textColor,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Start,
        )
    }
}

@Composable
internal fun LegendDescriptionRangeText(
    text: String,
    textColor: Color = TextColor.OnSurfaceLight,
    modifier: Modifier = Modifier,
) {
    Text(
        text,
        modifier = modifier,
        color = textColor,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Start,
    )
}

@Composable
fun Title(
    text: String,
    textColor: Color = TextColor.OnSurface,
    modifier: Modifier = Modifier,
) {
    Text(
        text,
        modifier = modifier,
        color = textColor,
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Start,
    )
}

@Composable
fun SubTitle(
    text: String,
    textColor: Color = TextColor.OnSurface,
    modifier: Modifier = Modifier,
) {
    Text(
        text,
        modifier = modifier,
        color = textColor,
        style = MaterialTheme.typography.titleSmall,
        textAlign = TextAlign.Start,
    )
}

@Composable
fun Description(
    text: String,
    textColor: Color,
    modifier: Modifier = Modifier,
) {
    Text(
        text,
        modifier = modifier,
        color = textColor,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Start,
    )
}

const val MAX_OVERFLOW_LINES = 2

@Composable
internal fun ListCardTitle(
    title: ListCardTitleModel,
    modifier: Modifier = Modifier,
) {
    val maxLines = if (title.allowOverflow) Int.MAX_VALUE else MAX_OVERFLOW_LINES

    Text(
        title.text,
        color = title.color ?: TextColor.OnPrimaryContainer,
        style = title.style ?: MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier,
    )
}

@Composable
internal fun ListCardDescription(
    description: ListCardDescriptionModel,
    modifier: Modifier = Modifier,
) {
    description.text?.let {
        Text(
            description.text,
            color = description.color ?: TextColor.OnSurface,
            style = description.style ?: MaterialTheme.typography.bodyMedium,
            modifier = modifier,
        )
    }
}

@Composable
internal fun TEIDetailTitle(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text,
        color = TextColor.OnSurface,
        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
        modifier = modifier.padding(bottom = Spacing.Spacing8),
    )
}

@Composable
internal fun ListCardLastUpdated(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text,
        color = TextColor.OnSurfaceLight,
        style = MaterialTheme.typography.bodySmall,
        modifier = modifier,
    )
}

@Composable
internal fun CarouselButtonText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text,
        style = MaterialTheme.typography.labelSmall,
        textAlign = TextAlign.Center,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier,
    )
}
