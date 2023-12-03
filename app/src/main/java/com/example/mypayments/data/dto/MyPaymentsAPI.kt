package com.example.mypayments.data.dto

import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface MyPaymentsAPI {
    
    @Headers("app-key:12345", "v:1")
    @POST("login")
    suspend fun authorization(
        @Body jsonObject: JsonObject
    ): ResponseAuthorizationDTO
}