package com.example.tradernet.ui.component

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.example.domain.entity.Quotes
import com.example.tradernet.R
import com.example.tradernet.ui.theme.animationSpeedMedium
import com.example.tradernet.ui.theme.animationSpeedShort
import com.example.tradernet.ui.theme.emptyIconSize
import com.example.tradernet.ui.theme.green
import com.example.tradernet.ui.theme.iconSize
import com.example.tradernet.ui.theme.lazyColumnItemHeight
import com.example.tradernet.ui.theme.lazyItemHorizontalPaddingEnd
import com.example.tradernet.ui.theme.lazyItemHorizontalPaddingPriceSign
import com.example.tradernet.ui.theme.lazyItemHorizontalPaddingStart
import com.example.tradernet.ui.theme.percentBoxRadius
import com.example.tradernet.ui.theme.red
import com.example.tradernet.ui.theme.regularFontSize
import com.example.tradernet.ui.theme.smallFontSize
import kotlinx.coroutines.launch
import java.text.DecimalFormat

private const val MINIMAL_LOGO_SIZE = 1
private const val SPACER_WEIGHT = 1f
private const val PERCENT_FORMAT = "0.##"

val shortColorSpec = tween<Color>(animationSpeedShort, easing = LinearEasing)
val mediumColorSpec = tween<Color>(animationSpeedMedium, easing = LinearEasing)
val shortAlphaSpec = tween<Float>(animationSpeedShort, easing = LinearEasing)
val mediumAlphaSpec = tween<Float>(animationSpeedMedium, easing = LinearEasing)

//const val innerBoxId = "innerBox"
const val percentTextId = "percentText"
const val logoId = "logo"
const val titleId = "title"
const val nameId = "name"
const val percentBoxId = "percentBox"
const val pricesBoxId = "pricesBox"
const val arrowId = "arrow"
const val dividerId = "divider"
val constraints = ConstraintSet {
    val percentBox = createRefFor(percentBoxId)
    val logo = createRefFor(logoId)
    val title = createRefFor(titleId)
    val name = createRefFor(nameId)
    val percentText = createRefFor(percentTextId)
    val pricesBox = createRefFor(pricesBoxId)
    val arrow = createRefFor(arrowId)
    val divider = createRefFor(dividerId)

    ///left///
    constrain(logo) {
        top.linkTo(title.top)
        start.linkTo(parent.start)
        bottom.linkTo(title.bottom)
    }

    constrain(title) {
        top.linkTo(parent.top)
        start.linkTo(logo.end)
        bottom.linkTo(name.top)
    }

    constrain(name) {
        top.linkTo(title.bottom)
        start.linkTo(parent.start)
        bottom.linkTo(parent.bottom)
    }

    ///right///
    constrain(percentText) {
        top.linkTo(parent.top)
        end.linkTo(arrow.start)
        bottom.linkTo(pricesBox.top)
    }

    constrain(percentBox) {
        top.linkTo(percentText.top)
        start.linkTo(percentText.start)
        bottom.linkTo(percentText.bottom)
        end.linkTo(percentText.end)
        width = Dimension.fillToConstraints
        height = Dimension.fillToConstraints
    }

    constrain(pricesBox) {
        top.linkTo(percentText.bottom)
        end.linkTo(arrow.start)
        bottom.linkTo(parent.bottom)
    }

    ///arrow///
    constrain(arrow) {
        top.linkTo(parent.top)
        bottom.linkTo(parent.bottom)
        end.linkTo(parent.end)
    }

    ///divider///
    constrain(divider) {
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        bottom.linkTo(parent.bottom)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyListItem(item: Quotes, modifier: Modifier = Modifier) {

    val listItem = remember(item.increased, item.decreased) { item }
    val percentBoxAlpha = remember { androidx.compose.animation.core.Animatable(0f) }
    val textColorState = remember { Animatable(if (item.positive) green else red) }
    var priceSignState by remember { mutableStateOf("") }

    priceSignState = if (listItem.positive) stringResource(id = R.string.lazy_item_plus) else ""

    LaunchedEffect(listItem) {
        if ((item.increased && !item.decreased) || (!item.increased && item.decreased)) {
            launch {
                percentBoxAlpha.animateTo(1f, animationSpec = shortAlphaSpec)
                percentBoxAlpha.animateTo(0f, animationSpec = mediumAlphaSpec)
            }
            launch {
                textColorState.animateTo(Color.White, animationSpec = shortColorSpec)
                textColorState.animateTo(if (item.positive) green else red, animationSpec = mediumColorSpec)
            }
        }
    }

    ConstraintLayout(
        constraintSet = constraints,
        modifier = modifier
            .height(lazyColumnItemHeight)
            .padding(start = lazyItemHorizontalPaddingStart)
    ) {
        ///left///
        val iconSize = remember { mutableStateOf(iconSize) }
        AsyncImage(
            onSuccess = {
                if (it.painter.intrinsicSize.height <= MINIMAL_LOGO_SIZE) {
                    iconSize.value = emptyIconSize
                }
            },
            model = listItem.logoUrl,
            contentDescription = null,
            modifier = modifier
                .layoutId(logoId)
                .size(iconSize.value)
        )
        Text(
            text = listItem.c.orEmpty(),
            fontSize = regularFontSize,
            modifier = modifier
                .layoutId(titleId)
                .basicMarquee()
        )
        Text(
            modifier = modifier.layoutId(nameId),
            text = stringResource(
                id = R.string.lazy_item_name,
                listItem.ltr.orEmpty(),
                listItem.name.orEmpty()
            ),
            fontSize = smallFontSize,
            color = Color.Gray
        )

        ///right///
        Box(
            modifier = modifier
                .alpha(percentBoxAlpha.value)
                .layoutId(percentBoxId)
                .background(
                    color = if (listItem.decreased) red
                    else if (listItem.increased) green
                    else Color.Transparent,
                    shape = RoundedCornerShape(percentBoxRadius)
                )

        )
        Text(
            modifier = modifier
                .layoutId(percentTextId)
                .padding(horizontal = lazyItemHorizontalPaddingPriceSign),
            text = stringResource(
                id = R.string.lazy_item_percent,
                priceSignState,
                DecimalFormat(PERCENT_FORMAT).format(listItem.ltpDiffPercent)
            ),
            color = textColorState.value,
            fontSize = regularFontSize,
            softWrap = false
        )


        Text(
            modifier = modifier.layoutId(pricesBoxId),
            text = stringResource(
                id = R.string.lazy_item_price,
                listItem.ltpFormatted,
                priceSignState,
                listItem.ltpDiffFormatted
            ),
            fontSize = smallFontSize,
            color = Color.Black,
            softWrap = false
        )

        ///arrow///
        Icon(
            imageVector = Icons.Rounded.KeyboardArrowRight,
            contentDescription = null,
            tint = Color.Gray,
            modifier = modifier
                .layoutId(arrowId)
                .padding(end = lazyItemHorizontalPaddingEnd)
        )

        ///divider///
        HorizontalDivider(
            modifier
                .layoutId(dividerId),
            color = Color.LightGray,
        )
    }
}