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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AuthorizationScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = "Авторизация:")

            val textLogin = rememberSaveable { mutableStateOf("") }
            TextField(
                value = textLogin.value,
                onValueChange = {
                    textLogin.value = it
                },
                label = { Text(text = "Login") },
                modifier = Modifier.fillMaxWidth()
            )

            val textPassword = rememberSaveable { mutableStateOf("") }
            TextField(
                value = textPassword.value,
                onValueChange = {
                    textPassword.value = it
                },
                label = { Text(text = "Password") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Войти")
            }
        }
    }
}