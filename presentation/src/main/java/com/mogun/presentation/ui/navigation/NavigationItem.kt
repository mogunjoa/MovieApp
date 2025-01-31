package com.mogun.presentation.ui.navigation

import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search

sealed class NavigationItem(
    val title: String,
    val route: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
) {
    object Home : NavigationItem(
        title = "Home",
        route = "home",
        icon = androidx.compose.material.icons.Icons.Default.Home,
    )
    object Search : NavigationItem(
        title = "Search",
        route = "search",
        icon = androidx.compose.material.icons.Icons.Default.Search,
    )
    object Watch : NavigationItem(
        title = "Watch",
        route = "watch",
        icon = androidx.compose.material.icons.Icons.Default.Favorite,
    )
}
