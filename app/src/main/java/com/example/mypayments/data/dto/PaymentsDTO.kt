package com.example.mypayments.data.dto


import com.google.gson.annotations.SerializedName

data class PaymentsDTO(
    @SerializedName("response")
    val response: List<Response>,
    @SerializedName("success")
    val success: String
) {
    data class Response(
        @SerializedName("amount")
        val amount: Any?,
        @SerializedName("created")
        val created: Int?,
        @SerializedName("id")
        val id: Int,
        @SerializedName("title")
        val title: String
    )
}