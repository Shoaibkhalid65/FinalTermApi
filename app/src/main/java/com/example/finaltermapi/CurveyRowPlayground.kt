package com.example.finaltermapi

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlin.math.atan2


@Preview(showBackground = true)
@Composable
fun CurveRowScreen(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        var boxSize by remember { mutableStateOf(IntSize.Zero) }
        val items=List(10){it.toString()}
          PathCurvedRow1 (modifier = Modifier.fillMaxWidth().height(200.dp)) {
              items.forEach {
                  Text(
                      text = it
                  )
              }
          }

    }
}

@Composable
fun PathCurvedRow(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        val placeables = measurables.map { it.measure(constraints) }
        val w=constraints.maxWidth.toFloat()
        val h=constraints.maxHeight.toFloat()
        // 1. Define the Path (e.g., an arc/wave)
        val path = Path().apply {
            moveTo(0f, 0f)
            // Creates a smooth cubic Bezier curve
            quadraticTo(w*0.5f,h*0.8f,w,0f)


        }

        // 2. Use PathMeasure to calculate positions
        val pathMeasure = android.graphics.PathMeasure(path.asAndroidPath(), false)
        val pathLength = pathMeasure.length

        layout(constraints.maxWidth, constraints.maxHeight) {
            placeables.forEachIndexed { index, placeable ->
                // Calculate how far along the path to place this item (0.0 to 1.0)
                val fraction = if (placeables.size > 1) {
                    index.toFloat() / (placeables.size - 1)
                } else {
                    1f
                }

                val distance = fraction * pathLength
                val pos = floatArrayOf(0f, 0f)
                val tan = floatArrayOf(0f, 0f)

                // Get the coordinates (pos) and the slope (tan) at this distance
                pathMeasure.getPosTan(distance, pos, tan)

                // 3. Calculate rotation angle from the tangent (slope)
//                val degrees = Math.toDegrees(atan2(tan[1].toDouble(), tan[0].toDouble())).toFloat()

                placeable.placeWithLayer(
                    x = (pos[0] - placeable.width / 2).toInt(),
                    y = (pos[1] - placeable.height / 2).toInt(),

                )
            }
        }
    }
}
@Composable
fun PathCurvedRow1(
    modifier: Modifier = Modifier,
    curveHeight: Int = 200,
    content: @Composable () -> Unit
) {
    Layout(content = content, modifier = modifier) { measurables, constraints ->

        val layoutWidth = constraints.maxWidth
        val layoutHeight = curveHeight

        val placeables = measurables.map {
            it.measure(
                constraints.copy(
                    minHeight = 0,
                    maxHeight = layoutHeight
                )
            )
        }

        val path = Path().apply {
            val centerY = layoutHeight / 2f
            moveTo(0f, centerY)
            quadraticTo(
                layoutWidth * 0.5f,
                centerY + layoutHeight * 0.4f,
                layoutWidth.toFloat(),
                centerY
            )
        }

        val pathMeasure = android.graphics.PathMeasure(path.asAndroidPath(), false)
        val pathLength = pathMeasure.length

        layout(layoutWidth/50, layoutHeight) {
            placeables.forEachIndexed { index, placeable ->

                val fraction =
                    if (placeables.size > 1)
                        index.toFloat() / (placeables.size - 1)
                    else 0.5f

                val distance = fraction * pathLength
                val pos = FloatArray(2)

                pathMeasure.getPosTan(distance, pos, null)

                placeable.place(
                    x = (pos[0] - placeable.width / 2).toInt(),
                    y = (pos[1] - placeable.height / 2).toInt()
                )
            }
        }
    }
}
