package com.sevdetneng.todoapp.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevdetneng.todoapp.model.Todo
import com.sevdetneng.todoapp.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(private val todoRepository: TodoRepository) : ViewModel() {
    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos = _todos.asStateFlow()
    val filterState : MutableState<String> = mutableStateOf("all")

    init {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.getAllTodos().distinctUntilChanged()
                .collect{   listOfTodos ->
                    _todos.value = listOfTodos
                }
        }
    }

    fun addTodo(todo : Todo){
        viewModelScope.launch {
            todoRepository.addTodo(todo)
        }
    }
    fun deleteTodo(todo : Todo){
        viewModelScope.launch {
            todoRepository.deleteTodo(todo)
        }
    }

    fun updateTodo(todo : Todo){
        viewModelScope.launch {
            todoRepository.addTodo(todo)
        }
    }
}