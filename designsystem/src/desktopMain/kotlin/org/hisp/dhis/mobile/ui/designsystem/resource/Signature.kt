package org.hisp.dhis.mobile.ui.designsystem.resource

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.changedToDown
import androidx.compose.ui.input.pointer.changedToUp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange

@Composable
actual fun SignatureCanvas(
    onReady: (Signature) -> Unit,
    onStartedSigning: () -> Unit,
) {
    val drawing = remember { mutableStateOf<Offset?>(null) }
    SignatureCanvas(
        drawing = drawing,
    )
}

@Composable
fun SignatureCanvas(modifier: Modifier = Modifier, drawing: MutableState<Offset?>) {
    val path by remember { mutableStateOf(Path()) }

    val actionIdle = 0
    val actionDown = 1
    val actionMove = 2
    val actionUp = 3
    var motionEvent by remember { mutableIntStateOf(actionIdle) }
    var currentPosition by remember { mutableStateOf(Offset.Unspecified) }
    val drawModifier = modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            awaitEachGesture {
                val down: PointerInputChange = awaitFirstDown().also {
                    motionEvent = actionDown
                    currentPosition = it.position
                }
                do {
                    val event: PointerEvent = awaitPointerEvent()

                    var eventChanges =
                        "DOWN changedToDown: ${down.changedToDown()} changedUp: ${down.changedToUp()}\n"
                    event.changes
                        .forEachIndexed { index: Int, pointerInputChange: PointerInputChange ->
                            eventChanges += "Index: $index, id: ${pointerInputChange.id}, " +
                                "changedUp: ${pointerInputChange.changedToUp()}" +
                                "pos: ${pointerInputChange.position}\n"

                            if (pointerInputChange.positionChange() != Offset.Zero) pointerInputChange.consume()
                        }

                    motionEvent = actionMove
                    currentPosition = event.changes.first().position
                } while (event.changes.any { it.pressed })

                motionEvent = actionUp
            }
        }

    Canvas(
        modifier = drawModifier,
    ) {
        when (motionEvent) {
            actionDown -> {
                drawing.value = Offset(currentPosition.x, currentPosition.y)
                path.moveTo(currentPosition.x, currentPosition.y)
            }

            actionMove -> {
                if (currentPosition != Offset.Unspecified) {
                    drawing.value = Offset(currentPosition.x, currentPosition.y)
                    path.lineTo(currentPosition.x, currentPosition.y)
                }
            }

            actionUp -> {
                drawing.value = Offset(currentPosition.x, currentPosition.y)
                path.lineTo(currentPosition.x, currentPosition.y)
                motionEvent = actionIdle
            }

            else -> Unit
        }

        drawing.value?.let {
            drawPath(
                path = path,
                color = Color.Black,
                alpha = 1f,
                style = Stroke(7f),
            )
        } ?: path.reset()
    }
}
