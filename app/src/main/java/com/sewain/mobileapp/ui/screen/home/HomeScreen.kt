package com.sewain.mobileapp.ui.screen.home

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.sewain.mobileapp.R
import com.sewain.mobileapp.data.local.entity.CatalogEntity
import com.sewain.mobileapp.data.remote.response.CatalogItem
import com.sewain.mobileapp.di.Injection
import com.sewain.mobileapp.ui.CatalogViewModelFactory
import com.sewain.mobileapp.ui.component.GridCatalogItem
import com.sewain.mobileapp.ui.component.SearchBar
import com.sewain.mobileapp.ui.navigation.Screen
import com.sewain.mobileapp.ui.theme.SewainAppTheme

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeScreenViewModel = viewModel(
    factory = CatalogViewModelFactory(Injection.provideCatalogRepository(LocalContext.current))
),) {
    val items = viewModel.catalogs.collectAsLazyPagingItems()
    val query = viewModel.searchQuery.collectAsState()

    SewainAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .padding(15.dp)
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                HeaderHome(navController)
                SearchBar(
                    query = query.value,
                    onQueryChange = viewModel::setSearchQuery,
                    modifier = Modifier
                )
//                BannerHome()
                CatalogsHome(items)
            }
        }
    }
}

@Composable
fun CatalogsHome(catalogItems: LazyPagingItems<CatalogEntity>) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(R.string.new_arrivals),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(4.dp),
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(catalogItems.itemCount) { index ->
                catalogItems[index]?.let { catalogItem ->
                    GridCatalogItem(catalogItem)
                }
            }
        }

        when (catalogItems.loadState.refresh) {
            is LoadState.Loading -> {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            is LoadState.Error, is LoadState.NotLoading -> {
                if (catalogItems.itemCount == 0) {
                    Button(
                        onClick = { catalogItems.retry() },
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        Text("Reload")
                    }
                }
            }
            else -> { /* Do nothing for other states */ }
        }
    }
}

@Composable
fun HeaderHome(navController: NavController){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Column{
            Text(
                text = stringResource(R.string.welcome),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.to_sewain_app),
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray
            )
        }
        AsyncImage(
            model = "https://storage.googleapis.com/sewain/etc/profile.png",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(60.dp)
                .clip(CircleShape)
                .clickable {
                    navController.navigate(Screen.Profile.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }

        )
    }
}

@Composable
fun BannerHome(){

}

@Preview
@Composable
fun PreviewHeaderHome(){
    HeaderHome(rememberNavController())
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_4_XL,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_4_XL,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun PreviewBottomNavigationBar() {
    HomeScreen(rememberNavController())
}

class FakeCatalogPagingSource : PagingSource<Int, CatalogEntity>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatalogEntity> {
        val items = List(20) { CatalogEntity(id = it.toString(), "Catalog Item $it") }
        return LoadResult.Page(items, prevKey = null, nextKey = null)
    }

    override fun getRefreshKey(state: PagingState<Int, CatalogEntity>): Int? {
        return null
    }
}

// Preview Composable
@Preview(showBackground = true)
@Composable
fun CatalogsHomePreview() {
    val fakePagingSource = FakeCatalogPagingSource()
    val pager = Pager(PagingConfig(pageSize = 10)) { fakePagingSource }
    val catalogItems = pager.flow.collectAsLazyPagingItems()

    CatalogsHome(catalogItems = catalogItems)
}
