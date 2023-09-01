package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun BottomSheetHeader(

    title: String,
    subTitle: String? = null,
    description: String? = null,
    icon: @Composable
    (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    val horizontalAlignment = if (icon != null) Alignment.CenterHorizontally else Alignment.Start
    Column(
        modifier = modifier.padding(horizontal = Spacing.Spacing24, vertical = Spacing.Spacing0),
        horizontalAlignment = horizontalAlignment,
    ) {
        icon?. let {
            it.invoke()

            Spacer(Modifier.size(Spacing.Spacing16))
        }

        Text(
            title,
            style = MaterialTheme.typography.headlineSmall,
            color = TextColor.OnPrimaryContainer,
            modifier = Modifier.padding(bottom = Spacing.Spacing4),
        )
        subTitle?.let {
            Text(
                subTitle,
                style = MaterialTheme.typography.bodySmall,
                color = TextColor.OnDisabledSurface,
                modifier = Modifier.padding(bottom = Spacing.Spacing16),
            )
        }

        description?.let {
            Text(
                description,
                style = MaterialTheme.typography.bodyMedium,
                color = TextColor.OnSurfaceLight,
            )
        }
    }
}
