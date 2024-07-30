package nz.co.plantandfood.mvvmtodoapp.presentation.screen.add_edit_todo

sealed class AddEditTodoAction {
    data class OnTitleChange(val title: String): AddEditTodoAction()
    data class OnDescriptionChange(val description: String): AddEditTodoAction()
    data object OnSaveTodoClick: AddEditTodoAction()
}
