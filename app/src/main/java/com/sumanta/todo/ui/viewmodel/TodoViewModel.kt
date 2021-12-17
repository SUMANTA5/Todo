package com.sumanta.todo.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumanta.todo.data.Todo
import com.sumanta.todo.repo.TodoRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel
@Inject
constructor(private val todoRepo: TodoRepo):ViewModel() {

    val response: MutableState<List<Todo>> = mutableStateOf(listOf())


    fun insert(todo: Todo) = viewModelScope.launch {
        todoRepo.insert(todo)
    }

    init {
        getAllTodo()
    }

    private fun getAllTodo() = viewModelScope.launch {
        todoRepo.getAllTodos()
            .catch { e->
                Log.d("main","Exception${e.message}")
            }.collect {
                response.value = it
            }
    }

}