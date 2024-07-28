package nz.co.plantandfood.mvvmtodoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import nz.co.plantandfood.mvvmtodoapp.view.add_edit_todo.AddEditTodoScreen
import nz.co.plantandfood.mvvmtodoapp.view.theme.MVVMTodoAppTheme
import nz.co.plantandfood.mvvmtodoapp.view.todo_list.TodoListScreen
import nz.co.plantandfood.mvvmtodoapp.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMTodoAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.todo_list_obj
                ) {
                    composable<Routes.todo_list_obj> {
                        TodoListScreen(
                            onNavigate = {
                                //navigate to instance oft todo_list_obj
                                navController.navigate(it.route)
                            }
                        )
                    }
                    composable <Routes.add_edit_todo> {
                        //  val taskScreen = it.toRoute<TaskScreenNav>()
                        AddEditTodoScreen(onPopBackStack = {
                            navController.popBackStack()
                        })
                    }
                }
            }
        }
    }
}