package com.seifmortada.applications.suprema.ui.users

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.seifmortada.applications.suprema.R
import com.seifmortada.applications.suprema.data.remote.apis.UserService
import com.seifmortada.applications.suprema.databinding.FragmentUsersBinding
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class UsersFragment : Fragment() {
    lateinit var binding: FragmentUsersBinding
    lateinit var adapter: UsersAdapter
    private val userService: UserService by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUsersBinding.inflate(inflater)
        adapter = UsersAdapter() { userId ->
            lifecycleScope.launch {
                userService.deleteUser(userId.toString())
            }
        }
        binding.recyclerView.adapter = adapter
        fetchUsers()
        binding.addButton.setOnClickListener {
            findNavController().navigate(R.id.action_usersFragment_to_addUserFragment)
        }
        return binding.root
    }

    private fun fetchUsers() {

        lifecycleScope.launch {
            try {
                val response = userService.getUsers()

                if (response.isSuccessful) {
                    val userResponse = response.body()
                    val rows = userResponse?.let { it.UserCollection.rows }
                  val filterdList=  rows?.filterNot { it.userId == "1" }
                    adapter.setData(filterdList?.toList()!!)
                } else {
                    Log.i("THE TAG", "fetchDoors: ${response.raw()}")
                    Toast.makeText(
                        requireContext(),
                        "Session Expired. Please log in again.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Failed Connection to Network", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }


}