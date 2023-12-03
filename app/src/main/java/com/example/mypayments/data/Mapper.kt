package com.example.mypayments.data

import com.example.mypayments.data.dto.ResponseAuthorizationDTO
import com.example.mypayments.domain.ResponseAuthorization

fun ResponseAuthorizationDTO.toDomain(): ResponseAuthorization {
    return if (response != null) {
        ResponseAuthorization.Successful(token = response.token)
    } else {
        ResponseAuthorization.Error(code = error.errorCode, message = error.errorMsg)
    }
}