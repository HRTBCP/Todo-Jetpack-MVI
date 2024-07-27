package nz.co.plantandfood.mvvmtodoapp.data

import kotlinx.coroutines.flow.Flow
import nz.co.plantandfood.mvvmtodoapp.data.Todo

interface TodoRepository {

    suspend fun insertTodo(todo: Todo)

    suspend fun deleteTodo(todo: Todo)

    suspend fun getTodoById(id: Int): Todo?

    fun getTodos(): Flow<List<Todo>>
}