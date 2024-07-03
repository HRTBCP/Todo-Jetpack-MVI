package nz.co.plantandfood.todocompose.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import nz.co.plantandfood.todocompose.util.Action
import nz.co.plantandfood.todocompose.util.Constants

fun NavGraphBuilder.taskComposable(
    navigateToTaskScreen: (action: Action) -> Unit
) {
    composable(
        route = Constants.TASK_SCREEN,
        arguments = listOf(navArgument(Constants.TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) {

    }
}