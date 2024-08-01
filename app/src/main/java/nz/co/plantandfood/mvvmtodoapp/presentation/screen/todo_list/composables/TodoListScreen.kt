package nz.co.plantandfood.mvvmtodoapp.presentation.screen.todo_list.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.Flow
import nz.co.plantandfood.mvvmtodoapp.domain.Todo
import nz.co.plantandfood.mvvmtodoapp.presentation.screen.todo_list.TodoListContract
import nz.co.plantandfood.mvvmtodoapp.presentation.screen.todo_list.TodoListViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TodoListScreenRoot(navController: NavController, viewModel: TodoListViewModel = hiltViewModel()) {
    val todos = viewModel.todos.collectAsState(initial = emptyList())

    TodoListScreen(todos.value, viewModel::onAction, viewModel.uiEffect) {
        navController.navigate(it.route)
    }


}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TodoListScreen(
    todos: List<Todo>,
    onAction: (TodoListContract.Action) -> Unit,
    uiEffect: Flow<TodoListContract.Effect>,
    onNavigationRequested: (TodoListContract.Effect.Navigate) -> Unit

) {
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(key1 = true) {
        uiEffect.collect { event ->
            when(event) {
                is TodoListContract.Effect.ShowSnackbar -> {
                    val result = snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                    if(result == SnackbarResult.ActionPerformed) {
                        onAction(TodoListContract.Action.OnUndoDeleteClick)
                    }
                }
                is TodoListContract.Effect.Navigate ->onNavigationRequested(event)
                else -> Unit
            }
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onAction(TodoListContract.Action.OnAddTodoClick)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(todos) { todo ->
                TodoItem(
                    todo = todo,
                    onAction = onAction,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onAction(TodoListContract.Action.OnTodoClick(todo))
                        }
                        .padding(16.dp)
                )
            }
        }
    }

}