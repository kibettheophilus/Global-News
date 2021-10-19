package dev.kibet.globalnews.data.api

import dev.kibet.globalnews.data.model.Article
import dev.kibet.globalnews.utils.Constants.APIKEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("q") query: String = "technology",
        @Query("apiKey") apiKey: String = APIKEY
    ): Article

    @GET("top-headlines")
    suspend fun getBusinessNews(
        @Query("category") category: String = "business",
    @Query("apiKey") apiKey: String = APIKEY
    ): Article
}