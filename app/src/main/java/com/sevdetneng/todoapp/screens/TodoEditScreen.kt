package com.sevdetneng.todoapp.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun TodoEditScreen(navController: NavController,id : Int?){
    val todoEditViewModel : TodoEditViewModel = hiltViewModel()
    val title = todoEditViewModel.title.collectAsState()
    val description = todoEditViewModel.description.collectAsState()


    //todoEditViewModel.todoTitleState.value = todo.title
    //todoEditViewModel.todoDescState = todo.description

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
    verticalArrangement = Arrangement.SpaceEvenly,
    horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(value = title.value,
            onValueChange = { newTitle ->
                todoEditViewModel.setTitle(newTitle)
            },
            label = {
                Text("Title")
            }
        )
        TextField(value = description.value,
            onValueChange = { desc ->
                todoEditViewModel.setDescription(desc)
            },
            label = {
                Text("Description")
            }
        )
        Button(onClick = {
            val newTodo = todoEditViewModel.todo.copy(title = title.value, description = description.value)
            todoEditViewModel.updateTodo(newTodo)
            navController.popBackStack()
        }){
            Text("Save")
        }
    }

}