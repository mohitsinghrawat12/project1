package com.android.hireme

import android.app.Application
import android.content.Context
import com.android.hireme.service.network.HireMeNetwork

class HireMeApplication : Application() {

    private lateinit var mContext: Context;

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
        // Initializng network
        HireMeNetwork.create(mContext)
    }
}