package org.hisp.dhis.common.screens.buttons

import androidx.compose.runtime.Composable
import org.hisp.dhis.common.screens.previews.ButtonPreview
import org.hisp.dhis.common.screens.previews.ButtonPreviewWithIcon
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ColorStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentItemContainer
import org.hisp.dhis.mobile.ui.designsystem.component.RowComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.Title

@Composable
fun ButtonScreen() {
    ColumnComponentContainer(title = ButtonScreens.BUTTON.label) {
        ColumnComponentItemContainer("Filled") {
            RowComponentContainer {
                ButtonPreview("Label", ButtonStyle.FILLED)
                ButtonPreview("Label", ButtonStyle.FILLED, false)
            }
            RowComponentContainer {
                ButtonPreviewWithIcon("Label", ButtonStyle.FILLED)
                ButtonPreviewWithIcon("Label", ButtonStyle.FILLED, false)
            }
        }

        ColumnComponentItemContainer("Outlined") {
            RowComponentContainer {
                ButtonPreview("Label")
                ButtonPreview("Label", ButtonStyle.OUTLINED, false)
            }
            RowComponentContainer {
                ButtonPreviewWithIcon("Label")
                ButtonPreviewWithIcon("Label", ButtonStyle.OUTLINED, false)
            }
        }

        ColumnComponentItemContainer("Text") {
            RowComponentContainer {
                ButtonPreview("Label", ButtonStyle.TEXT)
                ButtonPreview("Label", ButtonStyle.TEXT, false)
            }
            RowComponentContainer {
                ButtonPreviewWithIcon("Label", ButtonStyle.TEXT)
                ButtonPreviewWithIcon("Label", ButtonStyle.TEXT, false)
            }
        }

        ColumnComponentItemContainer("Elevated") {
            RowComponentContainer {
                ButtonPreview("Label", ButtonStyle.ELEVATED)
                ButtonPreview("Label", ButtonStyle.ELEVATED, false)
            }
            RowComponentContainer {
                ButtonPreviewWithIcon("Label", ButtonStyle.ELEVATED)
                ButtonPreviewWithIcon("Label", ButtonStyle.ELEVATED, false)
            }
        }

        ColumnComponentItemContainer("Tonal") {
            RowComponentContainer {
                ButtonPreview("Label", ButtonStyle.TONAL)
                ButtonPreview("Label", ButtonStyle.TONAL, false)
            }
            RowComponentContainer {
                ButtonPreviewWithIcon("Label", ButtonStyle.TONAL)
                ButtonPreviewWithIcon("Label", ButtonStyle.TONAL, false)
            }
        }

        ColumnComponentItemContainer("Keyboard") {
            RowComponentContainer {
                ButtonPreview("Label", ButtonStyle.KEYBOARDKEY)
                ButtonPreview("Label", ButtonStyle.KEYBOARDKEY, false)
            }
            RowComponentContainer {
                ButtonPreviewWithIcon("Label", ButtonStyle.KEYBOARDKEY)
                ButtonPreviewWithIcon("Label", ButtonStyle.KEYBOARDKEY, false)
            }
        }

        Title("Error Buttons")
        ColumnComponentItemContainer("Filled") {
            RowComponentContainer {
                ButtonPreview("Label", ButtonStyle.FILLED, true, ColorStyle.ERROR)
                ButtonPreview("Label", ButtonStyle.FILLED, false, ColorStyle.ERROR)
            }
            RowComponentContainer {
                ButtonPreviewWithIcon("Label", ButtonStyle.FILLED, true, ColorStyle.ERROR)
                ButtonPreviewWithIcon("Label", ButtonStyle.FILLED, false, ColorStyle.ERROR)
            }
        }

        ColumnComponentItemContainer("Outlined") {
            RowComponentContainer {
                ButtonPreview("Label", ButtonStyle.OUTLINED, true, ColorStyle.ERROR)
                ButtonPreview("Label", ButtonStyle.OUTLINED, false, ColorStyle.ERROR)
            }
            RowComponentContainer {
                ButtonPreviewWithIcon("Label", ButtonStyle.OUTLINED, true, ColorStyle.ERROR)
                ButtonPreviewWithIcon("Label", ButtonStyle.OUTLINED, false, ColorStyle.ERROR)
            }
        }

        ColumnComponentItemContainer("Text") {
            RowComponentContainer {
                ButtonPreview("Label", ButtonStyle.TEXT, true, ColorStyle.ERROR)
                ButtonPreview("Label", ButtonStyle.TEXT, false, ColorStyle.ERROR)
            }
            RowComponentContainer {
                ButtonPreviewWithIcon("Label", ButtonStyle.TEXT, true, ColorStyle.ERROR)
                ButtonPreviewWithIcon("Label", ButtonStyle.TEXT, false, ColorStyle.ERROR)
            }
        }

        Title("Warning Buttons")
        ColumnComponentItemContainer("Filled") {
            RowComponentContainer {
                ButtonPreview("Label", ButtonStyle.FILLED, true, ColorStyle.WARNING)
                ButtonPreview("Label", ButtonStyle.FILLED, false, ColorStyle.WARNING)
            }
            RowComponentContainer {
                ButtonPreviewWithIcon("Label", ButtonStyle.FILLED, true, ColorStyle.WARNING)
                ButtonPreviewWithIcon("Label", ButtonStyle.FILLED, false, ColorStyle.WARNING)
            }
        }

        ColumnComponentItemContainer("Outlined") {
            RowComponentContainer {
                ButtonPreview("Label", ButtonStyle.OUTLINED, true, ColorStyle.WARNING)
                ButtonPreview("Label", ButtonStyle.OUTLINED, false, ColorStyle.WARNING)
            }
            RowComponentContainer {
                ButtonPreviewWithIcon("Label", ButtonStyle.OUTLINED, true, ColorStyle.WARNING)
                ButtonPreviewWithIcon("Label", ButtonStyle.OUTLINED, false, ColorStyle.WARNING)
            }
        }

        ColumnComponentItemContainer("Text") {
            RowComponentContainer {
                ButtonPreview("Label", ButtonStyle.TEXT, true, ColorStyle.WARNING)
                ButtonPreview("Label", ButtonStyle.TEXT, false, ColorStyle.WARNING)
            }
            RowComponentContainer {
                ButtonPreviewWithIcon("Label", ButtonStyle.TEXT, true, ColorStyle.WARNING)
                ButtonPreviewWithIcon("Label", ButtonStyle.TEXT, false, ColorStyle.WARNING)
            }
        }
    }
}
