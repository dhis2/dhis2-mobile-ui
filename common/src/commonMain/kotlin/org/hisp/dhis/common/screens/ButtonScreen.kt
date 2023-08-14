package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hisp.dhis.common.screens.previews.ButtonPreview
import org.hisp.dhis.common.screens.previews.ButtonPreviewWithIcon
import org.hisp.dhis.mobileui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobileui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobileui.designsystem.component.RowComponentContainer
import org.hisp.dhis.mobileui.designsystem.theme.Spacing

@Composable
fun ButtonScreen() {
    Column(modifier = Modifier.padding(10.dp).verticalScroll(rememberScrollState())) {
        // Filled
        ColumnComponentContainer(
            title = "Filled",
            content = {
                RowComponentContainer() {
                    ButtonPreview("Label", ButtonStyle.FILLED)
                    ButtonPreview("Label", ButtonStyle.FILLED, false)
                }
                RowComponentContainer {
                    ButtonPreviewWithIcon("Label", ButtonStyle.FILLED)
                    ButtonPreviewWithIcon("Label", ButtonStyle.FILLED, false)
                }
            }
        )

        // Outlined
        ColumnComponentContainer(
            title = "Outlined",
            content = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(Spacing.Spacing10)
                ) {
                    ButtonPreview("Label")
                    ButtonPreview("Label", ButtonStyle.OUTLINED, false)
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(Spacing.Spacing10)
                ) {
                    ButtonPreviewWithIcon("Label")
                    ButtonPreviewWithIcon("Label", ButtonStyle.OUTLINED, false)
                }
            }
        )
        // Text
        ColumnComponentContainer(
            title = "Text",
            content = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(Spacing.Spacing10)
                ) {
                    ButtonPreview("Label", ButtonStyle.TEXT)
                    ButtonPreview("Label", ButtonStyle.TEXT, false)
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(Spacing.Spacing10)
                ) {
                    ButtonPreviewWithIcon("Label", ButtonStyle.TEXT)
                    ButtonPreviewWithIcon("Label", ButtonStyle.TEXT, false)
                }
            }
        )
        ColumnComponentContainer(
            title = "Elevated",
            content = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(Spacing.Spacing10)
                ) {
                    ButtonPreview("Label", ButtonStyle.ELEVATED)
                    ButtonPreview("Label", ButtonStyle.ELEVATED, false)
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(Spacing.Spacing10)
                ) {
                    ButtonPreviewWithIcon("Label", ButtonStyle.ELEVATED)
                    ButtonPreviewWithIcon("Label", ButtonStyle.ELEVATED, false)
                }
            }
        )
        Text("Tonal")

        Row(
            horizontalArrangement = Arrangement.spacedBy(Spacing.Spacing10)
        ) {
            ButtonPreview("Label", ButtonStyle.TONAL)
            ButtonPreview("Label", ButtonStyle.TONAL, false)
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(Spacing.Spacing10)
        ) {
            ButtonPreviewWithIcon("Label", ButtonStyle.TONAL)
            ButtonPreviewWithIcon("Label", ButtonStyle.TONAL, false)
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(Spacing.Spacing10)
        ) {
            ButtonPreview("Label", ButtonStyle.KEYBOARDKEY)
            ButtonPreview("Label", ButtonStyle.KEYBOARDKEY, false)
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(Spacing.Spacing10)
        ) {
            ButtonPreviewWithIcon("Label", ButtonStyle.KEYBOARDKEY)
            ButtonPreviewWithIcon("Label", ButtonStyle.KEYBOARDKEY, false)
        }
    }
}
