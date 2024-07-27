package nz.co.plantandfood.mvvmtodoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import nz.co.plantandfood.mvvmtodoapp.data.Todo
import nz.co.plantandfood.mvvmtodoapp.data.TodoDao

@Database(
    entities = [Todo::class],
    version = 1
)
abstract class TodoDatabase: RoomDatabase() {

    abstract val dao: TodoDao
}