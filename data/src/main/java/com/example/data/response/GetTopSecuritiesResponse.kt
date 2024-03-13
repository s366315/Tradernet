package com.example.data.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GetTopSecuritiesResponse(
    @SerializedName("tickers") val tickers: List<String> = emptyList()
)