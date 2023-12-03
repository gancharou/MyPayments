package com.example.mypayments.domain

sealed interface ResponseAuthorization {
    data class Error(val code: Int, val message: String) : ResponseAuthorization
    data class Successful(val token: String) : ResponseAuthorization
}