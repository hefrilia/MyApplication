package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemUserBinding
import com.example.myapplication.response.UserItem

class UserAdapter : PagingDataAdapter<UserItem, UserAdapter.MyViewHolder>(DIFF_CALLBACK){
    private lateinit var binding: ItemUserBinding

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: UserItem)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class MyViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(user: UserItem){
            binding.tvUsername.text = "${user.firstName} ${user.lastName}"
            binding.email.text = user.email

            Glide.with(binding.root)
                .load(user.avatar)
                .into(binding.imgUserPhoto)

            binding.root.setOnClickListener{
                onItemClickCallback.onItemClicked(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.MyViewHolder {
        binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: UserAdapter.MyViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null){
            holder.bind(user)
        }
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserItem>(){
            override fun areContentsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}

