package com.example.tradernet

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Quotes
import com.example.domain.entity.QuotesState
import com.example.domain.entity.copyWith
import com.example.domain.repository.QuotesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class MainScreenState(
    val quotes: List<Quotes> = emptyList(),
    val isConnected: Boolean = false,
    val isLoading: Boolean = false
)

class MainViewModel(private val repository: QuotesRepository) : ViewModel() {

    private val _mainScreenState = MutableStateFlow(MainScreenState())

    val mainScreenState: StateFlow<MainScreenState>
        get() = _mainScreenState.asStateFlow()

    init {
        getQuotes()
    }

    fun getQuotes() {
        viewModelScope.launch {

            _mainScreenState.update { state ->
                state.copy(isLoading = true)
            }

            repository.fetchQuotes()
                .catch {
                    _mainScreenState.update { state ->
                        state.copy(isConnected = false, isLoading = false)
                    }
                    println("catch in MainViewModel")
                    it.printStackTrace()
                }
                .collect {
                    when (it) {
                        is QuotesState.Connected -> {
                            Log.println(
                                Log.DEBUG,
                                "SocketClient",
                                "QuotesState.Connected!!!!!!!!!!!!!!!!!!!"
                            )
                            _mainScreenState.update { state ->
                                state.copy(isConnected = true, isLoading = false)
                            }
                        }

                        is QuotesState.Disconnected -> {
                            Log.println(
                                Log.DEBUG,
                                "SocketClient",
                                "QuotesState.Disconnected!!!!!!!!!!!!!!!!!!!"
                            )
                            _mainScreenState.update { state ->
                                state.copy(isConnected = false)
                            }
                        }

                        is QuotesState.Message -> {
                            val quotes = it.message

                            _mainScreenState.update { state ->
                                val list = state.quotes
                                val index = list.indexOfFirst { it.c == quotes.c }
                                if (index == -1) {
                                    if (!quotes.c.isNullOrBlank()) {
                                        state.copy(
                                            quotes = list.toMutableList().apply { add(quotes) })
                                    } else {
                                        state.copy(quotes = list)
                                    }
                                } else {
                                    state.copy(quotes = list.toMutableList()
                                        .apply {
                                            set(
                                                index, list[index]
                                                    .copyWith(quotes)
                                            )
                                        })
                                }
                            }
                        }
                    }
                }
        }
    }
}

