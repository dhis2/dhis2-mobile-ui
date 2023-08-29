package org.hisp.dhis.common.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.hisp.dhis.common.screens.previews.ButtonPreview
import org.hisp.dhis.common.screens.previews.ButtonPreviewWithIcon
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.RowComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.Title
import org.hisp.dhis.mobile.ui.designsystem.component.TextButtonSelector
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource

@Composable
fun ButtonScreen() {
    ColumnComponentContainer() {
        Text("TextButtonSelectors")
        TextButtonSelector(
            firstOptionText = provideStringResource("date_birth"),
            onClickFirstOption = {},
            middleText = provideStringResource("or"),
            secondOptionText = provideStringResource("age"),
            onClickSecondOption = {},
        )
        TextButtonSelector(
            enabled = false,
            firstOptionText = provideStringResource("date_birth"),
            onClickFirstOption = {},
            middleText = provideStringResource("or"),
            secondOptionText = provideStringResource("age"),
            onClickSecondOption = {},
        )
        Title("Buttons")
        SubTitle("Filled")
        RowComponentContainer() {
            ButtonPreview("Label", ButtonStyle.FILLED)
            ButtonPreview("Label", ButtonStyle.FILLED, false)
        }
        RowComponentContainer {
            ButtonPreviewWithIcon("Label", ButtonStyle.FILLED)
            ButtonPreviewWithIcon("Label", ButtonStyle.FILLED, false)
        }
        SubTitle("Outlined")
        RowComponentContainer() {
            ButtonPreview("Label")
            ButtonPreview("Label", ButtonStyle.OUTLINED, false)
        }
        RowComponentContainer() {
            ButtonPreviewWithIcon("Label")
            ButtonPreviewWithIcon("Label", ButtonStyle.OUTLINED, false)
        }
        SubTitle("Text")
        RowComponentContainer() {
            ButtonPreview("Label", ButtonStyle.TEXT)
            ButtonPreview("Label", ButtonStyle.TEXT, false)
        }
        RowComponentContainer() {
            ButtonPreviewWithIcon("Label", ButtonStyle.TEXT)
            ButtonPreviewWithIcon("Label", ButtonStyle.TEXT, false)
        }
        SubTitle("Elevated")
        RowComponentContainer() {
            ButtonPreview("Label", ButtonStyle.ELEVATED)
            ButtonPreview("Label", ButtonStyle.ELEVATED, false)
        }
        RowComponentContainer() {
            ButtonPreviewWithIcon("Label", ButtonStyle.ELEVATED)
            ButtonPreviewWithIcon("Label", ButtonStyle.ELEVATED, false)
        }
        SubTitle("Tonal")
        RowComponentContainer() {
            ButtonPreview("Label", ButtonStyle.TONAL)
            ButtonPreview("Label", ButtonStyle.TONAL, false)
        }
        RowComponentContainer() {
            ButtonPreviewWithIcon("Label", ButtonStyle.TONAL)
            ButtonPreviewWithIcon("Label", ButtonStyle.TONAL, false)
        }
        SubTitle("Keyboard")
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
