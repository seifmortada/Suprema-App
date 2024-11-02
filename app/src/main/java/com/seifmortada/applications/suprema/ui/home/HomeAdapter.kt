package com.seifmortada.applications.suprema.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.seifmortada.applications.suprema.R
import com.seifmortada.applications.suprema.databinding.HomeItemBinding
import com.seifmortada.applications.suprema.data.model.HomeItem

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {
    private var itemList: List<HomeItem> = emptyList()

    fun setData(itemList: List<HomeItem>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

    inner class MyViewHolder(val binding: HomeItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            HomeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        val currentItem = itemList[position]
        holder.binding.imageHomeItem.setImageResource(currentItem.image)
        holder.binding.nameHomeItem.text = currentItem.text
        holder.binding.cardItem.setOnClickListener {
            when(position){
                0->{findNavController(holder.itemView).navigate(R.id.action_homeFragment_to_usersFragment)}
                1->{findNavController(holder.itemView).navigate(R.id.action_homeFragment_to_doorsFragment)}
                else-> Unit
            }
        }
    }

    override fun getItemCount() = itemList.size


}