package news_api.example.news_api_test_scg.ViewModel

import news_api.example.news_api_test_scg.Model.NewsResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("everything")
    suspend fun getTopHeadlines(
        @Query("q") keywords: String = "programmer",
        @Query("apiKey") apiKey: String
    ): NewsResponseModel
}