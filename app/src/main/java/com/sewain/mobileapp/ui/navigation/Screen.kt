package com.sewain.mobileapp.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object DetailCatalog : Screen("detail_catalog/{id}")

    object ListTransaction : Screen("list_transaction")
    object Notification : Screen("notification")
    object Profile : Screen("profile")
    object DetailProfile : Screen("profile/detail/{id}") {
        fun createRoute(id: String) = "profile/detail/$id"
    }
    object ChangePassword : Screen("profile/change/{id}") {
        fun createRoute(id: String) = "profile/change/$id"
    }

    object Adresses : Screen("profile/address/{id}") {
        fun createRoute(id: String) = "profile/address/$id"
    }

    object SocialMedia : Screen("profile/socialMedia/{id}") {
        fun createRoute(id: String) = "profile/socialMedia/$id"
    }

    object ShopAccount : Screen("profile/shopAccount/{id}/{token}") {
        fun createRoute(id: String, token: String) = "profile/shopAccount/$id/$token"
    }

    object Maps : Screen("maps")
    object CreateCatalog : Screen("create_catalog")

    object Login : Screen("login")
    object Register : Screen("register")
}
