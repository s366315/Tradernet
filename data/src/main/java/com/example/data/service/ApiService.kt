package com.example.data.service

import com.example.data.request.GetTopSecuritiesRequest
import com.example.data.response.GetTopSecuritiesResponse

interface ApiService {
    suspend fun getTopSecurities(query: GetTopSecuritiesRequest): Result<GetTopSecuritiesResponse>
}