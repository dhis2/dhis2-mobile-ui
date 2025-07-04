package org.hisp.dhis.showcaseapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.Title
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun NoComponentSelectedScreen(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
) {
    ColumnScreenContainer(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.size(Spacing.Spacing120))
        Icon(
            modifier = Modifier.size(Spacing.Spacing48),
            imageVector = Icons.Outlined.Category,
            tint = SurfaceColor.ContainerHighest,
            contentDescription = "Please choose an option",
        )
        Spacer(Modifier.size(Spacing.Spacing16))

        Title("Please select a component", textColor = TextColor.OnSurfaceLight)

        if (onClick != null) {
            Spacer(Modifier.size(Spacing.Spacing24))

            Button(
                enabled = true,
                style = ButtonStyle.KEYBOARDKEY,
                text = "Select component",
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Category,
                        contentDescription = "Please choose an option",
                    )
                },
                onClick = onClick,
            )
        }
    }
}
