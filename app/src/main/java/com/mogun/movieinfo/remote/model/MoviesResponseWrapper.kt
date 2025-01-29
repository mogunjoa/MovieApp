package com.mogun.movieinfo.remote.model

import com.google.gson.annotations.SerializedName

data class MoviesResponseWrapper(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieResponse>,
)