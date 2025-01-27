package com.mogun.movieinfo.presentation.ui.screen

import androidx.compose.foundation.Image
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mogun.movieinfo.R
import com.mogun.movieinfo.presentation.ui.MovieApp
import com.mogun.movieinfo.presentation.ui.common.ContentPaddings
import com.mogun.movieinfo.presentation.ui.theme.Yellow
import com.mogun.movieinfo.presentation.viewmodel.MovieViewModel
import com.mogun.movieinfo.presentation.viewmodel.UiState
import kotlinx.coroutines.flow.onEach

enum class CardType {
    DEFAULT,
    RANKING,
}

@Composable
fun HomeScreen(
    viewModel: MovieViewModel = hiltViewModel()
) {
    viewModel.getPopularMovies()
    Column(
        modifier = Modifier
            .fillMaxSize() // 화면 전체를 채움
            .verticalScroll(rememberScrollState()) // 스크롤 상태 추가
    ) {
        Spacer(modifier = Modifier.Companion.height(ContentPaddings.large.dp))
        Section(
            title = "인기 영화 Top 10",
            cardHeight = 200,
            fontSize = 18,
        ) {
            RankingNumber(it + 1)
        }
        Spacer(modifier = Modifier.Companion.height(ContentPaddings.large.dp))
        Section(
            title = "현재 상영작",
            cardHeight = 150,
            fontSize = 14,
        ) {
            RankingNumber(it + 1)
        }
        Spacer(modifier = Modifier.Companion.height(ContentPaddings.large.dp))
        Section(
            title = "장르별 영화",
            cardHeight = 150,
            fontSize = 14,
        )
        Spacer(modifier = Modifier.Companion.height(ContentPaddings.large.dp))
    }
}

@Composable
fun MovieSection(
    sectionTitle: String,
    compose: @Composable (index: Int) -> Unit
) {
    Column {
        Text(
            text = sectionTitle,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(start = ContentPaddings.medium.dp)
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth().padding(top = ContentPaddings.medium.dp),
            horizontalArrangement = Arrangement.spacedBy(ContentPaddings.medium.dp), // 아이템 간 간격
            contentPadding = PaddingValues(horizontal = ContentPaddings.medium.dp) // 처음과 끝 여백
        ) {
            items(10) { item ->
                compose(item)
            }
        }
    }
}

@Composable
fun Section(
    title: String,
    cardHeight: Int,
    fontSize: Int,
    compose: @Composable (index: Int) -> Unit = {}
) {
    MovieSection(title) { index ->
        MovieCard(cardHeight, fontSize) {
            compose(index)
        }
    }
}

@Composable
fun MovieCard(
    height: Int,
    contentFontSize: Int,
    compose: @Composable () -> Unit = {}
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
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "Movie Poster",
                        contentScale = ContentScale.Crop, // 이미지를 카드 크기에 맞게 조정
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            // RankingNumber 컴포저 호출
            compose()
        }
        Spacer(modifier = Modifier.Companion.height(ContentPaddings.small.dp))
        Text(
            "영화 제목",
            fontSize = contentFontSize.sp,
            style = TextStyle(
                shadow = Shadow(
                    color = Color.Black, // 테두리 색상
                    offset = Offset(0f, 0f), // 그림자 위치
                )
            ),
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
                "9.0 (2,303)",
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