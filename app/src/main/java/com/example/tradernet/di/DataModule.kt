package com.example.tradernet.di

import com.example.data.repository.QuotesRepositoryImpl
import com.example.data.service.SocketClient
import com.example.data.service.SocketClientImpl
import com.example.domain.repository.QuotesRepository
import org.koin.dsl.module

val dataModule = module {
    factory <SocketClient> { SocketClientImpl() }

    single<QuotesRepository> { QuotesRepositoryImpl(get()) }
}