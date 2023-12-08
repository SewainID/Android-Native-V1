package com.sewain.mobileapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sewain.mobileapp.data.CatalogRepository
import com.sewain.mobileapp.data.local.entity.CatalogEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest

class HomeScreenViewModel(private val catalogRepository: CatalogRepository) : ViewModel()  {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    @OptIn(ExperimentalCoroutinesApi::class)
    val catalogs: Flow<PagingData<CatalogEntity>> = searchQuery.flatMapLatest { query ->
        catalogRepository.getCatalogs(query).cachedIn(viewModelScope)
    }
    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }
}