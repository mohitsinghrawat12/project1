package com.android.hireme.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.hireme.model.LoginResponse
import com.android.hireme.service.RetrofitHelper
import com.android.hireme.service.interfaces.ILoginAPI
import com.android.hireme.util.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val loginResponse = MutableLiveData<Resource<LoginResponse>>()

    fun getLoginResponse(): LiveData<Resource<LoginResponse>> {
        return loginResponse
    }

}