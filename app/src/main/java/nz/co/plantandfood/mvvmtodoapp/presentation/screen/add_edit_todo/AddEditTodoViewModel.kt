package nz.co.plantandfood.mvvmtodoapp.presentation.screen.add_edit_todo

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import nz.co.plantandfood.mvvmtodoapp.domain.Todo
import nz.co.plantandfood.mvvmtodoapp.domain.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val repository: TodoRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private var _todoS: MutableStateFlow<AddEditTodoContract.State> = MutableStateFlow(AddEditTodoContract.State())
    val todoState = _todoS.asStateFlow()

    private val _uiEffect =  Channel<AddEditTodoContract.Effect>()
    val uiEffect = _uiEffect.receiveAsFlow()

    init {
        val todoId = savedStateHandle.get<Int>("todoId")!!
        if(todoId != -1) {
            viewModelScope.launch {
                repository.getTodoById(todoId)?.let { todo ->
                    _todoS.update {
                        it.copy(title = todo.title, description =  todo.description ?: "", todo  = todo)
                    }
                }
            }
        }
    }

    fun onAction(action: AddEditTodoContract.Action) {
        when(action) {
            is AddEditTodoContract.Action.OnTitleChange -> {
                _todoS.update {
                    it.copy(title = action.title)
                }
            }
            is AddEditTodoContract.Action.OnDescriptionChange -> {
                _todoS.update {
                    it.copy(description = action.description)
                }
            }
            is AddEditTodoContract.Action.OnSaveTodoClick -> {

                viewModelScope.launch {
                    if(_todoS.value.title.isBlank()) {
                        sendUiEffect(
                            AddEditTodoContract.Effect.ShowSnackbar(
                            message = "The title can't be empty"
                        ))
                        return@launch
                    }
                    repository.insertTodo(
                        Todo(
                            title = _todoS.value.title,
                            description = _todoS.value.description,
                            isDone = _todoS.value.todo?.isDone ?: false,
                            id = _todoS.value.todo?.id
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