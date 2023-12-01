package com.example.mypayments.data

import com.example.mypayments.data.dto.MyPaymentsAPI
import com.example.mypayments.domain.Authorization
import com.example.mypayments.domain.Payment
import com.example.mypayments.domain.Repository
import com.example.mypayments.domain.ResponseFromLogin
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(private val apiService: MyPaymentsAPI) : Repository {
    override suspend fun login(authorization: Authorization): ResponseFromLogin {
        TODO("Not yet implemented")
    }

    override suspend fun getPayments(token: String): Flow<List<Payment>> {
        TODO("Not yet implemented")
    }
}