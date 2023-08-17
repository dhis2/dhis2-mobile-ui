package org.hisp.dhis.common.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.hisp.dhis.common.screens.previews.ButtonPreview
import org.hisp.dhis.common.screens.previews.ButtonPreviewWithIcon
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.RowComponentContainer

@Composable
fun ButtonScreen() {
    ColumnComponentContainer("Buttons") {
        Text("Filled")
        RowComponentContainer() {
            ButtonPreview("Label", ButtonStyle.FILLED)
            ButtonPreview("Label", ButtonStyle.FILLED, false)
        }
        RowComponentContainer {
            ButtonPreviewWithIcon("Label", ButtonStyle.FILLED)
            ButtonPreviewWithIcon("Label", ButtonStyle.FILLED, false)
        }
        // Outlined
        Text("Outlined")
        RowComponentContainer() {
            ButtonPreview("Label")
            ButtonPreview("Label", ButtonStyle.OUTLINED, false)
        }
        RowComponentContainer() {
            ButtonPreviewWithIcon("Label")
            ButtonPreviewWithIcon("Label", ButtonStyle.OUTLINED, false)
        }
        // Text
        Text("Text")
        RowComponentContainer() {
            ButtonPreview("Label", ButtonStyle.TEXT)
            ButtonPreview("Label", ButtonStyle.TEXT, false)
        }
        RowComponentContainer() {
            ButtonPreviewWithIcon("Label", ButtonStyle.TEXT)
            ButtonPreviewWithIcon("Label", ButtonStyle.TEXT, false)
        }
        Text("Elevated")
        RowComponentContainer() {
            ButtonPreview("Label", ButtonStyle.ELEVATED)
            ButtonPreview("Label", ButtonStyle.ELEVATED, false)
        }
        RowComponentContainer() {
            ButtonPreviewWithIcon("Label", ButtonStyle.ELEVATED)
            ButtonPreviewWithIcon("Label", ButtonStyle.ELEVATED, false)
        }
        Text("Tonal")
        RowComponentContainer() {
            ButtonPreview("Label", ButtonStyle.TONAL)
            ButtonPreview("Label", ButtonStyle.TONAL, false)
        }
        RowComponentContainer() {
            ButtonPreviewWithIcon("Label", ButtonStyle.TONAL)
            ButtonPreviewWithIcon("Label", ButtonStyle.TONAL, false)
        }
        Text("Keyboard")
        RowComponentContainer() {
            ButtonPreview("Label", ButtonStyle.KEYBOARDKEY)
            ButtonPreview("Label", ButtonStyle.KEYBOARDKEY, false)
        }
        RowComponentContainer() {
            ButtonPreviewWithIcon("Label", ButtonStyle.KEYBOARDKEY)
            ButtonPreviewWithIcon("Label", ButtonStyle.KEYBOARDKEY, false)
        }
    }
}
