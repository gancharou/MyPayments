package com.example.mypayments.data

import com.example.mypayments.data.dto.PaymentsDTO
import com.example.mypayments.data.dto.ResponseAuthorizationDTO
import com.example.mypayments.domain.Payment
import com.example.mypayments.domain.ResponseAuthorization

internal fun ResponseAuthorizationDTO.toDomain(): ResponseAuthorization {
    return if (response != null) {
        ResponseAuthorization.Successful(token = response.token)
    } else {
        ResponseAuthorization.Error(code = error.errorCode, message = error.errorMsg)
    }
}

internal fun List<PaymentsDTO.Response>.toDomain() = map {
    it.toDomain()
}

internal fun PaymentsDTO.Response.toDomain() =
    Payment(id = id, title = title, amount = amount, created = created)