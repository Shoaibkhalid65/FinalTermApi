package com.example.finaltermapi

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finaltermapi.ui.theme.Color3
import kotlinx.coroutines.delay

@Preview(showBackground = true)
@Composable
fun FavoriteAnimationScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(200.dp)
                .aspectRatio(1.5f / 1f)
                .clip(RoundedCornerShape(24.dp))
        ) {
            Image(
                painter = painterResource(R.drawable.fitness),
                contentDescription = "fitness image",
                modifier = Modifier.fillMaxSize()
            )
            var isFav by remember { mutableStateOf(false) }
            var favClicked by remember { mutableStateOf(false) }
            val animatable = remember { Animatable(1f) }
            if(favClicked) {
                LaunchedEffect(isFav) {
                    animatable.animateTo(
                        targetValue = 1.25f,
                        animationSpec = spring(stiffness = Spring.StiffnessHigh)
                    )
                    delay(200L)
                    animatable.animateTo(1f)
                }
            }


            Icon(
                imageVector = if (isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                tint = if (isFav) Color3 else Color.White,
                contentDescription = "favorite",
                modifier = Modifier
                    .padding(8.dp)
                    .size(36.dp)
                    .align(Alignment.TopEnd)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        isFav=!isFav
                        favClicked=true
                    }
                    .graphicsLayer{
                        scaleY=animatable.value
                        scaleX=animatable.value
                        shadowElevation=if(isFav) 10f else 0f
                    }
            )
        }
    }
}