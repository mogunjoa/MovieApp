package com.mogun.movieinfo.ui

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
import com.mogun.movieinfo.core.ui.compose.LocalSnackbarHostState
import com.mogun.movieinfo.core.ui.compose.MovieAppProvider
import com.mogun.movieinfo.ui.navigation.NavigationItem
import com.mogun.movieinfo.ui.screen.HomeScreen
import com.mogun.movieinfo.ui.screen.SearchScreen
import com.mogun.movieinfo.ui.screen.WatchScreen
import com.mogun.movieinfo.ui.theme.MovieInfoTheme

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