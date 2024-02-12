package bes.max.mobinttest.core.ui

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import bes.max.mobinttest.R
import bes.max.mobinttest.core.ui.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    val scaleState = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    LaunchedEffect(key1 = Unit) {
        scaleState.animateTo(
            targetValue = 1.3f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(3f).getInterpolation(it)
                }
            )
        )
        delay(1500)
        navController.navigate(Screen.CompanyCardsScreen.route)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_splash),
            contentDescription = "Splash image",
            modifier = Modifier.scale(scaleState.value)
        )
    }
}