package nz.co.plantandfood.mvvmtodoapp.presentation.util

import kotlinx.serialization.Serializable

sealed class Routes {

    @Serializable
    object TodoList

    @Serializable
    data class TodoEdit(val todoId: Int = -1)
}