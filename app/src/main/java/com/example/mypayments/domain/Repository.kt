package com.example.mypayments.domain

interface Repository {
    suspend fun login(authorization: Authorization):ResponseFromLogin
}