package com.example.mypayments.domain

import kotlinx.coroutines.flow.StateFlow

interface Repository {
    suspend fun login(authorization: Authorization): ResponseAuthorization
    suspend fun getPayments(token: String): StateFlow<List<Payment>>
}