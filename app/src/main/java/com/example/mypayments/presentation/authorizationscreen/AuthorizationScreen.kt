package com.example.mypayments.presentation.authorizationscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AuthorizationScreen(viewModel: AuthorizationViewModel) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = "Авторизация:")

            val textLogin = rememberSaveable { mutableStateOf("") }
            Login(textLogin)

            val textPassword = rememberSaveable { mutableStateOf("") }
            Password(textPassword)
            Button(
                onClick = { viewModel.goToLogin(textLogin.value, textPassword.value) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Войти")
            }
        }
    }
}

@Composable
private fun Password(textPassword: MutableState<String>) {
    TextField(
        value = textPassword.value,
        onValueChange = {
            textPassword.value = it
        },
        label = { Text(text = "Password") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun Login(textLogin: MutableState<String>) {
    TextField(
        value = textLogin.value,
        onValueChange = {
            textLogin.value = it
        },
        label = { Text(text = "Login") },
        modifier = Modifier.fillMaxWidth()
    )
}