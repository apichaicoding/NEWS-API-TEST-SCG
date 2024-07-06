package news_api.example.news_api_test_scg.ViewModel

import news_api.example.news_api_test_scg.Model.NewsResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository(private val apiService: NewsApiService) {

    suspend fun getTopHeadlines(apiKey: String): NewsResponseModel {
        return withContext(Dispatchers.IO) {
            apiService.getTopHeadlines(apiKey = apiKey)
        }
    }
}