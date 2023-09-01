package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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


@Composable
fun BottomSheetShell(
    title: String,
    subtitle: String,
    description: String,
    searchBar: @Composable (() -> Unit)? = null,
    buttonBlock: @Composable (() -> Unit)? = null,
    content: @Composable (() -> Unit)? = null) {
    Column(
        modifier = Modifier
            .padding(Spacing.Spacing24),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BottomSheetHeader(
            title,
            subtitle,
            description
        )
        searchBar?.invoke()

        Divider(
            color = TextColor.OnDisabledSurface,
            modifier = Modifier.fillMaxWidth().padding(top = Spacing.Spacing8, bottom = Spacing.Spacing8)
        )

        content?.let {
            it.invoke()
            Divider(
                color = TextColor.OnDisabledSurface,
                modifier = Modifier.fillMaxWidth().padding(Spacing.Spacing8)
            )
        }
        buttonBlock?.invoke()
    }
}
