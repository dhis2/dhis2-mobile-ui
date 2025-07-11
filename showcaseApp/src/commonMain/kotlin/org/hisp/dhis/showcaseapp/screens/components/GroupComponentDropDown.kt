package org.hisp.dhis.showcaseapp.screens.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.DropdownItem
import org.hisp.dhis.mobile.ui.designsystem.component.InputDropDown
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputStyle
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

@Composable
fun GroupComponentDropDown(
    modifier: Modifier = Modifier,
    dropdownItems: List<DropdownItem>,
    onItemSelected: (DropdownItem) -> Unit,
    onResetButtonClicked: () -> Unit,
    selectedItem: DropdownItem? = null,
) {
    InputDropDown(
        modifier = modifier.padding(horizontal = Spacing.Spacing16),
        title = "Component",
        itemCount = dropdownItems.size,
        onSearchOption = {},
        fetchItem = { index -> dropdownItems[index] },
        onItemSelected = { _, item -> onItemSelected(item) },
        onResetButtonClicked = onResetButtonClicked,
        selectedItem = selectedItem,
        state = InputShellState.UNFOCUSED,
        useDropDown = false,
        inputStyle =
            InputStyle
                .DataInputStyle()
                .apply { backGroundColor = SurfaceColor.SurfaceBright },
        loadOptions = {
            // no-op
        },
    )
}
