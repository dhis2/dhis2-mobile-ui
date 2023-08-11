package org.hisp.dhis.mobileui.designsystem.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobileui.designsystem.component.internal.ValueType
import org.hisp.dhis.mobileui.designsystem.theme.Spacing

@Composable
fun ColumnComponentContainer(
    title: String,
    content: @Composable (() -> Unit)
) {
    Column(modifier = Modifier.padding(10.dp).verticalScroll(rememberScrollState())) {
        Text(text = title, fontWeight = FontWeight.Bold)
        Spacer(Modifier.size(Spacing.Spacing4))
        content()
    }
}

@Composable
fun RowComponentContainer(
    title: String = "",
    content: @Composable (() -> Unit)
) {
    if (title.isNotEmpty()) Text(title)

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        content()
    }
}

@Composable
fun EmptyInput() {
    Box {
        val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
        Canvas(Modifier.fillMaxWidth().height(20.dp)) {
            drawLine(
                color = Color.Red,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                pathEffect = pathEffect
            )
        }
    }
}

/**
 * DHIS2 Text Input Field. Wraps Material· [TextField].
 * ValueType will allways be TEXT
 * @param title Controls the selected option state for multiple options.
 * @param enabled Controls the enabled state of the component. When `false`, this component will not be
 * clickable and will appear disabled to accessibility services.
 * @param showResetButton Controls reset button visibility
 * @param showSeparator Controls separator visibility
 * @param showActionButton Controls action button visibility
 * @param showLegend Controls action button visibility
 * @param valueType Controls the input field valueType.
 * @param onClick Will be called when the user clicks the action button.
 */
@Composable
fun InputShell(
    title: String,
    enabled: Boolean = true,
    valueType: ValueType = ValueType.TEXT,
    showResetButton: Boolean = true,
    showSeparator: Boolean = true,
    showActionButton: Boolean = true,
    showLegend: Boolean = false,
    onClick: () -> Unit
) {
    TextInputField(title, onClick = onClick)
}
