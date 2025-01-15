package com.mogun.movieinfo.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mogun.movieinfo.core.ui.compose.LocalSnackbarHostState
import com.mogun.movieinfo.core.ui.compose.MovieAppProvider

@Composable
fun MovieApp(
    modifier: Modifier = Modifier,
) {
    MovieAppProvider {
        MovieAppScreen(modifier)
    }

}

@Composable
private fun MovieAppScreen(
    modifier: Modifier = Modifier,
) {
    val snackbarHostState = LocalSnackbarHostState.current

    Scaffold(
        topBar = {

        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
            )
        }
    ) {
        Text("Movie App", modifier = modifier.padding(it))
    }
}