package nz.co.plantandfood.todocompose.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import nz.co.plantandfood.todocompose.util.Constants

@Entity(tableName = Constants.DATABASE_TABLE)
data class TodoTask(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val priority: Priority = Priority.NONE
)
