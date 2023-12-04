package com.example.mypayments.presentation.authorizationscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mypayments.R
import com.example.mypayments.presentation.theme.MyPaymentsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
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
                        navigateToLoginFragment(event.login, event.token)
                    }

                    is AuthorizationEvents.ErrorLogin -> {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.wrong_login_or_password),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }

                    is AuthorizationEvents.EmptyField -> {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.fill_in_fields),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }

                    is AuthorizationEvents.ErrorOther -> {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.error_other),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            }
        }
    }

    private fun navigateToLoginFragment(login: String, token: String) {
        val directions =
            AuthorizationFragmentDirections.actionAuthorizationFragmentToLoginFragment(login, token)
        findNavController().navigate(directions)
    }
}