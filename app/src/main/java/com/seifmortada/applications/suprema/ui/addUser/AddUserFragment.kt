package com.seifmortada.applications.suprema.ui.addUser

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.seifmortada.applications.suprema.data.remote.apis.UserService
import com.seifmortada.applications.suprema.databinding.FragmentAddUserBinding
import com.seifmortada.applications.suprema.data.remote.request.add_user_dataclasses.AddUser
import com.seifmortada.applications.suprema.data.remote.request.add_user_dataclasses.AddUserDataClass
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.nio.charset.Charset
import java.util.Arrays
import java.util.concurrent.Executor

@RequiresApi(Build.VERSION_CODES.P)
class AddUserFragment : Fragment() {
    lateinit var binding: FragmentAddUserBinding
    private val userService: UserService by inject()
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: androidx.biometric.BiometricPrompt
    private lateinit var promptInfo: PromptInfo
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddUserBinding.inflate(inflater)
        executor = ContextCompat.getMainExecutor(requireContext())
        biometricPrompt = androidx.biometric.BiometricPrompt(this@AddUserFragment, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(
                        requireContext(),
                        "Authentication error: $errString", Toast.LENGTH_SHORT
                    )
                        .show()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(
                        requireContext(),
                        "Authentication succeeded!", Toast.LENGTH_SHORT
                    )
                        .show()
            //        val encryptInfo :ByteArray= result.cryptoObject?.cipher?.doFinal()
//                    val encrypSing = result.cryptoObject?.signature
//                    Log.d("TAGG", "Encrypted information: " +
//                            Arrays.toString(encryptInfo))
//                    Log.e("TAGG",encrypSing.toString() +"Sign")
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(
                        requireContext(), "Authentication failed",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            })

        promptInfo = PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Use account password")
            .build()
        biometricPrompt.authenticate(promptInfo)
        binding.addButton.setOnClickListener {

            binding.apply {
                /*  val name = userName.text.toString()
                  val password = password.text.toString()
                  val email = email.text.toString()
                  val id = id.text.toString()
                  val loginId = loginId.text.toString()

                  if (name.isEmpty() || password.isEmpty() || loginId.isEmpty() || email.isEmpty() || id.isEmpty()) {
                      Toast.makeText(
                          requireContext(),
                          "Please Fill All The Fields",
                          Toast.LENGTH_LONG
                      )
                          .show()
                  } else {*/

                addUser(
                    AddUserDataClass(
                        AddUser(
                            name = "Ali Mahmoud",
                            password = "P@ssw0rd",
                            login_id = "seif",
                            user_id = "191218826",
                            email = "se465@gmail.com",
                            user_ip = "192.115.111.233"
                        )
                    )
                )
            }
        }

        return binding.root
    }

    private fun addUser(user: AddUserDataClass) {
        lifecycleScope.launch {
            try {
                val response = userService.addUser(user)
                if (response.isSuccessful) {
                    Toast.makeText(requireContext(), "Added Successful", Toast.LENGTH_LONG)
                        .show()
                } else {
                    Log.i("TAG", "addUserError:${response} ")
                }
            } catch (e: Exception) {
                Log.i("TAG", "addUserException:${e.message} ")

            }
        }
    }
}
