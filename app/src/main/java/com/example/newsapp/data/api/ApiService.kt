package com.example.newsapp.data.api

import com.example.newsapp.data.entity.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search-news/")
    suspend fun getNewsHeadline(
        @Query("source-countries") country: String,
        @Query("api-key") apiKey: String = "58c87b051ec04f69a10fd710dcb5f5a2"
    ) : Response<NewsResponse>
}

//https://api.worldnewsapi.com/search-news?api-key=58c87b051ec04f69a10fd710dcb5f5a2&source-countries=us