package com.sevdetneng.todoapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class Todo(
    @PrimaryKey
    val id : Int? = null,
    val title : String,
    val description : String,
    val isDone : Boolean = false
)
