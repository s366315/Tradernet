package com.example.data.service

import com.example.data.request.GetTopSecuritiesRequest
import com.example.data.response.GetTopSecuritiesResponse
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class ApiServiceImpl(private val ktor: HttpClient): ApiService {
    override suspend fun getTopSecurities(query: GetTopSecuritiesRequest): Result<GetTopSecuritiesResponse> = runCatching {
        ktor.get {
            parameter("q", Gson().toJson(query))
        }.body()
    }
}