package com.example.data.entity

sealed class SocketState {
    data object Connected : SocketState()
    data class Message(val message: String) : SocketState()
    data object Disconnected : SocketState()
}