package com.sewain.mobileapp.ui.component.bottomnav

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sewain.mobileapp.R
import com.sewain.mobileapp.data.local.model.SessionModel
import com.sewain.mobileapp.ui.navigation.Screen
import com.sewain.mobileapp.ui.screen.home.HomeScreen
import com.sewain.mobileapp.ui.screen.profile.AdressesScreen
import com.sewain.mobileapp.ui.screen.profile.ChangeScreenPasswordScreen
import com.sewain.mobileapp.ui.screen.profile.DetailProfileScreen
import com.sewain.mobileapp.ui.screen.profile.ProfileScreen
import com.sewain.mobileapp.ui.screen.profile.ShopAccountScreen
import com.sewain.mobileapp.ui.screen.profile.SocialMediaScreen
import com.sewain.mobileapp.ui.theme.SewainAppTheme
import com.sewain.mobileapp.ui.theme.SteelBlue
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeBottomNavBar(
    sessionModel: SessionModel,
    snackbarHostState: SnackbarHostState,
) {
//initializing the default selected item
    var navigationSelectedItem by remember {
        mutableIntStateOf(0)
    }

    /**
     * by using the rememberNavController()
     * we can get the instance of the navController
     */

    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var currentRoute = navBackStackEntry?.destination?.route

//scaffold to hold our bottom navigation Bar
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            if (currentRoute == Screen.DetailProfile.route ||
                currentRoute == Screen.ChangePassword.route ||
                currentRoute == Screen.Adresses.route ||
                currentRoute == Screen.SocialMedia.route ||
                currentRoute == Screen.ShopAccount.route
            ) {
                currentRoute = Screen.Profile.route
            }

            NavigationBar {
                //getting the list of bottom navigation items for our data class
                BottomNavItem().bottomNavigationItems().forEachIndexed { index, navigationItem ->

                    //iterating all items with their respective indexes
                    NavigationBarItem(
                        selected = navigationItem.route == currentRoute,
                        label = {
                            Text(navigationItem.label)
                        },
                        icon = {
                            Icon(
                                navigationItem.icon,
                                contentDescription = navigationItem.label
                            )
                        },
                        onClick = {
                            navigationSelectedItem = index
                            navController.navigate(navigationItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(indicatorColor = SteelBlue)
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(navController)
            }
            composable(Screen.ListTransaction.route) {
                //call our composable screens here
            }
            composable(Screen.Notification.route) {
                //call our composable screens here
            }
            composable(Screen.Profile.route) {
                //call our composable screens here
                ProfileScreen(navController, sessionModel)
            }
            composable(Screen.DetailProfile.route) {
                val id = it.arguments?.getString("id") ?: ""
                DetailProfileScreen(id, navController, snackbarHostState)
            }
            composable(Screen.ChangePassword.route) {
                ChangeScreenPasswordScreen(navController)
            }
            composable(Screen.Adresses.route) {
                AdressesScreen(navController)
            }
            composable(Screen.SocialMedia.route) {
                SocialMediaScreen(navController)
            }
            composable(Screen.ShopAccount.route) {
                val id = it.arguments?.getString("id") ?: ""
                ShopAccountScreen(id, navController)
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
fun PreviewBottomNavigationBar() {
    SewainAppTheme {
        HomeBottomNavBar(
            sessionModel = SessionModel("", ""),
            snackbarHostState = remember { SnackbarHostState() },
        )
    }
}