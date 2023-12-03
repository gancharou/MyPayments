package com.example.mypayments.data.dto


import com.google.gson.annotations.SerializedName

data class ResponseAuthorizationDTO(
    @SerializedName("response")
    val response: Response,
    @SerializedName("error")
    val error: Error,
    @SerializedName("success")
    val success: String
) {
    data class Response(
        @SerializedName("token")
        val token: String
    )

    data class Error(
        @SerializedName("error_code")
        val errorCode: Int,
        @SerializedName("error_msg")
        val errorMsg: String,
    )
}