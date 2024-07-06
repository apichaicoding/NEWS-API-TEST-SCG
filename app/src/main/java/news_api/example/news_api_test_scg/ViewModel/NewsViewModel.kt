package news_api.example.news_api_test_scg.ViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import news_api.example.news_api_test_scg.Model.ArticleModel
import kotlinx.coroutines.launch
import java.io.FileInputStream
import java.util.Properties

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {

    private val _newsList = mutableStateOf<List<ArticleModel>>(emptyList())
    val newsList: MutableState<List<ArticleModel>> = _newsList

    init {
        fetchTopHeadlines("NEWS_API_KEY")
    }

    private fun fetchTopHeadlines(apiKey: String) {
        viewModelScope.launch {
            try {
                val response = repository.getTopHeadlines(apiKey)
                _newsList.value = response.articles
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
