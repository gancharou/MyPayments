package com.example.mypayments.presentation.loginscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

sealed interface LoginEvents {
    object RouteToAuthorization : LoginEvents
    object RouteToPayments : LoginEvents
}

class LoginViewModel : ViewModel() {
    private val _events = Channel<LoginEvents>()
    val events get() = _events.receiveAsFlow()

    fun onGoOutClick() {
        viewModelScope.launch { _events.send(LoginEvents.RouteToAuthorization) }
    }

    fun onGetPayments() {
        viewModelScope.launch { _events.send(LoginEvents.RouteToAuthorization) }
    }
}