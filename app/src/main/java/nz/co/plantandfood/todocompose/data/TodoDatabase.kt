package nz.co.plantandfood.todocompose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import nz.co.plantandfood.todocompose.data.models.TodoTask

@Database(entities = [TodoTask::class], version = 1, exportSchema = false)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): ToDoDao
}