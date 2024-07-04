package nz.co.plantandfood.todocompose.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import nz.co.plantandfood.todocompose.navigation.ListScreenNav
import nz.co.plantandfood.todocompose.ui.screen.list.ListScreen

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (taskId: Int) -> Unit

) {

    composable<ListScreenNav> {
        val listScreen = it.toRoute<ListScreenNav>()
        ListScreen(
            navigateToTaskScreen = navigateToTaskScreen
        )

//        Column(
//            modifier = Modifier.fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Button(onClick = {
//                navigateToTaskScreen(1)
//
//            }) {
//                Text(text = "Go to task ${listScreen.action}")
//            }
//        }
    }
}