package nz.co.plantandfood.mvvmtodoapp.presentation.screen.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import nz.co.plantandfood.mvvmtodoapp.domain.Todo
import nz.co.plantandfood.mvvmtodoapp.domain.TodoRepository
import nz.co.plantandfood.mvvmtodoapp.presentation.util.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: TodoRepository
): ViewModel() {

    val todos = repository.getTodos()

    private val _uiEffect =  Channel<TodoListContract.Effect>()
    val uiEffect = _uiEffect.receiveAsFlow()

    private var deletedTodo: Todo? = null

    fun onAction(action: TodoListContract.Action) {
        when(action) {
            is TodoListContract.Action.OnTodoClick -> {
              action.todo.id?.let {
                    sendUiEffect(TodoListContract.Effect.Navigate(Routes.TodoEdit(it)))
                }


            }
            is TodoListContract.Action.OnAddTodoClick -> {
                sendUiEffect(TodoListContract.Effect.Navigate(Routes.TodoEdit()))
            }
            is TodoListContract.Action.OnUndoDeleteClick -> {
                deletedTodo?.let { todo ->
                    viewModelScope.launch {
                        repository.insertTodo(todo)
                    }
                }
            }
            is TodoListContract.Action.OnDeleteTodoClick -> {
                viewModelScope.launch {
                    deletedTodo = action.todo
                    repository.deleteTodo(action.todo)
                    sendUiEffect(
                        TodoListContract.Effect.ShowSnackbar(
                        message = "Todo deleted",
                        action = "Undo"
                    ))
                }
            }
            is TodoListContract.Action.OnDoneChange -> {
                viewModelScope.launch {
                    repository.insertTodo(
                        action.todo.copy(
                            isDone = action.isDone
                        )
                    )
                }
            }
        }
    }

    private fun sendUiEffect(event: TodoListContract.Effect) {
        viewModelScope.launch {
            _uiEffect.send(event)
        }
    }
}