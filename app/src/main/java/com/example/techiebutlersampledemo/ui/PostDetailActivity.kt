package com.example.techiebutlersampledemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.techiebutlersampledemo.databinding.ActivityPostDetailBinding

class PostDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val postId = intent.getIntExtra("postId", -1)
        val postDetail = intent.getStringExtra("postDetail")
        val postTitle = intent.getStringExtra("postTitle")

        // set data on UI

        binding.tvTitle.text = postTitle?:""
        binding.tvBody.text = postDetail?:""
        binding.tvId.text = (postId ?:-1).toString()
        clickListener()


    }

    private fun clickListener(){
        binding.backButton.setOnClickListener {
            finish()
        }
    }
}