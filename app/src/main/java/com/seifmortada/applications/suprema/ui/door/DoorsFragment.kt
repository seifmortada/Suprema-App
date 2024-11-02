package com.seifmortada.applications.suprema.ui.door

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.seifmortada.applications.suprema.data.remote.apis.DoorService
import com.seifmortada.applications.suprema.databinding.FragmentDoorsBinding
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class DoorsFragment : Fragment() {
    lateinit var binding: FragmentDoorsBinding
    lateinit var adapter: DoorAdapter
    private val doorService: DoorService by inject()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDoorsBinding.inflate(inflater)
        adapter = DoorAdapter { doorId ->
            lifecycleScope.launch {
                doorService.deleteDoor(doorId.toString())
            }
        }
        binding.recyclerView.adapter = adapter
        fetchDoors()
        return binding.root
    }

    private fun fetchDoors() {

        lifecycleScope.launch {
            try {
                val response = doorService.getDoors()
                if (response.isSuccessful) {
                    val doorResponse = response.body()
                    doorResponse?.let {
                        adapter.setData(it.DoorCollection.rows)
                    }
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