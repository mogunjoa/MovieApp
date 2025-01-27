package com.mogun.movieinfo.domain.repository

import com.mogun.movieinfo.domain.model.PopularMovie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovies(): Flow<List<PopularMovie>>
}