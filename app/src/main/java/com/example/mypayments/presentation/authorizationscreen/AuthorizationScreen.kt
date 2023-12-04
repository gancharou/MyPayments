package com.example.mypayments.presentation.authorizationscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
            TextLogin(textLogin)

            val textPassword = rememberSaveable { mutableStateOf("") }
            TextPassword(textPassword)
            ButtonLogIn(viewModel, textLogin, textPassword)
        }
    }
}

@Composable
private fun ButtonLogIn(
    viewModel: AuthorizationViewModel,
    textLogin: MutableState<String>,
    textPassword: MutableState<String>
) {
    Button(
        onClick = {
            viewModel.goToLogin(textLogin.value, textPassword.value)
            textLogin.value = ""
            textPassword.value = ""
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Войти")
    }
}

@Composable
private fun TextPassword(textPassword: MutableState<String>) {
    val passwordVisible = rememberSaveable { mutableStateOf(false) }
    TextField(
        value = textPassword.value,
        onValueChange = {
            textPassword.value = it
        },
        label = { Text(text = "Password") },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        placeholder = { Text("Password") },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (passwordVisible.value)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff
            val description = if (passwordVisible.value) "Hide password" else "Show password"

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = image, description)
            }
        }
    )
}

@Composable
private fun TextLogin(textLogin: MutableState<String>) {
    TextField(
        value = textLogin.value,
        onValueChange = {
            textLogin.value = it
        },
        label = { Text(text = "Login") },
        modifier = Modifier.fillMaxWidth()
    )
}