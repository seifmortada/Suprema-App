package com.seifmortada.applications.suprema.ui.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.seifmortada.applications.suprema.data.remote.apis.AuthService
import com.seifmortada.applications.suprema.data.remote.request.RequestBody
import com.seifmortada.applications.suprema.data.remote.request.UserLogin
import com.seifmortada.applications.suprema.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val authService: AuthService by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            loginButton.setOnClickListener {
                val username = binding.userName.text.toString()
                val password = binding.password.text.toString()

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "Please enter both username , password and IP Address",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    val userLogin = UserLogin(username, password)
                    val requestBody = RequestBody(User = userLogin)
                    performLogin(requestBody)

                }
            }
        }
    }

    private fun performLogin(requestBody: RequestBody) {
        lifecycleScope.launch {
            try {
                val response = authService.login(requestBody)
                if (response.isSuccessful) {
                    // Extract the bs-session-id from the response headers
                    val sessionId = response.headers()["bs-session-id"]

                    sessionId?.let {
                        // Save the session ID in SharedPreferences
                        val sharedPreferences = requireContext().getSharedPreferences(
                            "MyAppPrefs",
                            Context.MODE_PRIVATE
                        )
                        val editor = sharedPreferences.edit()
                        editor.putString("bs-session-id", it)
                        editor.apply()
                        // Navigate to doors fragment
                        findNavController().navigate(
                            LoginFragmentDirections.actionLoginFragmentToHomeFragment(
                                response.body()!!.user.name
                            )
                        )

                            Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_LONG)
                                .show()
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "User Name or Password Incorrect",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (e: Exception) {
                Log.i("TAG", "performLoginError:${e.message} ")

            }
        }
    }
}
