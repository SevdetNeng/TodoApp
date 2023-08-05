package com.sevdetneng.todoapp.repository

import com.sevdetneng.todoapp.data.todoDao
import com.sevdetneng.todoapp.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TodoRepository @Inject constructor(private val dao : todoDao) {
    suspend fun addTodo(todo : Todo){
        dao.insertTodo(todo)
    }

    suspend fun deleteTodo(todo : Todo){
        dao.deleteTodo(todo)
    }

    suspend fun getTodoById(id : Int) : Todo{
        return dao.getTodoById(id)
    }

    fun getAllTodos() : Flow<List<Todo>> = dao.getAllTodos().flowOn(Dispatchers.IO).conflate()
}