package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputYesNoField
import org.hisp.dhis.mobile.ui.designsystem.component.InputYesNoFieldValues
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

@Composable
fun InputYesNoFieldScreen() {
    var selectedItem by remember {
        mutableStateOf<InputYesNoFieldValues?>(null)
    }

    var selectedItem1 by remember {
        mutableStateOf<InputYesNoFieldValues?>(InputYesNoFieldValues.YES)
    }

    var selectedItemError by remember {
        mutableStateOf<InputYesNoFieldValues?>(null)
    }

    ColumnComponentContainer("Yes/No Field") {
        InputYesNoField(
            title = "Label",
            itemSelected = selectedItem,
            onItemChange = {
                selectedItem = it
            },
            state = InputShellState.UNFOCUSED,
        )
        Spacer(Modifier.size(Spacing.Spacing18))
        InputYesNoField(
            title = "Label",
            itemSelected = selectedItem1,
            onItemChange = {
                selectedItem1 = it
            },
            state = InputShellState.UNFOCUSED,
        )
        Spacer(Modifier.size(Spacing.Spacing18))
        InputYesNoField(
            title = "Label",
            state = InputShellState.ERROR,
            supportingText = listOf(SupportingTextData("Error text", SupportingTextState.ERROR)),
            itemSelected = selectedItemError,
            onItemChange = {
                selectedItemError = it
            },
        )
        Spacer(Modifier.size(Spacing.Spacing18))
        InputYesNoField(
            title = "Label",
            state = InputShellState.DISABLED,
            onItemChange = {
            },
        )
    }
}
