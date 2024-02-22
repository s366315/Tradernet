package com.example.tradernet

import org.json.JSONArray

data class EventRequest(
    private val eventName: String,
    private val tickers: List<String>
) {

    override fun toString(): String {
        val tickersArray = JSONArray(tickers)
        val common = JSONArray().put(eventName).put(tickersArray)
        return common.toString()
    }
}