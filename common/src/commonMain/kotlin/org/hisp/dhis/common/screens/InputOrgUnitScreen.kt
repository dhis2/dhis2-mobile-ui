package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputOrgUnit
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState
import org.hisp.dhis.mobile.ui.designsystem.component.Title
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun InputOrgUnitScreen() {
    ColumnComponentContainer {
        Title("Input Org. unit component", textColor = TextColor.OnSurfaceVariant)
        SubTitle("Basic Org. unit ", textColor = TextColor.OnSurfaceVariant)
        var inputText1 by rememberSaveable { mutableStateOf("") }

        InputOrgUnit(
            title = "Label",
            inputText = inputText1,
            onValueChanged = {
                if (it != null) {
                    inputText1 = it
                }
            },
            onOrgUnitActionCLicked = {},
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Basic Org. unit with content ", textColor = TextColor.OnSurfaceVariant)
        var inputText2 by rememberSaveable { mutableStateOf("PHC Fakename") }

        InputOrgUnit(
            title = "Label",
            inputText = inputText2,
            onValueChanged = {
                if (it != null) {
                    inputText2 = it
                }
            },
            onOrgUnitActionCLicked = {},
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Error Org. unit required field ", textColor = TextColor.OnSurfaceVariant)
        var inputText3 by rememberSaveable { mutableStateOf("") }

        InputOrgUnit(
            title = "Label",
            state = InputShellState.ERROR,
            supportingText = listOf(SupportingTextData("Required", SupportingTextState.ERROR)),
            inputText = inputText3,
            isRequiredField = true,
            onValueChanged = {
                if (it != null) {
                    inputText3 = it
                }
            },
            onOrgUnitActionCLicked = {},
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Disabled Org. unit with content ", textColor = TextColor.OnSurfaceVariant)
        var inputText5 by rememberSaveable { mutableStateOf("PHC Fakename") }
        InputOrgUnit(
            title = "Label",
            state = InputShellState.DISABLED,
            inputText = inputText5,
            onValueChanged = {
                if (it != null) {
                    inputText5 = it
                }
            },
            onOrgUnitActionCLicked = {},
        )
    }
}
