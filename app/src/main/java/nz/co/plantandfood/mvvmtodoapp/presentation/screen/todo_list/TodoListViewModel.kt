package nz.co.plantandfood.mvvmtodoapp.presentation.screen.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import nz.co.plantandfood.mvvmtodoapp.domain.Todo
import nz.co.plantandfood.mvvmtodoapp.domain.TodoRepository
import nz.co.plantandfood.mvvmtodoapp.presentation.util.Routes
import nz.co.plantandfood.mvvmtodoapp.presentation.util.UiEvent
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

    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedTodo: Todo? = null

    fun onAction(action: TodoListAction) {
        when(action) {
            is TodoListAction.OnTodoClick -> {
              action.todo.id?.let {
                    sendUiEvent(UiEvent.Navigate(Routes.add_edit_todo(it)))
                }


            }
            is TodoListAction.OnAddTodoClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.add_edit_todo()))
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
                    sendUiEvent(
                        UiEvent.ShowSnackbar(
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

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}