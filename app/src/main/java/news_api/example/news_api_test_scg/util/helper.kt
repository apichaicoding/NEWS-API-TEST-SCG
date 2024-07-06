package news_api.example.news_api_test_scg.util

import android.os.Build
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.annotation.RequiresApi
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.system.exitProcess

@RequiresApi(Build.VERSION_CODES.O)
fun formatPublishedAt(publishedAt: String): String {
    val formatter = DateTimeFormatter.ISO_DATE_TIME
    val dateTime = LocalDateTime.parse(publishedAt, formatter)

    val formattedDateTime = dateTime.format(DateTimeFormatter.ofPattern("MMM dd, HH:mm", Locale.ENGLISH))

    return "Updated: $formattedDateTime"
}

@Composable
fun BackHandler() {
    var backPressed by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(key1 = context) {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (backPressed) {
                    exitProcess(0)
                } else {
                    backPressed = true
                }
            }
        }
        requireNotNull(context as? OnBackPressedDispatcherOwner).onBackPressedDispatcher.addCallback(lifecycleOwner, callback)
        onDispose {
            callback.remove()
        }
    }

    if (backPressed) {
        AlertDialog(
            onDismissRequest = {
                backPressed = false
            },
            title = { Text(text = "ออกจากการยืนยัน") },
                text = { Text(text = "กดกลับอีกครั้งเพื่อออก") },
            confirmButton = {
                TextButton(onClick = { exitProcess(0) }) {
                    Text("ออก")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    backPressed = false
                }) {
                    Text("ยกเลิก")
                }
            }
        )
    }
}
