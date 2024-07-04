package nz.co.plantandfood.todocompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import nz.co.plantandfood.todocompose.navigation.destination.listComposable
import nz.co.plantandfood.todocompose.navigation.destination.taskComposable
import nz.co.plantandfood.todocompose.util.Action
import nz.co.plantandfood.todocompose.util.ListScreen

@Composable
fun SetupNavigation(
    navController: NavHostController
) {
    val screen = remember(navController) {
        Screens(navController)
    }
    NavHost(
        navController,
        startDestination = ListScreen(Action.NO_ACTION.name)
    ) {
        listComposable(screen.navigateToTaskScreen)
        taskComposable (screen.navigateToListScreen)

    }
}