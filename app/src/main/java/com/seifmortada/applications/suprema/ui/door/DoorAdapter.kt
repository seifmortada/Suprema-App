package com.seifmortada.applications.suprema.ui.door

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seifmortada.applications.suprema.databinding.DevicesItemBinding
import com.seifmortada.applications.suprema.data.remote.response.Door

class DoorAdapter(private val onItemDeleted: (itemID: Int) -> Unit) :
    RecyclerView.Adapter<DoorAdapter.MyViewHolder>() {
    private var itemList: MutableList<Door> = mutableListOf()

    fun setData(itemList: List<Door>) {
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
        holder.binding.doorName.text = "Name: ${currentItem.name}"
        holder.binding.deviceName.text = "Device: ${currentItem.entry_device_id.name}"
        holder.binding.deviceState.text = "ID: ${currentItem.id}"
        holder.itemView.setOnLongClickListener {
            onItemDeleted(currentItem.id.toInt())
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
