package com.sevdetneng.todoapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sevdetneng.todoapp.components.TodoRow
import com.sevdetneng.todoapp.model.Todo

@Composable
fun TodoListScreen(){

    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.LightGray) {
                Text("Todos")
            }
        }
    ) { padding ->
        Column(modifier = Modifier
            .padding(8.dp)){
            TodoRow(todo = Todo(title = "Todo Title", description = "Todo Desc", isDone = false))
        }
    }
}