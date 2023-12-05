package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import org.hisp.dhis.mobile.ui.designsystem.theme.Shape
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    text: String = "",
    placeHolderText: String = "Search",
    onActiveChange: (Boolean) -> Unit = {},
    onSearch: (String) -> Unit = {},
    onQueryChange: (String) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val focusRequester = remember { FocusRequester() }
    val containerColor = if (!isPressed) {
        SurfaceColor.ContainerLow
    } else {
        SurfaceColor.Container
    }

    BasicTextField(
        value = text,
        onValueChange = onQueryChange,
        modifier = modifier
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
                indication = rememberRipple(
                    true,
                    color = SurfaceColor.Primary,
                ),
            )
            .onFocusChanged { if (it.isFocused) onActiveChange(true) }
            .onKeyEvent {
                if (it.key == Key.Escape && it.type == KeyEventType.KeyDown) {
                    onActiveChange(false)
                    true
                } else {
                    false
                }
            },
        enabled = true,
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearch(text) }),
        interactionSource = interactionSource,
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
                        color = TextColor.OnDisabledSurface,
                    )
                },
                trailingIcon = {
                    if (text != "") {
                        IconButton(
                            modifier = Modifier.testTag("CANCEL_BUTTON"),
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.Cancel,
                                    contentDescription = "cancel button",
                                )
                            },
                            onClick = {
                                onQueryChange.invoke("")
                            },
                        )
                    } else {
                        IconButton(
                            modifier = Modifier.testTag("SEARCH_BUTTON"),
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.Search,
                                    contentDescription = "search button",
                                )
                            },
                            onClick = {},
                        )
                    }
                },
                shape = SearchBarDefaults.inputFieldShape,
                contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(),
                container = {},
            )
        },
    )
}
