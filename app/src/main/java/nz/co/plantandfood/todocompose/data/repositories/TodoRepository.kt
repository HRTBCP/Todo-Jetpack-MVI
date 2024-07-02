package nz.co.plantandfood.todocompose.data.repositories

import androidx.room.Insert
import dagger.hilt.android.scopes.ViewModelScoped
import nz.co.plantandfood.todocompose.data.ToDoDao
import nz.co.plantandfood.todocompose.data.models.TodoTask
import javax.inject.Inject
@ViewModelScoped
class TodoRepository @Inject constructor(private val todoDao: ToDoDao){
    val getAllTasks = todoDao.getAllTasks()
    val shortByLowPriority = todoDao.sortByLowPriority()
    val shortByHighPriority = todoDao.sortByHighPriority()
    fun getSelectedTask(taskId: Int) = todoDao.getSelectedTask(taskId)

    suspend fun insertTask(todoTask: TodoTask) = todoDao.insertTask(todoTask)
    suspend fun deleteTask(todoTask: TodoTask) = todoDao.deleteTask(todoTask)
    suspend fun deleteAllTasks() = todoDao.deleteAllTasks()
    fun searchDatabase(searchQuery: String) = todoDao.searchDatabase(searchQuery)


}