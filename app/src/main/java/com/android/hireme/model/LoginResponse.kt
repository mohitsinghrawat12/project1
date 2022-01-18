package com.android.hireme.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.parcelize.Parcelize
import java.io.Serializable


data class LoginResponse(
    @JsonProperty("data")
    var data: LoginDetail,
    @JsonProperty("message")
    var message: String,
    @JsonProperty("success")
    var success: Int
) : Serializable

data class LoginDetail(
    var profile_image: String,
    var user_type: Int,
    var id: Int,
    var firstname: String,
    var lastname: String,
    var phonenumber: String,
    var address: String,
    var status: Int,
    var createdat: String,
 ) : Serializable

