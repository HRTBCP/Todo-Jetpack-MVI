package nz.co.plantandfood.todocompose.navigation.destination

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import nz.co.plantandfood.todocompose.util.Action
import nz.co.plantandfood.todocompose.util.TaskScreen

fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (action:Action) -> Unit
) {
    composable<TaskScreen>(){
        val taskScreen = it.toRoute<TaskScreen>()
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = " task ${taskScreen.taskId}")
            Button(onClick = {

                navigateToListScreen(Action.ADD)
            }) {
                Text(text = "Go to list screen")
            }

        }
    }
}