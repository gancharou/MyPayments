package com.example.mypayments.presentation.paymentsscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mypayments.domain.Payment
import java.math.BigDecimal

@Composable
fun PaymentsScreen(state: State<PaymentsViewState>) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Список платежей:")
            Payments(state)
        }
    }
}

@Composable
private fun Payments(state: State<PaymentsViewState>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = state.value.payments, itemContent = { item ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                ItemPayment(item)
            }
        })
    }
}

@Composable
private fun ItemPayment(
    item: Payment,
) {
    val textAmount = when (item.amount) {
        null -> "Данные отсутствуют"
        "" -> "Данные отсутствуют"
        else -> item.amount.toString()
    }

    val textCreated =
        if (item.created == null || item.created.toString().isEmpty()) "Данные отсутствуют"
        else {
            val sdf = java.text.SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss")
            val date = java.util.Date(item.created.toLong() * 1000)
            sdf.format(date)
        }
    Text(text = item.id.toString())
    Text(text = item.title)
    Text(
        text = if (textAmount.contains('E')) BigDecimal(textAmount).toPlainString() else textAmount
    )
    Text(
        text = textCreated
    )
}