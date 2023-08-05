package com.sevdetneng.todoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sevdetneng.todoapp.screens.AddTodoScreen
import com.sevdetneng.todoapp.screens.TodoEditScreen
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
        composable(Screens.TodoEdit.name+"/{todoId}",
            arguments = listOf(navArgument(name = "todoId"){type = NavType.IntType} ) ){ backStackEntry ->
            TodoEditScreen(navController = navController, id = backStackEntry.arguments?.getInt("todoId"))

        }
    }
}