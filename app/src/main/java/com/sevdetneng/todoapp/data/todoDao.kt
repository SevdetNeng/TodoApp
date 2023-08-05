package com.sevdetneng.todoapp.data

import androidx.room.*
import com.sevdetneng.todoapp.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface todoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTodo(todo : Todo)

    @Delete
    fun deleteTodo(todo : Todo)

    @Query("SELECT * from todos")
    fun getAllTodos() : Flow<List<Todo>>

    @Query("SELECT * from todos WHERE id = :id")
    fun getTodoById(id : Int) : Todo
}