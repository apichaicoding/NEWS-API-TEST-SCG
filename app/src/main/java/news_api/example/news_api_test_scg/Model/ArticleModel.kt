package news_api.example.news_api_test_scg.Model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleModel(
    val source: SourceModel,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    @SerialName("urlToImage")
    val urlToImage: String?,
    @SerialName("publishedAt")
    val publishedAt: String,
    val content: String?
)