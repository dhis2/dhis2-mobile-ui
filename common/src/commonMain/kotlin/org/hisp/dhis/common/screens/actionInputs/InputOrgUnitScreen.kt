package org.hisp.dhis.common.screens.actionInputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputOrgUnit
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState

@Composable
fun InputOrgUnitScreen() {
    ColumnScreenContainer(title = ActionInputs.INPUT_ORG_UNIT.label) {
        ColumnComponentContainer("Basic Org. unit ") {
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
        }

        ColumnComponentContainer("Basic Org. unit with content ") {
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
        }

        ColumnComponentContainer("Error Org. unit required field ") {
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
        }

        ColumnComponentContainer("Disabled Org. unit with content ") {
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
}
