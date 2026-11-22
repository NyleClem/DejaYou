package com.example.dejayou

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dejayou.ui.theme.DejaYouTheme
import com.example.dejayou.ui.theme.DejaYouBackground
import com.example.dejayou.navigation.DejaYouNavGraph
import androidx.activity.viewModels
import com.example.dejayou.data.local.DatabaseProvider
import com.example.dejayou.data.local.MomentRepository
import com.example.dejayou.ui.viewmodel.MomentViewModel
import com.example.dejayou.ui.viewmodel.MomentViewModelFactory

class MainActivity : ComponentActivity() {
    // Create the ViewModel using the Room database and repository. NC
    private val momentViewModel: MomentViewModel by viewModels {
        val database = DatabaseProvider.getDatabase(applicationContext)
        val repository = MomentRepository(database.momentDao())
        MomentViewModelFactory(repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DejaYouTheme {
                DejaYouBackground {
                    DejaYouNavGraph(momentViewModel = momentViewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Text(
        text = "DejaYOU",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DejaYouTheme {
        DejaYouBackground {
            Greeting()
        }
    }
}