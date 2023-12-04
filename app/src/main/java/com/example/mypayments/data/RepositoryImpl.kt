package com.example.mypayments.data

import com.example.mypayments.data.dto.MyPaymentsAPI
import com.example.mypayments.domain.Authorization
import com.example.mypayments.domain.Payment
import com.example.mypayments.domain.Repository
import com.example.mypayments.domain.ResponseAuthorization
import com.google.gson.JsonObject

class RepositoryImpl(private val apiService: MyPaymentsAPI) : Repository {
    override suspend fun login(authorization: Authorization): ResponseAuthorization {
        val jsonObject = JsonObject()
        jsonObject.addProperty("login", authorization.login)
        jsonObject.addProperty("password", authorization.password)
        return apiService.authorization(jsonObject).toDomain()
    }

    override suspend fun getPayments(token: String): List<Payment> {
        return apiService.getPayments(token).response.toDomain()
    }
}