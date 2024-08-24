package com.example.newsapp.data.entity

import java.math.BigInteger

data class NewsResponse(
    val offset: Int,
    val number: Int,
    val available: BigInteger,
    val news: List<News>
)

data class News(
    val id: BigInteger,
    val title: String,
    val text:String,
    val summary: String,
    val url: String,
    val image: String,
    val video: String,
    val publish_date: String,
    val author: String,
    val authors: List<String>,
    val language: String,
    val category: String,
    val source_country: String,
    val sentiment: Float
)
