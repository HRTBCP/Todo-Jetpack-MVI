package nz.co.plantandfood.mvvmtodoapp.presentation.screen.add_edit_todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import nz.co.plantandfood.mvvmtodoapp.domain.Todo
import nz.co.plantandfood.mvvmtodoapp.domain.TodoRepository
import nz.co.plantandfood.mvvmtodoapp.presentation.util.UiEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val repository: TodoRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    val initialState: AddEditTodoState by lazy {
        AddEditTodoState()
    }
    var todoState by mutableStateOf(initialState)
        private set


    private val _uiEffect =  Channel<UiEffect>()
    val uiEffect = _uiEffect.receiveAsFlow()

    init {
        val todoId = savedStateHandle.get<Int>("todoId")!!
        todoState = AddEditTodoState()
        if(todoId != -1) {
            viewModelScope.launch {
                repository.getTodoById(todoId)?.let { todo ->
                    todoState = todoState.copy(title = todo.title, description =  todo.description ?: "", todo  = todo)
                   // this@AddEditTodoViewModel.todo = todo
                }
            }
        }
    }

    fun onAction(action: AddEditTodoAction) {
        when(action) {
            is AddEditTodoAction.OnTitleChange -> {
                todoState = todoState.copy(title = action.title)
            }
            is AddEditTodoAction.OnDescriptionChange -> {
                todoState = todoState.copy(description = action.description)
            }
            is AddEditTodoAction.OnSaveTodoClick -> {
                viewModelScope.launch {
                    if(todoState.title.isBlank()) {
                        sendUiEffect(
                            UiEffect.ShowSnackbar(
                            message = "The title can't be empty"
                        ))
                        return@launch
                    }
                    repository.insertTodo(
                        Todo(
                            title = todoState.title,
                            description = todoState.description,
                            isDone = todoState.todo?.isDone ?: false,
                            id = todoState.todo?.id
                        )
                    )
                    sendUiEffect(UiEffect.PopBackStack)
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