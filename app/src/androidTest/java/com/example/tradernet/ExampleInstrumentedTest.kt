package com.example.tradernet

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.domain.ext.orZero
import com.example.domain.ext.roundTo
import com.example.domain.request.Requests
import com.example.domain.request.Tickers
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun testRequiredEventName() {
        val params =
            EventRequest(Requests.REALTIME_QUOTES(), listOf(Tickers.MRKS(), Tickers.AAPL_US()))

        assertEquals("[\"realtimeQuotes\",[\"MRKS\",\"AAPL.US\"]]", params.toString())
    }

    @Test
    fun testRequests() {
        assertEquals(Requests.REALTIME_QUOTES(), "realtimeQuotes")
    }

    @Test
    fun testTickers() {
        assertEquals(Tickers.ANH_US(), "ANH.US")
    }

    @Test
    fun testRound() {
        val minStep = 0.01
        val num = 0.0
        val result = num.roundTo(minStep)

        assertEquals(result, 184.20.toString())
    }

    @Test
    fun testDoubleZero() {
        assert(158.0.orZero() > 0)
    }
}