package com.rts.spacex_launch.launchList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rts.spacex_launch.databinding.ItemLaunchBinding
import com.rts.spacex_launch.domain.Launch


class MyLaunchItemRecyclerViewAdapter: ListAdapter<Launch,LaunchViewHolder>(LaunchDiffCallback){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLaunchBinding.inflate(layoutInflater, parent, false)
        return LaunchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item!!)
    }


}
object LaunchDiffCallback: DiffUtil.ItemCallback<Launch>() {

    override fun areItemsTheSame(oldItem: Launch, newItem: Launch): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Launch, newItem: Launch): Boolean {
        return oldItem == newItem
    }
}

class LaunchViewHolder(
    private val binding: ItemLaunchBinding,
): RecyclerView.ViewHolder(binding.root){

    fun bind(launch:Launch) {
        binding.launch=launch
    }
}