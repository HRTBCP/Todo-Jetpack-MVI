package nz.co.plantandfood.mvvmtodoapp.presentation.screen.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import nz.co.plantandfood.mvvmtodoapp.domain.Todo
import nz.co.plantandfood.mvvmtodoapp.domain.TodoRepository
import nz.co.plantandfood.mvvmtodoapp.presentation.util.Routes
import nz.co.plantandfood.mvvmtodoapp.presentation.util.UiEffect
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

    private val _uiEffect =  Channel<UiEffect>()
    val uiEffect = _uiEffect.receiveAsFlow()

    private var deletedTodo: Todo? = null

    fun onAction(action: TodoListAction) {
        when(action) {
            is TodoListAction.OnTodoClick -> {
              action.todo.id?.let {
                    sendUiEffect(UiEffect.Navigate(Routes.TodoEdit(it)))
                }


            }
            is TodoListAction.OnAddTodoClick -> {
                sendUiEffect(UiEffect.Navigate(Routes.TodoEdit()))
            }
            is TodoListAction.OnUndoDeleteClick -> {
                deletedTodo?.let { todo ->
                    viewModelScope.launch {
                        repository.insertTodo(todo)
                    }
                }
            }
            is TodoListAction.OnDeleteTodoClick -> {
                viewModelScope.launch {
                    deletedTodo = action.todo
                    repository.deleteTodo(action.todo)
                    sendUiEffect(
                        UiEffect.ShowSnackbar(
                        message = "Todo deleted",
                        action = "Undo"
                    ))
                }
            }
            is TodoListAction.OnDoneChange -> {
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

    private fun sendUiEffect(event: UiEffect) {
        viewModelScope.launch {
            _uiEffect.send(event)
        }
    }
}