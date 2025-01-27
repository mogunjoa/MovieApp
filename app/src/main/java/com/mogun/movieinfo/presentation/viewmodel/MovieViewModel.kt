package com.mogun.movieinfo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mogun.movieinfo.domain.usecase.MovieUseCase
import com.mogun.movieinfo.presentation.model.PopularMovieUiState
import com.mogun.movieinfo.presentation.model.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

sealed class UiState<out T> {
    object Idle : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
): ViewModel() {
    private var _popularMovies = MutableStateFlow<UiState<List<PopularMovieUiState>>>(UiState.Idle)
    val popularMovies = _popularMovies.asStateFlow()

    fun getPopularMovies() {
        movieUseCase.getPopularMovies()
            .onStart {
                _popularMovies = MutableStateFlow(UiState.Loading)
            }
            .onEach {
                _popularMovies = MutableStateFlow(UiState.Success(it.map { movie -> movie.toUiState() }))
            }
            .catch {
                _popularMovies = MutableStateFlow(UiState.Error(it.message ?: "An error occurred"))
            }
            .launchIn(viewModelScope)
    }
}