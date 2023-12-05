package com.sewain.mobileapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sewain.mobileapp.data.CatalogRepository
import com.sewain.mobileapp.data.local.entity.CatalogEntity
import kotlinx.coroutines.flow.Flow

class HomeScreenViewModel(private val catalogRepository: CatalogRepository) : ViewModel()  {


    val catalogs: Flow<PagingData<CatalogEntity>> =
        catalogRepository.getCatalogs().cachedIn(viewModelScope)

}