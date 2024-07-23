package nz.co.plantandfood.todocompose.ui.screen.list

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nz.co.plantandfood.todocompose.R
import nz.co.plantandfood.todocompose.ui.viewmodel.SharedViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListScreen(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    val serachAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState
    Scaffold(
        topBar = {
            ListAppBar(sharedViewModel, serachAppBarState , searchTextState)
        } ,
        content = {},
        floatingActionButton = {
            ListFab(navigateToTaskScreen)
        }
    )

}

@Composable
fun ListFab(
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    FloatingActionButton(
        onClick = {
            navigateToTaskScreen(-1)
        }) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(id = R.string.add_button)
        )
    }
}

@Composable
@Preview
private fun ListScreenPreview() {
   // ListScreen(navigateToTaskScreen = {})

}