package com.example.mypayments.presentation.paymentsscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mypayments.domain.Payment
import com.example.mypayments.domain.Repository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

data class PaymentsViewState(
    val payments: List<Payment>
)

private fun initPaymentsViewState() = PaymentsViewState(payments = emptyList())

sealed interface PaymentsEvents {
    object Error : PaymentsEvents
}

const val TAG_ERROR = "Error"

class PaymentsViewModel @Inject constructor(
    private val token: String,
    private val repository: Repository
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(token: String): PaymentsViewModelFactory
    }

    private val _state: MutableStateFlow<PaymentsViewState> = MutableStateFlow(
        initPaymentsViewState()
    )
    val state: StateFlow<PaymentsViewState> get() = _state.asStateFlow()

    private val _events = Channel<PaymentsEvents>()
    val events get() = _events.receiveAsFlow()

    init {
        getPayments()
    }

    private fun getPayments() {
        viewModelScope.launch {
            try {
                val paymentsFromNW = repository.getPayments(token)
                _state.emit(state.value.copy(payments = paymentsFromNW))
            } catch (throwable: Throwable) {
                viewModelScope.launch {
                    _events.send(PaymentsEvents.Error)
                }
                Log.d(TAG_ERROR, throwable.message.toString())
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