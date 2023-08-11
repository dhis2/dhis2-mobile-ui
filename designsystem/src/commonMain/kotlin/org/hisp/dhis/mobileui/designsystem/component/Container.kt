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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
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
    if (!title.isNullOrEmpty()) Text(title)

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

@Composable
fun InputShell(
    title: String,
    valueType: ValueType = ValueType.TEXT,
    showResetButton: Boolean = true,
    showSeparator: Boolean = true,
    showActionButton: Boolean = true,
    showLegend: Boolean = false
) {
    TextInputField(title)
}
