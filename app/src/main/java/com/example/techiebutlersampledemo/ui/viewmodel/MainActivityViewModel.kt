package com.example.techiebutlersampledemo.ui.viewmodel

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.techiebutlersampledemo.ui.api.ApiService
import com.example.techiebutlersampledemo.ui.paging.TechiebutlerPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel
@Inject
constructor(
    private val apiService: ApiService
) : ViewModel() {

    val listData = Pager(PagingConfig(pageSize = 1)) {
        TechiebutlerPagingSource(apiService)

    }.flow.cachedIn(viewModelScope)

}
























