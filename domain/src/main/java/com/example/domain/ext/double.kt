package com.example.domain.ext

import java.text.DecimalFormat
import kotlin.math.ceil

fun Double?.orZero() = this ?: 0.0

fun Double.roundTo(minStep: Double): String {
    val ceiling = ceil(this / minStep)
    val roundedNum = ceiling * minStep
    return DecimalFormat("0.##").format(roundedNum)
}