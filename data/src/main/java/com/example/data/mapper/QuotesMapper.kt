package com.example.data.mapper

import com.example.data.response.QuotesResponse
import com.example.domain.entity.Quotes

fun QuotesResponse.map(logoUrl: String) = Quotes(
    c = this.c,
    ltp = this.ltp,
    ltr = this.ltr,
    minStep = this.minStep,
    name = this.name,
    logoUrl = logoUrl
)
