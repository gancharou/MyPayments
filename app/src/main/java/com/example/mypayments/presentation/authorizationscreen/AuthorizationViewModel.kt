package com.example.mypayments.presentation.authorizationscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypayments.domain.Authorization
import com.example.mypayments.domain.Repository
import com.example.mypayments.domain.ResponseAuthorization
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

sealed interface AuthorizationEvents {
    object ErrorLogin : AuthorizationEvents
    object EmptyField : AuthorizationEvents
    data class RouteToLogin(val token: String) : AuthorizationEvents
}

@HiltViewModel
class AuthorizationViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _events = Channel<AuthorizationEvents>()
    val events get() = _events.receiveAsFlow()

    fun goToLogin(login: String, password: String) {

        if (login.isEmpty() || password.isEmpty()) {
            viewModelScope.launch {
                _events.send(AuthorizationEvents.EmptyField)
            }
        } else {
            val authorization = Authorization(login = login, password = password)

            viewModelScope.launch {
                when (val response = repository.login(authorization)) {
                    is ResponseAuthorization.Successful -> {
                        Log.d("qqq", "Token - ${response.token}")
                        _events.send(AuthorizationEvents.RouteToLogin(""))
                    }

                    is ResponseAuthorization.Error -> {
                        Log.d("qqq", "error - ${response.message}")
                        _events.send(AuthorizationEvents.ErrorLogin)
                    }
                }
            }
        }
    }
}