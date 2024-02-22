package com.example.tradernet.ui.component

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import coil.compose.AsyncImage
import com.example.domain.entity.Quotes
import com.example.tradernet.ui.theme.animationSpeedMedium
import com.example.tradernet.ui.theme.animationSpeedShort
import com.example.tradernet.ui.theme.dividerStartPadding
import com.example.tradernet.ui.theme.emptyIconSize
import com.example.tradernet.ui.theme.green
import com.example.tradernet.ui.theme.iconSize
import com.example.tradernet.ui.theme.lazyColumnItemHeight
import com.example.tradernet.ui.theme.lazyItemHorizontalPadding
import com.example.tradernet.ui.theme.percentBoxPadding
import com.example.tradernet.ui.theme.percentBoxRadius
import com.example.tradernet.ui.theme.red
import com.example.tradernet.ui.theme.regularFontSize
import com.example.tradernet.ui.theme.smallFontSize
import java.text.DecimalFormat

private const val MINIMAL_LOGO_SIZE = 1

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyListItem(item: Quotes) {
    val textColor = if (item.positive) green else red

    val textColorState = remember { Animatable(textColor) }
    val textBackgroundState = remember { Animatable(Color.Transparent) }

    LaunchedEffect(item.increased , item.decreased) {
        if (item.increased && !item.decreased) {
            textBackgroundState.animateTo(red, animationSpec = tween(animationSpeedShort))
            textBackgroundState.animateTo(Color.Transparent, animationSpec = tween(animationSpeedMedium))
        } else if (!item.increased && item.decreased) {
            textBackgroundState.animateTo(green, animationSpec = tween(animationSpeedShort))
            textBackgroundState.animateTo(Color.Transparent, animationSpec = tween(animationSpeedMedium))
        } else {
            textBackgroundState.animateTo(Color.Transparent, animationSpec = tween(animationSpeedMedium))
        }
    }

    LaunchedEffect(item.increased , item.decreased, item.positive) {
        if (item.increased && !item.decreased) {
            textColorState.animateTo(Color.White, animationSpec = tween(animationSpeedShort))
            textColorState.animateTo(textColor, animationSpec = tween(animationSpeedMedium))
        } else if (!item.increased && item.decreased) {
            textColorState.animateTo(Color.White, animationSpec = tween(animationSpeedShort))
            textColorState.animateTo(textColor, animationSpec = tween(animationSpeedMedium))
        } else {
            textColorState.animateTo(textColor, animationSpec = tween(animationSpeedMedium))
        }
    }

    val percentSign = if (item.ltpDiffPercent > 0) "+" else ""
    val priseSign = if (item.ltpDiff > 0) "+" else ""

    Column(modifier = Modifier.height(lazyColumnItemHeight)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = lazyItemHorizontalPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            //name//
            Column {
                Row {
                    val iconSize = remember { mutableStateOf(iconSize) }
                    AsyncImage(
                        onSuccess = {
                            if (it.painter.intrinsicSize.height <= MINIMAL_LOGO_SIZE) {
                                iconSize.value = emptyIconSize
                            }
                        },
                        model = item.logoUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .size(iconSize.value)
                    )
                    Text(
                        text = item.c.orEmpty(),
                        fontSize = regularFontSize,
                        modifier = Modifier.basicMarquee()
                    )
                }

                Text(
                    text = "${item.ltr} | ${item.name}",
                    fontSize = smallFontSize,
                    color = Color.Gray
                )
            }
            //value//
            Spacer(modifier = Modifier.weight(1f))
            Column(horizontalAlignment = Alignment.End) {
                Box(
                    modifier = Modifier
                        .background(
                            color = textBackgroundState.value,
                            shape = RoundedCornerShape(percentBoxRadius)
                        )
                        .padding(horizontal = percentBoxPadding)
                ) {
                    Text(
                        text = "$percentSign${DecimalFormat("0.##").format(item.ltpDiffPercent)}%",
                        color = textColorState.value,
                        fontSize = regularFontSize,
                        softWrap = false
                    )
                }
                Text(
                    text = "${DecimalFormat("0.####").format(item.ltp)} ( $priseSign${
                        DecimalFormat("0.####").format(item.ltpDiff)
                    } )",
                    fontSize = smallFontSize,
                    color = Color.Black,
                    softWrap = false
                )
            }
            //arrow//
            Icon(
                imageVector = Icons.Rounded.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier
            )
        }
        HorizontalDivider(
            Modifier.padding(start = dividerStartPadding),
            color = Color.LightGray,
        )
    }
}