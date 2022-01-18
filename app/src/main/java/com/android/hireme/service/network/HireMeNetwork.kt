package com.android.hireme.service.network

import android.content.Context
import android.util.Log
import okhttp3.OkHttpClient
import java.lang.AssertionError
import java.lang.Exception
import java.lang.IllegalStateException
import java.security.GeneralSecurityException
import java.security.KeyManagementException
import java.security.KeyStore
import java.security.NoSuchAlgorithmException
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

object HireMeNetwork {
    private var mHttpClient : OkHttpClient? = null
    private var mClientBuilder : OkHttpClient.Builder = createSSLHttpClientBuilder()

    private fun createSSLHttpClientBuilder(): OkHttpClient.Builder {

        val builder = OkHttpClient.Builder()

        try {
            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(null, null, null)
            builder.sslSocketFactory(sslContext.socketFactory, systemDefaultTrustManager())
        } catch (e: NoSuchAlgorithmException) {
            Log.w("utills" , "TLS failure")

        } catch (e: KeyManagementException) {
            Log.w("utills" , "TLS failure")
        }
        return builder
    }

    private fun systemDefaultTrustManager(): X509TrustManager {
        try {
            val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
            trustManagerFactory.init(null as KeyStore?)
            val trustManagers = trustManagerFactory.trustManagers
            if (trustManagers.size !=1 ||  trustManagers[0] !is X509TrustManager) {
                throw IllegalStateException()
            }
            return trustManagers[0] as X509TrustManager
        } catch (e: GeneralSecurityException) {
            throw AssertionError()
        }
    }

    fun create(context: Context){

        mHttpClient?.let {
            throw  Exception("Network already created")
        }
        mHttpClient = mClientBuilder.build()
    }

    fun getClient() : OkHttpClient? {
        return mHttpClient
    }
}