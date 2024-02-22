package com.example.tradernet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Quotes
import com.example.domain.entity.QuotesState
import com.example.domain.entity.copyWith
import com.example.domain.repository.QuotesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(private val repository: QuotesRepository) : ViewModel() {

    private val _mainScreenState = MutableStateFlow<List<Quotes>>(emptyList())

    val mainScreenState: StateFlow<List<Quotes>>
        get() = _mainScreenState

    init {
        getQuotes()
    }

    private fun getQuotes() {
        viewModelScope.launch {
            val request = EventRequest(Requests.REALTIME_QUOTES(), Tickers.getAll())
            repository.fetchQuotes(request.toString())
                .catch { it.printStackTrace() }
                .collect {
                    when (it) {
                        is QuotesState.Connected -> {}
                        is QuotesState.Disconnected -> {}
                        is QuotesState.Message -> {
                            val quotes = it.message

                            _mainScreenState.update { list ->
                                val index = list.indexOfFirst { it.c == quotes.c }
                                if (index == -1) {
                                    if (!quotes.c.isNullOrBlank()) {
                                        list.toMutableList().apply { add(quotes) }
                                    } else {
                                        list
                                    }
                                } else {
                                    list.toMutableList()
                                        .apply { set(index, list[index].copyWith(quotes)) }
                                }
                            }
                        }
                    }
                }
        }
    }
}

