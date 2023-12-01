package com.example.mypayments.presentation.paymentsscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mypayments.presentation.theme.MyPaymentsTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PaymentsFragment : Fragment() {

    private val viewModel: PaymentsViewModel by viewModels {
        viewModelFactory.create("")
    }

    @Inject
    lateinit var viewModelFactory: PaymentsViewModel.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            MyPaymentsTheme {
                PaymentsScreen()
            }
        }
    }
}