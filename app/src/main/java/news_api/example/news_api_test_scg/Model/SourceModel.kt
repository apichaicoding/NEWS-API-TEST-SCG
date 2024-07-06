package news_api.example.news_api_test_scg.Model

import kotlinx.serialization.Serializable

@Serializable
data class SourceModel(
    val id: String?,
    val name: String
)