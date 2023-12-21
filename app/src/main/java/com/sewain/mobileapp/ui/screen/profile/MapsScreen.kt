package com.sewain.mobileapp.ui.screen.profile

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.LocationServices
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.sewain.mobileapp.R
import com.sewain.mobileapp.di.Injection
import com.sewain.mobileapp.ui.ViewModelFactory
import com.sewain.mobileapp.ui.theme.SewainAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ProfileViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideUserRepository(LocalContext.current))
    ),
) {
    val context = LocalContext.current
    val permission = Manifest.permission.ACCESS_FINE_LOCATION
    val permissionGranted = ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED

    if (permissionGranted) {
//        onPermissionGranted()
        Log.d("Test", "Get Location")
    } else {
        val requestPermissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
//                onPermissionGranted()
                Log.d("Test", "Permission Granted")
            } else {
//                onPermissionDenied()
                Log.d("Test", "Permission Denied")
            }
        }

        Button(onClick = { requestPermissionLauncher.launch(permission) }) {
            Text("Request Location Permission")
        }
    }

//    Log.d("Test", "Longitude in Screen: ${viewModel.longitude.value}")

//    val context = LocalContext.current.applicationContext

//    when (PackageManager.PERMISSION_GRANTED) {
//        context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) -> rememberLauncherForActivityResult(
//            contract = ActivityResultContracts.RequestPermission(),
//            onResult = { isGranted ->
//                if (isGranted) {
//                    Log.d("Test", "Success")
//                }
//            }
//        )
//    }



//    rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.RequestMultiplePermissions(),
//        onResult = { permissions ->
//            when {
//                permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false -> {
//                    getMyLastLocation(context)
//                }
//
//                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false -> {
//                    getMyLastLocation(context)
//                }
//
//                else -> {
//                    // No Access Location Granted
//                }
//            }
//        }
//    )

//    val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
//
//    viewModel.getDeviceLocation(fusedLocationProviderClient)

//    val mapProperties = MapProperties(
//        isMyLocationEnabled = true,
//    )
//    val cameraPositionState = rememberCameraPositionState()
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { },
//                navigationIcon = {
//                    Icon(
//                        imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
//                        contentDescription = stringResource(R.string.back),
//                        modifier = modifier
//                            .padding(start = 8.dp)
//                            .clickable {
//                                navController.navigateUp()
//                            }
//                    )
//                }
//            )
//        }
//    ) { innerPadding ->
//        GoogleMap(
//            modifier = modifier
//                .fillMaxSize()
//                .padding(innerPadding),
//            cameraPositionState = cameraPositionState,
////            properties = mapProperties,
//        ) {
//
//        }
//    }
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
fun MapsScreenPreview() {
    SewainAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            MapsScreen(
                navController = rememberNavController(),
            )
        }
    }
}