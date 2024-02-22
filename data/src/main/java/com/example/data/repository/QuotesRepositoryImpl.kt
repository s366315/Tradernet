package com.example.data.repository

import com.example.data.BuildConfig
import com.example.data.entity.SocketState
import com.example.data.mapper.map
import com.example.data.response.QuotesResponse
import com.example.data.service.SocketClient
import com.example.domain.entity.Quotes
import com.example.domain.entity.QuotesState
import com.example.domain.repository.QuotesRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.json.JSONArray

class QuotesRepositoryImpl(private val socketClient: SocketClient) : QuotesRepository {

    private companion object {
        const val EVENT_ELEMENT_INDEX = 0
        const val DATA_ELEMENT_INDEX = 1
        const val DATA_KEY = "q"
        const val LOGO_URL = BuildConfig.IMAGE_URL
    }

    override suspend fun fetchQuotes(event: String?): Flow<QuotesState> = callbackFlow {
        socketClient.collect(event) {
            val messageState: QuotesState = when (it) {
                is SocketState.Connected -> QuotesState.Connected
                is SocketState.Disconnected -> QuotesState.Disconnected
                is SocketState.Message -> mapMessage(it)
            }

            trySend(messageState)
        }
    }

    private fun mapMessage(it: SocketState.Message): QuotesState.Message {
        val msg = it.message

        return try {
            val rootElement = JSONArray(msg)
            if (rootElement[EVENT_ELEMENT_INDEX] == DATA_KEY) {
                val jsonData = rootElement.getJSONObject(DATA_ELEMENT_INDEX)
                val quotes = Gson().fromJson(jsonData.toString(), QuotesResponse::class.java)

                 val logoUrl = "$LOGO_URL${quotes.c?.lowercase()}"

                QuotesState.Message(quotes.map(logoUrl))
            } else QuotesState.Message(Quotes())
        } catch (e: Exception) {
            e.printStackTrace()
            QuotesState.Message(Quotes())
        }
    }

}