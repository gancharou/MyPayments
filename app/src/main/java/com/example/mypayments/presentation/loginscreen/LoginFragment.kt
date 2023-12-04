package com.example.mypayments.presentation.loginscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mypayments.presentation.theme.MyPaymentsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val safeArgs: LoginFragmentArgs by navArgs()

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            MyPaymentsTheme {
                LoginScreen(viewModel, safeArgs.login)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvents()
    }

    private fun observeEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.events.collect { event ->
                when (event) {
                    is LoginEvents.RouteToPayments -> {
                        navigateToPaymentsFragment()
                    }

                    is LoginEvents.RouteToAuthorization -> {
                        findNavController().popBackStack()
                    }
                }
            }
        }
    }

    private fun navigateToPaymentsFragment() {
        val directions =
            LoginFragmentDirections.actionLoginFragmentToPaymentsFragment(safeArgs.token)
        findNavController().navigate(directions)
    }
}