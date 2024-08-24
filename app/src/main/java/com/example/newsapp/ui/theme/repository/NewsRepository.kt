package com.example.newsapp.ui.theme.repository

import com.example.newsapp.data.datasource.NewsDataSource
import com.example.newsapp.data.entity.NewsResponse
import com.example.utilities.ResourceState
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsDataSource: NewsDataSource
) {
//    suspend fun getNewsHeadline(country:String):Response<NewsResponse>{
//        return newsDataSource.getNewsHeadline(country);
//    }

    suspend fun getNewsHeadline(country:String) : Flow<ResourceState<NewsResponse>>{
        return flow{
            emit(ResourceState.Loading())
            val response = newsDataSource.getNewsHeadline(country)

            if(response.isSuccessful && response.body() != null){
                emit(ResourceState.Success(response.body()!!))
            }else{
                emit(ResourceState.Error("Error Fetching News Data"))
            }
        }.catch { e ->
            emit(ResourceState.Error(e?.localizedMessage?:"An Error Occured... Please Try later"))
        }
    }
}