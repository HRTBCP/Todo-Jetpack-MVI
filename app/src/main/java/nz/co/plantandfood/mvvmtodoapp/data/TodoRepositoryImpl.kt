package nz.co.plantandfood.mvvmtodoapp.data

import kotlinx.coroutines.flow.Flow
import nz.co.plantandfood.mvvmtodoapp.data.Todo
import nz.co.plantandfood.mvvmtodoapp.data.TodoDao
import nz.co.plantandfood.mvvmtodoapp.data.TodoRepository

class TodoRepositoryImpl(
    private val dao: TodoDao
): TodoRepository {

    override suspend fun insertTodo(todo: Todo) {
        dao.insertTodo(todo)
    }

    override suspend fun deleteTodo(todo: Todo) {
        dao.deleteTodo(todo)
    }

    override suspend fun getTodoById(id: Int): Todo? {
        return dao.getTodoById(id)
    }

    override fun getTodos(): Flow<List<Todo>> {
        return dao.getTodos()
    }
}