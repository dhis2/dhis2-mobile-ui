package org.hisp.dhis.common

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.hisp.dhis.common.screens.ButtonScreen
import org.hisp.dhis.common.screens.Components
import org.hisp.dhis.common.screens.FormsComponentsScreen
import org.hisp.dhis.common.screens.IconButtonScreen
import org.hisp.dhis.common.screens.radio.RadioButtonScreen
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2Theme
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

@Composable
fun App() {
    DHIS2Theme {
        Main()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main() {
    val currentScreen = remember { mutableStateOf(Components.FORMS_COMPONENTS) }
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(currentScreen.value.name) }

    Column(modifier = Modifier.padding(Spacing.Spacing16)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            TextField(
                readOnly = true,
                value = selectedOptionText,
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
                        Modifier.clickable { expanded = !expanded }
                    )
                }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Button") },
                    onClick = {
                        currentScreen.value = Components.BUTTON
                        selectedOptionText = currentScreen.value.name
                        expanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Icon Button") },
                    onClick = {
                        currentScreen.value = Components.ICON_BUTTON
                        selectedOptionText = currentScreen.value.name
                        expanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Forms Components") },
                    onClick = {
                        currentScreen.value = Components.FORMS_COMPONENTS
                        selectedOptionText = currentScreen.value.name
                        expanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Radio") },
                    onClick = {
                        currentScreen.value = Components.RADIO
                        selectedOptionText = currentScreen.value.name
                        expanded = false
                    }
                )
            }
        }

        when (currentScreen.value) {
            Components.BUTTON -> ButtonScreen()
            Components.ICON_BUTTON -> IconButtonScreen()
            Components.FORMS_COMPONENTS -> FormsComponentsScreen()
            Components.RADIO -> RadioButtonScreen()
        }
    }
}
