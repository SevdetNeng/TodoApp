package com.sevdetneng.todoapp.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevdetneng.todoapp.model.Todo
import com.sevdetneng.todoapp.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTodoViewModel @Inject constructor(private val todoRepository: TodoRepository) :
    ViewModel() {
    var todoTitleState : MutableState<String> = mutableStateOf("")
    var todoDescriptionState = mutableStateOf("")

    fun addTodo(){
        val todo = createTodo()
        viewModelScope.launch {
            todoRepository.addTodo(todo)
        }

    }

    private fun createTodo() : Todo{
        return Todo(title = todoTitleState.value, description = todoDescriptionState.value)
    }

}