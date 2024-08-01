package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.mobile.ui.designsystem.theme.Shape
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

enum class TagType {
    ERROR,
    WARNING,
    DEFAULT,
}

/**
 * DHIS2 Tag
 * Short labels or identifiers used to categorize
 * and organize elements, making them easier
 * to locate and understand.
 * @param modifier: optional modifier.
 * @param label: text for label.
 * @param type: [TagType] can be [TagType.DEFAULT],
 * [TagType.ERROR] or [TagType.WARNING],
 */
@Composable
fun Tag(
    modifier: Modifier = Modifier,
    label: String,
    type: TagType,
    defaultBackgroundColor: Color = SurfaceColor.PrimaryContainer,
    defaultTextColor: Color = TextColor.OnPrimaryContainer,
) {
    Box(
        modifier = modifier
            .wrapContentSize()
            .background(
                color = when (type) {
                    TagType.ERROR -> SurfaceColor.ErrorContainer
                    TagType.WARNING -> SurfaceColor.WarningContainer
                    TagType.DEFAULT -> defaultBackgroundColor
                },
                shape = Shape.ExtraSmall,
            ).padding(horizontal = Spacing.Spacing8),
    ) {
        Text(
            modifier = Modifier.wrapContentSize(),
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = when (type) {
                TagType.ERROR -> TextColor.OnErrorContainer
                TagType.WARNING -> TextColor.OnWarningContainer
                TagType.DEFAULT -> defaultTextColor
            },
        )
    }
}
