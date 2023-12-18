package com.sewain.mobileapp.ui.screen.detail_catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.sewain.mobileapp.data.remote.response.CatalogItem
import com.sewain.mobileapp.di.Injection
import com.sewain.mobileapp.ui.CatalogViewModelFactory
import com.sewain.mobileapp.ui.theme.SewainAppTheme
import com.sewain.mobileapp.utils.rp

@Composable
fun DetailCatalogScreen(id : String, viewModel: DetailCatalogViewModel = viewModel(
    factory = CatalogViewModelFactory(Injection.provideCatalogRepository(LocalContext.current))
)
) {
    // React to state changes
    val catalog = viewModel.catalog.value

    // Call fetchCatalog when the composable enters the composition
    LaunchedEffect(id) {
        viewModel.fetchCatalog(id)
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
                    DetailCatalog(catalog)
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
fun DetailCatalog(catalog : CatalogItem){
        Column {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back press */ }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                modifier = Modifier.background(Color.Transparent),
            )

            AsyncImage(
                model = catalog.photoUrl,
                contentDescription = "Product Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            catalog.name?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(8.dp)
                )
            }
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

            catalog.description?.let {
                Text(
                    text = "Description",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(8.dp)
                )
            }
            // Add more detail texts
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${catalog.price.rp()} / ${catalog.dayRent} Days",
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
    DetailCatalog(CatalogItem(id = "1", price = 10000.0, name = "gojo", dayRent = 3, photoUrl = "https://down-id.img.susercontent.com/file/bc2dc2f92c402aa078b5409470625b46", description = "anjay gojo", size = "M"))
}