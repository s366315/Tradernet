package com.example.data.service

import java.security.cert.X509Certificate
import javax.net.ssl.X509TrustManager

class MainTrustManager : X509TrustManager {
    override fun checkClientTrusted(certificates: Array<out X509Certificate>?, authType: String?) = Unit

    override fun checkServerTrusted(certificates: Array<out X509Certificate>?, authType: String?) = Unit

    override fun getAcceptedIssuers(): Array<X509Certificate> = emptyArray()
}