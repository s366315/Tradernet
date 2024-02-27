package com.example.domain.ext

import java.math.BigDecimal

fun BigDecimal?.orZero() = this ?: BigDecimal("0.0")