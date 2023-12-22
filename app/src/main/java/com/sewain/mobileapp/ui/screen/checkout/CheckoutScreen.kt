package com.sewain.mobileapp.ui.screen.checkout

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.sewain.mobileapp.R
import com.sewain.mobileapp.data.remote.response.CatalogItem
import com.sewain.mobileapp.di.Injection
import com.sewain.mobileapp.ui.CatalogViewModelFactory
import com.sewain.mobileapp.ui.component.CustomButton
import com.sewain.mobileapp.ui.component.CustomDateRangePicker
import com.sewain.mobileapp.ui.screen.detail_catalog.DetailCatalog
import com.sewain.mobileapp.ui.screen.detail_catalog.DetailCatalogViewModel
import com.sewain.mobileapp.ui.theme.SewainAppTheme
import com.sewain.mobileapp.utils.rp
import java.util.Calendar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(id : String, viewModel: CheckoutViewModel = viewModel(
    factory = CatalogViewModelFactory(Injection.provideCatalogRepository(LocalContext.current))
)
) {
    // React to state changes
    val catalog = viewModel.catalog.value
    val showDatePicker = remember { mutableStateOf(false) }
    val totalAmount = remember { mutableStateOf(0.0) }

    val scrollState = rememberScrollState()
    val calendar = Calendar.getInstance()
    val currentDateInMillis = calendar.timeInMillis
    calendar.add(Calendar.DAY_OF_MONTH, catalog?.dayRent ?: 1)
    val toDaysInMillis = calendar.timeInMillis

    // Call fetchCatalog when the composable enters the composition
    LaunchedEffect(id) {
        viewModel.fetchCatalog(id)
    }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val stateDatePicker = rememberDateRangePickerState(
        initialSelectedStartDateMillis = currentDateInMillis,
        initialSelectedEndDateMillis = toDaysInMillis
    )
    LaunchedEffect(stateDatePicker.selectedStartDateMillis) {
        stateDatePicker.selectedStartDateMillis?.let { startDate ->
            val cal = Calendar.getInstance().apply {
                timeInMillis = startDate
                add(Calendar.DAY_OF_MONTH, catalog?.dayRent?.minus(1) ?: 1)
            }
            stateDatePicker.setSelection(startDate, cal.timeInMillis)
        }
    }
    SewainAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

                topBar = {
                    TopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.background,
                            titleContentColor = MaterialTheme.colorScheme.primary,
                        ),
                        title = {
                            Text(
                                "Checkout",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.titleMedium,
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = { /* do something */ }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Localized description"
                                )
                            }
                        },
                        scrollBehavior = scrollBehavior,
                    )
                },
            ) { innerPadding ->
                Column(
                    modifier = Modifier.padding(innerPadding)
                        .verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){
                    AddressContainer()
                    Spacer(modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 8.dp).height(1.dp).background(MaterialTheme.colorScheme.primary))
                    ProductCard(catalog, stateDatePicker, showDatePicker)
                    CustomDateRangePicker(stateDatePicker, showDatePicker)
                    Spacer(modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 8.dp).height(1.dp).background(MaterialTheme.colorScheme.primary))
                    DeliveryOption()
                    Spacer(modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 8.dp).height(1.dp).background(MaterialTheme.colorScheme.primary))
                    TotalSection(totalAmount.value)
                    Spacer(modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 8.dp).height(1.dp).background(MaterialTheme.colorScheme.primary))
                    GoToPaymentButton()
                }
            }
        }
    }
}

@Composable
fun AddressContainer(){
    Column(modifier = Modifier.padding(start = 32.dp, end = 32.dp)){
        Text(
            text = "Alamat Pengiriman",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Column(modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 16.dp, shape = RoundedCornerShape(15.dp)) // Add shadow with specified elevation
            .background(MaterialTheme.colorScheme.onPrimary, shape = RoundedCornerShape(15.dp))
            .border(BorderStroke(2.dp, MaterialTheme.colorScheme.primary), shape = RoundedCornerShape(15.dp))
            .padding(8.dp)
        ){
            Row(){
                Text(
                    text = "Nama Penerima : ",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Row(){
                Text(
                    text = "Alamat Lengkap : ",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Row(){
                Text(
                    text = "No Telpon : ",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCard(catalog : CatalogItem?, stateDatePicker : DateRangePickerState, showDatePicker: MutableState<Boolean>){
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    val startDate = stateDatePicker.selectedStartDateMillis?.let { millis ->
        dateFormat.format(Date(millis))
    } ?: "Not set"

    val endDate = stateDatePicker.selectedEndDateMillis?.let { millis ->
        dateFormat.format(Date(millis))
    } ?: "Not set"
    Column(modifier = Modifier.padding(start = 32.dp, end = 32.dp)){
        Text(
            text = catalog?.shop?.name ?: "",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Column(modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 16.dp, shape = RoundedCornerShape(15.dp)) // Add shadow with specified elevation
            .background(MaterialTheme.colorScheme.onPrimary, shape = RoundedCornerShape(15.dp))
            .border(BorderStroke(2.dp, MaterialTheme.colorScheme.primary), shape = RoundedCornerShape(15.dp))
            .padding(8.dp)
        ){
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = catalog?.photoUrl, contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(4.dp))
            )
            Column (modifier = Modifier.padding(8.dp)){
                Text(text = catalog?.name ?: "", style = MaterialTheme.typography.titleMedium,)
                Text(text = catalog?.size ?: "", style = MaterialTheme.typography.bodySmall,)
                Text(
                    text = "${catalog?.price?.rp() ?: 0} / ${catalog?.dayRent ?: 0} Days",
                    style = MaterialTheme.typography.bodySmall,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(), // Ensure the row fills the maximum width
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween // This will space out the children
                ){
                    Text(
                        text = "$startDate - $endDate",
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    IconButton(onClick = { showDatePicker.value = true}) {
                        Icon(Icons.Filled.DateRange, contentDescription = "Localized description")
                    }
                }

            }
        }
    }
}
}

@Composable
fun DeliveryOption() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Opsi Pengiriman",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        // Assuming you might have a list of delivery options, you could map over them here
        // For simplicity, we're just showing one option
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = true, // You'd have some state to manage this in a real app
                onClick = { /* Handle delivery option selection */ }
            )
            Column {
                Text(text = "Reguler",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary)
                Text(text = "Akan diterima pada tanggal 1 - 2 Jan",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary)
            }
        }
        // Add more spacing or dividers as needed
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun PromoCodeSection() {
    // Text field and Apply button
}

@Composable
fun TotalSection(totalAmount: Double) {
    Row (horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Total :",
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = totalAmount.rp(),
                style = MaterialTheme.typography.titleMedium,
            )
    }
}

@Composable
fun GoToPaymentButton() {
    CustomButton(
        text = "Go To Payment",
        onClick = {  },
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp))
    )
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewCheckoutScreen(){
    CheckoutScreen(id = "1")
}

@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun PreviewDarkCheckoutScreen(){
    CheckoutScreen(id = "1")
}