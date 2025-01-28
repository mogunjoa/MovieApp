package com.mogun.movieinfo.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.rememberAsyncImagePainter
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.mogun.movieinfo.R
import com.mogun.movieinfo.presentation.model.MovieUiState
import com.mogun.movieinfo.presentation.ui.MovieApp
import com.mogun.movieinfo.presentation.ui.common.ContentPaddings
import com.mogun.movieinfo.presentation.ui.theme.Yellow
import com.mogun.movieinfo.presentation.viewmodel.MovieViewModel
import com.mogun.movieinfo.presentation.viewmodel.UiState
import kotlinx.coroutines.async

enum class CardType {
    DEFAULT,
    RANKING,
}

@Composable
fun HomeScreen(
    viewModel: MovieViewModel = hiltViewModel(),
) {
    val popularMovies = viewModel.popularMovies.collectAsState()
    val nowPlayingMovies = viewModel.nowPlayingMovies.collectAsState()

    /**
     * popularMovies와 nowPlayingMovies가 초기 상태인 경우에만 API 호출
     * API 호출은 비동기로 진행되며, 두 API 호출이 병렬로 진행됨(TODO 추후 예외 전파 고려)
     */
    LaunchedEffect(Unit) {
        val popularMovieJob = async {
            if (popularMovies.value is UiState.Idle) {
                viewModel.getPopularMovies()
            }
        }
        val nowPlayingMovieJob = async {
            if (nowPlayingMovies.value is UiState.Idle) {
                viewModel.getNowPlayingMovies()
            }
        }

        popularMovieJob.await()
        nowPlayingMovieJob.await()
    }

    Column(
        modifier = Modifier
            .fillMaxSize() // 화면 전체를 채움
            .verticalScroll(rememberScrollState()) // 스크롤 상태 추가
    ) {
        Spacer(modifier = Modifier.Companion.height(ContentPaddings.large.dp))
        Section(
            sectionTitle = "인기 영화 Top 10",
            cardType = CardType.RANKING,
            movieData = (popularMovies.value as? UiState.Success)?.data?.take(10) ?: emptyList(),
        )
        Spacer(modifier = Modifier.Companion.height(ContentPaddings.large.dp))
        Section(
            sectionTitle = "현재 상영작",
            movieData = (nowPlayingMovies.value as? UiState.Success)?.data ?: emptyList(),
        )
        Spacer(modifier = Modifier.Companion.height(ContentPaddings.large.dp))
        Section(
            sectionTitle = "장르별 영화",
        )
        Spacer(modifier = Modifier.Companion.height(ContentPaddings.large.dp))
    }
}

@Composable
fun Section(
    sectionTitle: String,
    movieData: List<MovieUiState> = emptyList(),
    cardType: CardType = CardType.DEFAULT,
) {
    Column {
        Text(
            text = sectionTitle,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(start = ContentPaddings.medium.dp)
        )

        val placeholderMovies = remember {
            List(10) {
                MovieUiState(
                    id = "",
                    title = "Loading...",
                    overview = "",
                    posterPath = "",
                    rating = 0f,
                    rateCount = 0,
                    releasedAt = ""
                )
            }
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = ContentPaddings.medium.dp),
            horizontalArrangement = Arrangement.spacedBy(ContentPaddings.medium.dp),
            contentPadding = PaddingValues(horizontal = ContentPaddings.medium.dp)
        ) {
            val moviesToShow = if (movieData.isEmpty()) placeholderMovies else movieData

            items(movieData.size) { index ->
                MovieCard(
                    height = 200,
                    contentFontSize = 18,
                    data = moviesToShow[index],
                ) {
                    if (cardType == CardType.RANKING)
                        RankingNumber(index + 1)
                }
            }
        }
    }
}

@Composable
fun MovieCard(
    height: Int,
    contentFontSize: Int,
    data: MovieUiState,
    compose: @Composable () -> Unit = {},
) {
    Column {
        Box(
            modifier = Modifier
                .height(height.dp)
                .aspectRatio(2f / 3), // 세로 비율 설정
            contentAlignment = Alignment.BottomStart // 내용물의 기본 정렬을 왼쪽 하단으로 설정
        ) {
            Column {
                Card(
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (data.posterPath.isEmpty())
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Gray),
                        )
                    else
                        Image(
                            painter = rememberAsyncImagePainter(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(data.posterPath)
                                    .crossfade(true) // 부드러운 전환 효과
                                    .memoryCachePolicy(CachePolicy.ENABLED) // 메모리 캐싱
                                    .diskCachePolicy(CachePolicy.DISABLED)  // 디스크 캐싱
                                    .build()
                            ),
                            contentDescription = "Movie Poster",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                }
            }

            // RankingNumber 컴포저 호출
            compose()
        }
        Spacer(modifier = Modifier.Companion.height(ContentPaddings.small.dp))
        Text(
            data.title,
            modifier = Modifier.fillMaxWidth(),
            fontSize = contentFontSize.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                shadow = Shadow(
                    color = Color.Black, // 테두리 색상
                    offset = Offset(0f, 0f), // 그림자 위치
                )
            )
        )
        Spacer(modifier = Modifier.Companion.width(ContentPaddings.tiny.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_star_24),
                contentDescription = "Ranking Icon",
                modifier = Modifier.size(contentFontSize.dp),
                tint = Yellow
            )
            Spacer(modifier = Modifier.Companion.width(ContentPaddings.tiny.dp))
            Text(
                "${data.rating} (${data.rateCount})",
                fontSize = (contentFontSize / 1.2).sp,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black, // 테두리 색상
                        offset = Offset(0f, 0f), // 그림자 위치
                    )
                ),
            )
        }
    }
}

@Composable
fun RankingNumber(ranking: Int) {
    Text(
        text = ranking.toString(),
        fontSize = 48.sp,
        color = Color.White, // 텍스트 색상
        style = TextStyle(
            shadow = Shadow(
                color = Color.Black, // 테두리 색상
                offset = Offset(0f, 0f), // 그림자 위치
                blurRadius = 8f // 테두리 두께
            )
        ),
        modifier = Modifier.Companion.padding(ContentPaddings.small.dp) // 텍스트에 여백 추가
    )
}

@Preview
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        MovieApp()
    }
}