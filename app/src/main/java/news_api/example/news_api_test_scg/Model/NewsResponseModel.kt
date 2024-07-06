package news_api.example.news_api_test_scg.Model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponseModel(
    val status: String,
    @SerialName("totalResults")
    val totalResults: Int,
    val articles: List<ArticleModel>
)