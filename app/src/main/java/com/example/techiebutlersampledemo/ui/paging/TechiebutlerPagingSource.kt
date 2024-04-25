package com.example.techiebutlersampledemo.ui.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.techiebutlersampledemo.ui.api.ApiService
import com.example.techiebutlersampledemo.ui.model.Post

class TechiebutlerPagingSource
    (
    private val apiService: ApiService
) : PagingSource<Int, Post>() {

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, Post> {

        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getPosts(currentPage,10)
            val responseData = mutableListOf<Post>()
            val data = response ?: emptyList()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }
}