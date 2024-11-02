package com.seifmortada.applications.suprema.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.seifmortada.applications.suprema.R
import com.seifmortada.applications.suprema.databinding.FragmentHomeBinding
import com.seifmortada.applications.suprema.data.model.HomeItem

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    val adapter = HomeAdapter()

    private val args: HomeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        binding.recyclerView.adapter = adapter
        adapter.setData(fetchItems())
        return binding.root
    }

    private fun fetchItems(): MutableList<HomeItem> {
        val list = mutableListOf<HomeItem>()
        list.add(HomeItem(R.drawable.users, "Users"))
        list.add(HomeItem(R.drawable.door, "Doors"))
        return list

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nameText.text = args.name
    }

}