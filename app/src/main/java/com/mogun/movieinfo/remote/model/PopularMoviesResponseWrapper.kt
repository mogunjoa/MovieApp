package com.mogun.movieinfo.remote.model

import com.google.gson.annotations.SerializedName

data class PopularMoviesResponseWrapper(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<PopularMovieResponse>,
)