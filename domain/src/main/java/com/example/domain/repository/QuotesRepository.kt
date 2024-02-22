package com.example.domain.repository

import com.example.domain.entity.QuotesState
import kotlinx.coroutines.flow.Flow

interface QuotesRepository {
    suspend fun fetchQuotes(event: String?): Flow<QuotesState>
}