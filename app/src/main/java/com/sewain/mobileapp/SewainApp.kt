package com.sewain.mobileapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sewain.mobileapp.data.local.preferences.SessionPreferences
import com.sewain.mobileapp.ui.component.bottomnav.HomeBottomNavBar
import com.sewain.mobileapp.ui.navigation.Screen
import com.sewain.mobileapp.ui.screen.login.LoginScreen
import com.sewain.mobileapp.ui.screen.profile.DetailProfileScreen
import com.sewain.mobileapp.ui.screen.register.RegisterScreen

@Composable
fun SewainApp(
    sessionPreferences: SessionPreferences,
    navController: NavHostController = rememberNavController(),
) {
    // Retrieve the session
    val sessionModel = sessionPreferences.getSession().collectAsState(initial = null).value

    // Determine the start destination
    val startDestination =
        if (sessionModel?.token?.isNotEmpty() == true) {
        Screen.Home.route
    } else {
        Screen.Login.route
    }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                navigateToRegister = {
                    navController.popBackStack()
                    navController.navigate(Screen.Register.route)
                }
            )
        }
        composable(Screen.Register.route) {
            RegisterScreen(
                navigateToLogin = {
                    navController.popBackStack()
                    navController.navigate(Screen.Login.route)
                }
            )
        }
        composable(Screen.Home.route) {
            HomeBottomNavBar(sessionModel!!)
        }
    }
}

