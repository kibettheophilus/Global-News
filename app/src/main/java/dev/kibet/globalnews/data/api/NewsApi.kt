package dev.kibet.globalnews.data.api

import dev.kibet.globalnews.data.model.Article
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getTopHeadlines(
        @Query("q") query: String = "top-headlines",
        @Query("apiKey") apiKey: String = "04750dc0ea32495baadda58e6bd097fb"
    ): Article
}