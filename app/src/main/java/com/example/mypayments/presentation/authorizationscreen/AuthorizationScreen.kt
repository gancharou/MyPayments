package com.example.mypayments.presentation.authorizationscreen

import androidx.compose.runtime.Composable
import com.example.mypayments.presentation.authorizationscreen.widget.LoginScreen
import com.example.mypayments.presentation.authorizationscreen.widget.NoLoginScreen

@Composable
fun AuthorizationScreen(isLogin: Boolean) {
    if (isLogin) {
        LoginScreen()
    } else {
        NoLoginScreen()
    }
}