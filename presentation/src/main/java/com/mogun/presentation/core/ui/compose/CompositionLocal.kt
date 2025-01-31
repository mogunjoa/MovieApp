package com.mogun.presentation.core.ui.compose

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

val LocalSnackbarHostState = compositionLocalOf<SnackbarHostState> {
    error("No Snackbar Host State provided")
}

@Composable
fun MovieAppProvider(
    content: @Composable () -> Unit,
) {
    val snackbarHostState = SnackbarHostState()

    CompositionLocalProvider(
        LocalSnackbarHostState provides snackbarHostState
    ) {
        content()
    }
}
