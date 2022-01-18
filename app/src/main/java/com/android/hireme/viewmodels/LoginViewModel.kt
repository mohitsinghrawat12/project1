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

class LoginViewModel : ViewModel() {

    private val loginResponse = MutableLiveData<Resource<LoginResponse>>()

    fun getLoginResponse(): LiveData<Resource<LoginResponse>> {
        return loginResponse
    }

    fun getLogin(email: String, password: String) {

        loginResponse.postValue(Resource.loading(null))
        val login = RetrofitHelper.getInstance().create(ILoginAPI::class.java).getLogin(2, password, "2", email)
        login.enqueue( object: Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if(response.body()?.success==1) {
                    loginResponse.postValue(Resource.success(response.body()))

                }else {
                    loginResponse.postValue(response.body()?.let { Resource.error(it.message, response.body()) })

                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loginResponse.postValue(Resource.error("Something Went Wrong", null))

            }

        })
    }
}