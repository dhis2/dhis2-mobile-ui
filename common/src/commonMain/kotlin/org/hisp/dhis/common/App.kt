package org.hisp.dhis.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.common.screens.BottomSheetHeaderScreen
import org.hisp.dhis.common.screens.ButtonBlockScreen
import org.hisp.dhis.common.screens.ButtonScreen
import org.hisp.dhis.common.screens.CheckboxScreen
import org.hisp.dhis.common.screens.Components
import org.hisp.dhis.common.screens.FormShellsScreen
import org.hisp.dhis.common.screens.FormsComponentsScreen
import org.hisp.dhis.common.screens.IconButtonScreen
import org.hisp.dhis.common.screens.IconCardsScreen
import org.hisp.dhis.common.screens.InputIntegerScreen
import org.hisp.dhis.common.screens.InputLetterScreen
import org.hisp.dhis.common.screens.InputLongTextScreen
import org.hisp.dhis.common.screens.InputNegativeIntegerScreen
import org.hisp.dhis.common.screens.InputNumberScreen
import org.hisp.dhis.common.screens.InputPercentageScreen
import org.hisp.dhis.common.screens.InputPositiveIntegerOrZeroScreen
import org.hisp.dhis.common.screens.InputPositiveIntegerScreen
import org.hisp.dhis.common.screens.InputScreen
import org.hisp.dhis.common.screens.InputTextScreen
import org.hisp.dhis.common.screens.LegendDescriptionScreen
import org.hisp.dhis.common.screens.LegendScreen
import org.hisp.dhis.common.screens.ProgressScreen
import org.hisp.dhis.common.screens.RadioButtonScreen
import org.hisp.dhis.common.screens.SectionScreen
import org.hisp.dhis.common.screens.SupportingTextScreen
import org.hisp.dhis.common.screens.TagsScreen
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2Theme
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

@Composable
fun App() {
    DHIS2Theme {
        Main()
    }
}

@Composable
fun Main() {
    val currentScreen = remember { mutableStateOf(Components.FORM_SHELLS) }
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(Spacing.Spacing16),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            TextField(
                readOnly = true,
                value = currentScreen.value.label,
                onValueChange = {},
                label = { Text("Components") },
                leadingIcon = {
                    val icon = if (expanded) {
                        Icons.Filled.ArrowDropUp // it requires androidx.compose.material:material-icons-extended
                    } else {
                        Icons.Filled.ArrowDropDown
                    }
                    Icon(
                        icon,
                        "contentDescription",
                        Modifier.clickable { expanded = !expanded },
                    )
                },
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                Components.values().forEach {
                    DropdownMenuItem(
                        text = { Text(it.label) },
                        onClick = {
                            currentScreen.value = it
                            expanded = false
                        },
                    )
                }
            }
        }

        when (currentScreen.value) {
            Components.BUTTON -> ButtonScreen()
            Components.ICON_BUTTON -> IconButtonScreen()
            Components.FORMS_COMPONENTS -> FormsComponentsScreen()
            Components.RADIO -> RadioButtonScreen()
            Components.CHECK_BOX -> CheckboxScreen()
            Components.PROGRESS -> ProgressScreen()
            Components.LEGEND -> LegendScreen()
            Components.INPUT -> InputScreen()
            Components.SUPPORTING_TEXT -> SupportingTextScreen()
            Components.INPUT_TEXT -> InputTextScreen()
            Components.INPUT_LONG_TEXT -> InputLongTextScreen()
            Components.LEGEND_DESCRIPTION -> LegendDescriptionScreen()
            Components.FORM_SHELLS -> FormShellsScreen()
            Components.BUTTON_BLOCK -> ButtonBlockScreen()
            Components.ICON_CARDS -> IconCardsScreen()
            Components.BOTTOM_SHEET_HEADER -> BottomSheetHeaderScreen()
            Components.TAGS -> TagsScreen()
            Components.SECTIONS -> SectionScreen()
            Components.INPUT_POSITIVE_INTEGER_OR_ZERO -> InputPositiveIntegerOrZeroScreen()
            Components.INPUT_POSITIVE_INTEGER -> InputPositiveIntegerScreen()
            Components.INPUT_PERCENTAGE -> InputPercentageScreen()
            Components.INPUT_NEGATIVE_INTEGER -> InputNegativeIntegerScreen()
            Components.INPUT_INTEGER -> InputIntegerScreen()
            Components.INPUT_NUMBER -> InputNumberScreen()
            Components.INPUT_LETTER -> InputLetterScreen()
        }
    }
}
