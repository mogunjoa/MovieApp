package com.mogun.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mogun.presentation.ui.theme.MovieInfoTheme
import com.mogun.presentation.core.ui.compose.LocalSnackbarHostState
import com.mogun.presentation.core.ui.compose.MovieAppProvider
import com.mogun.presentation.ui.navigation.NavigationItem
import com.mogun.presentation.ui.screen.HomeScreen
import com.mogun.presentation.ui.screen.SearchScreen
import com.mogun.presentation.ui.screen.WatchScreen

@Composable
fun MovieApp(
    modifier: Modifier = Modifier,
) {
    MovieAppProvider {
        MovieAppScreen(modifier)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MovieAppScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val snackbarHostState = LocalSnackbarHostState.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Movie App")
                }
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
            )
        },
        bottomBar = {
            MovieAppBottomBar(
                navHostController = navController,
                currentRoute = currentDestination?.route,
            )
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = NavigationItem.Home.route,
            modifier = modifier.padding(it)
        ) {
            composable(NavigationItem.Home.route) { HomeScreen() }
            composable(NavigationItem.Search.route) { SearchScreen() }
            composable(NavigationItem.Watch.route) { WatchScreen() }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieAppPreview() {
    MovieInfoTheme {
        MovieApp()
    }
}