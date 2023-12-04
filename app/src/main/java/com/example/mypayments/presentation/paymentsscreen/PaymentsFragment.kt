package com.example.mypayments.presentation.paymentsscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.mypayments.R
import com.example.mypayments.presentation.theme.MyPaymentsTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PaymentsFragment : Fragment() {

    private val safeArgs: PaymentsFragmentArgs by navArgs()

    private val viewModel: PaymentsViewModel by viewModels {
        viewModelFactory.create(safeArgs.token)
    }

    @Inject
    lateinit var viewModelFactory: PaymentsViewModel.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            val state = viewModel.state.collectAsState()
            MyPaymentsTheme {
                PaymentsScreen(state)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvents()
    }

    private fun observeEvents() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.events.collect { events ->
                    when (events) {
                        is PaymentsEvents.Error -> Toast.makeText(
                            requireContext(),
                            getString(R.string.error_other),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}