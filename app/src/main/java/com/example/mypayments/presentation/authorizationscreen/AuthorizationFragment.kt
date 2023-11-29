package com.example.mypayments.presentation.authorizationscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mypayments.presentation.theme.MyPaymentsTheme
import kotlinx.coroutines.launch

class AuthorizationFragment : Fragment() {

    private val viewModel: AuthorizationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            MyPaymentsTheme {
                AuthorizationScreen(viewModel)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.events.collect { event ->
                when (event) {
                    is AuthorizationEvents.RouteToLogin -> {
                        navigateToLoginFragment()
                    }

                    is AuthorizationEvents.ErrorLogin -> {}
                }
            }
        }
    }

    private fun navigateToLoginFragment() {
        val directions =
            AuthorizationFragmentDirections.actionAuthorizationFragmentToLoginFragment()
        findNavController().navigate(directions)
    }
}