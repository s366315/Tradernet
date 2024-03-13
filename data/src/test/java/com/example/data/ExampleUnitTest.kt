package com.example.data

import com.example.data.request.GetTopSecuritiesRequest
import com.example.data.service.ApiServiceImpl
import com.example.data.service.ktorHttp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun ktorTickersTest(): Unit = runBlocking {
        val api = ApiServiceImpl(ktorHttp)

        val event = async(Dispatchers.IO) {
            api.getTopSecurities(query = GetTopSecuritiesRequest())
        }.await().getOrNull()?.tickers?.joinToString()

        assert(!event.isNullOrBlank())
    }
}