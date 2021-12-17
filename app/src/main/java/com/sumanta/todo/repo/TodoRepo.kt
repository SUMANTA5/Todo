package com.sumanta.todo.repo

import com.sumanta.todo.data.Todo
import com.sumanta.todo.data.TodoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TodoRepo
@Inject
constructor(private val todoDao: TodoDao){

    suspend fun insert(todo: Todo) = withContext(Dispatchers.IO){
        todoDao.insert(todo)
    }

    fun getAllTodos(): Flow<List<Todo>> = todoDao.getAllTodos()

}