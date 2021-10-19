package dev.kibet.domain.model

data class Article(
    val articles: List<ArticleX>,
    val status: String,
    val totalResults: Int
)

data class ArticleX(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)

data class Source(
    val id: String,
    val name: String
)