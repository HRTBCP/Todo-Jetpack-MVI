package nz.co.plantandfood.mvvmtodoapp.presentation.screen.add_edit_todo

import nz.co.plantandfood.mvvmtodoapp.domain.Todo

data class AddEditTodoState(
    val todo: Todo? = null,
    val title: String = "",
    val description: String = "",

)
