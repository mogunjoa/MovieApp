package com.mogun.movieinfo.data.page

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mogun.movieinfo.data.impl.MovieType
import com.mogun.movieinfo.data.source.MovieRemoteDataSource
import com.mogun.movieinfo.domain.model.Movie

class MoviePagingSource(
    private val remote: MovieRemoteDataSource,
    private val movieType: MovieType,
    private val genreId: Int? = null
): PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: START_PAGE_INDEX

            val result = when (movieType) {
                MovieType.POPULAR -> remote.getPopularMovies()
                MovieType.NOW_PLAYING -> remote.getNowPlayingMovies()
                MovieType.GENRE -> genreId?.let { remote.getMoviesWithGenre(it) } ?: emptyList()
            }

            LoadResult.Page(
                data = result.map { it.toDomain() }, // MovieEntity → Movie 변환
                prevKey = if (page != START_PAGE_INDEX) page - 1 else null,
                nextKey = if (result.size < DEFAULT_PAGE_SIZE) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val START_PAGE_INDEX = 1

        const val DEFAULT_PAGE_SIZE: Int = 20
    }
}