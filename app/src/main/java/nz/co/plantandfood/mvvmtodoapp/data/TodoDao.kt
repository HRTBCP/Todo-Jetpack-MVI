package nz.co.plantandfood.mvvmtodoapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import nz.co.plantandfood.mvvmtodoapp.data.Todo

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getTodoById(id: Int): Todo?

    @Query("SELECT * FROM todo")
    fun getTodos(): Flow<List<Todo>>
}