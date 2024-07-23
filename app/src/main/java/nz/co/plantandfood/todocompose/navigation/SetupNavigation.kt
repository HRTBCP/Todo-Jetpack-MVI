package nz.co.plantandfood.todocompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import kotlinx.serialization.Serializable
import nz.co.plantandfood.todocompose.navigation.destination.listComposable
import nz.co.plantandfood.todocompose.navigation.destination.taskComposable
import nz.co.plantandfood.todocompose.ui.viewmodel.SharedViewModel
import nz.co.plantandfood.todocompose.util.Action

@Serializable
data class ListScreenNav(val action: String? =Action.NO_ACTION.name)

@Serializable
data class TaskScreenNav(val taskId: Int)

@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val screen = remember(navController) {
        Screens(navController)
    }
    NavHost(
        navController,
        startDestination = ListScreenNav(Action.NO_ACTION.name)
    ) {
        listComposable(screen.navigateToTaskScreen,
        sharedViewModel = sharedViewModel)
        taskComposable (screen.navigateToListScreen)

    }
}