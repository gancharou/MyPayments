package com.example.mypayments.presentation.loginscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(viewModel: LoginViewModel, login: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = "Здравствуйте, $login!")
            ButtonGoToPayments(viewModel)
        }
        ButtonGoOut(viewModel, Modifier.align(Alignment.BottomEnd))
    }
}

@Composable
private fun ButtonGoOut(viewModel: LoginViewModel, modifier: Modifier) {
    Button(
        onClick = { viewModel.onGoOutClick() },
        modifier = modifier
            .wrapContentSize()
            .padding(bottom = 12.dp, end = 12.dp)
    ) {
        Text(text = "Выйти")
    }
}

@Composable
private fun ButtonGoToPayments(viewModel: LoginViewModel) {
    Button(
        onClick = { viewModel.onGetPayments() },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Получить список платежей")
    }
}