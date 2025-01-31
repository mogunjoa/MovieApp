package com.mogun.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.mogun.domain.model.Movie
import com.mogun.domain.usecase.MovieUseCase
import com.mogun.presentation.model.MovieUiState
import com.mogun.presentation.model.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
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
    private val movieUseCase: MovieUseCase,
) : ViewModel() {
    private var _popularMovies = MutableStateFlow<PagingData<MovieUiState>>(PagingData.empty())
    val popularMovies = _popularMovies.asStateFlow()

    private var _nowPlayingMovies = MutableStateFlow<PagingData<MovieUiState>>(PagingData.empty())
    val nowPlayingMovies = _nowPlayingMovies.asStateFlow()

    private var _genreMovies = MutableStateFlow<PagingData<MovieUiState>>(PagingData.empty())
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
        movieUseCase.getPopularMovies()
            .cachedIn(viewModelScope)
            .onEach { movies ->
                _popularMovies.value = movies.map { it.toUiState() }
            }
            .launchIn(viewModelScope)
    }

    fun getNowPlayingMovies() {
        movieUseCase.getNowPlayingMovies()
            .cachedIn(viewModelScope)
            .onEach { movies ->
                _nowPlayingMovies.value = movies.map { it.toUiState() }
            }
            .launchIn(viewModelScope)
    }

    fun getMoviesWithGenre(genreId: Int) {
        movieUseCase.getMoviesWithGenre(genreId)
            .cachedIn(viewModelScope)
            .onEach { movies ->
                _genreMovies.value = movies.map { it.toUiState() }
            }
            .launchIn(viewModelScope)
    }
}