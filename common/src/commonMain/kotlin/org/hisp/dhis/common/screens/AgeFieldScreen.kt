package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.hisp.dhis.mobile.ui.designsystem.component.AgeFieldHelper
import org.hisp.dhis.mobile.ui.designsystem.component.AgeFieldHelperValues
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.Orientation
import org.hisp.dhis.mobile.ui.designsystem.component.RadioButtonData
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle

@Composable
fun InputAgeScreen() {
    ColumnComponentContainer("Age Field components") {
        SubTitle("Horizontal Age Field Helper")
        var selectedFieldHorizontal by remember {
            mutableStateOf(RadioButtonData("0", selected = true, enabled = true, textInput = AgeFieldHelperValues.YEARS.value))
        }
        AgeFieldHelper(Orientation.HORIZONTAL, AgeFieldHelperValues.YEARS.value) {
            selectedFieldHorizontal = it
        }
    }
}
