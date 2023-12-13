package com.sewain.mobileapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sewain.mobileapp.data.CatalogRepository
import com.sewain.mobileapp.ui.screen.detail_catalog.DetailCatalogViewModel
import com.sewain.mobileapp.ui.screen.home.HomeScreenViewModel

class CatalogViewModelFactory(private val repository: CatalogRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeScreenViewModel::class.java)) {
            return HomeScreenViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(DetailCatalogViewModel::class.java)) {
            return DetailCatalogViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}