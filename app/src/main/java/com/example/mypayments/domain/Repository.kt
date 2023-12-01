package com.example.mypayments.domain

import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun login(authorization: Authorization): ResponseFromLogin
    suspend fun getPayments(token: String): Flow<List<Payment>>
}