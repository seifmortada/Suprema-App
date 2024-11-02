package com.seifmortada.applications.suprema.ui.users

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seifmortada.applications.suprema.R
import com.seifmortada.applications.suprema.databinding.DevicesItemBinding
import com.seifmortada.applications.suprema.data.remote.response.User

class UsersAdapter(private val onItemDeleted: (userId: Int) -> Unit) : RecyclerView.Adapter<UsersAdapter.MyViewHolder>() {
    private var itemList: MutableList<User> = mutableListOf()

    fun setData(itemList: List<User>) {
        this.itemList = itemList.toMutableList()
        notifyDataSetChanged()
    }

    inner class MyViewHolder(val binding: DevicesItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            DevicesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        val currentItem = itemList[position]
        holder.binding.imageView.setImageResource(R.drawable.users)
        holder.binding.doorName.text = "Name: ${currentItem.name}"
        holder.binding.deviceName.text = "ID: ${currentItem.userId}"
        holder.binding.deviceState.text = "Email: ${currentItem.email}"
        holder.itemView.setOnLongClickListener {
            onItemDeleted(currentItem.userId.toInt())
            deleteItem(position)
            true
        }
    }

    override fun getItemCount() = itemList.size
    fun deleteItem(id: Int) {
        itemList.removeAt(id)
        notifyItemRemoved(id)
        notifyDataSetChanged()
    }
}
