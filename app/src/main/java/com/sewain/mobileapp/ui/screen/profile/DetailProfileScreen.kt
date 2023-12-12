package com.sewain.mobileapp.ui.screen.profile

import android.content.res.Configuration
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailProfileScreen(
    id: String,
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    viewModel: ProfileViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideUserRepository(LocalContext.current))
    ),
    modifier: Modifier = Modifier,
) {
    viewModel.getUserById(id)

    var inputFullName by remember { mutableStateOf("") }
    var inputUsername by remember { mutableStateOf("") }
    var inputEmail by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    var loading by remember { mutableStateOf(false) }
    var success by remember { mutableStateOf(false) }
    var enabled by remember { mutableStateOf(true) }

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var hasImage by remember { mutableStateOf(false) }

    val launcherGallery = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            hasImage = uri != null
            imageUri = uri
        }
    )

    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(
                state = scrollState,
            )
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp),
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.back),
                modifier = modifier
                    .size(50.dp)
                    .clickable {
                        navController.navigateUp()
                    }
            )

            Text(
                text = stringResource(R.string.detail_profile),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = modifier.align(Center)
            )
        }

        Box(
            modifier = modifier
                .padding(top = 24.dp)
                .clickable {
                    launcherGallery.launch("image/*")
                },
            contentAlignment = Center
        ) {
            // Condition photo profile
            if (hasImage) {
                AsyncImage(
                    model = imageUri,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    alpha = 0.5f,
                    alignment = Center,
                    modifier = modifier
                        .size(150.dp)
                        .clip(CircleShape),
                )
            } else {
                Image(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    alpha = 0.5f,
                    modifier = modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
                )
            }

            Text(
                text = stringResource(R.string.click_to_change),
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }

        Text(
            text = stringResource(R.string.detail_profile_message_1),
            fontSize = 13.sp,
            modifier = modifier.padding(top = 36.dp)
        )

        Text(
            text = stringResource(R.string.detail_profile_message_2),
            fontSize = 13.sp,
            textAlign = TextAlign.Center,
        )

        Text(
            text = stringResource(R.string.full_name),
            fontSize = 16.sp,
            modifier = modifier
                .padding(start = 4.dp, top = 24.dp)
                .align(Start),
        )

        OutlinedTextField(
            value = inputFullName,
            onValueChange = { newInput ->
                inputFullName = newInput
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 2.dp),
            textStyle = TextStyle(
                color = MidnightBlue,
                fontSize = 20.sp
            ),
            placeholder = {
                Text(
                    text = "Full Name",
                    fontSize = 20.sp,
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
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
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
                .padding(start = 4.dp, top = 16.dp)
                .align(Start),
        )

        OutlinedTextField(
            value = inputUsername,
            onValueChange = { newInput ->
                inputUsername = newInput
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 2.dp),
            textStyle = TextStyle(
                color = MidnightBlue,
                fontSize = 20.sp
            ),
            placeholder = {
                Text(
                    text = viewModel.username.value,
                    fontSize = 20.sp,
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
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                cursorColor = RoyalBlue,
                selectionColors = TextSelectionColors(
                    handleColor = RoyalBlue,
                    backgroundColor = RoyalBlue
                ),
                focusedIndicatorColor = RoyalBlue,
            )
        )

        Text(
            text = stringResource(R.string.email),
            fontSize = 16.sp,
            modifier = modifier
                .padding(start = 4.dp, top = 16.dp)
                .align(Start),
        )

        OutlinedTextField(
            value = inputEmail,
            onValueChange = { newInput ->
                inputEmail = newInput
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 2.dp),
            textStyle = TextStyle(
                color = MidnightBlue,
                fontSize = 20.sp
            ),
            placeholder = {
                Text(
                    text = viewModel.email.value,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            },
            trailingIcon = {
                Icon(
                    Icons.Outlined.Email,
                    contentDescription = null,
                    tint = Gray700
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
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
                scope.launch {
                    if (inputUsername.isEmpty() &&
                        inputEmail.isEmpty()
                    ) {
                        snackbarHostState.showSnackbar(message = "Error: No changes made")
                    } else {
                        viewModel.updateUser(
                            id,
                            inputUsername,
                            inputEmail,
                        ).let {
                            loading = viewModel.loading.value
                        }

                        if (hasImage) {
                            imageUri?.let { uri ->
                                val imageFile = File(uri.path!!)
                                viewModel.uploadImage(imageFile)
                            }
                        }


                        delay(2000)

                        snackbarHostState.showSnackbar(message = viewModel.message.value)
                        loading = false

                    }
                    enabled = true
                }
            },
            modifier
                .padding(top = 48.dp)
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
fun DetailProfileScreenPreview() {
    SewainAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            DetailProfileScreen(
                id = "",
                navController = rememberNavController(),
                snackbarHostState = remember { SnackbarHostState() }
            )
        }
    }
}