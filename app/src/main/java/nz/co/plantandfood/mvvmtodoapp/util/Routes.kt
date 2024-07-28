package nz.co.plantandfood.mvvmtodoapp.util

object Routes {
    const val TODO_LIST = "todo_list"
    const val ADD_EDIT_TODO = "add_edit_todo"
    object todo_list_obj
    data class add_edit_todo(val todoId: Int? = null)
}