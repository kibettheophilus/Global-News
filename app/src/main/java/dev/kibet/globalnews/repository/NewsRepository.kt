package dev.kibet.globalnews.repository

import android.util.Log
import dev.kibet.globalnews.data.api.NewsApi
import dev.kibet.globalnews.data.model.Article
import dev.kibet.globalnews.utils.Resource
import retrofit2.HttpException
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: NewsApi
) {

    suspend fun getHeadlines(): Resource<Article> {
        return try {
            val response = api.getTopHeadlines()

            Log.d("NEWSRESPONSE","$response")
            Resource.success(response)
        } catch (e: Exception) {
            return if (e is HttpException) {
                Log.d("NEWSRESPONSE","$e")
                Resource.error(e.message(), null)
            } else {
                Log.d("NEWSRESPONSE","$e")
                Resource.error("Could not connect to servers, $e", null)
            }
        }
    }

}