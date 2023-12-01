package com.example.mypayments.presentation.paymentsscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mypayments.domain.Payment
import com.example.mypayments.domain.Repository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class PaymentsViewState(
    val payments: List<Payment>
)

private fun initPaymentsViewState() = PaymentsViewState(payments = emptyList())

class PaymentsViewModel @Inject constructor(
    private val token: String,
    private val repository: Repository
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(token: String):PaymentsViewModelFactory
    }

    private val _state: MutableStateFlow<PaymentsViewState> = MutableStateFlow(
        initPaymentsViewState()
    )
    val state: StateFlow<PaymentsViewState> get() = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val payments = repository.getPayments(token)
            payments.collect {
//                _state.emit(s)
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class PaymentsViewModelFactory @AssistedInject constructor(
    @Assisted private val token: String,
    private val repository: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PaymentsViewModel(
            token = token,
            repository = repository
        ) as T
    }
}