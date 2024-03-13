package com.example.data.request

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GetTopSecuritiesRequest(
    @SerializedName("cmd") val cmd: String = "getTopSecurities",
    @SerializedName("params") val params: GetTopSecuritiesRequestParams = GetTopSecuritiesRequestParams()
)

@Serializable
data class GetTopSecuritiesRequestParams(
    @SerializedName("type") val type: String = "stocks",
    @SerializedName("exchange") val exchange: String = "russia",
    @SerializedName("gainers") val gainers: Int = 0,
    @SerializedName("limit") val limit: Int = 30
)