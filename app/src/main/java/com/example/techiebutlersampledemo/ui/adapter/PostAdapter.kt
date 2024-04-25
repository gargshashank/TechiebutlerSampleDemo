package com.example.techiebutlersampledemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.techiebutlersampledemo.databinding.PostAdapterLayoutBinding
import com.example.techiebutlersampledemo.ui.model.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostAdapter(private val onItemClick:(Post?)->Unit) : PagingDataAdapter<Post,
        PostAdapter.PostViewHolder>(diffCallback) {


    inner class PostViewHolder(
        val binding: PostAdapterLayoutBinding
    ) :ViewHolder(binding.root)

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem == newItem
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            PostAdapterLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val postData = getItem(position)

        holder.binding.apply {

            holder.itemView.apply {
                holder.itemView.setOnClickListener { onItemClick(postData) }
                tvTitle.text = postData?.title?:""
                // Perform heavy computation asynchronously
                CoroutineScope(Dispatchers.Default).launch {
                    val additionalDetails = getAdditionalDetails(postData)
                    withContext(Dispatchers.Main) {
                        // Update UI with additional details
                        tvBody.text = additionalDetails
                    }
                }
            }
        }



    }
    private suspend fun getAdditionalDetails(post: Post?): String {
        // Simulate heavy computation
        delay(1000) // Placeholder for actual heavy computation
        // Return additional details
        return "Additional details for post ${post?.id}"
    }

}