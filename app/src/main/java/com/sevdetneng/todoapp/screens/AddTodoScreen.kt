package com.sevdetneng.todoapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import javax.inject.Inject

@Composable
fun AddTodoScreen(navController: NavController){

    val addTodoViewModel : AddTodoViewModel = hiltViewModel()
    Column(horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {

        TextField(value = addTodoViewModel.todoTitleState.value, onValueChange = { todoTitle ->
            addTodoViewModel.todoTitleState.value = todoTitle
        } , label = {
            Text("Todo Title")
        } )

        TextField(value = addTodoViewModel.todoDescriptionState.value, onValueChange = { todoDesc ->
            addTodoViewModel.todoDescriptionState.value = todoDesc
        } , label = {
            Text("Todo Description")
        } )

        Button(onClick = {
            addTodoViewModel.addTodo()
            navController.popBackStack()
        }){
            Text("Save")

        }
    }

}