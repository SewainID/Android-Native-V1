package com.sewain.mobileapp.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object ListTransaction : Screen("list_transaction")
    object Notification : Screen("notification")
    object Profile : Screen("profile")

    object Login : Screen("login")
    object Register : Screen("register")
}
