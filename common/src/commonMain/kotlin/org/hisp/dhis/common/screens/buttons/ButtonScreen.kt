package org.hisp.dhis.common.screens.buttons

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hisp.dhis.common.screens.previews.ButtonPreview
import org.hisp.dhis.common.screens.previews.ButtonPreviewWithIcon
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ColorStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.RowComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.Title
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

@Composable
fun ButtonScreen() {
    ColumnComponentContainer() {
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

        Spacer(Modifier.size(Spacing.Spacing18))
        SubTitle("Outlined")
        RowComponentContainer() {
            ButtonPreview("Label")
            ButtonPreview("Label", ButtonStyle.OUTLINED, false)
        }
        RowComponentContainer() {
            ButtonPreviewWithIcon("Label")
            ButtonPreviewWithIcon("Label", ButtonStyle.OUTLINED, false)
        }
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Text")
        RowComponentContainer() {
            ButtonPreview("Label", ButtonStyle.TEXT)
            ButtonPreview("Label", ButtonStyle.TEXT, false)
        }
        RowComponentContainer() {
            ButtonPreviewWithIcon("Label", ButtonStyle.TEXT)
            ButtonPreviewWithIcon("Label", ButtonStyle.TEXT, false)
        }
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Elevated")
        RowComponentContainer() {
            ButtonPreview("Label", ButtonStyle.ELEVATED)
            ButtonPreview("Label", ButtonStyle.ELEVATED, false)
        }
        RowComponentContainer() {
            ButtonPreviewWithIcon("Label", ButtonStyle.ELEVATED)
            ButtonPreviewWithIcon("Label", ButtonStyle.ELEVATED, false)
        }
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Tonal")
        RowComponentContainer() {
            ButtonPreview("Label", ButtonStyle.TONAL)
            ButtonPreview("Label", ButtonStyle.TONAL, false)
        }
        RowComponentContainer() {
            ButtonPreviewWithIcon("Label", ButtonStyle.TONAL)
            ButtonPreviewWithIcon("Label", ButtonStyle.TONAL, false)
        }
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Keyboard")
        RowComponentContainer() {
            ButtonPreview("Label", ButtonStyle.KEYBOARDKEY)
            ButtonPreview("Label", ButtonStyle.KEYBOARDKEY, false)
        }
        RowComponentContainer() {
            ButtonPreviewWithIcon("Label", ButtonStyle.KEYBOARDKEY)
            ButtonPreviewWithIcon("Label", ButtonStyle.KEYBOARDKEY, false)
        }

        Title("Error Buttons")
        SubTitle("Filled")
        RowComponentContainer() {
            ButtonPreview("Label", ButtonStyle.FILLED, true, ColorStyle.ERROR)
            ButtonPreview("Label", ButtonStyle.FILLED, false, ColorStyle.ERROR)
        }
        RowComponentContainer {
            ButtonPreviewWithIcon("Label", ButtonStyle.FILLED, true, ColorStyle.ERROR)
            ButtonPreviewWithIcon("Label", ButtonStyle.FILLED, false, ColorStyle.ERROR)
        }

        Spacer(Modifier.size(Spacing.Spacing18))
        SubTitle("Outlined")
        RowComponentContainer() {
            ButtonPreview("Label", ButtonStyle.OUTLINED, true, ColorStyle.ERROR)
            ButtonPreview("Label", ButtonStyle.OUTLINED, false, ColorStyle.ERROR)
        }
        RowComponentContainer() {
            ButtonPreviewWithIcon("Label", ButtonStyle.OUTLINED, true, ColorStyle.ERROR)
            ButtonPreviewWithIcon("Label", ButtonStyle.OUTLINED, false, ColorStyle.ERROR)
        }
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Text")
        RowComponentContainer() {
            ButtonPreview("Label", ButtonStyle.TEXT, true, ColorStyle.ERROR)
            ButtonPreview("Label", ButtonStyle.TEXT, false, ColorStyle.ERROR)
        }
        RowComponentContainer() {
            ButtonPreviewWithIcon("Label", ButtonStyle.TEXT, true, ColorStyle.ERROR)
            ButtonPreviewWithIcon("Label", ButtonStyle.TEXT, false, ColorStyle.ERROR)
        }
        Spacer(Modifier.size(Spacing.Spacing18))

        Title("Warning Buttons")
        SubTitle("Filled")
        RowComponentContainer() {
            ButtonPreview("Label", ButtonStyle.FILLED, true, ColorStyle.WARNING)
            ButtonPreview("Label", ButtonStyle.FILLED, false, ColorStyle.WARNING)
        }
        RowComponentContainer {
            ButtonPreviewWithIcon("Label", ButtonStyle.FILLED, true, ColorStyle.WARNING)
            ButtonPreviewWithIcon("Label", ButtonStyle.FILLED, false, ColorStyle.WARNING)
        }

        Spacer(Modifier.size(Spacing.Spacing18))
        SubTitle("Outlined")
        RowComponentContainer() {
            ButtonPreview("Label", ButtonStyle.OUTLINED, true, ColorStyle.WARNING)
            ButtonPreview("Label", ButtonStyle.OUTLINED, false, ColorStyle.WARNING)
        }
        RowComponentContainer() {
            ButtonPreviewWithIcon("Label", ButtonStyle.OUTLINED, true, ColorStyle.WARNING)
            ButtonPreviewWithIcon("Label", ButtonStyle.OUTLINED, false, ColorStyle.WARNING)
        }
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Text")
        RowComponentContainer() {
            ButtonPreview("Label", ButtonStyle.TEXT, true, ColorStyle.WARNING)
            ButtonPreview("Label", ButtonStyle.TEXT, false, ColorStyle.WARNING)
        }
        RowComponentContainer() {
            ButtonPreviewWithIcon("Label", ButtonStyle.TEXT, true, ColorStyle.WARNING)
            ButtonPreviewWithIcon("Label", ButtonStyle.TEXT, false, ColorStyle.WARNING)
        }
    }
}
