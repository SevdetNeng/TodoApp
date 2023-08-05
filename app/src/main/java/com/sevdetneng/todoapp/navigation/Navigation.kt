package com.sevdetneng.todoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sevdetneng.todoapp.screens.AddTodoScreen
import com.sevdetneng.todoapp.screens.TodoListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.TodoList.name ){
        composable(Screens.TodoList.name){
            TodoListScreen(navController)
        }
        composable(Screens.TodoAdd.name){
            AddTodoScreen(navController)
        }
    }
}