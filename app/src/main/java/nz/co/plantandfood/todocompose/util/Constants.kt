package nz.co.plantandfood.todocompose.util

import kotlinx.serialization.Serializable

object Constants {
    const val DATABASE_NAME = "todo_database"
    const val DATABASE_TABLE = "todo_table"


}

@Serializable
data class ListScreen(val action: String? =Action.NO_ACTION.name)

@Serializable
data class TaskScreen(val taskId: Int)