package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobileui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobileui.designsystem.theme.Spacing

@Composable
fun ButtonScreen() {
    Column(modifier = Modifier.padding(10.dp)) {
        // Filled
        ComponentContainer(
            title = "Filled",
            content = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(Spacing.Spacing10)
                ) {
                    ButtonPreview("Label", ButtonStyle.FILLED)
                    ButtonPreview("Label", ButtonStyle.FILLED, false)
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(Spacing.Spacing10)
                ) {
                    ButtonPreviewWithIcon("Label", ButtonStyle.FILLED)
                    ButtonPreviewWithIcon("Label", ButtonStyle.FILLED, false)
                }
            }
        )

        // Outlined
        ComponentContainer(
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
        ComponentContainer(
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
        ComponentContainer(
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
        ComponentContainer(
            title = "Tonal",
            content = {
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
            }
        )
        ComponentContainer(
            title = "KeyboardKey",
            content = {
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
        )
    }
}

// TODO refactor with extracted class when Radio Buttons issue branch has been merged
@Composable
fun ComponentContainer(
    title: String,
    content: @Composable (() -> Unit)
) {
    Column(modifier = Modifier.padding(10.dp)) {
        Text(text = title, fontWeight = FontWeight.Bold)
        content()
    }
}
