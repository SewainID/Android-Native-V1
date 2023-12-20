package com.sewain.mobileapp.ui.screen.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapsScreen(
    modifier: Modifier = Modifier,
) {
    val singapore = LatLng(1.35, 103.87)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }
    GoogleMap(
        modifier = modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    )

//    var mapView: MapView? = null
//    var googleMap: GoogleMap? by remember { mutableStateOf(null) }

//    AndroidView(
//        factory = { context ->
//            mapView = MapView(context).apply {
//                onCreate(null)
//                getMapAsync { map ->
//                    googleMap = map
//                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(0.0, 0.0), 10f))
//                    map.setOnMapLongClickListener { latLng ->
//                        map.addMarker(MarkerOptions().position(latLng))
//                    }
//                }
//            }
//            mapView as MapView
//        },
//        modifier = modifier.fillMaxSize(),
//        update = { mapView?.onResume() }
//    )
//
//    DisposableEffect(Unit) {
//        onDispose {
//            mapView?.onDestroy()
//        }
//    }
}