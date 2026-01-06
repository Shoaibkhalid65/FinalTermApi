package com.example.finaltermapi

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.finaltermapi.ui.theme.Color2
import com.example.finaltermapi.ui.theme.Color3

@Preview(showBackground = true)
@Composable
fun BlurPlaygroundScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
//        Image(
//            painter = painterResource(R.drawable.sit_with_shield),
//            contentDescription = "receiving award",
//            modifier = Modifier
//                .size(200.dp)
//                .clip(RoundedCornerShape(24.dp))
//                .blur(
//                    3.dp
//                ),
//            contentScale = ContentScale.Crop
//        )

//        BlurredImage(
//            modifier = Modifier.size(200.dp).clip(RoundedCornerShape(24.dp)),
//            painter = painterResource(R.drawable.receiving_award),
//            blurRadius = 16.dp
//        )


        val modifier =
            Modifier
                .border(border = BorderStroke(2.dp, Color2), RoundedCornerShape(24.dp))

        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color3)
                .then(modifier)
        )

    }
}

@Composable
fun BlurredImage(modifier: Modifier = Modifier, painter: Painter, blurRadius: Dp) {
    val density = LocalDensity.current
    val blurRadiusPx = with(density) {
        blurRadius.toPx()
    }
    val blurModifier = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        Modifier.graphicsLayer {
            renderEffect = RenderEffect.createBlurEffect(
                blurRadiusPx,
                blurRadiusPx,
                Shader.TileMode.CLAMP
            ).asComposeRenderEffect()
        }
    } else {
        Modifier.blur(radius = blurRadius)
    }
    Image(
        modifier = modifier.then(blurModifier),
        painter = painter,
        contentDescription = "image",
        contentScale = ContentScale.Crop
    )


}