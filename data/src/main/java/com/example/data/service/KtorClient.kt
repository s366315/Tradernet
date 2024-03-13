package com.example.data.service

import com.example.data.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.HttpHeaders
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import javax.net.ssl.HostnameVerifier

val ktorHttp by lazy {
    HttpClient(Android) {
        expectSuccess = true

        engine {
            sslManager = {
                it.hostnameVerifier = HostnameVerifier { _, _ -> true }
            }
        }

        headers {
            append(HttpHeaders.ContentType, "application/json")
        }

        defaultRequest {
            url(BuildConfig.QUOTES_URL)
        }

        install(ContentNegotiation) {
            json()
        }

        install(Logging) {
            logger = Logger.ANDROID
            level = LogLevel.BODY
        }
    }
}