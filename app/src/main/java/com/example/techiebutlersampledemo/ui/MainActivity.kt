package com.example.techiebutlersampledemo.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.techiebutlersampledemo.databinding.ActivityMainBinding
import com.example.techiebutlersampledemo.ui.adapter.PostAdapter
import com.example.techiebutlersampledemo.ui.model.Post
import com.example.techiebutlersampledemo.ui.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        loadData()

    }

    private fun setupRecyclerView() {

        postAdapter = PostAdapter { post -> navigateToDetail(post) }

        binding.recyclerView.apply {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(this.context)
            setHasFixedSize(true)
        }

    }

    private fun loadData() {
        lifecycleScope.launch {
            viewModel.listData.collect {
                Log.d("Android", "load: $it")
                postAdapter.submitData(it)
            }

        }
    }
    private fun navigateToDetail(post: Post?) {
        // Implement navigation to detail activity using Intent
        val intent = Intent(this, PostDetailActivity::class.java)
        intent.putExtra("postId", post?.id)
        intent.putExtra("postDetail", post?.body)
        intent.putExtra("postTitle", post?.title)
        startActivity(intent)
    }



}