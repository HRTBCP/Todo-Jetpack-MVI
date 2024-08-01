package nz.co.plantandfood.mvvmtodoapp.presentation.screen.add_edit_todo

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
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
import nz.co.plantandfood.mvvmtodoapp.presentation.util.UiEffect

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
    todoState: AddEditTodoState,
    uiEffect: Flow<UiEffect>,
    onAction: (AddEditTodoAction) -> Unit,
    onPopBackStack: () -> Unit
) {

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        uiEffect.collect { effect ->
            when (effect) {
                is UiEffect.PopBackStack -> {
                    onPopBackStack()

                }
                is UiEffect.ShowSnackbar -> {

                    snackbarHostState.showSnackbar(
                        message = effect.message,
                        actionLabel = effect.action
                    )
                }

                else -> Unit
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
                onAction(AddEditTodoAction.OnSaveTodoClick)
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
                    onAction(AddEditTodoAction.OnTitleChange(it))
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
                    onAction(AddEditTodoAction.OnDescriptionChange(it))
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