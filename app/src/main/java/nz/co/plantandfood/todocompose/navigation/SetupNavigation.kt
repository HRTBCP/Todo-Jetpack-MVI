package nz.co.plantandfood.todocompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import nz.co.plantandfood.todocompose.navigation.destination.listComposable
import nz.co.plantandfood.todocompose.navigation.destination.taskComposable
import nz.co.plantandfood.todocompose.util.Constants

@Composable
fun SetupNavigation(
    navController: NavHostController
) {
    val screen = remember(navController) {
        Screens(navController)
    }
    NavHost(
        navController,
        startDestination = Constants.LIST_SCREEN
    ) {
        listComposable(
            navigateToTaskScreen = screen.task
        )

        taskComposable (
            navigateToTaskScreen = screen.list
        )

    }
}