package com.sevdetneng.todoapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sevdetneng.todoapp.components.TodoRow
import com.sevdetneng.todoapp.model.Todo
import com.sevdetneng.todoapp.navigation.Screens

@Composable
fun TodoListScreen(navController: NavController){
    val todoListViewModel : TodoListViewModel = hiltViewModel()
    val todos = todoListViewModel.todos.collectAsState().value
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.LightGray) {
                Text("Todos")
            }
        }
    , floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screens.TodoAdd.name) }) {
                Icon(imageVector = Icons.Default.Add,"Add")
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(4.dp)) {
            items(todos){ todo ->
                TodoRow(todo = todo, onDeleteClick = { todo ->
                    todoListViewModel.deleteTodo(todo)
                }, onDoneChange = {
                    val newTodo = todo.copy(isDone = !todo.isDone)
                    todoListViewModel.addTodo(newTodo)
                }, onTodoClick = {
                    navController.navigate(Screens.TodoEdit.name + "/${todo.id}")
                })
            }
        }
    }
}