package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

/**
 * DHIS2 InfoBar.
 * InfoBars provide brief messages about app status.
 * @param modifier: optional modifier.
 * @param text: main text to be displayed within the item.
 * @param textColor: text color.
 * @param backgroundColor: background color.
 * @param icon: the icon to be displayed before the text.
 * @param displayProgress: whether to display a progress indicator on the top-end corner.
 */
@Composable
fun InfoBar(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color,
    backgroundColor: Color,
    displayProgress: Boolean = false,
    icon: @Composable (() -> Unit)? = null,
) {
    BasicInfoBar(
        modifier = modifier,
        text = text,
        textColor = textColor,
        actionText = null,
        backgroundColor = backgroundColor,
        icon = icon,
        displayProgress = displayProgress,
    )
}

/**
 * DHIS2 InfoBar.
 * InfoBars provide brief messages about app status and provides an action button.
 * @param modifier: optional modifier.
 * @param text: main text to be displayed within the item.
 * @param textColor: text color.
 * @param backgroundColor: background color.
 * @param icon: the icon to be displayed before the text.
 * @param actionText: the text to be used for action button.
 * @param onActionClick: callback for the action button.
 */
@Composable
fun InfoBar(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color,
    actionText: String,
    backgroundColor: Color,
    icon: @Composable (() -> Unit)? = null,
    onActionClick: () -> Unit,
) {
    BasicInfoBar(
        modifier = modifier,
        text = text,
        textColor = textColor,
        actionText = actionText,
        backgroundColor = backgroundColor,
        icon = icon,
        onActionClick = onActionClick,
        displayProgress = false,
    )
}

@Composable
private fun BasicInfoBar(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color,
    actionText: String?,
    backgroundColor: Color,
    displayProgress: Boolean,
    icon: @Composable (() -> Unit)? = null,
    onActionClick: () -> Unit = {},
) {
    Row(
        modifier =
            modifier
                .clip(shape = RoundedCornerShape(Radius.XL))
                .background(color = backgroundColor)
                .fillMaxWidth()
                .heightIn(min = InfoBarDefaults.minHeigh),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier =
                Modifier
                    .weight(1f)
                    .padding(Spacing.Spacing8),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = spacedBy(Spacing.Spacing8),
        ) {
            Column(Modifier.align(Alignment.Top)) {
                icon?.invoke()
            }
            Text(
                modifier = Modifier.weight(1f),
                color = textColor,
                text = text,
                style = MaterialTheme.typography.bodyMedium,
            )
        }

        if (displayProgress) {
            Column(
                Modifier
                    .padding(Spacing.Spacing8)
                    .align(Alignment.Top),
            ) {
                ProgressIndicator(
                    modifier = Modifier.size(InfoBarDefaults.progressIndicatorSize),
                    type = ProgressIndicatorType.CIRCULAR_SMALL,
                )
            }
        }

        actionText?.let {
            Button(
                modifier = Modifier.height(InfoBarDefaults.actionButtonHeight),
                style = ButtonStyle.TEXT,
                text = actionText,
                paddingValues =
                    PaddingValues(
                        horizontal = Spacing.Spacing16,
                        vertical = Spacing.Spacing0,
                    ),
                onClick = onActionClick,
            )
        }
    }
}

/**
 * Data model used for DHIS2  [InfoBar] component.
 * @param text: main text to be displayed within the item.
 * @param icon: the icon to be displayed.
 * @param color: text color.
 * @param backgroundColor: background color.
 * @param actionText: the text to be used for action button.
 * @param onClick: callback for the action button.
 */
data class InfoBarData(
    val text: String,
    val icon: @Composable (() -> Unit)? = null,
    val color: Color,
    val backgroundColor: Color,
    val actionText: String? = null,
    val onClick: (() -> Unit)? = null,
)

object InfoBarDefaults {
    val minHeigh = 40.dp
    val progressIndicatorSize = 24.dp
    val actionButtonHeight = 40.dp
}
