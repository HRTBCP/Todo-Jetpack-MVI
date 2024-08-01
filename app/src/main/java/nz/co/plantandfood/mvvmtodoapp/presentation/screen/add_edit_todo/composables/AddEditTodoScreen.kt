package nz.co.plantandfood.mvvmtodoapp.presentation.screen.add_edit_todo.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.Flow
import nz.co.plantandfood.mvvmtodoapp.presentation.screen.add_edit_todo.AddEditTodoContract
import nz.co.plantandfood.mvvmtodoapp.presentation.screen.add_edit_todo.AddEditTodoViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddEditToDoEventRoot(
    navController: NavController,
    viewModel: AddEditTodoViewModel = hiltViewModel()
) {

        AddEditTodoScreen(viewModel.todoState, viewModel.uiEffect, viewModel::onAction) {
            navController.popBackStack()
        }


}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddEditTodoScreen(
    todoState: AddEditTodoContract.State,
    uiEffect: Flow<AddEditTodoContract.Effect>,
    onAction: (AddEditTodoContract.Action) -> Unit,
    onPopBackStack: () -> Unit
) {

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        uiEffect.collect { effect ->
            when (effect) {
                is AddEditTodoContract.Effect.PopBackStack -> {
                    onPopBackStack()

                }
                is AddEditTodoContract.Effect.ShowSnackbar -> {

                    snackbarHostState.showSnackbar(
                        message = effect.message,
                        actionLabel = effect.action,
                        duration = effect.duration
                    )
                }

            }
        }
    }
    Scaffold(
        //scaffoldState = scaffoldState,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onAction(AddEditTodoContract.Action.OnSaveTodoClick)
            }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Save"
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TextField(
                value = todoState.title,
                onValueChange = {
                    onAction(AddEditTodoContract.Action.OnTitleChange(it))
                },
                placeholder = {
                    Text(text = "Title")
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = todoState.description ,
                onValueChange = {
                    onAction(AddEditTodoContract.Action.OnDescriptionChange(it))
                },
                placeholder = {
                    Text(text = "Description")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 5
            )
        }
    }
}