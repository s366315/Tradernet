package com.example.data.response

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class QuotesResponse(
    @SerializedName("c") val c: String? = null,
    @SerializedName("ltp") val ltp: BigDecimal? = null,
    @SerializedName("ltr") val ltr: String? = null,
    @SerializedName("min_step") val minStep: Double? = null,
    @SerializedName("name") val name: String? = null,
)