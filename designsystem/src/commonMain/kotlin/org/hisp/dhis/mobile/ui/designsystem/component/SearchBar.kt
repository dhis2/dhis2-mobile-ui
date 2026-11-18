package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import org.hisp.dhis.mobile.designsystem.generated.resources.Res
import org.hisp.dhis.mobile.designsystem.generated.resources.clear_search
import org.hisp.dhis.mobile.designsystem.generated.resources.search
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2Theme
import org.hisp.dhis.mobile.ui.designsystem.theme.Shape
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.jetbrains.compose.resources.stringResource

/**
 * DHIS2 Search bar.
 * @param text: the label to display.
 * @param placeHolderText: the placeholder to display.
 * @param onActiveChange: callback to when value is modified.
 * @param onSearch: on search callback.
 * @param onQueryChange: on query change callback.
 * @param state: input shell state.
 * @param modifier: optional modifier.
 * @param leadingIcon: optional leading icon to display.
 * @param focusRequester: optional focus requester.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    text: String = "",
    placeHolderText: String = provideStringResource("search"),
    onActiveChange: (Boolean) -> Unit = {},
    onSearch: (String) -> Unit = {},
    onQueryChange: (String) -> Unit = {},
    state: InputShellState = InputShellState.FOCUSED,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    focusRequester: FocusRequester = remember { FocusRequester() },
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val searchDescription = stringResource(Res.string.search)
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val containerColor =
        if (!isPressed) {
            DHIS2Theme.colorScheme.surfaceContainerLow
        } else {
            DHIS2Theme.colorScheme.surfaceContainer
        }
    val cursorColor =
        if (state == InputShellState.UNFOCUSED || state == InputShellState.FOCUSED) {
            InputShellState.FOCUSED.themedColor
        } else {
            state.themedColor
        }
    BasicTextField(
        value = text,
        onValueChange = onQueryChange,
        modifier =
            modifier
                .testTag("SEARCH_INPUT")
                .height(SearchBarDefaults.InputFieldHeight)
                .fillMaxWidth()
                .background(containerColor, Shape.Full)
                .clip(Shape.Full)
                .focusRequester(focusRequester)
                .clickable(
                    onClick = {},
                    role = Role.Button,
                    interactionSource = interactionSource,
                    indication =
                        ripple(
                            true,
                            color = DHIS2Theme.colorScheme.primary,
                        ),
                ).onFocusChanged { if (it.isFocused) onActiveChange(true) }
                .onKeyEvent {
                    if (it.key == Key.Escape && it.type == KeyEventType.KeyDown) {
                        onActiveChange(false)
                        true
                    } else {
                        false
                    }
                }.padding(horizontal = Spacing.Spacing4)
                .semantics {
                    contentDescription = searchDescription
                },
        enabled = true,
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions =
            KeyboardActions(onSearch = {
                keyboardController?.hide()
                focusManager.clearFocus()
                onSearch(text)
            }),
        interactionSource = interactionSource,
        textStyle = MaterialTheme.typography.bodyLarge,
        decorationBox = @Composable { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = text,
                innerTextField = innerTextField,
                enabled = true,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                placeholder = {
                    Text(
                        text = placeHolderText,
                        color = DHIS2Theme.extendedColors.onDisabledSurface,
                    )
                },
                leadingIcon = leadingIcon,
                trailingIcon = {
                    if (text != "") {
                        IconButton(
                            modifier =
                                Modifier
                                    .testTag("CANCEL_BUTTON"),
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.Cancel,
                                    contentDescription = stringResource(Res.string.clear_search),
                                )
                            },
                            onClick = {
                                onQueryChange.invoke("")
                            },
                        )
                    } else {
                        IconButton(
                            modifier =
                                Modifier
                                    .testTag("SEARCH_BUTTON"),
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.Search,
                                    contentDescription = stringResource(Res.string.search),
                                )
                            },
                            onClick = {
                                focusRequester.requestFocus()
                            },
                        )
                    }
                },
                shape = SearchBarDefaults.inputFieldShape,
                contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(),
                container = {},
            )
        },
        cursorBrush = SolidColor(cursorColor),
    )
}
