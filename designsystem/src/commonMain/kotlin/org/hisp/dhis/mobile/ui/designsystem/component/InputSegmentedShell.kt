package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.hisp.dhis.mobile.ui.designsystem.component.internal.clipboard.getText
import org.hisp.dhis.mobile.ui.designsystem.component.model.SegmentedShellType
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import kotlin.math.min

@Composable
fun InputSegmentedShell(
    modifier: Modifier = Modifier,
    segmentCount: Int,
    initialValue: String? = null,
    supportingTextData: SupportingTextData?,
    segmentedShellType: SegmentedShellType = SegmentedShellType.Numeric,
    onValueChanged: (String) -> Unit = {},
) {
    val clipboard = LocalClipboard.current
    val scope = rememberCoroutineScope()
    var hasError by remember(initialValue, supportingTextData) {
        mutableStateOf(
            supportingTextData?.state == SupportingTextState.ERROR,
        )
    }
    val segmentValues =
        remember {
            val initialSegments = initialValue ?: "-".repeat(segmentCount)
            mutableStateListOf(
                *Array(segmentCount) { index ->
                    TextFieldValue(
                        text =
                            initialSegments
                                .getOrNull(index)
                                ?.takeIf { segmentedShellType.isAllowed(it) }
                                ?.toString() ?: "",
                        selection = TextRange(1),
                    )
                },
            )
        }

    var currentFocus by remember {
        mutableStateOf(-1)
    }

    val focusRequesterList = remember { List(segmentCount) { FocusRequester() } }

    LaunchedEffect(currentFocus) {
        if (currentFocus in 0 until segmentCount) {
            focusRequesterList[currentFocus].requestFocus()
            val currentText = segmentValues[currentFocus].text
            segmentValues[currentFocus] =
                segmentValues[currentFocus].copy(selection = TextRange(currentText.length))
        }
    }

    fun updateFullValue() {
        val fullValue = segmentValues.joinToString("") { it.text.ifEmpty { "-" } }
        onValueChanged(fullValue)
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = spacedBy(Spacing.Spacing4),
    ) {
        Row(
            modifier = modifier.width(IntrinsicSize.Min),
            horizontalArrangement = spacedBy(Spacing.Spacing12),
        ) {
            repeat(segmentCount) { index ->
                var segmentState by remember(currentFocus, hasError) {
                    mutableStateOf(
                        when {
                            hasError -> InputShellState.ERROR
                            currentFocus == index -> InputShellState.FOCUSED
                            else -> InputShellState.UNFOCUSED
                        },
                    )
                }
                InputShell(
                    modifier =
                        Modifier
                            .weight(1f)
                            .focusRequester(focusRequesterList[index]),
                    title = "",
                    state = segmentState,
                    supportingText = null,
                    legend = null,
                    isRequiredField = false,
                    inputStyle = InputStyle.DataInputStyle(),
                    paddingValues =
                        PaddingValues(
                            start = Spacing.Spacing0,
                            top = Spacing.Spacing8,
                            end = Spacing.Spacing0,
                            bottom = Spacing.Spacing0,
                        ),
                    inputField = {
                        BasicTextField(
                            modifier =
                                Modifier
                                    .widthIn(min = 53.dp)
                                    .onPreviewKeyEvent { keyEvent ->
                                        if (keyEvent.type == KeyEventType.KeyDown && keyEvent.key == Key.Backspace) {
                                            if (segmentValues[index].text.isEmpty()) {
                                                // Current segment is empty, Backspace is pressed
                                                if (index > 0) {
                                                    // Not the first segment: clear previous, move focus
                                                    segmentValues[index - 1] = TextFieldValue("")
                                                    currentFocus = index - 1
                                                    updateFullValue()
                                                }
                                                // Whether it was the first segment or not, if it was empty and Backspace
                                                // was pressed, we've handled it (either by moving focus or by doing nothing
                                                // for the first segment). Consume the event to prevent propagation.
                                                return@onPreviewKeyEvent true
                                            }
                                            // Segment is NOT empty: let BasicTextField handle character deletion by returning false.
                                            // The BasicTextField's internal input connection will process the Backspace.
                                            return@onPreviewKeyEvent false
                                        }
                                        // Not a Backspace key down event, or not a KeyDown event: don't interfere.
                                        return@onPreviewKeyEvent false
                                    },
                            isSingleLine = true,
                            inputTextValue = segmentValues[index],
                            textStyle = MaterialTheme.typography.headlineMedium.copy(textAlign = TextAlign.Center),
                            onInputChanged = { newTextFieldValue ->
                                scope.launch {
                                    val value =
                                        newTextFieldValue.text.lastOrNull()?.toString() ?: ""
                                    val copiedValue = clipboard.getClipEntry()?.getText()
                                    val isCopiedValue =
                                        copiedValue != null && copiedValue == newTextFieldValue.text
                                    if (newTextFieldValue.text.all {
                                            segmentedShellType.isAllowed(
                                                it,
                                            )
                                        }
                                    ) {
                                        if (!isCopiedValue) {
                                            segmentValues[index] =
                                                newTextFieldValue.copy(
                                                    text = value.uppercase(),
                                                    selection = TextRange(newTextFieldValue.text.length),
                                                )
                                        } else {
                                            newTextFieldValue.text
                                                .substring(
                                                    0,
                                                    min(
                                                        newTextFieldValue.text.length,
                                                        segmentCount,
                                                    ),
                                                ).forEachIndexed { copiedValueIndex, copiedValue ->
                                                    segmentValues[copiedValueIndex] =
                                                        newTextFieldValue.copy(
                                                            text = copiedValue.uppercase(),
                                                            selection = TextRange(1),
                                                        )
                                                }
                                        }
                                        hasError = false
                                        updateFullValue()

                                        if (newTextFieldValue.text.isNotEmpty() && !isCopiedValue) {
                                            currentFocus =
                                                when {
                                                    index == segmentCount - 1 -> index
                                                    index < segmentCount - 1 -> index + 1
                                                    else -> -1
                                                }
                                        }
                                    }
                                }
                            },
                        )
                    },
                    onFocusChanged = { focused ->
                        if (focused && currentFocus != index) {
                            currentFocus = index
                        }
                    },
                )
            }
        }
        supportingTextData?.takeIf { hasError }?.let {
            SupportingText(
                text = it.text,
                state = it.state,
                maxLines = 3,
                paddingValues = PaddingValues(),
            )
        }
    }
}
