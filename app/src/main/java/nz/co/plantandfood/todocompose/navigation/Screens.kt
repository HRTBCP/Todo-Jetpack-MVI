package nz.co.plantandfood.todocompose.navigation

import androidx.navigation.NavHostController
import nz.co.plantandfood.todocompose.util.Action


class Screens(navController: NavHostController) {
    val navigateToListScreen: (Action) -> Unit = { action ->
        navController.navigate(ListScreenNav(action.name)) {
            popUpTo(ListScreenNav(action.name)) { inclusive = true }
        }

    }
    val navigateToTaskScreen: (Int) -> Unit = { taskId ->
        navController.navigate(TaskScreenNav(taskId))
    }
}