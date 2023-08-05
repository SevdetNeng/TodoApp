package com.sevdetneng.todoapp.screens

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevdetneng.todoapp.model.Todo
import com.sevdetneng.todoapp.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoEditViewModel @Inject constructor(private val todoRepository: TodoRepository,
savedStateHandle: SavedStateHandle) : ViewModel() {

    var todo by mutableStateOf(Todo(title = "", description = ""))
    private val _title = MutableStateFlow("")
    val title = _title.asStateFlow()
    private val todoId = savedStateHandle.get<Int>("todoId")


    private val _description = MutableStateFlow("")
    val description = _description.asStateFlow()

    init {
        viewModelScope.launch {
            todo = todoRepository.getTodoById(todoId!!)
            _title.value = todo.title
            _description.value = todo.description
        }
    }

    fun updateTodo(todo : Todo){
        viewModelScope.launch {
            if(todo.title.isNotEmpty()){
                todoRepository.addTodo(todo)
            }
        }
    }

    fun setTitle(title : String){
        _title.value = title
    }
    fun setDescription(desc : String){
        _description.value = desc
    }


}