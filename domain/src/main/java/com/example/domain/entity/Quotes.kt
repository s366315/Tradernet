package com.example.domain.entity

import com.example.domain.ext.orZero
import com.example.domain.ext.roundTo
import java.math.BigDecimal

data class Quotes(
    val c: String? = null,
    val ltp: BigDecimal? = null,
    val ltr: String? = null,
    val minStep: Double? = null,
    val name: String? = null,

    val ltpDiff: BigDecimal = BigDecimal("0"),
    val ltpDiffPercent: BigDecimal = BigDecimal("0"),
    val increased: Boolean = false,
    val decreased: Boolean = false,
    val positive: Boolean = false,
    val logoUrl: String = "",
    val ltpFormatted: String = "0",
    val ltpDiffFormatted: String = "0"
)

fun Quotes.copyWith(new: Quotes) =
    Quotes(
        c = new.c ?: this.c,
        ltp = new.ltp ?: this.ltp,
        ltr = new.ltr ?: this.ltr,
        minStep = new.minStep ?: this.minStep,
        name = new.name ?: this.name,

        ltpDiff = calcDiff(this.ltpDiff, new.ltp, this.ltp),
        ltpDiffPercent = calcDiffPercent(this.ltpDiffPercent, this.ltp, new.ltp),
        increased = new.ltp.orZero().compareTo(this.ltp.orZero()) > 0,
        decreased = new.ltp.orZero().compareTo(this.ltp.orZero()) < 0,
        positive = ltpDiff.compareTo(BigDecimal("0")) >= 0,
        logoUrl = this.logoUrl,
        ltpFormatted = ltp.orZero().toDouble().roundTo(this.minStep.orZero()),
        ltpDiffFormatted = ltpDiff.toDouble().roundTo(this.minStep.orZero())
    )

private fun calcDiff(oldDiff: BigDecimal, new: BigDecimal?, old: BigDecimal?): BigDecimal {
    old ?: return oldDiff
    new ?: return oldDiff

    val newDiff = new.minus(old)

    return newDiff
}

private fun calcDiffPercent(
    oldPercent: BigDecimal,
    old: BigDecimal?,
    new: BigDecimal?
): BigDecimal {
    old ?: return oldPercent
    new ?: return oldPercent
    val newDiff = new.minus(old)
    val newPercent = (newDiff.div(old).multiply(BigDecimal("100")))

    return newPercent
}