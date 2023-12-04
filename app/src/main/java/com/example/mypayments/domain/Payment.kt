package com.example.mypayments.domain

data class Payment(
    val id: Int,
    val title: String,
    val amount: Any?,
    val created: Int?
)