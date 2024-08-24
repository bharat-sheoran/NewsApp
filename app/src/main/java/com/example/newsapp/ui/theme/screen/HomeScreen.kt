package com.example.newsapp.ui.theme.screen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsapp.ui.theme.components.Loader
import com.example.newsapp.ui.theme.components.NewsList
import com.example.newsapp.ui.theme.components.NewsRowComponent
import com.example.newsapp.ui.theme.viewModel.NewsViewModel
import com.example.utilities.ResourceState


const val TAG = "HomeScreen"

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    newsViewModel: NewsViewModel = hiltViewModel()
) {

    val newsResponse by newsViewModel.news.collectAsState()

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        10
    }
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize(),
        pageSize = PageSize.Fill,
        pageSpacing = 8.dp,
    ) { page: Int ->
        when (newsResponse) {
            is ResourceState.Loading -> {
                Log.d(TAG, "Inside Loading");
                Loader()
            }

            is ResourceState.Success -> {
                val response = (newsResponse as ResourceState.Success).data
                Log.d(TAG, "Inside Success, ${response.available}");
                if (response.news.isNotEmpty()) {
                    NewsRowComponent(page, response.news[page])
                }
            }

            is ResourceState.Error -> {
                val response = (newsResponse as ResourceState.Error)
                Log.d(TAG, "Inside Error, $response");
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
