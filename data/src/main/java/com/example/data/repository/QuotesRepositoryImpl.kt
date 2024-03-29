package com.example.data.repository

import com.example.data.BuildConfig
import com.example.data.entity.SocketState
import com.example.data.mapper.map
import com.example.data.request.EventRequest
import com.example.data.request.GetTopSecuritiesRequest
import com.example.data.response.QuotesResponse
import com.example.data.service.ApiService
import com.example.data.service.SocketClient
import com.example.domain.entity.Quotes
import com.example.domain.entity.QuotesState
import com.example.domain.repository.QuotesRepository
import com.example.domain.request.Requests
import com.example.domain.request.Tickers
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext
import org.json.JSONArray

class QuotesRepositoryImpl(private val socketClient: SocketClient, private val api: ApiService) :
    QuotesRepository {

    private companion object {
        const val EVENT_ELEMENT_INDEX = 0
        const val DATA_ELEMENT_INDEX = 1
        const val DATA_KEY = "q"
        const val LOGO_URL = BuildConfig.IMAGE_URL
    }

    override suspend fun fetchQuotes(): Flow<QuotesState> = callbackFlow {

        val event = withContext(Dispatchers.IO) {
            api.getTopSecurities(query = GetTopSecuritiesRequest())
                .getOrNull()
                ?.tickers
                ?.let {
                    EventRequest(eventName = Requests.REALTIME_QUOTES(), tickers = it).toString()
                }
                ?: EventRequest(
                    eventName = Requests.REALTIME_QUOTES(),
                    tickers = Tickers.getAll()
                ).toString()
        }

        socketClient.collect(event) {
            val messageState: QuotesState = when (it) {
                is SocketState.Connected -> QuotesState.Connected
                is SocketState.Disconnected -> QuotesState.Disconnected
                is SocketState.Message -> mapMessage(it)
            }

            trySend(messageState)
        }

        awaitClose {
            channel.trySend(QuotesState.Disconnected)
            channel.close()
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