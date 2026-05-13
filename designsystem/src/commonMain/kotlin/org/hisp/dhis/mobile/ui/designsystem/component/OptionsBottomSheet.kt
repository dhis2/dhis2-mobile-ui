package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.state.BottomSheetShellDefaults
import org.hisp.dhis.mobile.ui.designsystem.component.state.BottomSheetShellUIState

const val MAX_OPTIONS_ITEMS_TO_SHOW = 50

@Composable
fun OptionsBottomSheet(
    items: List<OptionData>,
    title: String,
    maxItemsToShow: Int,
    noResultsFoundString: String,
    searchToFindMoreString: String,
    doneButtonText: String,
    windowInsets: @Composable () -> WindowInsets = { BottomSheetShellDefaults.windowInsets() },
    onDismiss: () -> Unit,
    content: @Composable (List<OptionData>) -> Unit,
) {
    var searchQuery by remember { mutableStateOf("") }
    val filteredOptions =
        items
            .filter { it.textInput!!.contains(searchQuery, ignoreCase = true) }
            .toMutableStateList()

    BottomSheetShell(
        uiState =
            BottomSheetShellUIState(
                title = title,
                searchQuery = searchQuery,
            ),
        modifier = Modifier.testTag("INPUT_OPTIONS_BOTTOM_SHEET"),
        contentScrollState = rememberScrollState(),
        content = {
            if (filteredOptions.take(maxItemsToShow).isNotEmpty()) {
                content(filteredOptions)
                if (items.size > filteredOptions.size && filteredOptions.size > maxItemsToShow) {
                    Text(
                        text = searchToFindMoreString,
                        textAlign = TextAlign.Center,
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                    )
                }
            } else {
                Text(
                    text = noResultsFoundString,
                    textAlign = TextAlign.Center,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                )
            }
        },
        windowInsets = windowInsets,
        buttonBlock = {
            Button(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(BottomSheetShellDefaults.buttonBlockPaddings()),
                onClick = onDismiss,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null,
                    )
                },
                text = doneButtonText,
                style = ButtonStyle.FILLED,
            )
        },
        onSearchQueryChanged = { searchQuery = it },
        onSearch = { searchQuery = it },
        onDismiss = onDismiss,
    )
}

sealed class OptionData(
    open val uid: String,
    open val selected: Boolean,
    open val enabled: Boolean,
    open val textInput: AnnotatedString?,
)
