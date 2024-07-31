package nz.co.plantandfood.mvvmtodoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nz.co.plantandfood.mvvmtodoapp.presentation.screen.add_edit_todo.AddEditTodoScreen
import nz.co.plantandfood.mvvmtodoapp.presentation.theme.MVVMTodoAppTheme
import nz.co.plantandfood.mvvmtodoapp.presentation.screen.todo_list.TodoListScreen
import nz.co.plantandfood.mvvmtodoapp.presentation.util.Routes
import dagger.hilt.android.AndroidEntryPoint
import nz.co.plantandfood.mvvmtodoapp.presentation.screen.add_edit_todo.AddEditToDoEventRoot
import nz.co.plantandfood.mvvmtodoapp.presentation.screen.todo_list.TodoListScreenRoot

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMTodoAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.TodoList
                ) {
                    composable<Routes.TodoList> {
                        TodoListScreenRoot(
                            navController
                        )
                    }
                    composable <Routes.TodoEdit> {
                        //  val taskScreen = it.toRoute<TaskScreenNav>()
                        AddEditToDoEventRoot(navController)

                    }
                }
            }
        }
    }
}