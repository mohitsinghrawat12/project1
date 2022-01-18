package com.android.hireme.service.network

import com.android.hireme.service.interfaces.INetworkServiceCreator
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

class RetroFitServiceCreator: INetworkServiceCreator<Retrofit> {

    override fun <S> create(networkLib: Retrofit?, serviceClass: Class<S>): S? {
        return networkLib?.create(serviceClass)
    }

    fun <S> createService(url: String, serviceClass: Class<S>,
                          jacksonConverterFactory: JacksonConverterFactory,
                          rxJava2CallAdapterFactory: RxJava2CallAdapterFactory, okHttpClient: OkHttpClient?): S?{

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(jacksonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)

        okHttpClient?.let {
            retrofitBuilder.client(okHttpClient)
        }
        return create(retrofitBuilder.build(), serviceClass)
    }
}