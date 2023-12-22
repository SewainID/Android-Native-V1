package com.sewain.mobileapp.ui.screen.create_catalog

import androidx.lifecycle.ViewModel
import com.sewain.mobileapp.data.CatalogRepository
import com.sewain.mobileapp.data.remote.model.Catalog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.HttpException
import java.net.SocketTimeoutException

class CreateCatalogViewModel(private val repository: CatalogRepository) : ViewModel() {
    private val _catalog = MutableStateFlow<Catalog?>(null)
    val catalog: StateFlow<Catalog?>
        get() = _catalog

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean>
        get() = _loading

    private val _message = MutableStateFlow("")
    val message: StateFlow<String>
        get() = _message

    private val _success = MutableStateFlow(false)
    val success: StateFlow<Boolean>
        get() = _success


    suspend fun addCatalog(catalog: Catalog) {
        _loading.value = true
        try {
            //get success message
            repository.addCatalog(catalog)
            _message.value = "Success: You are successfully added the catalog!"
            _success.value = true
        } catch (e: HttpException) {
            //get error message
            _message.value = "Error: You are failed added the catalog"
            _success.value = false
        } catch (e: SocketTimeoutException) {
            _message.value = "Error: Timeout! ${e.message}"
            _success.value = false
        }
    }
}