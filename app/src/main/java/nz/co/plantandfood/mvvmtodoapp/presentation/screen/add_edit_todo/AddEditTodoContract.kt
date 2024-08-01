package nz.co.plantandfood.mvvmtodoapp.presentation.screen.add_edit_todo

import androidx.compose.material3.SnackbarDuration

class AddEditTodoContract {

    sealed class Action {
        data class OnTitleChange(val title: String): Action()
        data class OnDescriptionChange(val description: String): Action()
        data object OnSaveTodoClick: Action()
    }

    sealed class Effect {
        object PopBackStack: Effect()
        data class ShowSnackbar(
            val message: String,
            val action: String? = null,
            var duration: SnackbarDuration = SnackbarDuration.Short
        ): Effect()
    }

}