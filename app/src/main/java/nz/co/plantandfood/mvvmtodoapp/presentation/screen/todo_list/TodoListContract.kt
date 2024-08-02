package nz.co.plantandfood.mvvmtodoapp.presentation.screen.todo_list

import androidx.compose.material3.SnackbarDuration
import nz.co.plantandfood.mvvmtodoapp.domain.Todo
import nz.co.plantandfood.mvvmtodoapp.presentation.util.Routes

class TodoListContract {

    data class State(
        val lastDeletedTodo : Todo? = null,
        val todos: List<Todo> = emptyList(),

    )
    sealed class Effect {

        data class Navigate(val route: Routes.TodoEdit): Effect()
        data class ShowSnackbar(
            val message: String,
            val action: String? = null,
            var duration: SnackbarDuration = SnackbarDuration.Long
        ): Effect()
    }

    sealed class Action {
        data class OnDeleteTodoClick(val todo: Todo): Action()
        data class OnDoneChange(val todo: Todo, val isDone: Boolean): Action()
        data object OnUndoDeleteClick: Action()
        data class OnTodoClick(val todo: Todo): Action()
        data object OnAddTodoClick: Action()
    }
}