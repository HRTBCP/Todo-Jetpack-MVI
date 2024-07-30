package nz.co.plantandfood.mvvmtodoapp.presentation.util

import kotlinx.serialization.Serializable

object Routes {

    @Serializable
    object todo_list_obj

    @Serializable
    data class add_edit_todo(val todoId: Int = -1)
}