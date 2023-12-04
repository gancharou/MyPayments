package com.example.mypayments.data.dto

import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface MyPaymentsAPI {

    @Headers("app-key:12345", "v:1")
    @POST("login")
    suspend fun authorization(
        @Body jsonObject: JsonObject
    ): ResponseAuthorizationDTO

    @Headers("app-key:12345", "v:1")
    @GET("payments")
    suspend fun getPayments(
        @Header("token") token:String
    ): Flow<List<PaymentsDTO.Response>>
}