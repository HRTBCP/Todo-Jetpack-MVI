package nz.co.plantandfood.todocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import nz.co.plantandfood.todocompose.navigation.SetupNavigation
import nz.co.plantandfood.todocompose.ui.theme.TodoComposeTheme
import nz.co.plantandfood.todocompose.ui.viewmodel.SharedViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val sharedViewModel: SharedViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoComposeTheme {
                navController = rememberNavController()
                SetupNavigation(navController = navController,
                sharedViewModel = sharedViewModel)
            }
        }
    }
}
