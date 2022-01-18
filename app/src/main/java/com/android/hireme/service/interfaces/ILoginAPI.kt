package com.android.hireme.service.interfaces

import com.android.hireme.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ILoginAPI {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
        "Platform: android")
    @GET("admin/LoginApi")
    fun getLogin(@Query("device_type") deviceType: Int
                 ,@Query("password") password: String
                 ,@Query("type") type: String
                 ,@Query("username") username: String): Call<LoginResponse>
}