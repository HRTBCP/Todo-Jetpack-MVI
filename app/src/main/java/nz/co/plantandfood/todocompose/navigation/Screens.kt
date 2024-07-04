package nz.co.plantandfood.todocompose.navigation

import androidx.navigation.NavHostController
import nz.co.plantandfood.todocompose.util.Action
import nz.co.plantandfood.todocompose.util.ListScreen
import nz.co.plantandfood.todocompose.util.TaskScreen

class Screens(navController: NavHostController) {
    val navigateToListScreen: (Action) -> Unit = { action ->
        navController.navigate(ListScreen(action.name)) {
           // popUpTo(ListScreen) { inclusive = true }
        }

    }
    val navigateToTaskScreen: (Int) -> Unit = { taskId ->
        navController.navigate(TaskScreen(taskId))
    }
}