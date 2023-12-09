package com.sewain.mobileapp.ui.screen.detail_catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.sewain.mobileapp.data.remote.response.CatalogItem
import com.sewain.mobileapp.di.Injection
import com.sewain.mobileapp.ui.CatalogViewModelFactory
import com.sewain.mobileapp.ui.component.SearchBar
import com.sewain.mobileapp.ui.screen.home.CatalogsHome
import com.sewain.mobileapp.ui.screen.home.HeaderHome
import com.sewain.mobileapp.ui.screen.home.HomeScreenViewModel
import com.sewain.mobileapp.ui.theme.SewainAppTheme

@Composable
fun DetailCatalogScreen(id : String) {
    val catalogViewModel: DetailCatalogViewModel = viewModel()

    // React to state changes
    val catalog = catalogViewModel.catalog.value

    // Call fetchCatalog when the composable enters the composition
    LaunchedEffect(id) {
        catalogViewModel.fetchCatalog(id)
    }

    SewainAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            }
            if (catalog != null) {
                Column(
                    modifier = Modifier
                        .padding(15.dp)
                    ,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){
                    DetailCatalog()
                }
            } else {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailCatalog(){
        Column {
            TopAppBar(
                title = { Text("Product Details") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back press */ }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
            AsyncImage(
                model = "https://storage.googleapis.com/sewain/etc/profile.png",
                contentDescription = "Product Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Text(
                text = "Gojo Satoru",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(8.dp)
            )
            // Add rating bar here
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(8.dp)
            ) {
                Button(onClick = { /* TODO */ }) {
                    Text("L")
                }
                Button(onClick = { /* TODO */ }) {
                    Text("M")
                }
                // Add more sizes as needed
            }
            Text(
                text = "Description",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(8.dp)
            )
            // Add more detail texts
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Rp90.000 / 3 Days",
                    style = MaterialTheme.typography.bodyLarge
                )
                Button(onClick = { /* Handle booking */ }) {
                    Text("Book Now")
                }
            }
        }
}

@Preview
@Composable
fun PreviewDetailCatalog(){
    DetailCatalog()
}