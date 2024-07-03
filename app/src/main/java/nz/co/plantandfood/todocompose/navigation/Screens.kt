package nz.co.plantandfood.todocompose.navigation

import androidx.navigation.NavHostController
import nz.co.plantandfood.todocompose.util.Action
import nz.co.plantandfood.todocompose.util.Constants

class Screens(navController: NavHostController) {
    val list: (Action) -> Unit = { action ->
        navController.navigate("list/${action.name}") {
            popUpTo(Constants.LIST_SCREEN) { inclusive = true }
        }

    }
    val task: (Int) -> Unit = { taskId ->
        navController.navigate("task/$taskId")
    }
}