package tw.edu.ntub.myapp10

import kotlinx.coroutines.flow.Flow

class TodoRepository(private val todoDao: TodoDao) {
    val allTodos: Flow<List<Todo>> = todoDao.getAllTodos()
    val allCompletedTodos: Flow<List<Todo>> = todoDao.getCompletedTodos()
    fun getTodoById(id: Int): Flow<Todo> {
        return todoDao.getTodoById(id)
    }

    suspend fun insert(todo: Todo) {
        todoDao.insert(todo)
    }

    suspend fun update(todo: Todo) {
        todoDao.update(todo)
    }

    suspend fun delete(todo: Todo) {
        todoDao.delete(todo)
    }
}