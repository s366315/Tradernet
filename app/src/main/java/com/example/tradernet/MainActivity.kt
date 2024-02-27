package com.example.tradernet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.tradernet.ui.MainScreen
import com.example.tradernet.ui.theme.TradernetTheme
import com.example.tradernet.ui.theme.animationSpeedMedium
import com.example.tradernet.ui.theme.animationSpeedShort
import com.example.tradernet.ui.theme.green
import com.example.tradernet.ui.theme.percentBoxPadding
import com.example.tradernet.ui.theme.percentBoxRadius
import com.example.tradernet.ui.theme.red
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TradernetTheme(darkTheme = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    MainScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TradernetTheme {
        Boxes()
    }
}

val innerBoxId = "innerBox"
val rootId = "innerBox"
val constraints = ConstraintSet {
    val redBox = createRefFor(innerBoxId)
    val root = createRefFor(rootId)

    constrain(redBox) {
        width = Dimension.matchParent
        height = Dimension.matchParent
    }
}

@Composable
fun Boxes() {
    val alpha = remember { Animatable(0f) }

    var trigger by remember { mutableStateOf(true) }

    val color = if (trigger) red else green

    LaunchedEffect(color) {
        alpha.animateTo(1f, animationSpec = tween(animationSpeedShort, easing = LinearEasing))
        alpha.animateTo(0f, animationSpec = tween(animationSpeedMedium, easing = LinearEasing))

        delay(1000)
        trigger = !trigger
    }

    ConstraintLayout(
        constraintSet = constraints,
        modifier = Modifier
            /*.background(
                color = red,
                shape = RoundedCornerShape(percentBoxRadius)
            )*/
            .size(200.dp)
            .padding(horizontal = percentBoxPadding)
    ) {
        Box(
            modifier = Modifier
                .alpha(alpha.value)
                .layoutId(innerBoxId)
                .background(
                    color = color,
                    shape = RoundedCornerShape(percentBoxRadius)
                )
        )
    }
}