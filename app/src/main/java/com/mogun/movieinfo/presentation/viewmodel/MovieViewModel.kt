package com.mogun.movieinfo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mogun.movieinfo.domain.model.Movie
import com.mogun.movieinfo.domain.usecase.MovieUseCase
import com.mogun.movieinfo.presentation.model.MovieUiState
import com.mogun.movieinfo.presentation.model.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.take
import javax.inject.Inject

sealed class UiState<out T> {
    object Idle : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
) : ViewModel() {
    private var _popularMovies = MutableStateFlow<UiState<List<MovieUiState>>>(UiState.Idle)
    val popularMovies = _popularMovies.asStateFlow()

    private var _nowPlayingMovies = MutableStateFlow<UiState<List<MovieUiState>>>(UiState.Idle)
    val nowPlayingMovies = _nowPlayingMovies.asStateFlow()

    private var _genreMovies = MutableStateFlow<UiState<List<MovieUiState>>>(UiState.Idle)
    val genreMovies = _genreMovies.asStateFlow()

    private fun fetchMovies(
        flow: Flow<List<Movie>>,
        stateFlow: MutableStateFlow<UiState<List<MovieUiState>>>
    ) {
        flow
            .onStart {
                stateFlow.value = UiState.Loading
            }
            .onEach { movies ->
                stateFlow.value = UiState.Success(movies.map { it.toUiState() })
            }
            .catch { throwable ->
                stateFlow.value = UiState.Error(throwable.message ?: "An error occurred")
            }
            .launchIn(viewModelScope)
    }

    fun getPopularMovies() {
        fetchMovies(
            flow = movieUseCase.getPopularMovies(),
            stateFlow = _popularMovies
        )
    }

    fun getNowPlayingMovies() {
        fetchMovies(
            flow = movieUseCase.getNowPlayingMovies(),
            stateFlow = _nowPlayingMovies
        )
    }

    fun getMoviesWithGenre(genreId: Int) {
        fetchMovies(
            flow = movieUseCase.getMoviesWithGenre(genreId),
            stateFlow = _genreMovies
        )
    }
}