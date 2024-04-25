package com.example.techiebutlersampledemo.ui.api

import com.example.techiebutlersampledemo.ui.model.Post
import com.example.techiebutlersampledemo.ui.model.ResponseApi
import com.example.techiebutlersampledemo.ui.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.END_POINT)
    suspend fun getPosts(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): List<Post>
}