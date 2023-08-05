package com.sevdetneng.todoapp.repository

import com.sevdetneng.todoapp.data.todoDao
import com.sevdetneng.todoapp.model.Todo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoRepository @Inject constructor(private val dao : todoDao) {
    fun addTodo(todo : Todo){
        dao.insertTodo(todo)
    }

    fun deleteTodo(todo : Todo){
        dao.deleteTodo(todo)
    }

    fun getTodoById(id : Int) : Todo{
        return dao.getTodoById(id)
    }

    fun getAllTodos() : Flow<List<Todo>>{
        return dao.getAllTodos()
    }
}