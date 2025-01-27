package com.mogun.movieinfo.presentation.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(
    val title: String,
    val route: String,
    val icon: ImageVector,
) {
    object Home : NavigationItem(
        title = "Home",
        route = "home",
        icon = Icons.Default.Home,
    )
    object Search : NavigationItem(
        title = "Search",
        route = "search",
        icon = Icons.Default.Search,
    )
    object Watch : NavigationItem(
        title = "Watch",
        route = "watch",
        icon = Icons.Default.Favorite,
    )
}
