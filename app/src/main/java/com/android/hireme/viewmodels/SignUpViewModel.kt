package com.android.hireme.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.hireme.model.LoginResponse
import com.android.hireme.service.RetrofitHelper
import com.android.hireme.service.interfaces.SignUpAPI
import com.android.hireme.util.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel :ViewModel(){
    private val signUpResponse = MutableLiveData<Resource<LoginResponse>>()

    fun getSignUpResponse(): LiveData<Resource<LoginResponse>> {
        return signUpResponse
    }

    fun signUp(email: String, password: String, firstName: String, lastName: String,userRole:String) {

        signUpResponse.postValue(Resource.loading(null))
        val login = RetrofitHelper.getInstance().create(SignUpAPI::class.java).signUp(2, password, firstName,
            lastName,"12345677","23456789","Banda",1,email,1,email)
        login.enqueue( object: Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if(response.body()?.success==1) {
                    signUpResponse.postValue(Resource.success(response.body()))

                }else {
                    signUpResponse.postValue(response.body()?.let { Resource.error(it.message, response.body()) })

                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                signUpResponse.postValue(Resource.error("Something Went Wrong", null))

            }

        })
    }

    fun isValidForm(email: String, password: String, firstName: String, lastName: String,userRole:String): Boolean {
        if (email.isEmpty())return false
        if (password.isEmpty())return false
        if (firstName.isEmpty())return false
        if (lastName.isEmpty())return false
        if (userRole.isEmpty())return false
        return true
    }
}