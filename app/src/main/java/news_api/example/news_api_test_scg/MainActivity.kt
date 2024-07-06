package news_api.example.news_api_test_scg

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import news_api.example.news_api_test_scg.Model.ArticleModel
import news_api.example.news_api_test_scg.View.NewsDetailScreen
import news_api.example.news_api_test_scg.View.NewsListScreen
import news_api.example.news_api_test_scg.ViewModel.NewsApiService
import news_api.example.news_api_test_scg.ViewModel.NewsRepository
import news_api.example.news_api_test_scg.ViewModel.NewsViewModel
import news_api.example.news_api_test_scg.ui.theme.NEWSAPITESTSCGTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Base64

class MainActivity : ComponentActivity() {
    private val newsApiService = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsApiService::class.java)

    private val newsRepository = NewsRepository(newsApiService)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NEWSAPITESTSCGTheme {
                NavHost(navController = navController, startDestination = "newsList") {
                    composable("newsList") {
                        NewsListScreen(
                            viewModel = NewsViewModel(newsRepository),
                            navController = navController
                        )
                    }
                    composable(
                        "newsDetail/{articleJson}",
                        arguments = listOf(navArgument("articleJson") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val articleJson = backStackEntry.arguments?.getString("articleJson")
                        val articleJsonDecoded = String(Base64.getDecoder().decode(articleJson), Charsets.UTF_8)
                        val article = Gson().fromJson(articleJsonDecoded, ArticleModel::class.java)
                        NewsDetailScreen(
                            article = article,
                            navigateBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}