package com.example.data.di

import com.example.data.repository.QuotesRepositoryImpl
import com.example.data.service.ApiService
import com.example.data.service.ApiServiceImpl
import com.example.data.service.ConnectionWatcher
import com.example.data.service.SocketClient
import com.example.data.service.SocketClientImpl
import com.example.data.service.ktorHttp
import com.example.domain.repository.QuotesRepository
import org.koin.dsl.module

val dataModule = module {
    single { ConnectionWatcher(get()) }

    factory<SocketClient> { SocketClientImpl() }
    factory<ApiService> { ApiServiceImpl(ktorHttp) }

    single<QuotesRepository> { QuotesRepositoryImpl(get(), get()) }
}