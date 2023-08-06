package com.sevdetneng.todoapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sevdetneng.todoapp.components.TodoRow
import com.sevdetneng.todoapp.model.Todo
import com.sevdetneng.todoapp.navigation.Screens

@Composable
fun TodoListScreen(navController: NavController){
    val todoListViewModel : TodoListViewModel = hiltViewModel()
    val todos = todoListViewModel.todos.collectAsState().value
    val todoCount = todos.size
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color(0xff6096B4)) {
                Text("Todos")
            }
        }
    , floatingActionButton = {
              ExtendedFloatingActionButton(text = { Text("Add")}, onClick = {
                  navController.navigate(Screens.TodoAdd.name)
              },icon = { Icon(imageVector = Icons.Default.Add, contentDescription = "Add")},
                  modifier = Modifier.border(BorderStroke(1.dp,Color.Black), shape = RoundedCornerShape(12.dp)),
              shape = RoundedCornerShape(12.dp),
                  backgroundColor = Color(0xff6096B4),

              )
        },
        floatingActionButtonPosition = FabPosition.Center,
        backgroundColor = Color(0xffeff3fc)
    ) { padding ->
        Column(){
            Card(shape = RoundedCornerShape(28.dp),
                backgroundColor = Color(0xff9ac1fa),
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 20.dp)
                    .fillMaxWidth()
                    .height(100.dp)
            ){
                Box(Modifier.background(Brush.verticalGradient(colors =  listOf(Color(0xff9ec4f4),Color(0xff8ab2e9))))){
                    Row(modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween){
                        Text("Manage your time well",
                            modifier = Modifier.weight(1f),
                            fontSize = 24.sp,
                            color = Color.White
                        )
                        Row(modifier = Modifier
                            .weight(1f),
                            horizontalArrangement = Arrangement.End){
                            Icon(imageVector = Icons.Default.Edit,"Book",
                                modifier = Modifier.size(75.dp),
                                tint = Color.White)
                        }

                    }
                }

            }
            Text("Categories", modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 4.dp),
            fontWeight = FontWeight.Bold
            )
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.Center){
                Column(verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Card(shape = RoundedCornerShape(16.dp),
                        backgroundColor = if(todoListViewModel.filterState.value == "false") Color(0xff9ac1fa)
                        else Color.White){
                        Icon(imageVector = Icons.Default.Close,"Done",
                            modifier = Modifier
                                .size(50.dp)
                                .padding(4.dp)
                                .clickable {
                                    if(todoListViewModel.filterState.value=="false"){
                                        todoListViewModel.filterState.value = "all"
                                    }else{
                                        todoListViewModel.filterState.value = "false"
                                    }

                                })
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("Todo", fontSize = 14.sp)
                }

                Spacer(Modifier.width(64.dp))
                Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                    Card(shape = RoundedCornerShape(16.dp),
                    backgroundColor = if(todoListViewModel.filterState.value == "true") Color(0xff9ac1fa)
                    else Color.White){
                        Icon(imageVector = Icons.Default.Done,"Done",
                            modifier = Modifier
                                .size(50.dp)
                                .padding(4.dp)
                                .clickable {
                                    if(todoListViewModel.filterState.value=="true"){
                                        todoListViewModel.filterState.value = "all"
                                    }else{
                                        todoListViewModel.filterState.value = "true"
                                    }
                                }
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("Done", fontSize = 14.sp)
                }

            }
            Spacer(Modifier.height(8.dp))
            Text("You have $todoCount tasks for today", modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 4.dp),
                fontWeight = FontWeight.Bold
            )
            LazyColumn(modifier = Modifier.padding(4.dp)) {
                items(todos.filter {
                    if(todoListViewModel.filterState.value == "all"){
                        true
                    }
                    else if(todoListViewModel.filterState.value == "true"){
                        it.isDone
                    }
                    else{
                        !it.isDone
                    }
                }){ todo ->
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
}