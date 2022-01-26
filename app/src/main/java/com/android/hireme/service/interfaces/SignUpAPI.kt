package com.android.hireme.service.interfaces

import com.android.hireme.model.LoginResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SignUpAPI {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
        "Platform: android")
    @GET("admin/UserRegisterApi")
    fun signUp(@Query("device_type") deviceType: Int
                 ,@Query("password") password: String
                 ,@Query("firstname") firstname: String
                 ,@Query("lastname") lastname: String
                 ,@Query("phonenumber") phoneNumber: String
                 ,@Query("device_token") device_token: String
                 ,@Query("address") address: String
                 ,@Query("type") type: Int
                 ,@Query("email") email: String
                 ,@Query("status") status:Int=1
                 ,@Query("username") username: String): Call<LoginResponse>
}