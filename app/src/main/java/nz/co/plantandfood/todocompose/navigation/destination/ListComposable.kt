package nz.co.plantandfood.todocompose.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import nz.co.plantandfood.todocompose.util.Constants

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (taskId: Int) -> Unit
) {

    composable(
        route = Constants.LIST_SCREEN,
        arguments = listOf(navArgument(Constants.LIST_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) {

    }
}