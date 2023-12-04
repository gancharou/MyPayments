package com.example.mypayments.domain

interface Repository {
    suspend fun login(authorization: Authorization): ResponseAuthorization
    suspend fun getPayments(token: String): List<Payment>
}