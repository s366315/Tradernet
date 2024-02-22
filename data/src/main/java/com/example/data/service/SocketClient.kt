package com.example.data.service

import com.example.data.BuildConfig
import com.example.data.entity.SocketState
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.http.HttpMethod
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.runBlocking

interface SocketClient {
    suspend fun collect(onEvent: String?, state: (SocketState) -> Unit)
    fun disconnect()
}

class SocketClientImpl : SocketClient {

    private val method = HttpMethod.Get
    private val host = BuildConfig.SERVER_URL

    private companion object {
        const val PING_INTERVAL = 10000L
    }

    private val client by lazy {
        HttpClient(CIO) {
            engine {
                https {
                    serverName = host
                    trustManager = MainTrustManager(this)
                }
            }
            install(WebSockets) {
                pingInterval = PING_INTERVAL
            }
        }
    }

    private val session = runBlocking {
        client.webSocketSession(method = method, host = host)
    }

    override suspend fun collect(onEvent: String?, state: (SocketState) -> Unit) {
        try {
            client.webSocket(method = method, host = host) {
                state(SocketState.Connected)

                onEvent?.let{ session.send(Frame.Text(onEvent)) }

                session.incoming.consumeEach {
                    state(SocketState.Message((it as? Frame.Text)?.readText().orEmpty()))
                }
            }
        } catch (e: Exception) {
            state(SocketState.Disconnected)
        }
    }

    override fun disconnect() {
        client.close()
    }
}

