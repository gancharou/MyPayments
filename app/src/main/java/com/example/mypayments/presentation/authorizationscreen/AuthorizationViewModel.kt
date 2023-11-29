package com.example.mypayments.presentation.authorizationscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

sealed interface AuthorizationEvents {
    object ErrorLogin : AuthorizationEvents
    data class RouteToLogin(val login: String, val password: String) : AuthorizationEvents
}

class AuthorizationViewModel : ViewModel() {

    private val _events = Channel<AuthorizationEvents>()
    val events get() = _events.receiveAsFlow()

    fun goToLogin(login: String, password: String) {
        viewModelScope.launch {
            _events.send(AuthorizationEvents.RouteToLogin(login, password))
        }
    }
}