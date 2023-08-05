package com.sevdetneng.todoapp.data

import androidx.room.*
import com.sevdetneng.todoapp.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface todoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo : Todo)

    @Delete
    suspend fun deleteTodo(todo : Todo)

    @Query("SELECT * from todos")
    fun getAllTodos() : Flow<List<Todo>>

    @Query("SELECT * from todos WHERE id = :id")
    suspend fun getTodoById(id : Int) : Todo
}