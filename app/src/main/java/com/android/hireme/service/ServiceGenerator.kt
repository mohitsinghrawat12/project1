package com.android.hireme.service

import android.text.TextUtils
import com.android.hireme.service.network.HireMeNetwork
import com.android.hireme.service.network.RetroFitServiceCreator
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

object ServiceGenerator {

    private val networkServiceCreator: RetroFitServiceCreator = RetroFitServiceCreator()

    fun <S> createService(baseUrl: String, serviceClass: Class<S>) : S? {
        if (TextUtils.isEmpty(baseUrl)) {
            return null
        }

        var url = baseUrl
        if(!url.endsWith("/")){
            url = url.plus("/")
        }

        return networkServiceCreator.createService(url, serviceClass, JacksonConverterFactory.create(), RxJava2CallAdapterFactory.create(), HireMeNetwork.getClient())
    }
}