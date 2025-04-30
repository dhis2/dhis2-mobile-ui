package org.hisp.dhis.mobile.ui.designsystem.component.state

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import org.hisp.dhis.mobile.ui.designsystem.theme.InternalSizeValues
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing.Spacing0
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing.Spacing16
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing.Spacing24

/**
 * Data class representing the UI state for the BottomSheetShell component.
 *
 * @property title The title to be displayed in the bottom sheet header.
 * @property subtitle The subtitle to be displayed in the bottom sheet header.
 * @property description The description to be displayed in the bottom sheet header.
 * @property searchQuery The search query to be displayed in the search bar.
 * @property showTopSectionDivider Whether to show the top section divider.
 * @property showBottomSectionDivider Whether to show the bottom section divider.
 * @property bottomPadding The lower padding for the bottom sheet shell.
 * @property headerTextAlignment The alignment for the header text.
 * @property scrollableContainerMinHeight The minimum height for the scrollable content container.
 * @property scrollableContainerMaxHeight The maximum height for the scrollable content container.
 * @property animateHeaderOnKeyboardAppearance Whether to animate the header when the keyboard appears.
 * @property contentPadding The padding values for the content.
 */

data class BottomSheetShellUIState(
    val title: String? = null,
    val subtitle: String? = null,
    val description: String? = null,
    val searchQuery: String? = null,
    val showTopSectionDivider: Boolean = true,
    val showBottomSectionDivider: Boolean = true,
    val bottomPadding: Dp = Spacing0,
    val headerTextAlignment: TextAlign = TextAlign.Center,
    val scrollableContainerMinHeight: Dp = Spacing0,
    val scrollableContainerMaxHeight: Dp = InternalSizeValues.Size386,
    val animateHeaderOnKeyboardAppearance: Boolean = true,
    val contentPadding: PaddingValues = PaddingValues(horizontal = Spacing24),
)

/**
 * Provides default values and configurations for the BottomSheet component.
 */
class BottomSheetShellDefaults {

    companion object {
        /**
         * Returns the default padding values for the button block in the BottomSheet.
         *
         * @return PaddingValues with top, bottom, start, and end padding.
         */
        fun buttonBlockPaddings(): PaddingValues {
            return PaddingValues(top = Spacing0, bottom = Spacing24, start = Spacing24, end = Spacing24)
        }

        /**
         * Returns the appropriate window insets for the BottomSheet based on whether edge-to-edge mode is enabled.
         *
         * @param isEdgeToEdgeEnabled Boolean indicating if edge-to-edge mode is enabled.
         * @return WindowInsets with appropriate values based on the edge-to-edge mode.
         */
        @Composable
        @OptIn(ExperimentalMaterial3Api::class)
        fun windowInsets(isEdgeToEdgeEnabled: Boolean): WindowInsets {
            return if (isEdgeToEdgeEnabled) WindowInsets(0, 0, 0, 0) else { BottomSheetDefaults.windowInsets }
        }

        /**
         * Returns the appropriate lower padding for the BottomSheet based on whether edge-to-edge mode is enabled.
         *
         * @param isEdgeToEdgeEnabled Boolean indicating if edge-to-edge mode is enabled.
         * @return a dp value based on the edge-to-edge mode.
         */
        fun lowerPadding(isEdgeToEdgeEnabled: Boolean): Dp {
            return if (isEdgeToEdgeEnabled) Spacing16 else Spacing0
        }
    }
}
