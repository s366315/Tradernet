package com.example.domain.entity

sealed class QuotesState {
    data object Connected : QuotesState()
    data class Message(val message: Quotes) : QuotesState()
    data object Disconnected : QuotesState()
}