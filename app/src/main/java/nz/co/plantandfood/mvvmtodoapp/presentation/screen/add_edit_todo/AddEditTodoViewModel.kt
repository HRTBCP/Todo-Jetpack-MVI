package nz.co.plantandfood.mvvmtodoapp.presentation.screen.add_edit_todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import nz.co.plantandfood.mvvmtodoapp.domain.Todo
import nz.co.plantandfood.mvvmtodoapp.domain.TodoRepository
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
    private val initialState: AddEditTodoContract.State by lazy {
        AddEditTodoContract.State()
    }
    var todoState by mutableStateOf(initialState)
        private set


    private val _uiEffect =  Channel<AddEditTodoContract.Effect>()
    val uiEffect = _uiEffect.receiveAsFlow()

    init {
        val todoId = savedStateHandle.get<Int>("todoId")!!
        if(todoId != -1) {
            viewModelScope.launch {
                repository.getTodoById(todoId)?.let { todo ->
                    todoState = todoState.copy(title = todo.title, description =  todo.description ?: "", todo  = todo)
                }
            }
        }
    }

    fun onAction(action: AddEditTodoContract.Action) {
        when(action) {
            is AddEditTodoContract.Action.OnTitleChange -> {
                todoState = todoState.copy(title = action.title)
            }
            is AddEditTodoContract.Action.OnDescriptionChange -> {
                todoState = todoState.copy(description = action.description)
            }
            is AddEditTodoContract.Action.OnSaveTodoClick -> {
                viewModelScope.launch {
                    if(todoState.title.isBlank()) {
                        sendUiEffect(
                            AddEditTodoContract.Effect.ShowSnackbar(
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
                    sendUiEffect(AddEditTodoContract.Effect.PopBackStack)
                }
            }
        }
    }

    private fun sendUiEffect(event: AddEditTodoContract.Effect) {
        viewModelScope.launch {
            _uiEffect.send(event)
        }
    }
}