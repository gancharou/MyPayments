package com.example.mypayments.domain

sealed interface ResponseFromLogin {
    data class Error(val message: String) : ResponseFromLogin
    data class Successful(val token: String) : ResponseFromLogin
}