package com.sewain.mobileapp.ui.screen.profile

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.sewain.mobileapp.R
import com.sewain.mobileapp.di.Injection
import com.sewain.mobileapp.ui.ViewModelFactory
import com.sewain.mobileapp.ui.theme.Gray700
import com.sewain.mobileapp.ui.theme.MidnightBlue
import com.sewain.mobileapp.ui.theme.RoyalBlue
import com.sewain.mobileapp.ui.theme.SewainAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopAccountScreen(
    modifier: Modifier = Modifier,
    id: String,
    token: String,
    navController: NavController,
    viewModel: ProfileViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideUserRepository(LocalContext.current))
    ),
) {
    viewModel.getUserById(id)

    var inputShopName by remember { mutableStateOf("") }
    var inputUsername by remember { mutableStateOf("") }

    var loading by remember { mutableStateOf(false) }
    var success by remember { mutableStateOf(false) }
    var enabled by remember { mutableStateOf(true) }

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.shop_account),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = modifier.padding(start = 24.dp)
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back),
                        modifier = modifier
                            .padding(start = 8.dp)
                            .clickable {
                                viewModel.setSession(id, token, false)
                                navController.navigateUp()
                            }
                    )
                }
            )
        }
    ) { innerPadding ->
        // Main UI
        Column(
            horizontalAlignment = CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(
                    state = scrollState
                )
        ) {

            HorizontalDivider(color = MaterialTheme.colorScheme.primary)

            // Condition photo profile
            if (viewModel.imageString.value != "null") {
                AsyncImage(
                    model = viewModel.imageString.value,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    alignment = Center,
                    modifier = modifier
                        .padding(top = 24.dp)
                        .size(150.dp)
                        .clip(CircleShape),
                )
            } else {
                Image(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    alignment = Center,
                    modifier = modifier
                        .padding(top = 24.dp)
                        .size(150.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
                )
            }

            Text(
                text = if (viewModel.shopName.value == "null") stringResource(R.string.shop_name) else viewModel.shopName.value,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = modifier.padding(top = 24.dp)
            )

            Text(
                text = viewModel.email.value,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.secondary,
                modifier = modifier.padding(top = 4.dp)
            )

            Text(
                text = stringResource(R.string.shop_account_message),
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .padding(top = 36.dp, start = 16.dp, end = 16.dp),
            )

            Text(
                text = stringResource(R.string.shop_name),
                fontSize = 16.sp,
                modifier = modifier
                    .padding(start = 20.dp, top = 24.dp, end = 16.dp)
                    .align(Start),
            )

            OutlinedTextField(
                value = inputShopName,
                onValueChange = { newInput ->
                    inputShopName = newInput
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp, start = 16.dp, end = 16.dp),
                textStyle = TextStyle(
                    color = MidnightBlue,
                    fontSize = 20.sp
                ),
                placeholder = {
                    Text(
                        text = "Shop Name",
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.AccountBox,
                        contentDescription = null,
                        tint = Gray700
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    cursorColor = RoyalBlue,
                    selectionColors = TextSelectionColors(
                        handleColor = RoyalBlue,
                        backgroundColor = RoyalBlue
                    ),
                    focusedIndicatorColor = RoyalBlue,
                )
            )

            Text(
                text = stringResource(R.string.username),
                fontSize = 16.sp,
                modifier = modifier
                    .padding(start = 20.dp, top = 16.dp, end = 16.dp)
                    .align(Start),
            )

            OutlinedTextField(
                value = inputUsername,
                onValueChange = { newInput ->
                    inputUsername = newInput
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp, start = 16.dp, end = 16.dp),
                textStyle = TextStyle(
                    color = MidnightBlue,
                    fontSize = 20.sp
                ),
                placeholder = {
                    Text(
                        text = "Username",
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                },
                trailingIcon = {
                    Icon(
                        Icons.Outlined.Person,
                        contentDescription = null,
                        tint = Gray700
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    cursorColor = RoyalBlue,
                    selectionColors = TextSelectionColors(
                        handleColor = RoyalBlue,
                        backgroundColor = RoyalBlue
                    ),
                    focusedIndicatorColor = RoyalBlue,
                )
            )

            Button(
                onClick = {

                },
                modifier
                    .padding(top = 48.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                enabled = enabled,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                if (loading) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary
                    )
                    enabled = false
                } else {
                    Text(
                        text = stringResource(R.string.save),
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

        }
    }
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
fun ShopAccountScreenPreview() {
    SewainAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            ShopAccountScreen(
                id = "",
                token = "",
                navController = rememberNavController(),
            )
        }
    }
}